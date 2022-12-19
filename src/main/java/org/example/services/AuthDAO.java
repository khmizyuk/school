package org.example.services;

import org.example.CurrentUser;
import org.example.Entity.Campus;
import org.example.Entity.Coalition;
import org.example.Entity.Staff;
import org.example.Entity.Student;
import org.example.repo.CampusRepository;
import org.example.repo.CoalitionRepository;
import org.example.repo.StaffRepository;
import org.example.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthDAO {
    private final StudentRepository studentRepository;
    private final StaffRepository staffRepository;
    private final CampusRepository campusRepository;
    private final CoalitionRepository coalitionRepository;

    @Autowired
    public AuthDAO(StudentRepository studentRepository, StaffRepository staffRepository, CampusRepository campusRepository, CoalitionRepository coalitionRepository) {
        this.studentRepository = studentRepository;
        this.staffRepository = staffRepository;
        this.campusRepository = campusRepository;
        this.coalitionRepository = coalitionRepository;
    }

    public String auth(String login, String password, Model model) {
        Optional<Student> studentOptional = studentRepository.findByEmail(login);
        Optional<Staff> staffOptional = staffRepository.findByEmail(login);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            if (student.getPassword().equals(password)) {
                CurrentUser.id = login;
                return "redirect:/profile";
            }
        }
        else if (staffOptional.isPresent()) {
            Staff staff = staffOptional.get();
            if (staff.getPassword().equals(password)) {
                CurrentUser.id = login;
                CurrentUser.isStaff = true;
                return "redirect:/profile";
            }
        }
        return "auth";
    }

    public String registrationStaff(String firstName, String lastName, String campusId, Model model) {

        Staff staff = new Staff(
                generateId(firstName, lastName),
                firstName,
                lastName,
                generateEmail(firstName, lastName),
                generatePassword(),
                generateCoalition(),
                campusId
        );

        staffRepository.save(staff);

        return "redirect:/profile";
    }

    public String registrationStudent(String firstName, String lastName, String campusId, Model model) {
        Student student = new Student(
                generateId(firstName, lastName),
                firstName,
                lastName,
                generateEmail(firstName, lastName),
                generatePassword(),
                0,
                "unavailable",
                generateCoalition(),
                campusId,
                1
        );

        studentRepository.save(student);

        return "redirect:/profile";
    }

    public String logOut(Model model) {
        if (CurrentUser.id != null)
            CurrentUser.id = null;
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

    private String generateCoalition() {
        return "42_Paris_The_Order";
    }

    private String generatePassword() {
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                char letter = (char) (random.nextInt(25) + 97);
                password.append(letter);
            }
            if (i != 2)
                password.append("-");
        }
        return password.toString();
    }

    private String generateEmail(String firstName, String lastName) {
        return Character.toLowerCase(firstName.charAt(0)) + lastName.toLowerCase() + "@42.fr";
    }

    private String generateId(String firstName, String lastName) {
        return Character.toLowerCase(firstName.charAt(0)) + lastName.toLowerCase();
    }
}
