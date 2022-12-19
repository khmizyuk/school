package org.example.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Project {
    @Id
    private String  id;

    private String  description;
    private String  subject;
    private String  checkList;
    private int     experience_points;
    private int     weight;

    public Project() {}

    public Project(String id, String description, String subject, String checkList, int experience_points, int weight) {
        this.id = id;
        this.description = description;
        this.subject = subject;
        this.checkList = checkList;
        this.experience_points = experience_points;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCheckList() {
        return checkList;
    }

    public void setCheckList(String checkList) {
        this.checkList = checkList;
    }

    public int getExperience_points() {
        return experience_points;
    }

    public void setExperience_points(int experience_points) {
        this.experience_points = experience_points;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
