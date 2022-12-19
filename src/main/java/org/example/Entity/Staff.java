package org.example.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Staff {
    @Id
    private String id;

    private String  firstName;
    private String  lastName;
    private String  email;
    private String  password;
    private String  coalitionId;
    private String  campusId;

    public Staff() {}

    public Staff(String id, String firstName, String lastName, String email, String password, String coalitionId, String campusId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.coalitionId = coalitionId;
        this.campusId = campusId;
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
}
