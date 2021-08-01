package com.example.mycontact.controller;

import com.example.mycontact.domain.Person;
import com.example.mycontact.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/person")
@RestController
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping
    @RequestMapping(value = "/{id}")
    public Person getPerson( Long id){
        return personService.getPerson(id);
    }
}
