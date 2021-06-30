package com.pali.eventgo.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(max = 300)
    private String content;

    @ManyToOne
    private Event event;

    @ManyToOne
    private AppUser user;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public AppUser getUser() {

        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (getId() != null ? !getId().equals(comment.getId()) : comment.getId() != null) return false;
        if (getContent() != null ? !getContent().equals(comment.getContent()) : comment.getContent() != null)
            return false;
        if (getEvent() != null ? !getEvent().equals(comment.getEvent()) : comment.getEvent() != null) return false;
        return getUser() != null ? getUser().equals(comment.getUser()) : comment.getUser() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        result = 31 * result + (getEvent() != null ? getEvent().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        return result;
    }
}