package com.ee_stats.initial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ee_stats.initial.models.Person;
import com.ee_stats.initial.repositories.PersonRepository;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Person findById(String id) {
        return personRepository.findById(id).orElse(null);
    }

    public Page<Person> findAll(Integer page, Integer size) {
        return personRepository.findAll(PageRequest.of(page, size));
    }
}
