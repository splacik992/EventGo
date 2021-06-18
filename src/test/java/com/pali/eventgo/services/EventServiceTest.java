package com.pali.eventgo.services;

import com.pali.eventgo.entity.AppUser;
import com.pali.eventgo.entity.Event;
import com.pali.eventgo.entity.Localization;
import com.pali.eventgo.exceptions.ResourceAlreadyExistException;
import com.pali.eventgo.exceptions.ResourceNotExistException;
import com.pali.eventgo.repository.EventRepository;
import com.pali.eventgo.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    private static final Long eventId = 1L;
    private static final String userUsername = "rafaello";


    @Mock
    EventRepository eventRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    EventService eventService;



    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        eventService = new EventService(eventRepository,userRepository);
        eventService.createNewEventByCurrentUser(getEvent(),"a");

    }

    @Test(expected = ResourceNotExistException.class)
    public void createNewEventByCurrentUser() throws ResourceNotExistException, ResourceAlreadyExistException {

          Event requestedEvent = getEvent();
          AppUser appUser = getUser();
          requestedEvent.setUser(appUser);

          Mockito.lenient().when(eventRepository.save(any(Event.class))).thenReturn(requestedEvent);

          Event event = eventService.createNewEventByCurrentUser(requestedEvent, appUser.getUsername());

          assertEquals(1L, event.getId(), "Id should be '1'.");

    }

    @Test
    public void getAllEvents() {
        List<Event> allEvents = eventService.getAllEvents();
        allEvents.add(getEvent());

        when(eventRepository.findAllByOrderByIdDesc()).thenReturn(allEvents);

        List<Event> events = eventService.getAllEvents();

        assertEquals(1, events.size());
    }

    @Test
    public void getEventById() throws ResourceNotExistException {

        Event requestedEvent = getEvent();

        when(eventRepository.findEventById(any())).thenReturn(requestedEvent);

        Event event = eventService.getEventById(eventId);

        assertEquals(eventId,event.getId());

        verify(eventRepository,times(2)).findEventById(anyLong());

    }

    public static Event getEvent(){
        return new Event.EventBuilder()
                .buildId(1L)
                .buildName("Event of the day!")
                .buildLocalization(new Localization(1L,"boisko", "zelazna 33","Warszawa"))
                .build();
    }

    public static AppUser getUser(){
        AppUser appUser = new AppUser();
        appUser.setFirstName("Rafal");
        appUser.setId(1L);
        appUser.setLastName("Pali");
        appUser.setUsername("rafaello");
        appUser.setEmail("email@email.com");
        return appUser;
    }
}