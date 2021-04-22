package mk.ukim.finki.wp.project.service.impl;

import mk.ukim.finki.wp.project.model.Person;
import mk.ukim.finki.wp.project.repository.PersonRepository;
import mk.ukim.finki.wp.project.service.PersonService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAll() {
        return this.personRepository.findAll();
    }

    @Override
    public Optional<Person> findById(Long id) {
        return this.personRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.personRepository.deleteById(id);
    }

    @Override
    public Optional<Person> save(String name, String surname, String bio) {
        return Optional.of(this.personRepository.save(new Person(name, surname, bio)));
    }

    @Override
    public Optional<Person> edit(Long id, String name, String surname, String bio) {
        Person person = this.personRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id.toString()));
        person.setName(name);
        person.setSurname(surname);
        person.setBio(bio);

        return Optional.of(this.personRepository.save(person));
    }
}
