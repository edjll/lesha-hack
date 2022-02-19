package ru.frankwoods.data.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CandleDetails {

    private BigDecimal low;

    private BigDecimal high;

    private BigDecimal open;

    private BigDecimal close;

    private Instant openTime;
}