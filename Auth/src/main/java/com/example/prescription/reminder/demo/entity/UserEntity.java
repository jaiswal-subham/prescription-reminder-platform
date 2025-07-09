package com.example.prescription.reminder.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@Entity
@Table(name="user")
public class UserEntity {


    @Id
    @GeneratedValue(generator = "uuid")  // 👈 tie it to custom generator
    @GenericGenerator(name = "uuid", strategy = "uuid2")  // 👈 generate a random UUID
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private String id;


    @Column(nullable = false,unique = true)
    String username;

    @Column(nullable = false)
    String password;

    @Column(name = "email_Id", nullable = false,unique = true)
    String emailId;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    public UserEntity(String username, String password,  String emailId,  String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public UserEntity() {

    }
}
