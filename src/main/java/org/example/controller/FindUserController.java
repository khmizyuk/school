//package org.example.controller;
//
//import org.example.Entity.Student;
//import org.example.services.UserDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.List;
//
//@Controller
//public class FindUserController {
//    @Autowired
//    private UserDAO userService;
//
//    @GetMapping("/user/{id}")
//    public @ResponseBody
//    String findUserById(@PathVariable String id) {
//        return userService.findUserById(id);
//    }
//
//    @GetMapping("/users")
//    public @ResponseBody
//    Iterable<Student> findAllUsers() {
//        return userService.findAllUsers();
//    }
//
//    @GetMapping("/users/students")
//    public @ResponseBody
//    List<Student> findAllStudents() {
//        return userService.findAllStudents();
//    }
//}
//
////Gson gson = new GsonBuilder()
////        .setPrettyPrinting()
////        .create();
////String response = "hello, " + name;
////    return gson.toJson(response);