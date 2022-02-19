package ru.frankwoods.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.frankwoods.data.entity.Figi;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterTriggerRequest {

    private int percents;

    private Long userId;

    private Figi figi;
}