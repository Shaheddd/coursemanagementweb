package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.Entity.Role;
import com.assignment.course_management_system.Entity.User;
import com.assignment.course_management_system.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserInterface {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdministratorRepository administratorRepository;

    @Autowired
    HeadmasterRepository headmasterRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User searchUserByUsername(String username) {
        return userRepository.searchUsername(username);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByTableID(int userID) {
        return userRepository.getOne(userID);
    }

    @Override
    public void deleteByTableID(int id) {
        User user = userRepository.getByTableID(id);
        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.searchUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username Not Found!");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

    @Override
    public User getCurrentUser() {
        String username=null;

        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return userRepository.searchUsername(username);
    }

    @Override
    public User findByID(int user_ID) {

        return userRepository.findByUserID(user_ID);
    }

    @Override
    public void deleteAdministrator(User user) {
        userRepository.deleteById(user.getUserID());
        administratorRepository.deleteById(user.getTableID());
    }

    @Override
    public void deleteHeadmaster(User user) {
        userRepository.deleteById(user.getUserID());
        headmasterRepository.deleteById(user.getTableID());
    }

    @Override
    public void deleteTeacher(User user) {
        userRepository.deleteById(user.getUserID());
        teacherRepository.deleteById(user.getTableID());
    }

    @Override
    public void deleteStudent(User user) {
        userRepository.deleteById(user.getUserID());
        studentRepository.deleteById(user.getTableID());
    }

}
