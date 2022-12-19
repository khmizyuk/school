package org.example.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(MakingProjects.class)
public class MakingProjects implements Serializable {

    @Id
    private String  studentId;

    @Id
    private String  projectId;

    public MakingProjects() {}

    public MakingProjects(String studentId, String projectId) {
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
