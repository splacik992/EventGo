package com.pali.eventgo.services;

import com.pali.eventgo.entity.Event;
import com.pali.eventgo.exceptions.ResourceAlreadyExistException;
import com.pali.eventgo.exceptions.ResourceNotExistException;
import com.pali.eventgo.repository.EventRepository;
import com.pali.eventgo.repository.LocalizationRepository;
import com.pali.eventgo.repository.UserRepository;
import com.pali.eventgo.utils.ImageUploader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventService {

    private final static String NAME_TAKEN_MESSAGE = "Nazwa jest już zajęta!";
    private final static String USER_NOT_FOUND_MESSAGE = "Użytkownik nie isnieje!";
    private final static String EVENT_NOT_FOUND_MESSAGE = "Wydarzenie nie istnieje!";
    private final static String LOCALIZATION_NOT_FOUND_MESSAGE = "Lokalizacja o podanej nazwie nie istnieje!";

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final LocalizationRepository localizationRepository;


    public EventService(LocalizationRepository localizationRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.localizationRepository = localizationRepository;

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

    public List<Event> findAllEventsByIdDesc() {
        return eventRepository.findAllByOrderByIdDesc();
    }


    public Event findEventById(Long eventId) throws ResourceNotExistException {

        if (eventRepository.findEventById(eventId) == null) {
            throw new ResourceNotExistException(EVENT_NOT_FOUND_MESSAGE);
        } else
            return eventRepository.findEventById(eventId);
    }

//    public List<Event> findEventsByPlace(String placeOfEvent) throws ResourceNotExistException {
//
//        if(localizationRepository.findByName(placeOfEvent) == null){
//            throw new ResourceNotExistException(EVENT_NOT_FOUND_MESSAGE);
//        }
//        return eventRepository.findEventsByLocalizationName(placeOfEvent);
//
//    }


    public List<Event> findEventsByName(String eventSearchByName) throws ResourceNotExistException {

        Event eventByName = eventRepository.findByName(eventSearchByName);
        if(eventByName == null){
            throw new ResourceNotExistException(EVENT_NOT_FOUND_MESSAGE);
        }
        return eventRepository.findEventsByName(eventSearchByName);
    }

    public List<Event> findEventsByCategory(String eventSearchByCategory) throws ResourceNotExistException {

        List<Event> eventsByCategoriesName = eventRepository.findEventsByCategoriesNameOrderByEventDateDesc(eventSearchByCategory);
        if(eventsByCategoriesName == null){
            throw new ResourceNotExistException(EVENT_NOT_FOUND_MESSAGE);
        }
        return eventsByCategoriesName;
    }

//    public void addImageFilePathToCreatedEvent(Event requestedEvent, String imagePath) throws ResourceNotExistException {
//        Event event = eventRepository.findByName(requestedEvent.getName());
//        if(event == null){
//            throw new ResourceNotExistException(EVENT_NOT_FOUND_MESSAGE);
//        }else {
//            event.setImageFilePath(imageUploader.uploadFile(imagePath));
//        }
//    }
}
