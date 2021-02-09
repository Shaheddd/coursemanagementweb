package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.DataTransferObject.CourseRegistration;
import com.assignment.course_management_system.Entity.Course;

import java.util.List;

public interface CourseInterface {

    Course createCourse(CourseRegistration courseRegistration);

    void saveCourse(Course saveCourse);

    List<Course> getAllCourses();

    Course getCourseByID(int id);

    void deleteCourse(int id);

    Course searchCourseByName(String courseName);

    Course searchCourseByID(int courseID);

    Course save(Course updateCourse);

    void deleteEntireCourse(Course course);
}
