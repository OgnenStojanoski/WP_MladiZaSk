package mk.ukim.finki.wp.project.service;

import mk.ukim.finki.wp.project.model.Event;
import mk.ukim.finki.wp.project.model.Project;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventService{
    List<Event> findAll();
    Optional<Event> save(Long id, Long band_id, Long artist_id, LocalDateTime localDateTime);
    void deleteById(Long id);
    Optional<Event> findById(Long id);
    void saveProduct(Long event_id, List<Long> product_id);
}
