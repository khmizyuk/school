package org.example.controller;

import org.example.CurrentUser;
import org.example.Entity.Project;
import org.example.Entity.Staff;
import org.example.Entity.Student;
import org.example.repo.ProjectRepository;
import org.example.repo.StaffRepository;
import org.example.repo.StudentRepository;
import org.example.services.ProfileDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.crypto.Cipher;
import java.util.Optional;

@Controller
public class ProfileController {
    @Autowired
    private ProfileDAO profileService;
    private final StudentRepository studentRepository;
    private final StaffRepository staffRepository;
    private final ProjectRepository projectRepository;

    public ProfileController(StudentRepository studentRepository, StaffRepository staffRepository, ProjectRepository projectRepository) {
        this.studentRepository = studentRepository;
        this.staffRepository = staffRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        return profileService.profile(model);
    }

    @GetMapping("/add/project")
    public String addProject(Model model) {
        if (CurrentUser.id == null || !CurrentUser.isStaff)
            return "redirect:/";
        return "add-project";
    }

    @PostMapping("/add/project")
    public String addProject(@RequestParam String id,
                                    @RequestParam String description,
                                    @RequestParam String subject,
                                    @RequestParam String check_list,
                                    @RequestParam int experience_points,
                                    @RequestParam int weight,
                                    Model model) {
        return profileService.addProject(id, description, subject, check_list, experience_points, weight, model);
    }

    @GetMapping("/delete/student")
    public String deleteStudent(Model model) {
        if (CurrentUser.id == null || !CurrentUser.isStaff)
            return "redirect:/";
        return "delete-student";
    }

    @PostMapping("/delete/student")
    public String deleteStudent(@RequestParam String id,
                                Model model) {
        return profileService.deleteStudent(id, model);
    }

    @GetMapping("/delete/staff")
    public String deleteStaff(Model model) {
        if (CurrentUser.id == null || !CurrentUser.isStaff)
            return "redirect:/";
        return "delete-staff";
    }

    @PostMapping("/delete/staff")
    public String deleteStaff(@RequestParam String id,
                                Model model) {
        return profileService.deleteStaff(id, model);
    }

    @GetMapping("/delete/project")
    public String deleteProject(Model model) {
        if (CurrentUser.id == null || !CurrentUser.isStaff)
            return "redirect:/";
        return "delete-project";
    }

    @PostMapping("/delete/project")
    public String deleteProject(@RequestParam String id,
                              Model model) {
        return profileService.deleteProject(id, model);
    }

    @GetMapping("/start/project")
    public String startProject(Model model) {
        if (CurrentUser.id == null || CurrentUser.isStaff)
            return "redirect:/";
        return profileService.startProject();
    }

    @GetMapping("/finish/project")
    public String finishProject(Model model) {
        if (CurrentUser.id == null || CurrentUser.isStaff)
            return "redirect:/";
        return profileService.finishProject();
    }

    @GetMapping("/change/info/student")
    public String changeInfoStudent(Model model) {
        if (CurrentUser.id == null)
            return "redirect:/";
        Optional<Student> studentOptional = studentRepository.findByEmail(CurrentUser.id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            model.addAttribute("selectedUserFirstName", student.getFirstName());
            model.addAttribute("selectedUserLastName", student.getLastName());
        }
        return "change-info-student";
    }

    @GetMapping("/change/info/staff")
    public String changeInfoStaff(Model model) {
        if (CurrentUser.id == null)
            return "redirect:/";
        Optional<Staff> staffOptional = staffRepository.findByEmail(CurrentUser.id);
        if (staffOptional.isPresent()) {
            Staff staff = staffOptional.get();
            model.addAttribute("selectedUserFirstName", staff.getFirstName());
            model.addAttribute("selectedUserLastName", staff.getLastName());
            model.addAttribute("selectedUserCoalitionId", staff.getCoalitionId());
            model.addAttribute("selectedUserCampusId", staff.getCampusId());
        }
        return "change-info-staff";
    }

    @PostMapping("/change/info/student")
    public String changeInfoStudent(@RequestParam String firstName,
                             @RequestParam String lastName,
                             Model model) {
        return profileService.changeInfoStudent(firstName, lastName, model);
    }

    @PostMapping("/change/info/staff")
    public String changeInfoStaff(@RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String coalitionId,
                             @RequestParam String campusId,
                             Model model) {
        return profileService.changeInfoStaff(firstName, lastName, coalitionId, campusId, model);
    }

    @GetMapping("/change/project/id")
    public String changeProjectId(Model model) {
        if (CurrentUser.id == null)
            return "redirect:/";
        return "change-project-id";
    }

    @PostMapping("/change/project/id")
    public String changeProjectId(@RequestParam String id,
                                Model model) {
        return profileService.changeProjectId(id, model);
    }

    @GetMapping("/change/project")
    public String changeProject(Model model) {
        if (CurrentUser.id == null)
            return "redirect:/";
        Optional<Project> projectOptional = projectRepository.findById(CurrentUser.selectedProject);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            model.addAttribute("projectDescription", project.getDescription());
            model.addAttribute("projectSubject", project.getSubject());
            model.addAttribute("projectCheckList", project.getCheckList());
            model.addAttribute("projectWeight", project.getWeight());
            model.addAttribute("projectExperiencePoints", project.getExperience_points());
        }
        return "change-project";
    }

    @PostMapping("/change/project")
    public String changeProject(@RequestParam String description,
                                @RequestParam String subject,
                                @RequestParam String check_list,
                                @RequestParam int experience_points,
                                @RequestParam int weight,
                                Model model) {
        return profileService.changeProject(description, subject, check_list, experience_points, weight, model);
    }

    @GetMapping("/change/student/id")
    public String changeStudentId(Model model) {
        if (CurrentUser.id == null)
            return "redirect:/";
        return "change-student-id";
    }

    @PostMapping("/change/student/id")
    public String changeStudentId(@RequestParam String id,
                                  Model model) {
        return profileService.changeStudentId(id, model);
    }

    @GetMapping("/change/student")
    public String changeStudent(Model model) {
        if (CurrentUser.id == null)
            return "redirect:/";
        Optional<Student> studentOptional = studentRepository.findById(CurrentUser.selectedStudent);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            model.addAttribute("studentFirstName", student.getFirstName());
            model.addAttribute("studentLastName", student.getLastName());
            model.addAttribute("studentExperiencePoint", student.getExperiencePoint());
            model.addAttribute("studentAllowedProject", student.getAllowedProject());
            model.addAttribute("studentCampusId", student.getCampusId());
            model.addAttribute("studentCoalitionId", student.getCoalitionId());
        }
        return "change-student";
    }

    @PostMapping("/change/student")
    public String changeStudent(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam String campusId,
                                @RequestParam String coalitionId,
                                @RequestParam int experiencePoint,
                                @RequestParam int allowedProject,
                                Model model) {
        return profileService.changeStudent(firstName, lastName, campusId, coalitionId, experiencePoint, allowedProject, model);
    }

    @GetMapping("/change/staff/id")
    public String changeStaffId(Model model) {
        if (CurrentUser.id == null)
            return "redirect:/";
        return "change-staff-id";
    }

    @PostMapping("/change/staff/id")
    public String changeStaffId(@RequestParam String id,
                                  Model model) {
        return profileService.changeStaffId(id, model);
    }

    @GetMapping("/change/staff")
    public String changeStaff(Model model) {
        if (CurrentUser.id == null)
            return "redirect:/";
        Optional<Staff> staffOptional = staffRepository.findById(CurrentUser.selectedStaffMember);
        if (staffOptional.isPresent()) {
            Staff staff = staffOptional.get();
            model.addAttribute("staffFirstName", staff.getFirstName());
            model.addAttribute("staffLastName", staff.getLastName());
            model.addAttribute("staffCampusId", staff.getCampusId());
            model.addAttribute("staffCoalitionId", staff.getCoalitionId());
        }
        return "change-staff";
    }

    @PostMapping("/change/staff")
    public String changeStaff(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam String campusId,
                                @RequestParam String coalitionId,
                                Model model) {
        return profileService.changeStaff(firstName, lastName, campusId, coalitionId, model);
    }

    @GetMapping("/select/projects")
    public String selectProjects(Model model) {
        if (CurrentUser.id == null || !CurrentUser.isStaff)
            return "redirect:/";
        return profileService.selectProjects(model);
    }

    @GetMapping("/select/students")
    public String selectStudents(Model model) {
        if (CurrentUser.id == null || !CurrentUser.isStaff)
            return "redirect:/";
        return profileService.selectStudents(model);
    }

    @GetMapping("/select/staff")
    public String selectStaff(Model model) {
        if (CurrentUser.id == null || !CurrentUser.isStaff)
            return "redirect:/";
        return profileService.selectStaff(model);
    }
}
