package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.DataTransferObject.TeacherRegistration;
import com.assignment.course_management_system.Entity.Teacher;

import java.util.List;

public interface TeacherInterface {

    Teacher registerTeacher(TeacherRegistration teacherRegistration);

    void saveTeacher(Teacher registerTeacher);

    List<Teacher> getAllTeachers();

    Teacher getTeacherByID(int id);

    Teacher getByTeacherID(int id);

    void deleteTeacher(int id);


    boolean assignTeacherToStudent(TeacherRegistration teacherRegistration);

    List<Teacher> getTeacherFromCourse(int courseID);

    Teacher getTeachersByID(int id);
}
