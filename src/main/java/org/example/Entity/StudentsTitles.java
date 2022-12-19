package org.example.Entity;

import javax.persistence.Id;

public class StudentsTitles {
    @Id
    private String  studentId;

    @Id
    private String  titleId;

    public StudentsTitles() {}

    public StudentsTitles(String studentId, String titleId) {
        this.studentId = studentId;
        this.titleId = titleId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTitleId() {
        return titleId;
    }

    public void setTitleId(String titleId) {
        this.titleId = titleId;
    }
}
