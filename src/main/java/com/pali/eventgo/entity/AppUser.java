package com.pali.eventgo.entity;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@Entity
public class AppUser {

    private final static String WHITE_SPACES_MESSAGE = "Usuń spację!";
    private final static String BLANK_FIELD_MESSAGE = "Pole nie może być puste!";
    private final static String NUMBER_FORMAT_MESSAGE = "Wpisz same cyfry!";
    private final static String EMAIL_FORMAT_MESSAGE = "Wpisz prawidłowy email";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 60)
    @NotEmpty(message = BLANK_FIELD_MESSAGE)
    private String username;

    @NotEmpty(message = BLANK_FIELD_MESSAGE)
    private String password;

    @Column(unique = true)
    @NotEmpty(message = BLANK_FIELD_MESSAGE)
//    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"
//            ,message = EMAIL_FORMAT_MESSAGE)
    private String email;

    @Column(length = 50)
    @NotEmpty(message = BLANK_FIELD_MESSAGE)
    @Pattern(regexp = ".*\\S+.*", message = WHITE_SPACES_MESSAGE)
    private String firstName;

    @Column(length = 50)
    @NotEmpty(message = BLANK_FIELD_MESSAGE)
    @Pattern(regexp = ".*\\S+.*", message = WHITE_SPACES_MESSAGE)
    private String lastName;

    @Column(name = "phone")
    @Pattern(regexp="^(0|[1-9][0-9]*)$" , message = NUMBER_FORMAT_MESSAGE)
    @Pattern(regexp = ".*\\S+.*", message = WHITE_SPACES_MESSAGE)
    @NotEmpty(message = BLANK_FIELD_MESSAGE)
    private String telNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @ManyToMany
    private List<AppUser> friends;

    private int accountEnabled;

    private String hashCodeToEnableAccount;

    @OneToMany
    private List<Event> events;




    public AppUser() {
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getHashCodeToEnableAccount() {
        return hashCodeToEnableAccount;
    }

    public void setHashCodeToEnableAccount(String hashCodeToEnableAccount) {
        this.hashCodeToEnableAccount = hashCodeToEnableAccount;
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
        this.username = username.trim();
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
