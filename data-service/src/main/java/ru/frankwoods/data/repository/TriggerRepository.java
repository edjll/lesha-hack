package ru.frankwoods.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.frankwoods.data.entity.Figi;
import ru.frankwoods.data.entity.Trigger;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TriggerRepository extends JpaRepository<Trigger, Long> {

    @Modifying
    @Transactional
    void deleteAllByUserId(Long userId);

    List<Trigger> getAllByFigiAndTargetPriceGreaterThanEqual(Figi figi, BigDecimal targetPrice);
}