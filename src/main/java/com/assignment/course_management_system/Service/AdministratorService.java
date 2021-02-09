package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.DataTransferObject.AdministratorRegistration;
import com.assignment.course_management_system.Entity.Administrator;
import com.assignment.course_management_system.Repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorService implements AdministratorInterface {

    @Autowired
    AdministratorRepository administratorRepository;

    @Override
    public void save(Administrator administrator) {
        administratorRepository.save(administrator);
    }

    @Override
    public Administrator registerAdministrator(AdministratorRegistration administratorRegistration) {
        Administrator administrator = new Administrator();
        administrator.setAdministratorFirstName(administratorRegistration.getFirstName());
        administrator.setAdministratorLastName(administratorRegistration.getLastName());
        administrator.setAdministratorAddress(administratorRegistration.getAddress());
        administrator.setAdministratorPhoneNumber(administratorRegistration.getPhoneNumber());
        return administratorRepository.save(administrator);
    }

    @Override
    public List<Administrator> getAll() {
        return administratorRepository.findAll();
    }

    @Override
    public Administrator getAdministratorByID(int id) {
        return administratorRepository.getAdministratorsByID(id);
    }
}
