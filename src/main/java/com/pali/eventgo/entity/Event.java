package com.pali.eventgo.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Event {

    private final static String LENGTH_MESSAGE = "Musi mieć przynajmniej 5 znaków!";
    private final static String BLANK_FIELD_MESSAGE = "Pole nie może być puste!";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = BLANK_FIELD_MESSAGE)
    @Length(min = 5, max = 100, message = LENGTH_MESSAGE)
    private String name;

    @NotEmpty(message = BLANK_FIELD_MESSAGE)
    @Length(min = 5, max = 600, message = LENGTH_MESSAGE)
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @ManyToOne()
    private Category categories;

    private int quantityOfMembers;

    private LocalDateTime eventStart;

    private LocalDateTime durationOfTheEvent;

    @ManyToOne(cascade = CascadeType.ALL)
    private Localization localization;

    @ManyToOne
    private AppUser user;

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
    }

    public Event() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (quantityOfMembers != event.quantityOfMembers) return false;
        if (id != null ? !id.equals(event.id) : event.id != null) return false;
        if (name != null ? !name.equals(event.name) : event.name != null) return false;
        if (description != null ? !description.equals(event.description) : event.description != null) return false;
        if (eventDate != null ? !eventDate.equals(event.eventDate) : event.eventDate != null) return false;
        if (createdOn != null ? !createdOn.equals(event.createdOn) : event.createdOn != null) return false;
        if (updatedOn != null ? !updatedOn.equals(event.updatedOn) : event.updatedOn != null) return false;
        if (categories != null ? !categories.equals(event.categories) : event.categories != null) return false;
        if (eventStart != null ? !eventStart.equals(event.eventStart) : event.eventStart != null) return false;
        if (durationOfTheEvent != null ? !durationOfTheEvent.equals(event.durationOfTheEvent) : event.durationOfTheEvent != null)
            return false;
        return localization != null ? localization.equals(event.localization) : event.localization == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (eventDate != null ? eventDate.hashCode() : 0);
        result = 31 * result + (createdOn != null ? createdOn.hashCode() : 0);
        result = 31 * result + (updatedOn != null ? updatedOn.hashCode() : 0);
        result = 31 * result + (categories != null ? categories.hashCode() : 0);
        result = 31 * result + quantityOfMembers;
        result = 31 * result + (eventStart != null ? eventStart.hashCode() : 0);
        result = 31 * result + (durationOfTheEvent != null ? durationOfTheEvent.hashCode() : 0);
        result = 31 * result + (localization != null ? localization.hashCode() : 0);
        return result;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public int getQuantityOfMembers() {
        return quantityOfMembers;
    }

    public void setQuantityOfMembers(int quantityOfMembers) {
        this.quantityOfMembers = quantityOfMembers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Category getCategories() {
        return categories;
    }

    public void setCategories(Category categories) {
        this.categories = categories;
    }


    public LocalDateTime getEventStart() {
        return eventStart;
    }

    public void setEventStart(LocalDateTime eventStart) {
        this.eventStart = eventStart;
    }

    public LocalDateTime getDurationOfTheEvent() {
        return durationOfTheEvent;
    }

    public void setDurationOfTheEvent(LocalDateTime durationOfTheEvent) {
        this.durationOfTheEvent = durationOfTheEvent;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }
}