package mk.ukim.finki.wp.project.service;

import mk.ukim.finki.wp.project.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService{
    List<Person> findAll();
    Optional<Person> findById(Long id);
    void deleteById(Long id);
    Optional<Person> save(String name, String surname, String bio);
    Optional<Person> edit(Long id, String name, String surname, String bio);
}
