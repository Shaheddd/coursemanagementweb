package com.assignment.course_management_system.RestControllers;

import com.assignment.course_management_system.DataTransferObject.AdministratorRegistration;
import com.assignment.course_management_system.Entity.Administrator;
import com.assignment.course_management_system.Entity.Role;
import com.assignment.course_management_system.Entity.User;
import com.assignment.course_management_system.RestEntities.LoginRest;
import com.assignment.course_management_system.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

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

    @Autowired
    LoginService loginService;

    @PostMapping("/saveAdministrator")
    public String RegisterAdministrator(@RequestBody AdministratorRegistration administratorRegistration) {

        Administrator registerAdministrator = administratorService.registerAdministrator(administratorRegistration);

        User user = new User();
        user.setTableID(registerAdministrator.getAdministratorID());
        user.setUsername(administratorRegistration.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(administratorRegistration.getPassword()));
        user.setFirstName(administratorRegistration.getFirstName());
        user.setLastName(administratorRegistration.getLastName());
        user.setPhoneNumber(administratorRegistration.getPhoneNumber());
        user.setAddress(administratorRegistration.getAddress());
        user.setRoles(Arrays.asList(new Role("Administrator")));
        User registeredUser = userService.save(user);
        registerAdministrator.setUserID(registeredUser.getUserID());
        administratorService.save(registerAdministrator);

        return "Account Has Been Created Successfully!";
    }


    @PostMapping("/login")
    public String userLogin(@RequestBody LoginRest user) {
        try {
            String check = loginService.login(user);

            return check;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
