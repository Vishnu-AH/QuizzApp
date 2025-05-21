package com.example.quizzApp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false , updatable = false)
    private int id;

    @Column(name = "firstName" , nullable = false)
    private String firstName;

    @Column(name = "lastName" , nullable = false)
    private String lastName;

    @Column(name = "email" , nullable = false)
    private String email;

    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "phone_number" , nullable = false)
    private long phoneNumber;

    @Column(name = "is_active" , nullable = false)
    private boolean isActive;
}
