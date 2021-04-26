package mk.ukim.finki.wp.project.service.impl;

import mk.ukim.finki.wp.project.model.Event;
import mk.ukim.finki.wp.project.model.Projects.MusicBand;
import mk.ukim.finki.wp.project.model.Projects.VisualArtist;
import mk.ukim.finki.wp.project.repository.EventRepository;
import mk.ukim.finki.wp.project.repository.ProjectRepository;
import mk.ukim.finki.wp.project.service.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;
    private final ProjectRepository projectRepository;

    public EventServiceImpl(EventRepository eventRepository, ProjectRepository projectRepository) {
        this.eventRepository = eventRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Event> findAll() {
        return this.eventRepository.findAll();
    }

    @Override
    public Optional<Event> save(Long id, Long band_id, Long artist_id, LocalDateTime localDateTime) {
        MusicBand musicBand = (MusicBand) this.projectRepository.findById(band_id).orElseThrow();
        VisualArtist visualArtist = (VisualArtist) this.projectRepository.findById(artist_id).orElseThrow();

        return Optional.of(this.eventRepository.save(new Event(id, visualArtist, musicBand, localDateTime)));
    }

    @Override
    public void deleteById(Long id) {
        this.eventRepository.deleteById(id);
    }
}