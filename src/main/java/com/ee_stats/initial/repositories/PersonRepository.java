package com.ee_stats.initial.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import com.ee_stats.initial.models.Person;

public interface PersonRepository extends CrudRepository<Person, String> {
    @NonNull
    Optional<Person> findById(@NonNull String id);

    Page<Person> findAll(@NonNull Pageable pageable);
}
