package com.assignment.course_management_system.Repository;

import com.assignment.course_management_system.Entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

    @Query("SELECT administrator from Administrator administrator where administrator.administratorID = ?1")
    Administrator getAdministratorsByID(int id);
}
