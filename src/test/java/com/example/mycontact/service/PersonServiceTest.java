package com.example.mycontact.service;

import com.example.mycontact.controller.dto.PersonDto;
import com.example.mycontact.domain.Person;
import com.example.mycontact.domain.dto.Birthday;
import com.example.mycontact.exception.PersonNotFoundException;
import com.example.mycontact.exception.RenameNotPermiitedException;
import com.example.mycontact.repository.PersonRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @InjectMocks // test 대상이 되는 클래스
    private PersonService personService;

    @Mock // test 대상 클래스에서 Autowise하는 것들
    private PersonRepository personRepository;

    private PersonDto mockPersonDto() {
        return PersonDto.of("martin", "programming", "판교", LocalDate.now(), "programmer", "010-1111-2222");
    }

    @Test
    void getAll() {
        when(personRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(
                        Lists.newArrayList(new Person("martin"), new Person("dennis"), new Person("tony"))));

        Page<Person> result = personService.getAll(PageRequest.of(0, 3));

        assertThat(result.getNumberOfElements()).isEqualTo(3);
        assertThat(result.getContent().get(0).getName()).isEqualTo("martin");
        assertThat(result.getContent().get(1).getName()).isEqualTo("dennis");
        assertThat(result.getContent().get(2).getName()).isEqualTo("tony");
    }


    @Test
    void getPeopleByName() {
        when(personRepository.findByName("martin"))
                .thenReturn(Lists.newArrayList(new Person("martin")));

        List<Person> result = personService.getPeopleByName("martin");

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("martin");
    }

    @Test
    void getPerson() {
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("martin")));

        Person person = personService.getPerson(1L);

        assertThat(person.getName()).isEqualTo("martin");
    }

    @Test
    void getPersonifNotFount() {
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());

        Person person = personService.getPerson(1L);
        assertThat(person).isNull();
    }

    @Test
    void put() {
        personService.put(mockPersonDto());
        verify(personRepository, times(1)).save(argThat(new IsPersonWillBeInserted()));
    }


    @Test
    void modifyIfPersonNotFound() {
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, ()-> personService.modify(1L, mockPersonDto()));
    }

    @Test
    void modifyIfNameIsDifferent() {
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("tony")));

        assertThrows(RenameNotPermiitedException.class, () -> personService.modify(1L, mockPersonDto()));
    }

    @Test
    void modify() {
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("martin")));

        personService.modify(1L, mockPersonDto());

        verify(personRepository, times(1)).save(argThat(new IsPersonWillBeUpdate()));
    }

    @Test
    void modifyByNameIfPersonNotFound() {
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> personService.modify(1L, "daniel"));
    }

    @Test
    void modifyByName() {
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("martin")));

        personService.modify(1L, "daniel");
        verify(personRepository, times(1)).save(argThat(new IsNameWillBeUpdated()));
    }

    @Test
    void deleteIfPersonNotFound() {
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> personService.delete(1L));
    }

    @Test
    void delete() {
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("martin")));

        personService.delete(1L);
        verify(personRepository, times(1)).save(argThat(new IsPersonWillBeDeleted()));

    }

    private static class IsPersonWillBeInserted implements ArgumentMatcher<Person> {
        @Override
        public boolean matches(Person person) {
            return equals(person.getName(), "martin")
                    && equals(person.getHobby(), "programming")
                    && equals(person.getAddress(), "판교")
                    && equals(person.getBirthday(), Birthday.of(LocalDate.now()))
                    && equals(person.getJob(), "programmer")
                    && equals(person.getPhoneNumber(), "010-1111-2222");
        }

        private boolean equals(Object actual, Object expected) {
            return expected.equals(actual);
        }
    }

    private static class IsPersonWillBeUpdate implements ArgumentMatcher<Person> {
        @Override
        public boolean matches(Person person) {
            return equals(person.getName(), "martin")
                    && equals(person.getHobby(), "programming")
                    && equals(person.getAddress(), "판교")
                    && equals(person.getBirthday(), Birthday.of(LocalDate.now()))
                    && equals(person.getJob(), "programmer")
                    && equals(person.getPhoneNumber(), "010-1111-2222");
        }

        private boolean equals(Object actual, Object expected) {
            return expected.equals(actual);
        }
    }

    private static class IsNameWillBeUpdated implements ArgumentMatcher<Person> {
        @Override
        public boolean matches(Person person) {
            return person.getName().equals("daniel");
        }
    }

    private static class IsPersonWillBeDeleted implements ArgumentMatcher<Person> {
        @Override
        public boolean matches(Person person) {
            return person.isDeleted();
        }
    }

    @Test
    void getBirthdayFriends() {
        when(personRepository.findByMonthOfBirthdayAndDayOfBirthday(8, 8, 8, 9))
                .thenReturn(Lists.newArrayList(new Person("buzz"), new Person("woody"), new Person("jessie"), new Person("trixie")));

        List<Person> result = personService.getBirthdayFriends();
        assertThat(result.size()).isEqualTo(4);
        assertThat(result.get(0).getName()).isEqualTo("buzz");
    }
}