package com.guilou.eventsmicroservice.controllers;

import com.guilou.eventsmicroservice.domain.Event;
import com.guilou.eventsmicroservice.dtos.EventRequestDTO;
import com.guilou.eventsmicroservice.dtos.SubscriptionRequestDTO;
import com.guilou.eventsmicroservice.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/upcoming")
    public List<Event> getUpcomingEvents() {
        return eventService.getUpcomingEvents();
    }

    @PostMapping
    public Event createEvent(@RequestBody EventRequestDTO event) {
        return eventService.createEvent(event);
    }

    @PostMapping("/{eventId}/register")
    public void registerParticipant(@PathVariable UUID eventId, @RequestBody SubscriptionRequestDTO subscriptionRequest) {
        eventService.registerParticipant(eventId, subscriptionRequest.participantEmail());
    }
}
