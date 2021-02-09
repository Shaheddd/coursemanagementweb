package com.assignment.course_management_system.DataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRegistration {

    private String courseName;
    private String courseType;
    private String courseDescription;

}
