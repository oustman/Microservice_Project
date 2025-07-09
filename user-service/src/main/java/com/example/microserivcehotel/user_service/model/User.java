package com.example.microserivcehotel.user_service.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_service")
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "mail",nullable = false,unique = true)
    private String email;
    @Column(name = "password",nullable = false)
    private String passWord;
    @Column(name = "name", nullable = false)
    private String name;

}
