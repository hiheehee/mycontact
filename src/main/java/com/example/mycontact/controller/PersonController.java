package com.example.mycontact.controller;

import com.example.mycontact.controller.dto.PersonDto;
import com.example.mycontact.domain.Person;
import com.example.mycontact.exception.PersonNotFoundException;
import com.example.mycontact.exception.RenameNotPermiitedException;
import com.example.mycontact.exception.dto.ErrorResponse;
import com.example.mycontact.repository.PersonRepository;
import com.example.mycontact.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/api/person")
@RestController
@Slf4j
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody @Valid PersonDto personDto) {
        personService.put(personDto);
    }

    @PutMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        personService.modify(id, personDto);
    }

    @PatchMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, String name) {
        personService.modify(id, name);
    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.delete(id);
    }

}