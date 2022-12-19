package org.example.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Coalition {
    @Id
    private String  id;

    private String  name;
    private String  score;

    public Coalition() {}

    public Coalition(String id, String name, String score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
