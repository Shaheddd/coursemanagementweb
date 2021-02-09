package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.DataTransferObject.BatchRegistration;
import com.assignment.course_management_system.Entity.Batch;
import com.assignment.course_management_system.Entity.Course;
import com.assignment.course_management_system.Entity.Student;
import com.assignment.course_management_system.Entity.Teacher;
import com.assignment.course_management_system.Repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BatchService implements BatchInterface {

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private BatchService batchService;

    @Autowired
    private StudentService studentService;


    @Override
    public Batch save(Batch batch) {
        return batchRepository.save(batch);
    }

    @Override
    public void saveBatch(BatchRegistration batchRegistration) {

        Course course = courseService.searchCourseByID(batchRegistration.getCourseID());
        Teacher teacher = teacherService.getTeacherByID(batchRegistration.getTeacherID());

        Batch batch = new Batch();
        batch.setBatchCode(batchRegistration.getBatchCode());

        List<Batch> newBatch = new ArrayList<>();
        batch.setTeacher(teacher);
        batch.setCourse(course);

        newBatch = teacher.getBatchList();
        newBatch.add(batch);
        teacher.setBatchList(newBatch);
        newBatch = course.getBatchList();
        newBatch.add(batch);
        course.setBatchList(newBatch);

        batchRepository.save(batch);
    }

    @Override
    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }

    @Override
    public Batch getBatchByID(int id) {
        return batchRepository.getOne(id);
    }

    @Override
    public Batch searchBatchByID(int id) {
        return batchRepository.searchBatch(id);
    }

    @Override
    public void deleteBatch(int id) {
        batchRepository.deleteById(id);
    }

    @Override
    public Batch findByBatchID(int batchID) {
        return batchRepository.getOne(batchID);
    }

    @Override
    public Batch findBatchByTheirID(int batchID) {

        return batchRepository.findBatchByTheirID(batchID);
    }

    @Override
    public List<Batch> getBatchFromCourse(int batchID) {
        return batchRepository.getBatchFromCourse(batchID);
    }

    @Override
    public boolean addStudentToBatch(BatchRegistration batchRegistration) {

        Batch batch = batchService.getBatchByID(batchRegistration.getBatchID());

        Student student = studentService.getStudentByID(batchRegistration.getStudentID());


        if (batch != null && student != null)
        {
            List<Batch> batchList = new ArrayList<>();
            batchList = student.getBatchList();
            batchList.add(batch);
            student.setBatchList(batchList);
            List<Student> newStudent = new ArrayList<>();
            newStudent = batch.getStudentList();
            newStudent.add(student);
            batch.setStudentList(newStudent);
            batchRepository.save(batch);
            return true;
        }

        return false;
    }

    @Override
    public List<Batch> getMyBatchesForTeacher(int teacherID) {
        return batchRepository.getMyBatchesForTeacher(teacherID);
    }

    @Override
    public void deleteEntireBatch(Batch batch) {
        batchRepository.deleteById(batch.getBatchID());
    }


}
