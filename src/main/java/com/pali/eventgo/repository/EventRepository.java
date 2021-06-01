package com.pali.eventgo.repository;

import com.pali.eventgo.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Event findByName(String eventName);
}
