package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.Entity.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeacherServiceTest {

    @Test
    void registerTeacher() {

        Teacher teacher = new Teacher();
        Course course = new Course();
        Exam exam = new Exam();
        Student student = new Student();
        Batch batch = new Batch();
        Mark mark = new Mark();

        ArrayList<Teacher> teacherArrayList = new ArrayList(Arrays.asList(teacher));

        ArrayList<Exam> examArrayList = new ArrayList(Arrays.asList(
                new Exam(1, "React MCQ", "Test", "MCQ", batch, teacher, Arrays.asList(student), Arrays.asList(mark))));
        ArrayList<Mark> markArrayList = new ArrayList(Arrays.asList(
                new Mark(1, 75, "First Class", exam, student)));
        ArrayList<Batch> batchArrayList = new ArrayList(Arrays.asList(
                new Batch(1, "HF12345", course, examArrayList, teacher, Arrays.asList(student))));
        ArrayList<Student> studentArrayList = new ArrayList(Arrays.asList(
                new Student(1, "Crash", "Bandicoot", "Overworld",
                        "07779876543", 5, markArrayList, examArrayList, batchArrayList, teacherArrayList)));

        teacher.setTeacherID(1);
        teacher.setTeacherFirstName("Harry");
        teacher.setTeacherLastName("Potter");
        teacher.setTeacherAddress("Hogwarts");
        teacher.setTeacherPhoneNumber("0773456789");
        teacher.setUserID(1);
        teacher.setStudentList(studentArrayList);
        teacher.setBatchList(batchArrayList);
        teacher.setExamList(examArrayList);

        assertEquals(1, teacher.getTeacherID());
        assertEquals("Harry", teacher.getTeacherFirstName());
        assertEquals("Potter", teacher.getTeacherLastName());
        assertEquals("Hogwarts", teacher.getTeacherAddress());
        assertEquals("0773456789", teacher.getTeacherPhoneNumber());
        assertEquals(1, teacher.getUserID());
        assertEquals(studentArrayList, teacher.getStudentList());
        assertEquals(batchArrayList, teacher.getBatchList());
        assertEquals(examArrayList, teacher.getExamList());
    }

    @Test
    void getAllTeachers() {

        Teacher teacher = new Teacher();
        Course course = new Course();
        Exam exam = new Exam();
        Student student = new Student();
        Batch batch = new Batch();
        Mark mark = new Mark();

        ArrayList<Teacher> teacherArrayList = new ArrayList(Arrays.asList(teacher));

        ArrayList<Exam> examArrayList = new ArrayList(Arrays.asList(
                new Exam(1, "React MCQ", "Test", "MCQ", batch, teacher, Arrays.asList(student), Arrays.asList(mark))));
        ArrayList<Mark> markArrayList = new ArrayList(Arrays.asList(
                new Mark(1, 75, "First Class", exam, student)));
        ArrayList<Batch> batchArrayList = new ArrayList(Arrays.asList(
                new Batch(1, "HF12345", course, examArrayList, teacher, Arrays.asList(student))));
        ArrayList<Student> studentArrayList = new ArrayList(Arrays.asList(
                new Student(1, "Crash", "Bandicoot", "Overworld",
                        "07779876543", 5, markArrayList, examArrayList, batchArrayList, teacherArrayList)));

        List<Teacher> testTeachers = Arrays.asList(
                new Teacher(1, "Harry", "Potter", "Hogwarts", "0773456789", 7,
                        batchArrayList, examArrayList, studentArrayList),
                new Teacher(2, "Hermione", "Granger", "Hogwarts", "0779876543", 5,
                        batchArrayList, examArrayList, studentArrayList));

        Assertions.assertThat(testTeachers).contains(testTeachers.get(0), testTeachers.get(1));
    }
}