package com.example.mycontact.service;

import com.example.mycontact.exception.PersonNotFoundException;
import com.example.mycontact.controller.dto.PersonDto;
import com.example.mycontact.domain.Person;
import com.example.mycontact.exception.RenameNotPermiitedException;
import com.example.mycontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Slf4j
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional(readOnly = true)
    public Person getPerson(Long id){
        return personRepository.findById(id).orElse(null);
    }

    public Page<Person> getAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    @Transactional
    public void put(PersonDto personDto){
        Person person = new Person();
        person.set(personDto);
        person.setName(personDto.getName());
        personRepository.save(person);
    }

    public List<Person> getPeopleByName(String name){
        // List<Person> people = personRepository.findAll();
        // return people.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());
        return personRepository.findByName(name);
    }

    @Transactional
    public void modify(Long id, PersonDto personDto) {
        //  데이터 조회
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);

        if(!person.getName().equals(personDto.getName())) throw new RenameNotPermiitedException();

        person.set(personDto);

        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, String name) {
        //  데이터 조회
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);

        person.setName(name);

        personRepository.save(person);
    }
    @Transactional
    public void delete(Long id) {
        // 실제로 데이터를 바로 지울 수 있지만 이는 매우 위험한 방식이므로 boolean값만 변경해주도록 한다.
        // personRepository.deleteById(id);

        //  데이터 조회
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setDeleted(true);
        personRepository.save(person);
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }
}
