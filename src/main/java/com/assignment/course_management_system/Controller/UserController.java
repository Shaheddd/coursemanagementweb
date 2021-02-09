package com.assignment.course_management_system.Controller;

import com.assignment.course_management_system.DataTransferObject.AdministratorRegistration;
import com.assignment.course_management_system.DataTransferObject.HeadmasterRegistration;
import com.assignment.course_management_system.DataTransferObject.StudentRegistration;
import com.assignment.course_management_system.DataTransferObject.TeacherRegistration;
import com.assignment.course_management_system.Entity.*;
import com.assignment.course_management_system.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/user")
public class UserController {

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

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/loadAdministratorForm")
    public String loadAdministratorForm(Model model) {
        model.addAttribute("administrator", new AdministratorRegistration());
        return "RegisterAdministrator";
    }

    @PostMapping("/registerAdministrator")
    public String registerAdministrator(@ModelAttribute("administrator") AdministratorRegistration administratorRegistration) {

        Administrator registerAdministrator = administratorService.registerAdministrator(administratorRegistration);
        User user = new User();
        user.setTableID(registerAdministrator.getAdministratorID());
        user.setUsername(administratorRegistration.getUsername());

        user.setFirstName(administratorRegistration.getFirstName());
        user.setLastName(administratorRegistration.getLastName());
        user.setPhoneNumber(administratorRegistration.getPhoneNumber());
        user.setAddress(administratorRegistration.getAddress());
        user.setRoles(Arrays.asList(new Role("Administrator")));
        User registeredUser = userService.save(user);
        registerAdministrator.setUserID(registeredUser.getUserID());
        administratorService.save(registerAdministrator);

        return "redirect:/user/loadAdministrator?success";
    }

    @GetMapping("/loadStudentForm")
    public String loadStudentForm(Model model) {
        model.addAttribute("student", new StudentRegistration());
        return "RegisterStudent";
    }

    @PostMapping("/registerStudent")
    public String registerStudent(@ModelAttribute("student") StudentRegistration studentRegistration) {

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

        return "redirect:/user/loadStudentForm?success";
    }

    @GetMapping("/loadTeacherForm")
    public String loadTeacherForm(Model model) {
        model.addAttribute("teacher", new TeacherRegistration());
        return "RegisterTeacher";
    }

    @PostMapping("/registerTeacher")
    public String registerTeacher(@ModelAttribute("teacher") TeacherRegistration teacherRegistration) {
        Teacher registerTeacher = teacherService.registerTeacher(teacherRegistration);

        User user = new User();
        user.setTableID(registerTeacher.getTeacherID());
        user.setUsername(teacherRegistration.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(teacherRegistration.getPassword()));
        user.setFirstName(teacherRegistration.getFirstName());
        user.setLastName(teacherRegistration.getLastName());
        user.setAddress(teacherRegistration.getAddress());
        user.setPhoneNumber(teacherRegistration.getPhoneNumber());
        user.setRoles(Arrays.asList(new Role("Teacher")));
        User registeredUser = userService.save(user);
        registerTeacher.setUserID(registeredUser.getUserID());
        teacherService.saveTeacher(registerTeacher);

        return "redirect:/user/loadTeacherForm?success";
    }

    @GetMapping("/loadHeadmasterForm")
    public String loadHeadmasterForm(Model model) {
        model.addAttribute("headmaster", new HeadmasterRegistration());
        return "RegisterHeadmaster";
    }

    @PostMapping("/registerHeadmaster")
    public String registerHeadmaster(@ModelAttribute("headmaster") HeadmasterRegistration headmasterRegistration) {
        Headmaster registerHeadmaster = headmasterService.registerHeadmaster(headmasterRegistration);

        User user = new User();
        user.setTableID(registerHeadmaster.getHeadmasterID());
        user.setUsername(headmasterRegistration.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(headmasterRegistration.getPassword()));
        user.setFirstName(headmasterRegistration.getFirstName());
        user.setLastName(headmasterRegistration.getLastName());
        user.setAddress(headmasterRegistration.getAddress());
        user.setPhoneNumber(headmasterRegistration.getPhoneNumber());
        user.setRoles(Arrays.asList(new Role("Headmaster")));
        User registeredUser = userService.save(user);
        registerHeadmaster.setUserID(registeredUser.getUserID());
        headmasterService.saveHeadmaster(registerHeadmaster);

        return "redirect:/user/loadHeadmasterForm?success";
    }
}
