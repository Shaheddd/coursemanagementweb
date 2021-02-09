package com.assignment.course_management_system.DataTransferObject;

import com.assignment.course_management_system.Entity.Exam;
import com.assignment.course_management_system.Entity.Student;
import com.assignment.course_management_system.Entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarkRegistration {

    private int marks;
    private String grade;

    private int examID;
    private int teacherID;
    private int studentID;
    private int markID;

    private Exam exam;
    private Teacher teacher;
    private Student student;

    public MarkRegistration(int studentID) {
        this.studentID = studentID;
    }
}
