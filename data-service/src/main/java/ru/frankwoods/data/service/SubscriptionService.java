package ru.frankwoods.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import ru.frankwoods.data.config.constants.TelegramServiceConstants;
import ru.frankwoods.data.entity.Candle;
import ru.frankwoods.data.entity.Figi;
import ru.frankwoods.data.entity.Subscription;
import ru.frankwoods.data.repository.SubscriptionRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository repository;
    private final Map<Long, ScheduledFuture<?>> subscriptions = new HashMap<>();
    private final ThreadPoolTaskScheduler taskScheduler;
    private final RestTemplate restTemplate;

    @Autowired
    @Lazy
    private DataService dataService;

    @Value(TelegramServiceConstants.VALUE_TELEGRAM_SERVICE_SERVER)
    private String telegramServiceServer;

    @PostConstruct
    public void start() {
        List<Subscription> subscriptions = repository.findAll();
        subscriptions.forEach(this::schedule);
    }

    @Transactional
    public void subscribe(Subscription subscription) {
        Subscription savedSubscription = repository.save(subscription);
        schedule(savedSubscription);
    }

    @Transactional
    public void unsubscribe(Long userId, Figi figi) {
        List<Long> subscriptionsIds = new ArrayList<>();
        if (figi == null) {
            subscriptionsIds.addAll(repository.getAllByUserId(userId));
        } else {
            Long subscriptionId = repository.getIdByUserIdAndFigi(userId, figi)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
            subscriptionsIds.add(subscriptionId);
        }

        subscriptionsIds.forEach(subscriptionId -> {
            repository.deleteById(subscriptionId);
            subscriptions.get(subscriptionId).cancel(true);
            subscriptions.remove(subscriptionId);
        });
    }

    public void sendMessage(Subscription subscription) {
        HttpEntity<Candle> httpEntity = new HttpEntity<>(dataService.getCandleByFigi(subscription.getFigi()));
        restTemplate.exchange(telegramServiceServer + subscription.getAction().getUrl(), subscription.getAction().getMethod(), httpEntity, Object.class);
    }

    public List<Figi> getAllFigiForUser(Long userId) {
        return repository.getAllFigiForUser(userId);
    }

    private void schedule(Subscription subscription) {
        ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(
                () -> this.sendMessage(subscription),
                new PeriodicTrigger(subscription.getInterval())
        );
        subscriptions.put(subscription.getId(), scheduledFuture);
    }
}
