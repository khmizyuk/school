//package org.example.controller;
//
//import org.example.services.RegistrationDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class RegistrationController {
//    @Autowired
//    private RegistrationDAO registrationService;
//
//    @PostMapping("/user/add")
//    public @ResponseBody
//    String registration(@RequestParam String firstName,
//                        @RequestParam String lastName,
//                        @RequestParam String accountType) {
//        return registrationService.registration(firstName, lastName, accountType);
//    }
//}
//
//// localhost:8080/registration?firstName=Sergey&middleName=Ivanovich&lastName=Khmizyuk&accountType=student
//
