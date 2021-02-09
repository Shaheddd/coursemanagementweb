package com.assignment.course_management_system.RestControllers;

import com.assignment.course_management_system.DataTransferObject.TeacherRegistration;
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
@RequestMapping("/api/teacher")
public class TeacherRestController {

    @Autowired
    LoginService loginService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;

    @Autowired
    BatchService batchService;

    @Autowired
    ExamService examService;

    @Autowired
    MarkService markService;

    @Autowired
    StudentService studentService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/loadTeacherHomepage")
    public String loadHomePage(Model model) {
        String username;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.searchUserByUsername(username);

        Teacher teacher = teacherService.getByTeacherID(user.getUserID());

        List<Batch> batchList = batchService.getMyBatchesForTeacher(teacher.getTeacherID());

        model.addAttribute("batches", batchList);

        return "TeacherHomepage";
    }

    @PostMapping("/saveTeacher")
    public String registerTeacher(@RequestBody TeacherRegistration teacherRegistration) {
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

        return "Account Has Been Created Successfully!";
    }

    @GetMapping("/viewTeacher/{user_ID}")
    public User editTeacher(@PathVariable(value = "user_ID") int user_ID)
    {
        User user = userService.findByID(user_ID);
        return user;
    }

    @PostMapping("/editTeacher")
    public String updateTeacher(@RequestBody User user)
    {
        userService.save(user);
        Teacher teacher = teacherService.getTeachersByID(user.getTableID());
        teacher.setTeacherFirstName(user.getFirstName());
        teacher.setTeacherLastName(user.getLastName());
        teacher.setTeacherAddress(user.getAddress());
        teacher.setTeacherPhoneNumber(user.getPhoneNumber());
        teacherService.saveTeacher(teacher);
        String message = "Updated";
        return message;
    }

    @GetMapping("/deleteTeacher/{user_ID}")
    public String deleteTeacher(@PathVariable(value = "user_ID") int user_ID)
    {
        User user = userService.findByTableID(user_ID);

        userService.deleteTeacher(user);
        return "Deleted";
    }

    @GetMapping("/viewBatchesForTeacher")
    public List<Batch> viewBatchesForTeacher(@RequestHeader("account-id") int accountId)
    {
        loginService.CreateCurrentSession(accountId);
        User currentUser = userService.getCurrentUser();
        Teacher teacher = teacherService.getByTeacherID(currentUser.getUserID());
        return batchService.getMyBatchesForTeacher(teacher.getTeacherID());

    }

    @GetMapping("/viewExamsForTeacher")
    public List<Exam> viewExamsForTeacher(@RequestHeader("account-id") int accountId) {
        loginService.CreateCurrentSession(accountId);
        User currentUser = userService.getCurrentUser();
        Teacher teacher = teacherService.getByTeacherID(currentUser.getUserID());
        return examService.getMyExamsForTeacher(teacher.getTeacherID());
    }


}
