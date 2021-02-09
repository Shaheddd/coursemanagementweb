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
@Table(name = "exam")
@JsonRootName("exam")
public class Exam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int examID;
    private String examName;
    private String examType;
    private String examDescription;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_batchID")
    private Batch batch;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_teacherID")
    private Teacher teacher;

    @JsonIgnore
    @ManyToMany(mappedBy = "examList")
    List<Student> studentList;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Mark.class)
    private List<Mark> markList;
}
