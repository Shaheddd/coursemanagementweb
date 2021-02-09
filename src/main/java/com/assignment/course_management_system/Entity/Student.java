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
@Table(name = "student")
@JsonRootName("student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentID;
    private String studentFirstName;
    private String studentLastName;
    private String studentAddress;
    private String studentPhoneNumber;
    private int userID;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Mark.class)
    private List<Mark> markList;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "student_exam",
            joinColumns = @JoinColumn(name = "studentID"),
            inverseJoinColumns = @JoinColumn(name = "examID"))
    List<Exam> examList;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "student_batch",
            joinColumns = @JoinColumn(name = "studentID"),
            inverseJoinColumns = @JoinColumn(name = "batchID"))
            List<Batch> batchList;

    @JsonIgnore
    @ManyToMany(mappedBy = "studentList")
    List<Teacher> teacherList;

}
