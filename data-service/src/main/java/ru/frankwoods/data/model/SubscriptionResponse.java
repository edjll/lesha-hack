package ru.frankwoods.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.frankwoods.data.entity.Candle;
import ru.frankwoods.data.entity.User;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubscriptionResponse {

    private User user;

    private Candle candle;
}