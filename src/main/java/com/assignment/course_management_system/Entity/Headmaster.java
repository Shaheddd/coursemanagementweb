package com.assignment.course_management_system.Entity;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "headmaster")
@JsonRootName("headmaster")
public class Headmaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int headmasterID;
    private String headmasterFirstName;
    private String headmasterLastName;
    private String headmasterAddress;
    private String headmasterPhoneNumber;
    private int userID;
}
