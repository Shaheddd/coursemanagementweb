package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.Entity.Role;
import com.assignment.course_management_system.RestEntities.LoginRest;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface LoginServiceInterface {
    String login(LoginRest loginRest);

    Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles);

    void CreateCurrentSession(int id);
}
