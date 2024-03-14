package com.guilou.eventsmicroservice.repositories;

import com.guilou.eventsmicroservice.domain.Event;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    @Query(value = "SELECT * FROM tb_events e WHERE TO_DATE(e.date, 'dd/MM/yyyy') > :currentDate", nativeQuery = true)
    List<Event> findUpcomingEvents(@Param("currentDate") LocalDateTime currentDate);
}
