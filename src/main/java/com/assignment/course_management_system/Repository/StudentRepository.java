package com.assignment.course_management_system.Repository;

import com.assignment.course_management_system.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT student from Student student where student.studentID = ?1")
    List<Student> getStudentListByBatchID(int batchID);

    @Query("SELECT student from Student student where student.studentID = ?1")
    Student getById(int id);

    @Query("SELECT student from Student student where student.studentID = ?1")
    Student getStudentsByID(int id);

    @Query("SELECT mark from Student mark where mark.userID= ?1")
    Student getMyMarksForStudent(int studentID);

    @Query("SELECT exam from Student exam where exam.userID= ?1")
    Student getMyExamsForStudent(int studentID);

}
