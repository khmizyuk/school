package org.example.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(CompletedProjects.class)
public class CompletedProjects implements Serializable {
    @Id
    private String  studentId;

    @Id
    private String  projectId;

    public CompletedProjects() {}

    public CompletedProjects(String studentId, String projectId) {
        this.studentId = studentId;
        this.projectId = projectId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
