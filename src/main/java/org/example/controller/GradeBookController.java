//package org.example.controller;
//
//import org.example.services.GradeBookDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//public class GradeBookController {
//    @Autowired
//    private GradeBookDAO gradeBookService;
//
//    @GetMapping("/user/grades")
//    public @ResponseBody
//    List<org.example.Entity.GradeBook> getTable() {
//        return gradeBookService.getTable();
//    }
//
//    @PostMapping("/user/grades/add")
//    public @ResponseBody
//    String addGrade(@RequestParam String studentId,
//                        @RequestParam String subject,
//                        @RequestParam Integer markId) {
//        return gradeBookService.addGrade(studentId, subject, markId);
//    }
//
//    @PutMapping("/user/grades/change")
//    public @ResponseBody
//    String changeGrade(@RequestParam Long gradeId,
//                    @RequestParam Integer markId) {
//        return gradeBookService.changeGrade(gradeId, markId);
//    }
//
//    @DeleteMapping("/user/grades/delete")
//    public @ResponseBody
//    String deleteGrade(@RequestParam Long gradeId) {
//        return gradeBookService.deleteGrade(gradeId);
//    }
//}
//
//// localhost:8080/user/grades/
//
//// localhost:8080/user/grades/add?studentId=ress.d.s@42.fr&subjectId=0&markId=5
//
//// localhost:8080/user/grades/change?gradeId=5&markId=4
//
//// localhost:8080/user/grades/delete?gradeId=5