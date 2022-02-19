package ru.frankwoods.data.model;

import lombok.*;
import ru.frankwoods.data.entity.Candle;
import ru.frankwoods.data.entity.Figi;
import ru.frankwoods.data.entity.Trigger;
import ru.frankwoods.data.entity.User;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TriggerResponse {

    private User user;

    private Figi figi;

    private BigDecimal price;

    public TriggerResponse(Trigger trigger, Candle candle) {
        this.user = trigger.getUser();
        this.figi = trigger.getFigi();
        this.price = candle.getDetails().getClose();
    }
}