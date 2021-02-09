package com.assignment.course_management_system.Repository;

import com.assignment.course_management_system.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("SELECT course from Course course where course.courseName = ?1")
    Course searchCourseName(String courseName);

    @Query("SELECT course from Course course where course.courseID = ?1")
    Course searchByID(int courseID);

}
