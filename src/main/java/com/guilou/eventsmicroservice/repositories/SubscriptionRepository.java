package com.guilou.eventsmicroservice.repositories;

import com.guilou.eventsmicroservice.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
}
