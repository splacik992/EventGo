package com.pali.eventgo.controllers;

import com.pali.eventgo.entity.AppUser;
import com.pali.eventgo.repository.EventRepository;
import com.pali.eventgo.repository.UserRepository;
import com.pali.eventgo.services.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class IndexControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private static EventRepository eventRepository;
    @MockBean
    private EventService eventServiceMock;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private UserRepository userRepository;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //Build MockMVC
        eventServiceMock = new EventService(eventRepository, userRepository);

    }

    @Test
    void shouldReturnNewEventAfrerPostFormWithNewEvent() throws Exception {

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

    @Test
    void getModelViewOfNewEvent() {
    }


    @Test
    void categories() {
    }
}