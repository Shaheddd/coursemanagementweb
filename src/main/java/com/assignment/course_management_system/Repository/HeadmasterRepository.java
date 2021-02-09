package com.assignment.course_management_system.Repository;

import com.assignment.course_management_system.Entity.Headmaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadmasterRepository extends JpaRepository<Headmaster, Integer> {

    @Query("SELECT headmaster from Headmaster headmaster where headmaster.headmasterID = ?1")
    Headmaster getHeadmasterByID(int id);
}
