package com.assignment.course_management_system.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "batch")
@JsonRootName("batch")
public class Batch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int batchID;
    private String batchCode;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_CourseID")
    private Course course;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Exam.class)
    private List<Exam> examsList;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batch_teacherID")
    private Teacher teacher;

    @JsonIgnore
    @ManyToMany(mappedBy = "batchList")
    List<Student> studentList;

}
