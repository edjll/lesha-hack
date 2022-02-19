package ru.frankwoods.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.frankwoods.data.config.constants.TelegramServiceConstants;
import ru.frankwoods.data.entity.Candle;
import ru.frankwoods.data.entity.Trigger;
import ru.frankwoods.data.model.RegisterTriggerRequest;
import ru.frankwoods.data.model.TriggerResponse;
import ru.frankwoods.data.repository.TriggerRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TriggerService {

    @Autowired
    @Lazy
    private DataService dataService;
    private final TriggerRepository repository;
    private final RestTemplate restTemplate;

    @Value(TelegramServiceConstants.VALUE_TELEGRAM_SERVICE_SERVER)
    private String telegramServiceServer;

    @Value(TelegramServiceConstants.VALUE_TELEGRAM_SERVICE_TRIGGERS_ENDPOINT)
    private String telegramServiceTriggersEndpoint;

    public void register(RegisterTriggerRequest registerTriggerRequest) {
        Trigger trigger = new Trigger();
        trigger.setFigi(registerTriggerRequest.getFigi());
        trigger.setUserId(registerTriggerRequest.getUserId());
        trigger.setCurrentPrice(dataService.getCandleByFigi(registerTriggerRequest.getFigi()).getDetails().getClose());
        trigger.setTargetPrice(trigger.getCurrentPrice().multiply(BigDecimal.valueOf((100 - registerTriggerRequest.getPercents()) / 100D)));
        repository.save(trigger);
    }

    @Transactional
    public void checkTriggers(Candle candle) {
        List<Trigger> triggers = repository.getAllByFigiAndTargetPriceGreaterThanEqual(candle.getFigi(), candle.getDetails().getClose());
        if (!triggers.isEmpty()) {
            HttpEntity<List<TriggerResponse>> httpEntity = new HttpEntity<>(
                    triggers.stream()
                            .map(trigger -> new TriggerResponse(trigger, candle))
                            .collect(Collectors.toList())
            );
            repository.deleteAll(triggers);
            restTemplate.exchange(telegramServiceServer + telegramServiceTriggersEndpoint, HttpMethod.POST, httpEntity, Object.class);
        }
    }

    public void delete(Long userId) {
        repository.deleteAllByUserId(userId);
    }
}
