package ru.frankwoods.data.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.frankwoods.data.entity.User;
import ru.frankwoods.data.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void register(User user) {
        repository.save(user);
    }

    public Optional<User> getUserById(Long userId) {
        return repository.findById(userId);
    }
}