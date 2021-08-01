package com.example.mycontact.service;

import com.example.mycontact.domain.Block;
import com.example.mycontact.domain.Person;
import com.example.mycontact.domain.dto.Birthday;
import com.example.mycontact.repository.BlockRepository;
import com.example.mycontact.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Test
    void getPeopleExcludeBlocks(){
        givenPeople();
       // givenBlocks();

        List<Person> result = personService.getPeopleExcludeBlocks();

        result.forEach(System.out::println);
    }
    /*
        private void givenBlocks() {
            givenBlock("martin")        }
    /*
        private Block givenBlock(String name) {
            return blockRepository.save(new Block(name));
        }
    */
    @Test
    private void givenPeople(){
        givenPerson("martin", 10, "A");
        givenPerson("david", 9, "B");
        givenPerson("dennis", 7, "O");
        givenBlockPerson("martin", 11, "AB");
    }

    private void givenBlockPerson(String name, int age, String bloodType){
        Person blockPerson = new Person(name, age, bloodType);
        blockPerson.setBlock(new Block(name));

        personRepository.save(blockPerson);
    }

    private void givenPerson(String name, int age, String bloodType) {
        givenPerson(name, age, bloodType, null);
    }

    private void givenPerson(String name, int age, String bloodType, LocalDate birthday) {
        Person person = new Person(name, age, bloodType);
        person.setBirthday(new Birthday(birthday));
        personRepository.save(person);
    }

    @Test
    void findByBloodType(){
        givenPerson("martin", 10, "A");
        givenPerson("david", 9, "B");
        givenPerson("dennis", 8, "O");
        givenPerson("sophia", 7, "AB");
        givenPerson("benny", 7, "A");
        givenPerson("john", 7, "A");

        List<Person> result = personRepository.findByBloodType("A");
        result.forEach(System.out::println);

    }

    @Test
    void findByBirthdayBetween(){
        givenPerson("martin", 10, "A", LocalDate.of(1991, 8, 5));
        givenPerson("david", 9, "B", LocalDate.of(1993, 9, 1));
        givenPerson("dennis", 8, "O", LocalDate.of(1990, 8, 30));
        givenPerson("sophia", 7, "AB", LocalDate.of(1991, 12, 31));
        givenPerson("benny", 7, "A", LocalDate.of(1991, 10, 3));
        givenPerson("john", 7, "A", LocalDate.of(1994, 1, 5));

        List<Person> result = personRepository.findByMothOfBirthday(8);
        result.forEach(System.out::println);
    }

    @Test
    void getPeopleByName(){
        givenPeople();
        List<Person> result = personService.getPeopleByName("martin");

        result.forEach(System.out::println);
    }

    @Test
    void cascadeTest(){
        givenPeople();
        List<Person> result = personRepository.findAll();

        result.forEach(System.out::println);

        Person person = result.get(3);
        person.getBlock().setStartDate(LocalDateTime.now());
        person.getBlock().setEndDate(LocalDateTime.now());

        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);

       // personRepository.delete(person);
        //personRepository.findAll().forEach(System.out::println);
       // blockRepository.findAll().forEach(System.out::println);
        person.setBlock(null);
        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);

        blockRepository.findAll().forEach(System.out::println);
    }

    @Test
    void getPerson(){
        givenPeople();
        Person person = personService.getPerson(3L);
    }
}