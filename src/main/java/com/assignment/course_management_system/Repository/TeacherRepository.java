package com.assignment.course_management_system.Repository;

import com.assignment.course_management_system.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Query("SELECT batch from Teacher batch where batch.userID = ?1")
    Teacher getByUserID(int id);

    @Query("SELECT teacherFromCourse from Teacher teacherFromCourse where teacherFromCourse.teacherID = ?1")
    List<Teacher> getTeacherFromCourse(int courseID);

    @Query("SELECT teacher from Teacher teacher where teacher.teacherID = ?1")
    Teacher getTeacherByID(int id);

}
