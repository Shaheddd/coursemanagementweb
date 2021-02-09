package com.assignment.course_management_system.RestControllers;

import com.assignment.course_management_system.DataTransferObject.BatchRegistration;
import com.assignment.course_management_system.DataTransferObject.CourseRegistration;
import com.assignment.course_management_system.DataTransferObject.HeadmasterRegistration;
import com.assignment.course_management_system.Entity.*;
import com.assignment.course_management_system.Repository.BatchRepository;
import com.assignment.course_management_system.Repository.CourseRepository;
import com.assignment.course_management_system.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/headmaster")
public class HeadmasterRestController {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    BatchRepository batchRepository;

    @Autowired
    HeadmasterService headmasterService;

    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    @Autowired
    BatchService batchService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/loadHeadmasterHomepage")
    public String loadHomePage(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "HeadmasterHomepage";
    }

    @PostMapping("/saveHeadmaster")
    public String registerHeadmaster(@RequestBody HeadmasterRegistration headmasterRegistration) {

        Headmaster registerHeadmaster = headmasterService.registerHeadmaster(headmasterRegistration);

        User user = new User();
        user.setTableID(registerHeadmaster.getHeadmasterID());
        user.setUsername(headmasterRegistration.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(headmasterRegistration.getPassword()));
        user.setFirstName(headmasterRegistration.getFirstName());
        user.setLastName(headmasterRegistration.getLastName());
        user.setAddress(headmasterRegistration.getAddress());
        user.setPhoneNumber(headmasterRegistration.getPhoneNumber());
        user.setRoles(Arrays.asList(new Role("Headmaster")));
        User registeredUser = userService.save(user);
        registerHeadmaster.setUserID(registeredUser.getUserID());
        headmasterService.saveHeadmaster(registerHeadmaster);

        return "Account Has Been Created Successfully!";
    }

    @GetMapping("/listAllCourses")
    public List<Course> listAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping("/saveCourse")
    public String RegisterCourse(@RequestBody CourseRegistration courseRegistration) {

        Course course = new Course();
        course.setCourseName(courseRegistration.getCourseName());
        course.setCourseType(courseRegistration.getCourseType());
        course.setCourseDescription(courseRegistration.getCourseDescription());
        courseRepository.save(course);

        return "Course Has Been Created Successfully!";
    }

    @GetMapping("/viewCourse/{course_ID}")
    public Course editCourse(@PathVariable(value = "course_ID") int course_ID) {
        Course course = courseService.searchCourseByID(course_ID);
        return course;
    }

    @PostMapping("/editCourse")
    public String updateCourse(@RequestBody Course course) {
        Course editCourse = courseService.searchCourseByID(course.getCourseID());
        editCourse.setCourseName(course.getCourseName());
        editCourse.setCourseType(course.getCourseType());
        editCourse.setCourseDescription(course.getCourseDescription());
        courseRepository.save(editCourse);

        String message = "Updated";
        return message;
    }

    @GetMapping("/deleteCourse/{course_ID}")
    public String deleteEntireCourse(@PathVariable(value = "course_ID") int course_ID) {
        Course course = courseService.getCourseByID(course_ID);

        courseService.deleteEntireCourse(course);

        return "Deleted";
    }

    @GetMapping("/viewHeadmaster/{user_ID}")
    public User editHeadmaster(@PathVariable(value = "user_ID") int user_ID)
    {
        User user = userService.findByID(user_ID);
        return user;
    }

    @GetMapping("/deleteHeadmaster/{user_ID}")
    public String deleteHeadmaster(@PathVariable(value = "user_ID") int user_ID)
    {
        User user = userService.findByTableID(user_ID);

        userService.deleteHeadmaster(user);
        return "Deleted";
    }

    @PostMapping("/editHeadmaster")
    public String updateHeadmaster(@RequestBody User user)
    {
        userService.save(user);
        Headmaster headmaster = headmasterService.getHeadmastersByID(user.getTableID());
        headmaster.setHeadmasterFirstName(user.getFirstName());
        headmaster.setHeadmasterLastName(user.getLastName());
        headmaster.setHeadmasterAddress(user.getAddress());
        headmaster.setHeadmasterPhoneNumber(user.getPhoneNumber());
        headmasterService.save(headmaster);
        String message = "Updated";
        return message;
    }

    @GetMapping("/listAllBatches")
    public List<Batch> listAllBatches() {
        return batchService.getAllBatches();
    }

    @PostMapping("/addNewBatch")
    public String createNewBatch(@RequestBody BatchRegistration batchRegistration) {
        Course course = courseService.searchCourseByID(batchRegistration.getCourseID());
        Teacher teacher = teacherService.getTeachersByID(batchRegistration.getTeacherID());

        Batch createBatch = new Batch();
        createBatch.setBatchID(batchRegistration.getBatchID());
        createBatch.setBatchCode(batchRegistration.getBatchCode());

        List<Batch> newBatch = new ArrayList<>();
        createBatch.setTeacher(teacher);
        createBatch.setCourse(course);
        newBatch.add(createBatch);

        teacher.setBatchList(newBatch);
        course.setBatchList(newBatch);

        batchRepository.save(createBatch);

        return "Batch Has Been Successfully Created!";
    }

    @GetMapping("/viewBatch/{batch_ID}")
    public Batch viewBatch(@PathVariable(value = "batch_ID") int batch_ID)
    {
        Batch batch = batchService.findBatchByTheirID(batch_ID);
        return batch;
    }

    @PostMapping("/updateBatch")
    public String updateBatch(@RequestBody Batch batch) {

        batchService.save(batch);
        String message = "Updated";
        return message;
    }

    @GetMapping("/deleteBatch/{batch_ID}")
    public String deleteEntireBatch(@PathVariable(value = "batch_ID") int batch_ID) {
        Batch batch = batchService.getBatchByID(batch_ID);

        batchService.deleteEntireBatch(batch);

        return "Deleted";
    }


}
