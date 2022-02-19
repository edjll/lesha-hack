package ru.frankwoods.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.frankwoods.data.model.RegisterTriggerRequest;
import ru.frankwoods.data.service.TriggerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/triggers")
public class TriggerController {

    private final TriggerService triggerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterTriggerRequest registerTriggerRequest) {
        triggerService.register(registerTriggerRequest);
    }

    @DeleteMapping
    public void delete(@RequestParam Long userId) {
        triggerService.delete(userId);
    }
}