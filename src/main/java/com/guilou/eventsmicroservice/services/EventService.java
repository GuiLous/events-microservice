package com.guilou.eventsmicroservice.services;

import com.guilou.eventsmicroservice.domain.Event;
import com.guilou.eventsmicroservice.domain.Subscription;
import com.guilou.eventsmicroservice.dtos.EmailRequestDTO;
import com.guilou.eventsmicroservice.dtos.EventRequestDTO;
import com.guilou.eventsmicroservice.exceptions.EventFullException;
import com.guilou.eventsmicroservice.exceptions.EventNotFoundException;
import com.guilou.eventsmicroservice.repositories.EventRepository;
import com.guilou.eventsmicroservice.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private EmailServiceClient emailServiceClient;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getUpcomingEvents() {
        return eventRepository.findUpcomingEvents(LocalDateTime.now());
    }

    public Event createEvent(EventRequestDTO eventRequest) {
        Event newEvent = new Event(eventRequest);
        return eventRepository.save(newEvent);
    }

    private Boolean isEventFull(Event event){
        return event.getRegisteredParticipants() >= event.getMaxParticipants();
    }

    public void registerParticipant(UUID eventId, String participantEmail) {
        Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);

        if(isEventFull(event)) {
            throw new EventFullException();
        }

        Subscription subscription = new Subscription(event, participantEmail);
        subscriptionRepository.save(subscription);

        event.setRegisteredParticipants(event.getRegisteredParticipants() + 1);

        EmailRequestDTO emailRequest = new EmailRequestDTO(participantEmail, "Confirmação de Inscrição", "Você foi inscrito no evento com sucesso!");

        emailServiceClient.sendEmail(emailRequest);
    }
}