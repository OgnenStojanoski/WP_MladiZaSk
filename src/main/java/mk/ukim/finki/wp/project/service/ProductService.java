package mk.ukim.finki.wp.project.service;

import mk.ukim.finki.wp.project.model.Product;
import mk.ukim.finki.wp.project.model.enumerations.Size;

import java.util.List;
import java.util.Optional;

public interface ProductService{
    List<Product> findAll();

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    //Optional<Product> edit(Long id, String name, Double price, Integer quantity, Long eventId);

    void deleteById(Long id);

    Optional<Product> save(String name, Integer price, Integer quantity, Integer numberOfSongs, Integer length);

    Optional<Product> save(String name, Integer price, Integer quantity, Size size);

    Optional<Product> save(String name, Integer price, Integer quantity);
}
