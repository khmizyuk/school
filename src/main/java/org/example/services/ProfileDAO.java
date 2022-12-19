package org.example.services;

import org.example.CurrentUser;
import org.example.Entity.*;
import org.example.repo.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.util.*;
import java.util.logging.Logger;

@Service
public class ProfileDAO {
    private final StudentRepository studentRepository;
    private final StaffRepository staffRepository;
    private final CampusRepository campusRepository;
    private final CoalitionRepository coalitionRepository;
    private final ProjectRepository projectRepository;
    private final MakingProjectsRepository makingProjectsRepository;
    private final CompletedProjectsRepository completedProjectsRepository;

    public ProfileDAO(StudentRepository studentRepository, StaffRepository staffRepository, CampusRepository campusRepository, CoalitionRepository coalitionRepository, ProjectRepository projectRepository, MakingProjectsRepository makingProjectsRepository, CompletedProjectsRepository completedProjectsRepository) {
        this.studentRepository = studentRepository;
        this.staffRepository = staffRepository;
        this.campusRepository = campusRepository;
        this.coalitionRepository = coalitionRepository;
        this.projectRepository = projectRepository;
        this.makingProjectsRepository = makingProjectsRepository;
        this.completedProjectsRepository = completedProjectsRepository;
    }

    public String profile(Model model) {
        Optional<Student> studentOptional = studentRepository.findByEmail(CurrentUser.id);
        Optional<Staff> staffOptional = staffRepository.findByEmail(CurrentUser.id);

        if (isStaff(CurrentUser.id)) {
            Staff staff = staffOptional.get();
            Optional<Campus> campusOptional = campusRepository.findById(staff.getCampusId());
            if (campusOptional.isPresent()) {
                Campus campus = campusOptional.get();
                model.addAttribute("userCampus", campus.getCity());
            }
            else {
                model.addAttribute("userCampus", "-");
            }
            Optional<Coalition> coalitionOptional = coalitionRepository.findById(staff.getCoalitionId());
            if (coalitionOptional.isPresent()) {
                Coalition coalition = coalitionOptional.get();
                model.addAttribute("userCoalition", coalition.getName());
            }
            else {
                model.addAttribute("userCoalition", "-");
            }
            model.addAttribute("userName", staff.getFirstName() + " " + staff.getLastName());
            model.addAttribute("userEmail", staff.getEmail());
            model.addAttribute("userLogin", staff.getId());
            return "staffprofile";
        }
        else if (isStudent(CurrentUser.id)) {
            Student student = studentOptional.get();
            Optional<Campus> campusOptional = campusRepository.findById(student.getCampusId());
            if (campusOptional.isPresent()) {
                Campus campus = campusOptional.get();
                model.addAttribute("userCampus", campus.getCity());
            }
            else {
                model.addAttribute("userCampus", "-");
            }
            Optional<Coalition> coalitionOptional = coalitionRepository.findById(student.getCoalitionId());
            if (coalitionOptional.isPresent()) {
                Coalition coalition = coalitionOptional.get();
                model.addAttribute("userCoalition", coalition.getName());
                model.addAttribute("coalitionScore", coalition.getScore());
            }
            else {
                model.addAttribute("userCoalition", "-");
                model.addAttribute("coalitionScore", "-");
            }
            model.addAttribute("userName", student.getFirstName() + " " + student.getLastName());
            model.addAttribute("userEmail", student.getEmail());
            model.addAttribute("userLogin", student.getId());
            model.addAttribute("experiencePoint", student.getExperiencePoint());
            model.addAttribute("placeId", student.getPlaceId());

            Iterable<org.example.Entity.CompletedProjects> completedProjects = completedProjectsRepository.findAll();
            List<Project> finishedProjects = new ArrayList<>();
            for (CompletedProjects completedProject: completedProjects) {
                if (completedProject.getStudentId().equals(student.getId())) {
                    Optional<Project> projectOptional = projectRepository.findById(completedProject.getProjectId());
                    if (projectOptional.isPresent()) {
                        finishedProjects.add(projectOptional.get());
                    }
                }
            }
            model.addAttribute("finishedProjects", finishedProjects);

            Iterable<org.example.Entity.MakingProjects> makingProjects = makingProjectsRepository.findAll();
            List<Project> projectInProgress = new ArrayList<>();
            for (MakingProjects makingProject: makingProjects) {
                if (makingProject.getStudentId().equals(student.getId())) { // and not in finishedProjects
                    boolean consist = false;
                    for (Project project: finishedProjects) {
                        if (project.getId().equals(makingProject.getProjectId()))
                            consist = true;
                    }
                    Optional<Project> projectOptional = projectRepository.findById(makingProject.getProjectId());
                    if (projectOptional.isPresent() && !consist) {
                        projectInProgress.add(projectOptional.get());
                    }
                }
            }
            model.addAttribute("projectsInProgress", projectInProgress);

            Iterable<org.example.Entity.Project> allProjects = projectRepository.findAll();
            List<Project> projectsNotStarted = new ArrayList<>();
            for (Project project: allProjects) {
                boolean consist1 = false;
                for (Project project1: projectInProgress) {
                    if (project1.getId().equals(project.getId())) {
                        consist1 = true;
                        break;
                    }
                }
                for (Project project1: finishedProjects) {
                    if (project1.getId().equals(project.getId())) {
                        consist1 = true;
                        break;
                    }
                }
                if (project.getWeight() <= student.getAllowedProject() && !consist1) { // and not in projectInProgress
                    projectsNotStarted.add(project);
                }
            }
            model.addAttribute("projects", projectsNotStarted);


            return "studentprofile";
        }
        return "redirect:/auth";
    }

    public String addProject(String id, String description, String subject, String check_list, int experience_points, int weight, Model model) {

        Project project = new Project(id, description, subject, check_list, experience_points, weight);

        projectRepository.save(project);

        return "redirect:/profile";
    }

    public String changeInfoStudent(String firstName, String lastName, Model model) {
        Optional<Student> studentOptional = studentRepository.findByEmail(CurrentUser.id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            studentRepository.save(student);
        }
        return "redirect:/";
    }

    public String changeInfoStaff(String firstName, String lastName, String coalitionId, String campusId, Model model) {
        Optional<Student> studentOptional = studentRepository.findByEmail(CurrentUser.id);
        Optional<Staff> staffOptional = staffRepository.findByEmail(CurrentUser.id);
        if (staffOptional.isPresent()) {
            Staff staff = staffOptional.get();
            staff.setFirstName(firstName);
            staff.setLastName(lastName);
            staff.setCoalitionId(coalitionId);
            staff.setCampusId(campusId);
            staffRepository.save(staff);
        }
        return "redirect:/";
    }

    public String changeProjectId(String id, Model model) {

        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            CurrentUser.selectedProject = id;
            return "redirect:/change/project";
        }
        return "redirect:/";
    }

    public String changeProject(String description, String subject, String check_list, int experience_points, int weight, Model model) {
        Optional<Project> projectOptional = projectRepository.findById(CurrentUser.selectedProject);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            project.setDescription(description);
            project.setSubject(subject);
            project.setCheckList(check_list);
            project.setExperience_points(experience_points);
            project.setWeight(weight);
            projectRepository.save(project);
        }
        return "redirect:/";
    }

    public String changeStudentId(String id, Model model) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            CurrentUser.selectedStudent = id;
            return "redirect:/change/student";
        }
        return "redirect:/";
    }

    public String changeStudent(String firstName, String lastName, String campusId, String coalitionId, int experiencePoint, int allowedProject, Model model) {
        Optional<Student> studentOptional = studentRepository.findById(CurrentUser.selectedStudent);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setCampusId(campusId);
            student.setCoalitionId(coalitionId);
            student.setExperiencePoint(experiencePoint);
            student.setAllowedProject(allowedProject);
            studentRepository.save(student);
        }
        return "redirect:/";
    }

    public String changeStaffId(String id, Model model) {
        Optional<Staff> staffOptional = staffRepository.findById(id);
        if (staffOptional.isPresent()) {
            CurrentUser.selectedStaffMember = id;
            return "redirect:/change/staff";
        }
        return "redirect:/";
    }

    public String changeStaff(String firstName, String lastName, String campusId, String coalitionId, Model model) {
        Optional<Staff> staffOptional = staffRepository.findById(CurrentUser.selectedStaffMember);
        if (staffOptional.isPresent()) {
            Staff staff = staffOptional.get();
            staff.setFirstName(firstName);
            staff.setLastName(lastName);
            staff.setCampusId(campusId);
            staff.setCoalitionId(coalitionId);
            staffRepository.save(staff);
        }
        return "redirect:/";
    }

    public String deleteStudent(String id, Model model) {

        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            studentRepository.delete(student);
        }

        return "redirect:/profile";
    }

    public String deleteStaff(String id, Model model) {

        Optional<Staff> staffOptional = staffRepository.findById(id);

        if (staffOptional.isPresent()) {
            Staff staff = staffOptional.get();
            staffRepository.delete(staff);
        }

        return "redirect:/profile";
    }

    public String deleteProject(String id, Model model) {

        Optional<Project> projectOptional = projectRepository.findById(id);

        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            projectRepository.delete(project);
        }

        return "redirect:/profile";
    }

    public String selectProjects(Model model) {
        Iterable<Project> projects = projectRepository.findAll();
        model.addAttribute("allProjects", projects);
        return "select-projects";
    }

    public String selectStudents(Model model) {
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("allStudents", students);
        return "select-students";
    }

    public String selectStaff(Model model) {
        Iterable<Staff> staff = staffRepository.findAll();
        model.addAttribute("allStaff", staff);
        return "select-staff";
    }

    public String startProject() {
        Optional<Student> studentOptional = studentRepository.findByEmail(CurrentUser.id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            Iterable<org.example.Entity.Project> projects = projectRepository.findAll();
            for (Project project: projects) {
                if (project.getWeight() <= student.getAllowedProject()) {
                    MakingProjects makingProject = new MakingProjects(student.getId(), project.getId());
                    makingProjectsRepository.save(makingProject);
                }
            }
        }
        return "redirect:/";
    }

    public String finishProject() {
        Optional<Student> studentOptional = studentRepository.findByEmail(CurrentUser.id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            Iterable<org.example.Entity.Project> projects = projectRepository.findAll();
            for (Project project: projects) {
                if (project.getWeight() <= student.getAllowedProject()) {
                    CompletedProjects completedProject = new CompletedProjects(student.getId(), project.getId());
                    completedProjectsRepository.save(completedProject);
                }
            }
        }
        return "redirect:/";
    }

    public Boolean isStudent(String login) {
        Optional<Student> studentOptional = studentRepository.findByEmail(login);

        return studentOptional.isPresent();
    }

    public Boolean isStaff(String login) {
        Optional<Staff> staffOptional = staffRepository.findByEmail(login);

        return staffOptional.isPresent();
    }
}
