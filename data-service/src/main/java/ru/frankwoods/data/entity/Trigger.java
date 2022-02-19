package ru.frankwoods.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Trigger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "figi", nullable = false)
    private Figi figi;

    @Column(name = "current_price", nullable = false)
    private BigDecimal currentPrice;

    @Column(name = "target_price", nullable = false)
    private BigDecimal targetPrice;
}