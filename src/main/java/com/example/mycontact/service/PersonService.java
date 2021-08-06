package com.example.mycontact.service;

import com.example.mycontact.controller.dto.PersonDto;
import com.example.mycontact.domain.Person;
import com.example.mycontact.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
       Person person = personRepository.findById(id).orElse(null);
        log.info("person : {}",person);
        return person;
    }

    @Transactional
    public void put(Person person){
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
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다."));

        if(!person.getName().equals(personDto.getName())) throw new RuntimeException("이름이 다릅니다.");

        person.set(personDto);

        personRepository.save(person);
    }

    @Transactional
    public void modify(Long id, String name) {
        //  데이터 조회
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다."));

        person.setName(name);

        personRepository.save(person);
    }
    @Transactional
    public void delete(Long id) {
        // 실제로 데이터를 바로 지울 수 있지만 이는 매우 위험한 방식이므로 boolean값만 변경해주도록 한다.
        // personRepository.deleteById(id);

        //  데이터 조회
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다."));
        person.setDeleted(true);
        personRepository.save(person);
    }
}
