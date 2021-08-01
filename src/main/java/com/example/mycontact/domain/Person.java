package com.example.mycontact.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private int age;

    private String hobby;

    @NonNull
    private String bloodType;

    private String address;

    private String job;

    private LocalDateTime birthday;

    @ToString.Exclude
    private String phoneNumber;

    private boolean block;

    private String blockReason;

    private LocalDateTime blockStartDate;

    private LocalDateTime blockEndDate;
}
