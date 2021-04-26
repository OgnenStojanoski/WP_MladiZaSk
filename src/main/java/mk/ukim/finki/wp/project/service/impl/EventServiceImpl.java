package mk.ukim.finki.wp.project.service.impl;

import mk.ukim.finki.wp.project.model.Event;
import mk.ukim.finki.wp.project.model.Product;
import mk.ukim.finki.wp.project.model.Projects.MusicBand;
import mk.ukim.finki.wp.project.model.Projects.VisualArtist;
import mk.ukim.finki.wp.project.repository.EventRepository;
import mk.ukim.finki.wp.project.repository.ProductRepository;
import mk.ukim.finki.wp.project.repository.ProjectRepository;
import mk.ukim.finki.wp.project.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;
    private final ProjectRepository projectRepository;
    private final ProductRepository productRepository;

    public EventServiceImpl(EventRepository eventRepository, ProjectRepository projectRepository, ProductRepository productRepository) {
        this.eventRepository = eventRepository;
        this.projectRepository = projectRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Event> findAll() {
        return this.eventRepository.findAll();
    }

    @Override
    public Page<Event> findAll(Pageable pageable) {
        return this.eventRepository.findAll(pageable);
    }

    @Override
    public Optional<Event> save(Long id, Long band_id, Long artist_id, LocalDateTime localDateTime) {
        MusicBand musicBand = (MusicBand) this.projectRepository.findById(band_id).orElseThrow();
        VisualArtist visualArtist = (VisualArtist) this.projectRepository.findById(artist_id).orElseThrow();

        return Optional.of(this.eventRepository.save(new Event(id, visualArtist, musicBand, localDateTime)));
    }

    @Override
    public void deleteById(Long id) {
        Event event = this.eventRepository.findById(id).orElseThrow();
        for(Product p : event.getProducts()){
//            this.productRepository.deleteById(p.getId());
            this.productRepository.findById(p.getId()).get().setEvent(null);
        }
        this.eventRepository.deleteById(id);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return this.eventRepository.findById(id);
    }

    @Override
    public void saveProduct(Long event_id, List<Long> product_id) {
        Event event = this.eventRepository.findById(event_id).orElseThrow();
        for(Long id : product_id) {
            Product product = this.productRepository.findById(id).orElseThrow();
            if (!event.getProducts().contains(product)) {
                event.getProducts().add(product);
                this.eventRepository.save(event);

                product.setEvent(event);
                this.productRepository.save(product);
            }
        }
    }
}