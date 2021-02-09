package com.assignment.course_management_system.Controller;

import com.assignment.course_management_system.DataTransferObject.ExamRegistration;
import com.assignment.course_management_system.DataTransferObject.MarkRegistration;
import com.assignment.course_management_system.Entity.*;
import com.assignment.course_management_system.Service.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher")
@AllArgsConstructor
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;

    @Autowired
    BatchService batchService;

    @Autowired
    ExamService examService;

    @Autowired
    MarkService markService;

    @Autowired
    StudentService studentService;

    @GetMapping("/loadTeacherHomepage")
    public String loadHomePage(Model model) {
        String username;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.searchUserByUsername(username);

        Teacher teacher = teacherService.getByTeacherID(user.getUserID());

        List<Batch> batchList = batchService.getMyBatchesForTeacher(teacher.getTeacherID());

        model.addAttribute("batches", batchList);

        return "TeacherHomepage";
    }

    @GetMapping("/listAllExams")
    public String listAllExams(Model model) {
        model.addAttribute("exams", examService.getAllExams());
        return "ListExams";
    }

    @GetMapping("/loadExamForm")
    public String loadExamForm(Model model) {
        model.addAttribute("exam", new ExamRegistration());
        return "AddExamToBatch";
    }

    @GetMapping("/createExam/{id}")
    public String createExam(@PathVariable(value = "id") int batchID, Model model) {

        ExamRegistration examRegistration = new ExamRegistration();
        examRegistration.setBatchID(batchID);
        model.addAttribute("exam", examRegistration);
        return "EnterBatchID";
    }

    @PostMapping("/getBatchDetails")
    public String getDetailsOfBatch(@ModelAttribute("exam") ExamRegistration examRegistration, Model model) {

        Batch batch = batchService.getBatchByID(examRegistration.getBatchID());

        if(batch != null) {

            ExamRegistration registration = new ExamRegistration();
            registration.setBatchID(batch.getBatchID());
            registration.setBatch(batch);
            registration.setTeacherID(batch.getTeacher().getTeacherID());

            model.addAttribute("exam", registration);
            return "AddExamToBatch";
        }

        return null;
    }


    @PostMapping("/addExamToBatch")
    public String saveExamToBatch(@ModelAttribute("examRegistration") ExamRegistration examRegistration) {

        boolean save = examService.createExam(examRegistration);
        if(save == true) {
            return "redirect:/teacher/loadTeacherHomepage?examAdded";
        }

        return "redirect:/teacher/loadTeacherHomepage?failedToSaveExam";
    }

    @GetMapping("/createMarks/{id}")
    public String createMarks(@PathVariable(value = "id") int examID, Model model) {

        MarkRegistration markRegistration = new MarkRegistration();
        markRegistration.setExamID(examID);
        model.addAttribute("mark", markRegistration);
        return "EnterStudentIDForMarks";
    }

    @PostMapping("/getStudentID")
    public String getStudentID(@ModelAttribute("student") MarkRegistration markRegistration, Model model) {

        Student student = studentService.getStudentByID(markRegistration.getStudentID());
        Exam exam = examService.getExamByID(markRegistration.getExamID());

        if(student != null && exam != null) {

            markRegistration.setTeacherID(exam.getTeacher().getTeacherID());
            markRegistration.setExamID(exam.getExamID());
            markRegistration.setStudent(student);
            markRegistration.setExam(exam);
            markRegistration.setTeacherID(exam.getTeacher().getTeacherID());
            markRegistration.setTeacher(exam.getTeacher());
            model.addAttribute("mark", markRegistration);
            return "AddMarksToExam";
        }

        return null;
    }

    @PostMapping("/addMarksToStudent")
    public String saveMarksToStudent(@ModelAttribute("markRegistration") MarkRegistration markRegistration) {

        markService.saveMarksToStudent(markRegistration);
        return "redirect:/teacher/loadTeacherHomepage";
    }

    @GetMapping("/getMyExamsForTeacher")
    public String getMyExamsForTeacher(Model model) {
        String username;

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        User user = userService.searchUserByUsername(username);

        Teacher teacher = teacherService.getByTeacherID(user.getUserID());

        List<Exam> examList = examService.getMyExamsForTeacher(teacher.getTeacherID());
        model.addAttribute("exams", examList);

        return "MyAssignedExamsForTeacher";
    }

}
