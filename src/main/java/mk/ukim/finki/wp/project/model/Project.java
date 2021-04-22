package mk.ukim.finki.wp.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Inheritance
@DiscriminatorColumn(name = "PROJ_TYPE")
@Table(name = "project")
public abstract class Project{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "projects")
    private List<Person> members;

    public Project(String name) {
        this.name = name;
    }
}
