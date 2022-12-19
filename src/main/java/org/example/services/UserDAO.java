//package org.example.services;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import org.example.CurrentUser;
//import org.example.Entity.Student;
//import org.example.repo.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserDAO {
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserDAO(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public String showUserInfo() {
//        if (CurrentUser.accountType != null) {
//            Optional<Student> userOptional = userRepository.findById(CurrentUser.id);
//
//            if (userOptional.isPresent()) {
//                Student user = userOptional.get();
//                return "Welcome back, " + user.getFirstName() + " " + user.getLastName() + "!";
//            }
//        }
//        return "No users here... Sign in for continue.";
//    }
//
//    public String findUserById(String email) {
//        Optional<Student> userOptional = userRepository.findByEmail(email);
//
//        if (CurrentUser.accountType != null && CurrentUser.accountType.equals("staff")) {
//            if (userOptional.isPresent()) {
//                Student user = userOptional.get();
//                Gson gson = new GsonBuilder()
//                        .setPrettyPrinting()
//                        .create();
//                return gson.toJson(user);
//            }
//            return "User with ID " + email + " not found.";
//        }
//        else {
//            return "Permission denied!";
//        }
//    }
//
//    public Iterable<Student> findAllUsers() {
//        Iterable<Student> users = userRepository.findAll();
//
//        if (CurrentUser.accountType != null && CurrentUser.accountType.equals("staff")) {
//            return users;
//        }
//        else {
//            return null;
//        }
//    }
//
//    public List<Student> findAllStudents() {
//        Iterable<Student> users = userRepository.findAll();
//        List<Student> students = new ArrayList<>();
//
//        if (CurrentUser.accountType != null && CurrentUser.accountType.equals("staff")) {
//            for (Student user: users) {
//                if (user.getAccountType().equals("student")) {
//                    students.add(user);
//                }
//            }
//        }
//        else {
//            return null;
//        }
//        return students;
//    }
//
//}
