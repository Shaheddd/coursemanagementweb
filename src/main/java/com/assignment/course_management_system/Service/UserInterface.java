package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.Entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserInterface extends UserDetailsService {

    User save(User user);

    User searchUserByUsername(String username);

    List<User> getAll();

    User findByTableID(int userID);

    void deleteByTableID(int id);

    User getCurrentUser();

    User findByID(int user_ID);

    void deleteAdministrator(User user);

    void deleteHeadmaster(User user);

    void deleteTeacher(User user);

    void deleteStudent(User user);
}
