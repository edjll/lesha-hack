package ru.frankwoods.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.frankwoods.data.entity.Figi;
import ru.frankwoods.data.entity.Subscription;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query("select s.figi from Subscription s where s.userId = :userId")
    List<Figi> getAllFigiForUser(@Param("userId") Long userId);

    @Query("select s.id from Subscription s where s.userId = :userId and s.figi = :figi")
    Optional<Long> getIdByUserIdAndFigi(@Param("userId") Long userId, @Param("figi") Figi figi);

    List<Long> getAllByUserId(Long userId);
}
