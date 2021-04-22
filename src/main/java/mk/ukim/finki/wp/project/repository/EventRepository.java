package mk.ukim.finki.wp.project.repository;

import mk.ukim.finki.wp.project.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
