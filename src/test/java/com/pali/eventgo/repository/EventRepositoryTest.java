package com.pali.eventgo.repository;

import com.pali.eventgo.entity.Event;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EventRepositoryTest {

    @Autowired
    EventRepository eventRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByName() {
        Event event = new Event.EventBuilder()
                .buildId(1L)
                .buildName("Event Name")
                .buildDescription("bla bla")
                .build();

        eventRepository.save(event);

        Event findByName = eventRepository.findByName("Event Name");
        assertEquals("Event Name", findByName.getName());


    }
}