package com.guilou.eventsmicroservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_subscriptions")
@EqualsAndHashCode(of = "id")
public class Subscription {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @ManyToOne
    private Event event;

    private String participantEmail;

    public Subscription(Event event, String participantEmail) {
        this.event = event;
        this.participantEmail = participantEmail;
    }
}
