package com.assignment.course_management_system.Controller;

import com.assignment.course_management_system.Entity.Exam;
import com.assignment.course_management_system.Entity.Mark;
import com.assignment.course_management_system.Entity.Student;
import com.assignment.course_management_system.Entity.User;
import com.assignment.course_management_system.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    UserService userService;

    @Autowired
    MarkService markService;

    @Autowired
    ExamService examService;

    @Autowired
    BatchService batchService;

    @GetMapping("/loadStudentHomepage")
    public String loadStudentHomepage(Model model) {

        String username;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.searchUserByUsername(username);

        Student student = studentService.getStudentByID(user.getTableID());

        List<Exam> examList = student.getExamList();
        model.addAttribute("exams", examList);

        return "StudentHomepage";
    }

    @GetMapping("/getMyMarks")
    public String getMyMarks(Model model) {
        String username;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.searchUserByUsername(username);

        Student student = studentService.getStudentByID(user.getTableID());

        List<Mark> markList = markService.getMyMarks(student.getStudentID());
        model.addAttribute("marks", markList);

        return "GetMyMarks";
    }

}
