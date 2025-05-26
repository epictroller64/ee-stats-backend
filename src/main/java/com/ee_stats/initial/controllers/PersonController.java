package com.ee_stats.initial.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ee_stats.initial.models.Person;
import com.ee_stats.initial.services.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/{id}")
    public Person findById(@PathVariable String id) {
        return personService.findById(id);
    }

    @GetMapping("/all/{page}/{size}")
    public Page<Person> findAll(@PathVariable Integer page, @PathVariable Integer size) {
        return personService.findAll(page, size);
    }
}
