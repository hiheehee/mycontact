package com.example.mycontact.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString(exclude = "phoneNumber")
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int age;

    @ToString.Exclude
    private String hobby;

    private String bloodType;

    private String address;

    private String job;

    private LocalDateTime birthday;

    private String phoneNumber;
}
