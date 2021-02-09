package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.Entity.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentServiceTest {

    @Test
    void getAllStudents() {

        ArrayList<Batch> batchArrayList = new ArrayList(Arrays.asList(""));
        ArrayList<Mark> markArrayList = new ArrayList(Arrays.asList(""));
        ArrayList<Teacher> teacherArrayList = new ArrayList(Arrays.asList(""));
        ArrayList<Exam> examArrayList = new ArrayList(Arrays.asList(""));

        List<Student> testStudent = Arrays.asList(
                new Student(1, "April", "Ludgate", "USA",
                        "0775432109", 1, markArrayList, examArrayList, batchArrayList,
                        teacherArrayList),
                new Student(2, "Andy", "Dwyer", "Metropolis",
                        "0774567891", 2, markArrayList, examArrayList, batchArrayList,
                        teacherArrayList));

        Assertions.assertThat(testStudent).contains(testStudent.get(0), testStudent.get(1));
    }

    @Test
    void registerStudent() {

        ArrayList<Batch> batchArrayList = new ArrayList(Arrays.asList(""));
        ArrayList<Mark> markArrayList = new ArrayList(Arrays.asList(""));
        ArrayList<Teacher> teacherArrayList = new ArrayList(Arrays.asList(""));
        ArrayList<Exam> examArrayList = new ArrayList(Arrays.asList(""));

        Student student = new Student();
        student.setStudentID(1);
        student.setStudentFirstName("April");
        student.setStudentLastName("Ludgate");
        student.setStudentAddress("USA");
        student.setStudentPhoneNumber("0775432109");
        student.setUserID(1);
        student.setBatchList(batchArrayList);
        student.setMarkList(markArrayList);
        student.setTeacherList(teacherArrayList);
        student.setExamList(examArrayList);

        assertEquals(1, student.getStudentID());
        assertEquals("April", student.getStudentFirstName());
        assertEquals("Ludgate", student.getStudentLastName());
        assertEquals("USA", student.getStudentAddress());
        assertEquals("0775432109", student.getStudentPhoneNumber());
        assertEquals(1, student.getUserID());
        assertEquals(batchArrayList, student.getBatchList());
        assertEquals(markArrayList, student.getMarkList());
        assertEquals(teacherArrayList, student.getTeacherList());
        assertEquals(examArrayList, student.getExamList());
    }
}