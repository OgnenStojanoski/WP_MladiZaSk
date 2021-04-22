package mk.ukim.finki.wp.project.repository;

import mk.ukim.finki.wp.project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
