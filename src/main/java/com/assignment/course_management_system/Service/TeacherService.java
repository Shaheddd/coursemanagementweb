package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.DataTransferObject.TeacherRegistration;
import com.assignment.course_management_system.Entity.Student;
import com.assignment.course_management_system.Entity.Teacher;
import com.assignment.course_management_system.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService implements  TeacherInterface {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    ExamService examService;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher registerTeacher(TeacherRegistration teacherRegistration) {
        Teacher newTeacher = new Teacher();
        newTeacher.setTeacherFirstName(teacherRegistration.getFirstName());
        newTeacher.setTeacherLastName(teacherRegistration.getLastName());
        newTeacher.setTeacherAddress(teacherRegistration.getAddress());
        newTeacher.setTeacherPhoneNumber(teacherRegistration.getPhoneNumber());
        return this.teacherRepository.save(newTeacher);
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher getTeacherByID(int id) {
        return teacherRepository.getOne(id);
    }

    @Override
    public Teacher getByTeacherID(int id) {
        return teacherRepository.getByUserID(id);
    }

    @Override
    public void deleteTeacher(int id) {
        teacherRepository.deleteById(id);
    }


    @Override
    public boolean assignTeacherToStudent(TeacherRegistration teacherRegistration) {

        Teacher teacher = teacherService.getTeacherByID(teacherRegistration.getTeacherID());

        Student student = studentService.getStudentByID(teacherRegistration.getStudentID());


        if (teacher != null && student != null)
        {
            List<Teacher> teacherList = new ArrayList<>();
            teacherList = student.getTeacherList();
            teacherList.add(teacher);
            student.setTeacherList(teacherList);
            List<Student> newStudent = new ArrayList<>();
            newStudent = teacher.getStudentList();
            newStudent.add(student);
            teacher.setStudentList(newStudent);
            teacherRepository.save(teacher);
            return true;
        }

        return false;
    }

    @Override
    public List<Teacher> getTeacherFromCourse(int courseID) {
        return teacherRepository.getTeacherFromCourse(courseID);
    }

    @Override
    public Teacher getTeachersByID(int id) {
        return teacherRepository.getTeacherByID(id);
    }

}

