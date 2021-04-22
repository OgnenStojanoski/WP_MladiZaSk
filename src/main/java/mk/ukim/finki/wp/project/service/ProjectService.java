package mk.ukim.finki.wp.project.service;

import mk.ukim.finki.wp.project.model.Project;
import mk.ukim.finki.wp.project.model.enumerations.Genre;

import java.util.List;
import java.util.Optional;

public interface ProjectService{
    List<Project> findAll();
    Optional<Project> save(String name, Genre genre);
    Optional<Project> save(String name, String technique);
    Optional<Project> findById(Long id);
    void saveMember(Long project_id, Long person_id);
    void deleteById(Long id);
}
