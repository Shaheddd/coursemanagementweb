package com.assignment.course_management_system.RestControllers;

import com.assignment.course_management_system.Entity.*;
import com.assignment.course_management_system.Service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/administrator")
public class AdministratorRestController {

    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    HeadmasterService headmasterService;

    @Autowired
    AdministratorService administratorService;

    @GetMapping("/listAllUsers")
    public List<User> listAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/listAllAdmins")
    public List<Administrator> listAllAdministrator() {
        return administratorService.getAll();
    }

    @GetMapping("/listAllHeadmasters")
    public List<Headmaster> listAllHeadmasters() {
        return headmasterService.getAllHeadmasters();
    }

    @GetMapping("/listAllTeachers")
    public List<Teacher> listAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/listAllStudents")
    public List<Student> listAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/viewAdministrator/{user_ID}")
    public User editAdministrator(@PathVariable(value = "user_ID") int user_ID)
    {
        User user = userService.findByID(user_ID);
        return user;
    }

    @PostMapping("/editAdministrator")
    public String updateAdministrator(@RequestBody User user)
    {
        userService.save(user);
        Administrator administrator = administratorService.getAdministratorByID(user.getTableID());
        administrator.setAdministratorFirstName(user.getFirstName());
        administrator.setAdministratorLastName(user.getLastName());
        administrator.setAdministratorAddress(user.getAddress());
        administrator.setAdministratorPhoneNumber(user.getPhoneNumber());
        administratorService.save(administrator);
        String message = "Updated";
        return message;
    }

    @GetMapping("/deleteAdministrator/{user_ID}")
    public String deleteAdministrator(@PathVariable(value = "user_ID") int user_ID)
    {
        User user = userService.findByTableID(user_ID);

        userService.deleteAdministrator(user);
        return "Deleted";
    }

}
