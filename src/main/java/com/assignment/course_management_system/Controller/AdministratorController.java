package com.assignment.course_management_system.Controller;

import com.assignment.course_management_system.Entity.Headmaster;
import com.assignment.course_management_system.Entity.Student;
import com.assignment.course_management_system.Entity.Teacher;
import com.assignment.course_management_system.Entity.User;
import com.assignment.course_management_system.Service.HeadmasterService;
import com.assignment.course_management_system.Service.StudentService;
import com.assignment.course_management_system.Service.TeacherService;
import com.assignment.course_management_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    HeadmasterService headmasterService;

    @GetMapping("loadAdministratorHomepage")
    public String loadHomePage(Model model) {
        model.addAttribute("users", userService.getAll());
        return "AdministratorHomepage";
    }

    @GetMapping("/manageStudents")
    public String listAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "ManageStudents";
    }

    @GetMapping("/editStudent/{id}")
    public String editCurrentStudent(@PathVariable(value = "id") int id, Model model) {
        Student student = studentService.getStudentByID(id);
        model.addAttribute("student", student);
        return "EditCurrentStudent";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable(value = "id") int id) {
        userService.deleteByTableID(id);
        studentService.deleteStudent(id);
        return "redirect:/administrator/loadAdministratorHomepage?deletesuccess";
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute("student") Student student) {
        User user = userService.findByTableID(student.getUserID());
        user.setFirstName(student.getStudentFirstName());
        user.setLastName(student.getStudentLastName());
        user.setAddress(student.getStudentAddress());
        user.setPhoneNumber(student.getStudentPhoneNumber());
        userService.save(user);
        studentService.saveStudent(student);
        return "redirect:/administrator/manageStudents";
    }

    @GetMapping("/manageTeachers")
    public String editTeacher(Model model) {
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "ManageTeachers";
    }

    @GetMapping("/editTeacher/{id}")
    public String editCurrentTeacher(@PathVariable(value = "id") int id, Model model) {
        Teacher teacher = teacherService.getTeacherByID(id);
        model.addAttribute("teacher", teacher);
        return "EditCurrentTeacher";
    }

    @GetMapping("/deleteTeacher/{id}")
    public String deleteTeacher(@PathVariable(value = "id") int id) {
        userService.deleteByTableID(id);
        teacherService.deleteTeacher(id);
        return "redirect:/administrator/loadAdministratorHomepage?deletesuccess";
    }

    @PostMapping("/updateTeacher")
    public String updateTeacher(@ModelAttribute("teacher") Teacher teacher) {
        User user = userService.findByTableID(teacher.getUserID());
        user.setFirstName(teacher.getTeacherFirstName());
        user.setLastName(teacher.getTeacherLastName());
        user.setAddress(teacher.getTeacherAddress());
        user.setPhoneNumber(teacher.getTeacherPhoneNumber());
        userService.save(user);
        teacherService.saveTeacher(teacher);
        return "redirect:/administrator/manageTeachers";
    }

    @GetMapping("/manageHeadmasters")
    public String listAllHeadMasters(Model model) {
        model.addAttribute("headmasters", headmasterService.getAllHeadmasters());
        return "ManageHeadmasters";
    }

    @GetMapping("/editHeadmaster/{id}")
    public String editCurrentHeadmaster(@PathVariable(value = "id") int id, Model model) {
        Headmaster headmaster = headmasterService.getHeadmasterByID(id);
        model.addAttribute("headmaster", headmaster);
        return "EditCurrentHeadmaster";
    }

    @GetMapping("/deleteHeadmaster/{id}")
    public String deleteHeadmaster(@PathVariable(value = "id") int id) {
        userService.deleteByTableID(id);
        headmasterService.deleteHeadmaster(id);
        return "redirect:/administrator/loadAdministratorHomepage?deletesuccess";
    }

    @PostMapping("/updateHeadmaster")
    public String updateHeadmaster(@ModelAttribute("headmaster") Headmaster headmaster) {
        User user = userService.findByTableID(headmaster.getUserID());
        user.setFirstName(headmaster.getHeadmasterFirstName());
        user.setLastName(headmaster.getHeadmasterLastName());
        user.setAddress(headmaster.getHeadmasterAddress());
        user.setPhoneNumber(headmaster.getHeadmasterPhoneNumber());
        userService.save(user);
        headmasterService.saveHeadmaster(headmaster);
        return "redirect:/administrator/manageHeadmasters";
    }


}
