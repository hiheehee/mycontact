package com.example.mycontact.service;

import com.example.mycontact.domain.Block;
import com.example.mycontact.domain.Person;
import com.example.mycontact.repository.BlockRepository;
import com.example.mycontact.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getPeopleExcludeBlocks(){
        List<Person> people = personRepository.findAll();

        return people.stream().filter(person -> person.getBlock() == null).collect(Collectors.toList());

    }
}