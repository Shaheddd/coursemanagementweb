package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.Entity.Batch;
import com.assignment.course_management_system.Entity.Course;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseServiceTest {

    @Test
    void createCourse() {

        ArrayList<Batch> batchArrayList = new ArrayList(Arrays.asList("HF12345"));

        Course course = new Course();
        course.setCourseID(1);
        course.setCourseName("React");
        course.setCourseType("Software Engineering");
        course.setCourseDescription("Learn about React Native");
        course.setBatchList(batchArrayList);

        assertEquals(1, course.getCourseID());
        assertEquals("React", course.getCourseName());
        assertEquals("Software Engineering", course.getCourseType());
        assertEquals("Learn about React Native", course.getCourseDescription());
        assertEquals(Arrays.asList("HF12345"), course.getBatchList());
    }

    @Test
    void getAllCourses() throws Exception {

        ArrayList<Batch> batchArrayList = new ArrayList(Arrays.asList("HF12345"));
        List<Course> testCourses = new ArrayList(Arrays.asList(
                new Course(1, "React", "Software Engineering", "Learn about React Native", batchArrayList),
                new Course(2, "Mathematics", "IT Mathematics", "Learn about Differentiation", batchArrayList)));

        Assertions.assertThat(testCourses).contains(testCourses.get(0), testCourses.get(1));
    }
}