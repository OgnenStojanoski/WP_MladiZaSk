package mk.ukim.finki.wp.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.project.model.Projects.MusicBand;
import mk.ukim.finki.wp.project.model.Projects.VisualArtist;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Event{
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 64)
    private String photos;

    @OneToOne
    private VisualArtist artist;

    @OneToOne
    private MusicBand band;

    @DateTimeFormat(pattern = "RFC_1123_DATE_TIME")
    private LocalDateTime date;


    @OneToMany(mappedBy = "event")
    private List<Product> products;
    //private String place;

    public Event(Long id, VisualArtist visualArtist, MusicBand musicBand, LocalDateTime localDateTime, String fileName) {
        this.id = id;
        this.artist = visualArtist;
        this.band = musicBand;
        this.date = localDateTime;
        this.photos = fileName;
    }

    public Event(Long id, VisualArtist artist, MusicBand band, LocalDateTime date) {
        this.id = id;
        this.artist = artist;
        this.band = band;
        this.date = date;
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
//        this.date.format(formatter);
    }

    public Date toLocalDate(){
        return java.sql.Date.valueOf(this.date.toLocalDate());
    }

    @Transient
    public String getPhotosImagePath() {
        if (photos == null || id == null) return null;
        return "/user-photos/" + id + "/" + photos;
    }

    public String getDate1() {
        return date.format(DateTimeFormatter.ofPattern("EEEE, dd-MM-yyyy HH:mm"));
    }
}
