package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.DataTransferObject.BatchRegistration;
import com.assignment.course_management_system.Entity.Batch;

import java.util.List;

public interface BatchInterface {

    void saveBatch(BatchRegistration batchRegistration);

    List<Batch> getAllBatches();

    Batch getBatchByID(int id);

    Batch searchBatchByID(int id);

    void deleteBatch(int id);

    Batch findByBatchID(int batchID);

    Batch save(Batch batch);

    Batch findBatchByTheirID(int batchID);

    List<Batch> getBatchFromCourse(int batchID);

    boolean addStudentToBatch(BatchRegistration batchRegistration);

    List<Batch> getMyBatchesForTeacher(int teacherID);

    void deleteEntireBatch(Batch batch);
}
