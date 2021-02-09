package com.assignment.course_management_system.DataTransferObject;

import com.assignment.course_management_system.Entity.Batch;
import com.assignment.course_management_system.Entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamRegistration {

    private int examID;
    private String examName;
    private String examType;
    private String examDescription;

    private int batchID;
    private int teacherID;

    private Batch batch;
    private Teacher teacher;

    public void setBatchID(int batchID) {
        this.batchID = batchID;
    }
}
