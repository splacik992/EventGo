package com.pali.eventgo.repository;

import com.pali.eventgo.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Event findByName(String eventName);

    List<Event> findAllByOrderByIdDesc();

    @Query(value = "SELECT * FROM event where id =:eventId", nativeQuery = true)
    Event findEventById(@Param("eventId") Long id);

    List<Event> findEventsByName(String nameOfEvent);

    @Query(value = "SELECT * FROM event where id =:nameOfLocalization", nativeQuery = true)
    Event findByLocalization(@Param("nameOfLocalization") String localizationOfEvent);

    List<Event> findEventsByLocalizationName(String name);

}
