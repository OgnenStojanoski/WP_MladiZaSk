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
public class Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    @Column(length = 400)
    private String bio;

    @ManyToMany
    @JoinTable(
            name = "person_projects",
            joinColumns = @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID")
    )
    private List<Project> projects;

    public Person(String name, String surname, String bio) {
        this.name = name;
        this.surname = surname;
        this.bio = bio;
    }
}

