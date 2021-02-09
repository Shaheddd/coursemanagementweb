package com.assignment.course_management_system.Repository;

import com.assignment.course_management_system.Entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Integer> {

    @Query("SELECT mark from Mark mark where mark.student.studentID = ?1")
    List<Mark> getMyMarks(int studentID);

    @Query("SELECT mark from Mark mark where mark.markID = ?1")
    Mark getMarks(int markID);

}
