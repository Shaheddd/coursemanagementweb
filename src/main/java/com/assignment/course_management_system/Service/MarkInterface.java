package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.DataTransferObject.MarkRegistration;
import com.assignment.course_management_system.Entity.Mark;

import java.util.List;

public interface MarkInterface {
    Mark getMarkByID(int id);

    void saveMarksToStudent(MarkRegistration markRegistration);

    List<Mark> getMyMarks(int studentID);

    Mark getMarks(int markID);
}
