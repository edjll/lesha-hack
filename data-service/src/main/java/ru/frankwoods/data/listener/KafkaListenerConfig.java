package ru.frankwoods.data.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import ru.frankwoods.data.config.constants.KafkaConfigConstants;
import ru.frankwoods.data.entity.CandleHolder;
import ru.frankwoods.data.service.DataService;

@RequiredArgsConstructor
@Configuration
public class KafkaListenerConfig {

    private final DataService dataService;

    @KafkaListener(topics = KafkaConfigConstants.TOPIC, groupId = KafkaConfigConstants.GROUP_ID)
    public void listenCandle(CandleHolder candleHolder) {
        dataService.updateCandle(candleHolder.getCandle());
    }
}