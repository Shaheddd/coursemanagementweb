package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.DataTransferObject.CourseRegistration;
import com.assignment.course_management_system.Entity.Course;
import com.assignment.course_management_system.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements CourseInterface {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public Course createCourse(CourseRegistration courseRegistration) {
        Course course = new Course();
        course.setCourseName(courseRegistration.getCourseName());
        course.setCourseType(courseRegistration.getCourseType());
        course.setCourseDescription(courseRegistration.getCourseDescription());
        return courseRepository.save(course);
    }

    @Override
    public void saveCourse(Course saveCourse) {
        courseRepository.save(saveCourse);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseByID(int id) {
        return courseRepository.getOne(id);
    }

    @Override
    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course searchCourseByName(String courseName) {
        return courseRepository.searchCourseName(courseName);
    }

    @Override
    public Course searchCourseByID(int courseID) {
        return courseRepository.searchByID(courseID);
    }

    @Override
    public Course save(Course updateCourse) {
        return courseRepository.save(updateCourse);
    }

    @Override
    public void deleteEntireCourse(Course course) {
        courseRepository.deleteById(course.getCourseID());
    }
}
