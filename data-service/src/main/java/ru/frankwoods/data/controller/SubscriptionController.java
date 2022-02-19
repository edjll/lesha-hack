package ru.frankwoods.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.frankwoods.data.entity.Figi;
import ru.frankwoods.data.entity.Subscription;
import ru.frankwoods.data.service.SubscriptionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void subscribe(@RequestBody Subscription subscription) {
        subscriptionService.subscribe(subscription);
    }

    @DeleteMapping
    public void unsubscribe(@RequestParam Long userId, @RequestParam(required = false) Figi figi) {
        subscriptionService.unsubscribe(userId, figi);
    }

    @GetMapping("/{userId}/figies")
    public List<Figi> getAllFigiForUser(@PathVariable Long userId) {
        return subscriptionService.getAllFigiForUser(userId);
    }
}