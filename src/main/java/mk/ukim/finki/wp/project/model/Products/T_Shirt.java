package mk.ukim.finki.wp.project.model.Products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.project.model.Product;
import mk.ukim.finki.wp.project.model.enumerations.Size;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@DiscriminatorValue("T_Shirt")
public class T_Shirt extends Product {
    @Enumerated(value = EnumType.STRING)
    private Size size;
}