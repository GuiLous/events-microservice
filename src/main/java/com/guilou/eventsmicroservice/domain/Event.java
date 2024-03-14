package com.guilou.eventsmicroservice.domain;

import com.guilou.eventsmicroservice.dtos.EventRequestDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tb_events")
@EqualsAndHashCode(of = "id")
public class Event {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private int maxParticipants;
    private int registeredParticipants;
    private String date;
    private String title;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Event(EventRequestDTO eventRequest) {
        this.date = eventRequest.date();
        this.maxParticipants = eventRequest.maxParticipants();
        this.registeredParticipants = eventRequest.registeredParticipants();
        this.title = eventRequest.title();
        this.description = eventRequest.description();
    }
}
