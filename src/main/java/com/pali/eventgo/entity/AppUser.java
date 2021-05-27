package com.pali.eventgo.entity;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 60)
    @NotEmpty(message = "Pole nie może być puste!")
    private String username;

    @NotEmpty(message = "Pole nie może być puste!")
    private String password;

    @Column(unique = true)
    private String email;

    @Column(length = 50)
    private String firstName;
    @Column(length = 50)
    private String lastName;

    @Column(name = "phone")
    private String telNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @ManyToMany
    private List<AppUser> friends;

    private int accountEnabled;

    public AppUser() {
    }

    public AppUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<AppUser> getFriends() {
        return friends;
    }

    public void setFriends(List<AppUser> friends) {
        this.friends = friends;
    }

    public int getAccountEnabled() {
        return accountEnabled;
    }

    public void setAccountEnabled(int accountEnabled) {
        this.accountEnabled = accountEnabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }


}
