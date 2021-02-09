package com.assignment.course_management_system.RestControllers;

import com.assignment.course_management_system.DataTransferObject.StudentRegistration;
import com.assignment.course_management_system.Entity.*;
import com.assignment.course_management_system.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {

    @Autowired
    StudentService studentService;

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

    @Autowired
    MarkService markService;

    @Autowired
    ExamService examService;

    @Autowired
    BatchService batchService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

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

    @PostMapping("/saveStudent")
    public String registerStudent(@RequestBody StudentRegistration studentRegistration) {

        Student registerStudent = studentService.registerStudent(studentRegistration);

        User user = new User();
        user.setTableID(registerStudent.getStudentID());
        user.setUsername(studentRegistration.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(studentRegistration.getPassword()));
        user.setFirstName(studentRegistration.getFirstName());
        user.setLastName(studentRegistration.getLastName());
        user.setAddress(studentRegistration.getAddress());
        user.setPhoneNumber(studentRegistration.getPhoneNumber());
        user.setRoles(Arrays.asList(new Role("Student")));
        User registeredUser = userService.save(user);
        registerStudent.setUserID(registeredUser.getUserID());
        studentService.saveStudent(registerStudent);

        return "Account Has Been Created Successfully!";
    }

    @GetMapping("/viewStudent/{user_ID}")
    public User editStudent(@PathVariable(value = "user_ID") int user_ID) {
        User user = userService.findByID(user_ID);
        return user;
    }

    @PostMapping("/editStudent")
    public String updateStudent(@RequestBody User user) {
        userService.save(user);
        Student student = studentService.getStudentsByID(user.getTableID());
        student.setStudentFirstName(user.getFirstName());
        student.setStudentLastName(user.getLastName());
        student.setStudentAddress(user.getAddress());
        student.setStudentPhoneNumber(user.getPhoneNumber());
        studentService.saveStudent(student);
        String message = "Updated";
        return message;
    }

    @GetMapping("/deleteStudent/{user_ID}")
    public String deleteStudent(@PathVariable(value = "user_ID") int user_ID)
    {
        User user = userService.findByTableID(user_ID);

        userService.deleteStudent(user);
        return "Deleted";
    }

    @GetMapping("/viewMarksForStudent")
    public List<Mark> viewMarksForStudent(@RequestHeader("account-id") int accountId) {
        loginService.CreateCurrentSession(accountId);
        User currentUser = userService.getCurrentUser();
        Student student = studentService.getMyMarksForStudent(currentUser.getUserID());
        return markService.getMyMarks(student.getStudentID());
    }

}
