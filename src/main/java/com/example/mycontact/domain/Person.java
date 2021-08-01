package com.example.mycontact.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 변수 포함하는 생성자
@RequiredArgsConstructor // 반드시 있어야 하는 생성자 지정
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private int age;

    private String hobby;

    private String bloodType;

    private String address;

    private String job;

    private LocalDateTime birthday;

    private String phoneNumber;

    @Override
    public boolean equals(Object o) {
        if(o == null){
            return false;
        }
        Person person = (Person) o;

        if(!person.getName().equals(this.getName())) return false;
        if(person.getAge() != this.getAge()) return false;
        return  true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, hobby, bloodType, address, job, birthday, phoneNumber);
    }
}
