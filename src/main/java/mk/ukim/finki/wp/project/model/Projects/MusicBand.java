package mk.ukim.finki.wp.project.model.Projects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.project.model.Project;
import mk.ukim.finki.wp.project.model.enumerations.Genre;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@DiscriminatorValue("Band")
public class MusicBand extends Project {
    @Enumerated(EnumType.STRING)
    private Genre genre;
}
