package mk.ukim.finki.wp.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.project.model.Projects.MusicBand;
import mk.ukim.finki.wp.project.model.Projects.VisualArtist;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Event{
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private VisualArtist artist;

    @OneToOne
    private MusicBand band;

    private LocalDateTime date;

    @OneToMany(mappedBy = "event")
    private List<Product> products;
    //private String place;


    public Event(Long id, VisualArtist artist, MusicBand band, LocalDateTime date) {
        this.id = id;
        this.artist = artist;
        this.band = band;
        this.date = date;
    }
}
