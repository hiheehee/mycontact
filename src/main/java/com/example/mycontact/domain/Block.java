package com.example.mycontact.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Block {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private  String name;

    private  String reason;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
