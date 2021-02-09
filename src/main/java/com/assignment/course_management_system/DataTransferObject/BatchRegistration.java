package com.assignment.course_management_system.DataTransferObject;

import com.assignment.course_management_system.Entity.Student;
import com.assignment.course_management_system.Entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchRegistration {

    private String batchCode;
    private int teacherID;
    private int courseID;
    private int studentID;
    private int batchID;
    private int examID;

    private String teacherFirstName;
    private String teacherLastName;

    private Teacher teacher;
    private Student student;

    public BatchRegistration(int teacherID) {
        this.teacherID = teacherID;
    }

}
