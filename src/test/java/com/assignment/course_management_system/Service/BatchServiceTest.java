package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.Entity.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BatchServiceTest {

    @Test
    void saveBatch() {

        ArrayList<Student> studentArrayList = new ArrayList(Arrays.asList(""));
        ArrayList<Exam> examArrayList = new ArrayList(Arrays.asList(""));

        Course course = new Course();
        Teacher teacher = new Teacher();

        Batch batch = new Batch();
        batch.setBatchID(1);
        batch.setBatchCode("HF12345");
        batch.setCourse(course);
        batch.setTeacher(teacher);
        batch.setExamsList(examArrayList);
        batch.setStudentList(studentArrayList);

        assertEquals(1, batch.getBatchID());
        assertEquals("HF12345", batch.getBatchCode());
        assertEquals(course, batch.getCourse());
        assertEquals(teacher, batch.getTeacher());
        assertEquals(examArrayList, batch.getExamsList());
        assertEquals(studentArrayList, batch.getStudentList());
    }

    @Test
    void getAllBatches() {

        ArrayList<Student> studentArrayList = new ArrayList(Arrays.asList(""));
        ArrayList<Exam> examArrayList = new ArrayList(Arrays.asList(""));

        Course course = new Course();
        Teacher teacher = new Teacher();

        List<Batch> testBatches = new ArrayList(Arrays.asList(
                new Batch(1, "HF12345", course, examArrayList, teacher, studentArrayList),
                new Batch(2, "IF78910", course, examArrayList, teacher, studentArrayList)));

        Assertions.assertThat(testBatches).contains(testBatches.get(0), testBatches.get(1));
    }
}