package com.assignment.course_management_system.Controller;

import com.assignment.course_management_system.DataTransferObject.BatchRegistration;
import com.assignment.course_management_system.DataTransferObject.CourseRegistration;
import com.assignment.course_management_system.DataTransferObject.TeacherRegistration;
import com.assignment.course_management_system.Entity.Batch;
import com.assignment.course_management_system.Entity.Course;
import com.assignment.course_management_system.Entity.Student;
import com.assignment.course_management_system.Entity.Teacher;
import com.assignment.course_management_system.Service.BatchService;
import com.assignment.course_management_system.Service.CourseService;
import com.assignment.course_management_system.Service.StudentService;
import com.assignment.course_management_system.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/headmaster")
public class HeadmasterController {

    @Autowired
    CourseService courseService;

    @Autowired
    BatchService batchService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @GetMapping("/loadHeadmasterHomepage")
    public String loadHomePage(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "HeadmasterHomepage";
    }

    @GetMapping("/loadCourse")
    public String loadCourse(Model model) {
        model.addAttribute("course", new CourseRegistration());
        return "RegisterCourse";
    }

    @PostMapping("/createCourse")
    public String createCourse(@ModelAttribute("course") CourseRegistration courseRegistration) {

        courseService.createCourse(courseRegistration);

        return "redirect:/headmaster/loadHeadmasterHomepage?success";

    }

    @GetMapping("/listAllCourses")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "ListCourses";
    }

    @GetMapping("/editCourse/{id}")
    public String editCurrentCourse(@PathVariable(value = "id") int id, Model model) {
        Course course = courseService.getCourseByID(id);
        model.addAttribute("course", course);
        return "EditCurrentCourse";
    }

    @PostMapping("/updateCourse")
    public String updateCourse(@ModelAttribute("course") Course course) {
        Course updateCourse = courseService.getCourseByID(course.getCourseID());
        updateCourse.setCourseName(course.getCourseName());
        updateCourse.setCourseType(course.getCourseType());
        updateCourse.setCourseDescription(course.getCourseDescription());
        courseService.save(updateCourse);

        return "redirect:/headmaster/loadHeadmasterHomepage";

    }

    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable(value = "id") int id) {
        courseService.deleteCourse(id);
        return "redirect:/headmaster/loadHeadmasterHomepage?deletesuccess";
    }

    @PostMapping("/getTeacherDetails")
    public String getTeacherDetails(@ModelAttribute("batch") BatchRegistration batchRegistration, Model model) {

        Teacher teacher = teacherService.getTeacherByID(batchRegistration.getTeacherID());

        if(teacher != null) {
            batchRegistration.setTeacher(teacher);
            model.addAttribute("batch", batchRegistration);
            return "AddBatch";
        }

        return "redirect:/headmaster/loadHeadmasterHomepage";
    }

    @PostMapping("/saveBatch")
    public String saveBatch(@ModelAttribute("batchRegistration") BatchRegistration batchRegistration) {
        batchService.saveBatch(batchRegistration);

        return "redirect:/headmaster/loadHeadmasterHomepage";
    }

    @GetMapping("/createNewBatch/{id}")
    public String createNewBatch(@PathVariable(value = "id") int courseID, Model model) {
        BatchRegistration batchRegistration = new BatchRegistration();
        batchRegistration.setCourseID(courseID);
        model.addAttribute("batch", batchRegistration);
        return "EnterTeacherID";
    }

    @GetMapping("/listBatches")
    public String listAllBatches(Model model) {
        model.addAttribute("batches", batchService.getAllBatches());
        return "ListBatches";
    }

    @GetMapping("/editBatch/{id}")
    public String editBatch(@PathVariable(value = "id") int id, Model model) {
        Batch batch = batchService.getBatchByID(id);
        model.addAttribute("batch", batch);
        return "EditCurrentBatch";
    }

    @GetMapping("/deleteBatch/{id}")
    public String deleteBatch(@PathVariable(value = "id") int id) {
        batchService.deleteBatch(id);
        return "redirect:/headmaster/loadHeadmasterHomepage";
    }

    @PostMapping("/updateBatch")
    public String updateBatch(@ModelAttribute("batch") Batch batch) {
        Batch updateBatch = batchService.getBatchByID(batch.getBatchID());
        updateBatch.setBatchCode(batch.getBatchCode());
        batchService.save(updateBatch);

        return "redirect:/headmaster/loadHeadmasterHomepage";

    }

    @GetMapping("/listAllTeachers")
    public String listAllTeachers(Model model) {
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "ListAllTeachers";
    }

    @GetMapping("/listAllTeachers/{id}")
    public String listTeachers(@PathVariable(value = "id") int id, Model model) {

        Course course = courseService.getCourseByID(id);

        Teacher teacher = teacherService.getTeacherByID(course.getCourseID());

        List<Teacher> teacherList = teacherService.getTeacherFromCourse(teacher.getTeacherID());
        model.addAttribute("currentTeachers", teacherList);
        return "ViewStudentsUnderTeacher";
    }

    @GetMapping("/listAllStudents")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "ListAllStudents";
    }

    @GetMapping("/listCurrentBatches/{id}")
    public String listCurrentBatches(@PathVariable(value = "id") int id, Model model) {
        Course course = courseService.getCourseByID(id);

        Batch batch = batchService.getBatchByID(course.getCourseID());

        List<Batch> batchList = batchService.getBatchFromCourse(batch.getBatchID());
        model.addAttribute("batches", batchList);
        return "ViewBatchesUnderCourse";
    }

    @GetMapping("/loadBatchAddStudent/{id}")
    public String loadAddStudentPage(@PathVariable(value = "id") int id, Model model) {

        BatchRegistration batchRegistration = new BatchRegistration();
        batchRegistration.setBatchID(id);

        model.addAttribute("batchRegistration", batchRegistration);
        return "EnterStudentID";
    }

    @PostMapping("/getBatchStudent")
    public String loadSearchedStudentPage(@ModelAttribute("batchRegistration") BatchRegistration batchRegistration, Model model) {

        Student student = studentService.getStudentByID(batchRegistration.getStudentID());
        if(student != null)
        {
            batchRegistration.setStudentID(student.getStudentID());
            batchRegistration.setStudent(student);
            model.addAttribute("batchRegistration", batchRegistration);
            return "AddStudentToBatch";
        }
            return "redirect:/headmaster/loadBatchAddStudent?notFound";
    }

    @PostMapping("/addStudentToBatch")
    public String saveStudentToBatch(@ModelAttribute("batchRegistration") BatchRegistration batchRegistration, Model model) {

        boolean save = batchService.addStudentToBatch(batchRegistration);
        if(save ==true)
        {
            return "redirect:/headmaster/loadHeadmasterHomepage?studentAdded";
        }
        return "redirect:/headmaster/loadHeadmasterHomepage?failedToSaveStudent";
    }

    @GetMapping("/loadTeacherAssignStudent/{id}")
    public String loadTeacherAssignStudent(@PathVariable(value = "id") int id, Model model) {

        TeacherRegistration teacherRegistration = new TeacherRegistration();
        teacherRegistration.setTeacherID(id);

        model.addAttribute("teacherRegistration", teacherRegistration);
        return "AssignStudentID";
    }

    @PostMapping("/getStudentDetails")
    public String getStudentDetails(@ModelAttribute("teacherRegistration") TeacherRegistration teacherRegistration, Model model) {

        Student student = studentService.getStudentID(teacherRegistration.getStudentID());

        if(student != null)
        {
            teacherRegistration.setStudentID(student.getStudentID());
            teacherRegistration.setStudent(student);
            model.addAttribute("teacherRegistration", teacherRegistration);
            return "AssignStudentToTeacher";
        }

        return null;
    }

    @PostMapping("/assignStudentToTeacher")
    public String assignStudentToTeacher(@ModelAttribute("teacherRegistration") TeacherRegistration teacherRegistration, Model model) {

        boolean save = teacherService.assignTeacherToStudent(teacherRegistration);
        if(save ==true)
        {

            return "redirect:/headmaster/loadHeadmasterHomepage?studentAssigned";
        }

        return "redirect:/headmaster/loadHeadmasterHomepage?failedToAssignStudent";
    }
}
