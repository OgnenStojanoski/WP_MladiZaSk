package mk.ukim.finki.wp.project.service.impl;

import mk.ukim.finki.wp.project.model.Person;
import mk.ukim.finki.wp.project.model.Project;
import mk.ukim.finki.wp.project.model.Projects.MusicBand;
import mk.ukim.finki.wp.project.model.Projects.VisualArtist;
import mk.ukim.finki.wp.project.model.enumerations.Genre;
import mk.ukim.finki.wp.project.repository.PersonRepository;
import mk.ukim.finki.wp.project.repository.ProjectRepository;
import mk.ukim.finki.wp.project.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;
    private final PersonRepository personRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, PersonRepository personRepository) {
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<Project> findAll() {
        return this.projectRepository.findAll();
    }

    @Override
    public Optional<Project> save(String name, Genre genre) {
        return Optional.of(this.projectRepository.save(new MusicBand(name, genre)));
    }

    @Override
    public Optional<Project> save(String name, String technique) {
        return Optional.of(this.projectRepository.save(new VisualArtist(name, technique)));
    }

    @Override
    public Optional<Project> findById(Long id) {
        return this.projectRepository.findById(id);
    }

    @Override
    public void saveMember(Long project_id, Long person_id) {
        Person person = this.personRepository.findById(person_id).orElseThrow();
        Project project = this.projectRepository.findById(project_id).orElseThrow();

//        List<Person> people = project.getMembers();
//        people.add(person);

        if(!project.getMembers().contains(person)) {
            project.getMembers().add(person);
            this.projectRepository.save(project);

            person.getProjects().add(project);
            this.personRepository.save(person);
        }
    }

    @Override
    public void deleteById(Long id) {
        this.projectRepository.findById(id).get().setMembers(null);
        this.projectRepository.deleteById(id);
    }

    @Override
    public List<MusicBand> getBands() {
        List<Project> projects = this.projectRepository.findAll();
        List<MusicBand> bands = new ArrayList<>();
        for(Project p : projects){
            if(p instanceof MusicBand)
                bands.add((MusicBand) p);
        }
        return bands;
    }

    @Override
    public List<VisualArtist> getArtists() {
        List<Project> projects = this.projectRepository.findAll();
        List<VisualArtist> artists = new ArrayList<>();
        for(Project p : projects){
            if(p instanceof VisualArtist)
                artists.add((VisualArtist) p);
        }
        return artists;
    }
}
