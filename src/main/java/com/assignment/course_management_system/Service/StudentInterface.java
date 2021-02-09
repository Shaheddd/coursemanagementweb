package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.DataTransferObject.StudentRegistration;
import com.assignment.course_management_system.Entity.Student;

import java.util.List;

public interface StudentInterface {

    Student registerStudent(StudentRegistration studentRegistration);

    void saveStudent(Student registerStudent);

    List<Student> getAllStudents();

    Student getStudentByID(int id);

    Student getStudentID(int id);

    Student getStudentsByID(int id);

    Student getMyMarksForStudent(int studentID);

    Student getMyExamsForStudent(int studentID);

    void deleteStudent(int id);
}
