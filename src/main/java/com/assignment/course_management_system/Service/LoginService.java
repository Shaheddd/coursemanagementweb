package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.Entity.Role;
import com.assignment.course_management_system.Entity.User;
import com.assignment.course_management_system.RestEntities.LoginRest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoginService implements LoginServiceInterface{

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String login(LoginRest loginRest) {
        try{
            User check = userService.searchUserByUsername(loginRest.getUsername());

            if (check != null) {

                if(bCryptPasswordEncoder.matches(loginRest.getPassword(),check.getPassword()))
                {
                    User user = check;

                    return ((user.getRoles().stream().findFirst()).get()).getRole()+" "+ user.getUserID()+ " "+user.getUsername();
                }
                else
                {
                    return "Invalid";
                }
            }
            else {
                return "Invalid";
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

    @Override
    public void CreateCurrentSession(int id) {
        try
        {
            User user = userService.findByTableID(id);
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
