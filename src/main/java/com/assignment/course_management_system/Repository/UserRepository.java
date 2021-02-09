package com.assignment.course_management_system.Repository;

import com.assignment.course_management_system.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT user from User user where user.username = ?1")
    User searchUsername(String username);

    @Query("SELECT user from User user where user.tableID = ?1")
    User getByTableID(int id);

    @Query("SELECT user from User user where user.userID = ?1")
    User findByUserID(int user_ID);
}
