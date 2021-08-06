package com.example.mycontact.domain;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.example.mycontact.controller.dto.PersonDto;
import com.example.mycontact.domain.dto.Birthday;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

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

    private String address;

    private String job;

    @Valid
    @Embedded
    private Birthday birthday;

    private String phoneNumber;

    @ColumnDefault("0")
    private boolean deleted;

    public void set(PersonDto personDto) {
        if (!StringUtils.isEmpty(personDto.getHobby())) this.setHobby(personDto.getHobby());
        if (!StringUtils.isEmpty(personDto.getAddress())) this.setAddress(personDto.getAddress());
        if (!StringUtils.isEmpty(personDto.getJob())) this.setJob(personDto.getJob());
        if (!StringUtils.isEmpty(personDto.getPhoneNumber())) this.setPhoneNumber(personDto.getPhoneNumber());
        if (personDto.getBirthday() != null) {
            this.setBirthday(Birthday.of(personDto.getBirthday()));
        }
    }

    public Integer getAge() {
        if(this.birthday!= null) return LocalDate.now().getYear() - this.birthday.getDayOfBirthday() + 1;
        else return null;
    }

    public boolean isBirthdayToday(){
        return LocalDate.now().equals(LocalDate.of(this.birthday.getYearOfBirthday(),this.birthday.getMonthOfBirthday(),this.birthday.getDayOfBirthday()));
    }
}
