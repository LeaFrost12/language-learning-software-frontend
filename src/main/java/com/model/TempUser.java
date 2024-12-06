package com.model;

import java.util.UUID;

public class TempUser {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
    private final String username;
    private final String password;
    private Language language;
    private UUID currentUnitId;
    private UUID currentLessonId;

    public TempUser(String firstName, String lastName, String email, String phoneNumber, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    /**
     * Sets the Lesson user is currently on by UUID
     *
     * @param lessonId UUID of Lesson user is on
     */
    public void setCurrentLessonId(UUID lessonId) {
        this.currentLessonId = lessonId;
    }

    /**
     * Sets the Unit user is currently on by UUID
     *
     * @param lessonId UUID of Unit user is on
     */
    public void setCurrentUnitId(UUID unitId) {
        this.currentUnitId = unitId;
    }
}
