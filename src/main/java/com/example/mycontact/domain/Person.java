package com.example.mycontact.domain;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;
import com.example.mycontact.controller.dto.PersonDto;
import com.example.mycontact.domain.dto.Birthday;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Where(clause = "deleted = fales")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    private String hobby;

    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String bloodType;

    private String address;

    private String job;

    @Valid
    @Embedded
    private Birthday birthday;

    @ToString.Exclude
    private String phoneNumber;

    @ColumnDefault("0")
    private boolean deleted;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Block block;

    public void set(PersonDto personDto) {
        if (!StringUtils.isEmpty(personDto.getHobby())) this.setHobby(personDto.getHobby());
        if (!StringUtils.isEmpty(personDto.getBloodType())) this.setBloodType(personDto.getBloodType());
        if (!StringUtils.isEmpty(personDto.getAddress())) this.setBloodType(personDto.getAddress());
        if (!StringUtils.isEmpty(personDto.getJob())) this.setBloodType(personDto.getJob());
        if (!StringUtils.isEmpty(personDto.getPhoneNumber())) this.setBloodType(personDto.getPhoneNumber());
    }

    public Integer getAge() {
        if(this.birthday!= null) return LocalDate.now().getYear() - this.birthday.getDayOfBirthday() + 1;
        else return null;
    }

    public boolean isBirthdayToday(){
        return LocalDate.now().equals(LocalDate.of(this.birthday.getYearOfBirthday(),this.birthday.getMonthOfBirthday(),this.birthday.getDayOfBirthday()));
    }
}
