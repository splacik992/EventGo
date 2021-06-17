package com.pali.eventgo.repository;

import com.pali.eventgo.entity.Event;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventRepositoryTest {

    @Autowired
    EventRepository eventRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByName() {
        Event findByName = eventRepository.findByName("Event Name");

        assertEquals("Event Name", findByName.getName());

    }
}