package ru.frankwoods.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import ru.frankwoods.data.entity.Candle;
import ru.frankwoods.data.entity.Figi;

import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class DataService {

    private final ConcurrentHashMap<Figi, Candle> candles = new ConcurrentHashMap<>();
    private final TriggerService triggerService;
    private final ThreadPoolTaskScheduler taskScheduler;

    public void updateCandle(Candle candle) {
        candles.put(candle.getFigi(), candle);
        taskScheduler.execute(() -> triggerService.checkTriggers(candle));
    }

    public Candle getCandleByFigi(Figi figi) {
        return candles.get(figi);
    }
}