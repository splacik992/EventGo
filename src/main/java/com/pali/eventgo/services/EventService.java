package com.pali.eventgo.services;

import com.pali.eventgo.entity.Event;
import com.pali.eventgo.exceptions.ResourceAlreadyExistException;
import com.pali.eventgo.exceptions.ResourceNotExistException;
import com.pali.eventgo.repository.EventRepository;
import com.pali.eventgo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventService {

    private final static String NAME_TAKEN_MESSAGE = "Nazwa jest już zajęta!";
    private final static String USER_NOT_FOUND_MESSAGE = "Użytkownik nie isnieje!";
    private final static String EVENT_NOT_FOUND_MESSAGE = "Event nie istnieje!";

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Event createNewEventByCurrentUser(Event event, String username) throws ResourceAlreadyExistException,
            ResourceNotExistException {
        if (eventRepository.findByName(event.getName()) != null) {
            throw new ResourceAlreadyExistException(NAME_TAKEN_MESSAGE);
        }
        if (userRepository.findByUsername(username) == null) {
            throw new ResourceNotExistException(USER_NOT_FOUND_MESSAGE);
        }
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAllByOrderByIdDesc();
    }


    public Event getEventById(Long eventId) throws ResourceNotExistException {

        if(eventRepository.findEventById(eventId) == null){
            throw new ResourceNotExistException(EVENT_NOT_FOUND_MESSAGE);
        }
        else
            return eventRepository.findEventById(eventId);
    }
}
