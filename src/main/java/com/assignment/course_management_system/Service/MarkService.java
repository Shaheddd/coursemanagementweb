package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.DataTransferObject.MarkRegistration;
import com.assignment.course_management_system.Entity.Exam;
import com.assignment.course_management_system.Entity.Mark;
import com.assignment.course_management_system.Entity.Student;
import com.assignment.course_management_system.Repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarkService implements MarkInterface {

    @Autowired
    MarkRepository markRepository;

    @Autowired
    MarkService markService;

    @Autowired
    StudentService studentService;

    @Autowired
    ExamService examService;

    @Autowired
    TeacherService teacherService;

    @Override
    public Mark getMarkByID(int id) {
        return markRepository.getOne(id);
    }

    @Override
    public void saveMarksToStudent(MarkRegistration markRegistration) {

        Student student = studentService.getStudentByID(markRegistration.getStudentID());

        Exam exam = examService.getExamByID(markRegistration.getExamID());

        Mark mark = new Mark();
        mark.setMarks(markRegistration.getMarks());
        mark.setGrade(markRegistration.getGrade());

        List<Mark> getMarks = new ArrayList<>();
        mark.setStudent(student);
        mark.setExam(exam);
        getMarks = student.getMarkList();
        getMarks.add(mark);
        student.setMarkList(getMarks);
        getMarks = exam.getMarkList();
        getMarks.add(mark);
        exam.setMarkList(getMarks);

        markRepository.save(mark);
    }

    @Override
    public List<Mark> getMyMarks(int studentID) {
        return markRepository.getMyMarks(studentID);
    }

    @Override
    public Mark getMarks(int markID) {
        return markRepository.getMarks(markID);
    }
}
