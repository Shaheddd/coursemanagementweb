package com.assignment.course_management_system.Repository;

import com.assignment.course_management_system.Entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {

    @Query("SELECT batchFromCourse from Batch batchFromCourse where batchFromCourse.course.courseID = ?1")
    List<Batch> getBatchFromCourse(int batchID);

    @Query("SELECT batch from Batch batch where batch.teacher.teacherID = ?1")
    List<Batch> getMyBatchesForTeacher(int teacherID);

    @Query("SELECT batch from Batch batch where batch.batchID= ?1")
    Batch searchBatch(int id);

    @Query("SELECT batch from Batch batch where batch.batchID = ?1")
    Batch findBatchByTheirID(int batchID);


}
