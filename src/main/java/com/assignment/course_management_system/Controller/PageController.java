package com.assignment.course_management_system.Controller;

import com.assignment.course_management_system.Entity.User;
import com.assignment.course_management_system.Service.CourseService;
import com.assignment.course_management_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    @GetMapping("/login")
    public String loadLogin() {
        return "login";
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        String username;

        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (object instanceof UserDetails) {
            username = ((UserDetails) object).getUsername();
        } else {
            username = object.toString();
        }

        User user = userService.searchUserByUsername(username);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("Administrator"))) {
            model.addAttribute("listUsers", userService.getAll());
            return "redirect:/administrator/loadAdministratorHomepage";
        }

        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("Teacher"))) {
            return "redirect:/teacher/loadTeacherHomepage";
        }

        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("Student"))) {
            model.addAttribute("listStudents", userService.getAll());
            return "redirect:/student/loadStudentHomepage";
        }

        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("Headmaster"))) {
            model.addAttribute("listAllCourses", courseService.getAllCourses());
            return "redirect:/headmaster/loadHeadmasterHomepage";
        }

        return "login";
    }
}
