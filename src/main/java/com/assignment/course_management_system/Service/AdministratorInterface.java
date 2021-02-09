package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.DataTransferObject.AdministratorRegistration;
import com.assignment.course_management_system.Entity.Administrator;

import java.util.List;

public interface AdministratorInterface {

    void save(Administrator administrator);

    Administrator registerAdministrator(AdministratorRegistration administratorRegistration);

    List<Administrator> getAll();

    Administrator getAdministratorByID(int id);
}
