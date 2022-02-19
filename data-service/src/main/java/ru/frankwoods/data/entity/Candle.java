package ru.frankwoods.data.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Candle {

    private long interval;

    private Figi figi;

    private CandleDetails details;
}