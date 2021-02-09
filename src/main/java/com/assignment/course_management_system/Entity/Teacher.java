package com.assignment.course_management_system.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teacher")
@JsonRootName("teacher")
public class Teacher implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teacherID;
    private String teacherFirstName;
    private String teacherLastName;
    private String teacherAddress;
    private String teacherPhoneNumber;
    private int userID;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Batch.class)
    private List<Batch> batchList;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Exam.class)
    private List<Exam> examList;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "teacher_student",
            joinColumns = @JoinColumn(name = "teacherID"),
            inverseJoinColumns = @JoinColumn(name = "studentID"))
    List<Student> studentList;
}
