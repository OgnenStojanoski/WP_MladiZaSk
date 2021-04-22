package mk.ukim.finki.wp.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Inheritance
@DiscriminatorColumn(name = "PROD_TYPE")
@Table(name = "product")
public abstract class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer price;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "EVENT_ID", referencedColumnName = "ID")
    private Event event;
    // CDProduct
    // MerchProduct
    // TicketProduct
}
