package com.example.mycontact.repository;

import com.example.mycontact.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.assertj.core.api.InstanceOfAssertFactories.LOCAL_DATE;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;
    @Test
    void crud(){
        Person person = new Person();
        person.setAge(10);
        person.setName("admin");
        personRepository.save(person);

        List<Person> people = personRepository.findAll();
        assertThat(people.size()).isEqualTo(1);
        assertThat(people.get(0).getName()).isEqualTo("admin");
        assertThat(people.get(0).getAge()).isEqualTo(10);
    }

    @Test
    void hashCodeEquals(){
        Person person1 = new Person("martin",10);
        Person person2 = new Person("martin",10);

        System.out.println(person1.equals(person2));
   }
}