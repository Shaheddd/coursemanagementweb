package com.assignment.course_management_system.RestEntities;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonRootName("loginUser")
public class LoginRest {

    private String username;
    private String password;
}
