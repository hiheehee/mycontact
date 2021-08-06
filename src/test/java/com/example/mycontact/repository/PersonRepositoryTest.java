package com.example.mycontact.repository;

import com.example.mycontact.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;
    @Test
    void crud(){
        Person person = new Person();
        person.setName("john");

        personRepository.save(person);

        List<Person> people = personRepository.findByName("john");
        assertThat(people.get(0).getName()).isEqualTo("john");
        // assertThat(people.get(0).getAge()).isEqualTo(10);
    }

    @Test
    void findByBirthdayBetween(){
        List<Person> result = personRepository.findByMothOfBirthday(8);

        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getName()).isEqualTo("martin");
        assertThat(result.get(1).getName()).isEqualTo("sophia");
    }
}