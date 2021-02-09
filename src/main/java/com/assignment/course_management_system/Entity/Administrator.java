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
@Table(name = "administrator")
@JsonRootName("administrator")
public class Administrator implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int administratorID;
    private String administratorFirstName;
    private String administratorLastName;
    private String administratorAddress;
    private String administratorPhoneNumber;
    private int userID;
}
