package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.DataTransferObject.HeadmasterRegistration;
import com.assignment.course_management_system.Entity.Headmaster;

import java.util.List;

public interface HeadmasterInterface {

    Headmaster registerHeadmaster(HeadmasterRegistration headmasterRegistration);

    void saveHeadmaster(Headmaster registerHeadmaster);

    List<Headmaster> getAllHeadmasters();

    Headmaster getHeadmasterByID(int id);

    void deleteHeadmaster(int id);

    Headmaster getHeadmastersByID(int id);

    void save(Headmaster headmaster);
}
