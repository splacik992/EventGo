package com.pali.eventgo.controllers;

import com.pali.eventgo.entity.AppUser;
import com.pali.eventgo.entity.Category;
import com.pali.eventgo.entity.Event;
import com.pali.eventgo.repository.CategoryRepository;
import com.pali.eventgo.repository.EventRepository;
import com.pali.eventgo.repository.UserRepository;
import com.pali.eventgo.services.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class IndexControllerTest {

    private MockMvc mockMvc;

    @MockBean
    Model model;

    IndexController indexController;

    @MockBean
    EventRepository eventRepository;
    @MockBean
    EventService eventServiceMock;
    @MockBean
    CategoryRepository categoryRepository;
    @MockBean
    UserRepository userRepository;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        indexController = new IndexController(categoryRepository,eventServiceMock);
    }

    @Test
    void getIndexPage() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        //given
        List<Event> events = new ArrayList<>();
        Event event = new Event.EventBuilder()
                .buildId(1L)
                .build();
        events.add(event);
        Event event1 = new Event.EventBuilder()
                .buildId(2L)
                .build();
        events.add(event1);
        Event event2 = new Event.EventBuilder()
                .buildId(3L)
                .build();
        events.add(event2);


        List<Category> categories = new ArrayList<>();
        Category category = new Category();
        category.setId(1L);
        categories.add(category);
        Category category2 = new Category();
        category.setId(2L);
        categories.add(category2);


        when(eventServiceMock.getAllEvents()).thenReturn(events);

        ArgumentCaptor<List<Event>> argumentCaptorEvents = ArgumentCaptor.forClass(List.class);
//        ArgumentCaptor<List<Category>> argumentCaptorCategories = ArgumentCaptor.forClass(ArrayList.class);

        //when
        String viewName = indexController.getHomePage(model, new Event());


        //then
        assertEquals("home/home",viewName);
        verify(eventServiceMock,times(1)).getAllEvents();
        verify(model,times(1)).addAttribute(eq("event"),any(Event.class));
        verify(model,times(1)).addAttribute(eq("events"),argumentCaptorEvents.capture());
//        verify(model,times(1)).addAttribute(eq("categories"),argumentCaptorCategories.capture());
        List<Event> listEventsInController = argumentCaptorEvents.getValue();
//        List<Category> listCategoriesInController = argumentCaptorCategories.getValue();
        assertEquals(3, listEventsInController.size());
//        assertEquals(2,listCategoriesInController.size());

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home/home"));
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