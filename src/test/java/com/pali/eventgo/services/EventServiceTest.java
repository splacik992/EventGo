package com.pali.eventgo.services;

import com.pali.eventgo.entity.AppUser;
import com.pali.eventgo.entity.Event;
import com.pali.eventgo.entity.Localization;
import com.pali.eventgo.repository.EventRepository;
import com.pali.eventgo.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EventServiceTest {


    @MockBean
    private static EventRepository eventRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private EventService eventServiceMock;

    @BeforeEach
    public void setup() {
        eventServiceMock = new EventService(eventRepository, userRepository);

    }

    @Test
    public void shouldCreateNewEventByCurrentUser() throws Exception {

        Event event = new Event.EventBuilder()
                .buildId(1L)
                .buildName("Event Name")
                .buildLocalization(new Localization(1L, "Parking", "Zelazna", "Warszawa"))
                .buildDescription("event description")
                .build();
        event.setUser(getUser());

        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event event1 = eventServiceMock.createNewEventByCurrentUser(event,getUser().getUsername());
//
        assertEquals(1L, event1.getId());
        assertEquals("rafaello", event1.getUser().getUsername(), "actual 'rafaello' username");

    }

    private static AppUser getUser() {
        AppUser appUser = new AppUser();
        appUser.setId(1L);
        appUser.setUsername("rafaello");
        appUser.setFirstName("rafal");
        appUser.setLastName("paliwoda");
        appUser.setTelNumber(String.valueOf(12345));
        appUser.setPassword("pali");
        return appUser;
    }
}