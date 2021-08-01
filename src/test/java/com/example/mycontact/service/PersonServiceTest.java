package com.example.mycontact.service;

import com.example.mycontact.domain.Block;
import com.example.mycontact.domain.Person;
import com.example.mycontact.repository.BlockRepository;
import com.example.mycontact.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        givenBlocks();

        List<Person> result = personService.getPeopleExcludeBlocks();

        result.forEach(System.out::println);
    }

    private void givenBlockPerson(String name, int age, String bloodType){
        Person blockPerson = new Person(name, age, bloodType);
        blockPerson.setBlock(givenBlock(name));

        personRepository.save(blockPerson);
    }

    private void givenBlocks() {
        givenBlock("martin");
    }

    private Block givenBlock(String name) {
        return blockRepository.save(new Block(name));
    }

    @Test
    private void givenPeople(){
        givenPerson("martin", 10, "A");
        givenPerson("david", 9, "B");
        givenPerson("dennis", 7, "O");
        givenBlockPerson("martin", 11, "AB");
    }

    private void givenPerson(String name, int age, String bloodType) {
        personRepository.save(new Person(name, age, bloodType));
    }
}