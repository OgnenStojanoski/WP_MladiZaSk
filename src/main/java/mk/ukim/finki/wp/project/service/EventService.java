package mk.ukim.finki.wp.project.service;

import mk.ukim.finki.wp.project.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventService{
    List<Event> findAll();
    Page<Event> findAll(Pageable pageable);
    Optional<Event> save(Long id, Long band_id, Long artist_id, LocalDateTime localDateTime, String fileName);
    void deleteById(Long id);
    Optional<Event> findById(Long id);
    void saveProduct(Long event_id, List<Long> product_id);
}
