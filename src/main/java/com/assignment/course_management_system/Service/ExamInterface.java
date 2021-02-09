package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.DataTransferObject.ExamRegistration;
import com.assignment.course_management_system.Entity.Exam;

import java.util.List;

public interface ExamInterface {

    boolean createExam(ExamRegistration examRegistration);

    void saveExam(Exam saveExam);

    List<Exam> getAllExams();

    Exam getExamByID(int id);

    List<Exam> getMyExamsForTeacher(int teacherID);

    List<Exam> getMyExamsForStudent(int studentID);
}
