//package org.example.services;
//
//import org.example.CurrentUser;
//import org.example.repo.GradeBookRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class GradeBookDAO {
//    private final GradeBookRepository gradeBookRepository;
//
//    public GradeBookDAO(GradeBookRepository gradeBookRepository) {
//        this.gradeBookRepository = gradeBookRepository;
//    }
//
//    public List<GradeBook> getTable() {
//        if (CurrentUser.id != null && CurrentUser.accountType.equals("student")) {
//            Iterable<org.example.Entity.GradeBook> gradesOptional = gradeBookRepository.findAll();
//            List<GradeBook> currentUserGrades = new ArrayList<>();;
//
//            for (GradeBook grade: gradesOptional) {
//                if (grade.getStudentId().equals(CurrentUser.id)) {
//                    currentUserGrades.add(grade);
//                }
//            }
//            return currentUserGrades;
//        }
//
//        Iterable<org.example.Entity.GradeBook> gradesOptional = gradeBookRepository.findAll();
//        List<GradeBook> currentUserGrades = new ArrayList<>();;
//
//        for (GradeBook grade: gradesOptional) {
//            if (grade.getTeacherId().equals(CurrentUser.id)) {
//                currentUserGrades.add(grade);
//            }
//        }
//        return currentUserGrades;
//    }
//
//    public String addGrade( String studentId, String subject, Integer markId) {
//        if (CurrentUser.id != null && CurrentUser.accountType.equals("staff")) {
//            GradeBook grade = new GradeBook();
//            grade.setDate(new Date());
//            grade.setStudentId(studentId);
//            grade.setSubject(subject);
//            grade.setMarkId(markId);
//            grade.setTeacherId(CurrentUser.id);
//            gradeBookRepository.save(grade);
//            return "Success!";
//        }
//        return "Permission denied!";
//    }
//
//    public String changeGrade(Long gradeId, Integer markId) {
//        if (CurrentUser.id != null && CurrentUser.accountType.equals("staff")) {
//
//            Optional<GradeBook> gradeOptional = gradeBookRepository.findById(gradeId);
//
//            if (gradeOptional.isPresent()) {
//                GradeBook gradeBook = gradeOptional.get();
//                gradeBook.setMarkId(markId);
//                gradeBookRepository.save(gradeBook);
//                return "Success!";
//            }
//            return "Record with ID " + gradeId + " not found.";
//        }
//        return "Permission denied!";
//    }
//
//    public String deleteGrade(Long gradeId) {
//        if (CurrentUser.id != null && CurrentUser.accountType.equals("staff")) {
//
//            Optional<GradeBook> gradeOptional = gradeBookRepository.findById(gradeId);
//
//            if (gradeOptional.isPresent()) {
//                GradeBook gradeBook = gradeOptional.get();
//                gradeBookRepository.delete(gradeBook);
//                return "Success!";
//            }
//            return "Record with ID " + gradeId + " not found.";
//        }
//        return "Permission denied!";
//    }
//}
