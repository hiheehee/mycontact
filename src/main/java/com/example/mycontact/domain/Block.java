package com.example.mycontact.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Block {
    @Id
    @GeneratedValue
    private Long id;

    private  String name;

    private  String reason;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
