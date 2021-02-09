package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.DataTransferObject.ExamRegistration;
import com.assignment.course_management_system.Entity.Batch;
import com.assignment.course_management_system.Entity.Exam;
import com.assignment.course_management_system.Entity.Student;
import com.assignment.course_management_system.Entity.Teacher;
import com.assignment.course_management_system.Repository.ExamRepository;
import com.assignment.course_management_system.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService implements ExamInterface {

    @Autowired
    ExamRepository examRepository;

    @Autowired
    BatchService batchService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public boolean createExam(ExamRegistration examRegistration) {

        Batch batch = batchService.searchBatchByID(examRegistration.getBatchID());
        Teacher teacher = teacherService.getTeacherByID(examRegistration.getTeacherID());
        List<Student> studentList = batch.getStudentList();

        Exam newExam = new Exam();

        newExam.setExamName(examRegistration.getExamName());
        newExam.setExamType(examRegistration.getExamType());
        newExam.setExamDescription(examRegistration.getExamDescription());

        List<Exam> examList = new ArrayList<>();
        newExam.setBatch(batch);
        newExam.setTeacher(teacher);

        for (Student student : studentList) {
            examList = student.getExamList();
            examList.add(newExam);
            student.setExamList(examList);
        }
        examList = batch.getExamsList();
        examList.add(newExam);
        batch.setExamsList(examList);
        examList = teacher.getExamList();
        examList.add(newExam);
        teacher.setExamList(examList);

        examRepository.save(newExam);
        return true;

    }

    @Override
    public void saveExam(Exam saveExam) {
        examRepository.save(saveExam);
    }

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public Exam getExamByID(int id) {
        return examRepository.getOne(id);
    }

    @Override
    public List<Exam> getMyExamsForTeacher(int teacherID) {
        return examRepository.getMyExamsForTeacher(teacherID);
    }

    @Override
    public List<Exam> getMyExamsForStudent(int studentID) {
        return examRepository.getMyExamsForStudent(studentID);
    }

}
