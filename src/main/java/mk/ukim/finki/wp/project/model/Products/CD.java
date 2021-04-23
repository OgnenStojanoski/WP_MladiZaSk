package mk.ukim.finki.wp.project.model.Products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.project.model.Product;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@DiscriminatorValue("CD")
public class CD extends Product{
    private Integer numberOfSongs;
    private Integer length;

    public CD(String name, Integer price, Integer quantity, Integer numberOfSongs, Integer length) {
        super(name, price, quantity);
        this.numberOfSongs = numberOfSongs;
        this.length = length;
    }
}
