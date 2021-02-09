package com.assignment.course_management_system.Repository;

import com.assignment.course_management_system.Entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {

    @Query("SELECT exam from Exam exam where exam.teacher.teacherID = ?1")
    List<Exam> getMyExamsForTeacher(int teacherID);

    @Query("SELECT exam from Exam exam where exam.batch.course.courseID = ?1")
    List<Exam> getMyExamsForStudent(int studentID);
}
