package com.assignment.course_management_system.DataTransferObject;

import com.assignment.course_management_system.Entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherRegistration {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

    private int teacherID;
    private int studentID;

    private Student student;

    public int getStudentID() {
       return this.studentID = studentID;
    }
}
