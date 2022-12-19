package org.example.controller;

import org.example.CurrentUser;
import org.example.services.AuthDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {
    @Autowired
    private AuthDAO authService;

    @GetMapping("/")
    public String main(Model model) {
        if (CurrentUser.id != null)
            return "redirect:/profile";
        return "redirect:/auth";
    }

    @GetMapping("/auth")
    public String auth(Model model) {
        if (CurrentUser.id != null)
            return "redirect:/profile";
        return "auth";
    }

    @PostMapping("/auth")
    public String auth(@RequestParam String login, @RequestParam String password, Model model) {
        return authService.auth(login, password, model);
    }

    @GetMapping("/registration/staff")
    public String registrationStaff(Model model) {
        if (CurrentUser.id == null || !CurrentUser.isStaff)
            return "redirect:/";
        return "registration-staff";
    }

    @PostMapping("/registration/staff")
    public String registrationStaff(@RequestParam String firstName,
                        @RequestParam String lastName,
                        @RequestParam String campusId,
                        Model model) {
        return authService.registrationStaff(firstName, lastName, campusId, model);
    }

    @GetMapping("/registration/student")
    public String studentRegistration(Model model) {
        if (CurrentUser.id == null || !CurrentUser.isStaff)
            return "redirect:/";
        return "registration-student";
    }

    @PostMapping("/registration/student")
    public String studentRegistration(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String campusId,
                               Model model) {
        return authService.registrationStudent(firstName, lastName, campusId, model);
    }

    @GetMapping("/logout")
    public String logOut(Model model) {
        return authService.logOut(model);
    }
}