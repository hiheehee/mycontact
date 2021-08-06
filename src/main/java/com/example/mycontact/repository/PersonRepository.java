package com.example.mycontact.repository;

import com.example.mycontact.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);

    /*
    // 인자 순서 번호로 받는 방법
    @Query(value = "select person from Person person where person.birthday.monthOfBirthday = ?1 and person.birthday.dayOfBirthday = ?2")
    List<Person> findByMothOfBirthday(int monthOfBirthday, int dayOfBirthday);

    // 쿼리를 native로 받는 방법
    @Query(value = "select * from person where month_of_birthday = :monthOfBirthday and day_of_birthday = :dayOfBirthday", nativeQuery = true)
    List<Person> findByMothOfBirthday(@Param("monthOfBirthday") int monthOfBirthday,@Param("dayOfBirthday") int dayOfBirthday);

    // 인자 이름으로 받는 방법
    @Query(value = "select person from Person person where person.birthday.monthOfBirthday = :monthOfBirthday and person.birthday.dayOfBirthday = :dayOfBirthday")
    List<Person> findByMothOfBirthday(@Param("monthOfBirthday") int monthOfBirthday,@Param("dayOfBirthday") int dayOfBirthday);
    */

    // 인자 이름으로 받는 방법
    @Query(value = "select person from Person person where person.birthday.monthOfBirthday = :monthOfBirthday")
    List<Person> findByMothOfBirthday(@Param("monthOfBirthday") int monthOfBirthday);

    @Query(value = "select * from person where person.deleted = true", nativeQuery = true)
    List<Person> findPeopleDeleted();
}

