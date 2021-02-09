package com.assignment.course_management_system.Service;

import com.assignment.course_management_system.DataTransferObject.HeadmasterRegistration;
import com.assignment.course_management_system.Entity.Headmaster;
import com.assignment.course_management_system.Repository.HeadmasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeadmasterService implements HeadmasterInterface {

    @Autowired
    HeadmasterRepository headmasterRepository;

    @Override
    public List<Headmaster> getAllHeadmasters() {
        return headmasterRepository.findAll();
    }

    @Override
    public Headmaster registerHeadmaster(HeadmasterRegistration headmasterRegistration) {
        Headmaster headmaster = new Headmaster();
        headmaster.setHeadmasterFirstName(headmasterRegistration.getFirstName());
        headmaster.setHeadmasterLastName(headmasterRegistration.getLastName());
        headmaster.setHeadmasterAddress(headmasterRegistration.getAddress());
        headmaster.setHeadmasterPhoneNumber(headmasterRegistration.getPhoneNumber());
        return headmasterRepository.save(headmaster);
    }

    public void saveHeadmaster(Headmaster registerHeadmaster) {
        headmasterRepository.save(registerHeadmaster);
    }

    @Override
    public Headmaster getHeadmasterByID(int id) {
        return headmasterRepository.getOne(id);
    }

    @Override
    public void deleteHeadmaster(int id) {
        headmasterRepository.deleteById(id);
    }

    @Override
    public Headmaster getHeadmastersByID(int id) {
        return headmasterRepository.getHeadmasterByID(id);
    }

    @Override
    public void save(Headmaster headmaster) {
        headmasterRepository.save(headmaster);
    }
}
