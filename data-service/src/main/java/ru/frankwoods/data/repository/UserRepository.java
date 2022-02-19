package ru.frankwoods.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.frankwoods.data.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}