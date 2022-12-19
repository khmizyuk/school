package org.example.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
    @Id
    private String id;

    private String  firstName;
    private String  lastName;
    private String  email;
    private String  password;
    private int     experiencePoint;
    private String  placeId;
    private String  coalitionId;
    private String  campusId;
    private int  allowedProject;

    public Student() {}

    public Student(String id, String firstName, String lastName, String email, String password, int experiencePoint, String placeId, String coalitionId, String campusId, int allowedProject) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.experiencePoint = experiencePoint;
        this.placeId = placeId;
        this.coalitionId = coalitionId;
        this.campusId = campusId;
        this.allowedProject = allowedProject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getExperiencePoint() {
        return experiencePoint;
    }

    public void setExperiencePoint(int experiencePoint) {
        this.experiencePoint = experiencePoint;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getCoalitionId() {
        return coalitionId;
    }

    public void setCoalitionId(String coalitionId) {
        this.coalitionId = coalitionId;
    }

    public String getCampusId() {
        return campusId;
    }

    public void setCampusId(String campusId) {
        this.campusId = campusId;
    }

    public int getAllowedProject() {
        return allowedProject;
    }

    public void setAllowedProject(int allowedProject) {
        this.allowedProject = allowedProject;
    }
}
