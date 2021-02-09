package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.DataTransferObject.StudentRegistration;
import com.assignment.course_management_system.Entity.Student;
import com.assignment.course_management_system.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements StudentInterface {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student registerStudent(StudentRegistration studentRegistration) {
        Student student = new Student();
        student.setStudentFirstName(studentRegistration.getFirstName());
        student.setStudentLastName(studentRegistration.getLastName());
        student.setStudentAddress(studentRegistration.getAddress());
        student.setStudentPhoneNumber(studentRegistration.getPhoneNumber());
        return studentRepository.save(student);
    }

    public void saveStudent(Student registerStudent) {
        studentRepository.save(registerStudent);
    }

    @Override
    public Student getStudentByID(int id) {
        return studentRepository.getById(id);
    }

    @Override
    public Student getStudentID(int id) {
        return studentRepository.getOne(id);
    }

    @Override
    public Student getStudentsByID(int id) {
        return studentRepository.getStudentsByID(id);
    }

    @Override
    public Student getMyMarksForStudent(int studentID) {
        return studentRepository.getMyMarksForStudent(studentID);
    }

    @Override
    public Student getMyExamsForStudent(int studentID) {
        return studentRepository.getMyExamsForStudent(studentID);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
}
