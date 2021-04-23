package mk.ukim.finki.wp.project.service.impl;

import mk.ukim.finki.wp.project.model.Product;
import mk.ukim.finki.wp.project.model.Products.CD;
import mk.ukim.finki.wp.project.model.Products.T_Shirt;
import mk.ukim.finki.wp.project.model.Products.Ticket;
import mk.ukim.finki.wp.project.model.Projects.MusicBand;
import mk.ukim.finki.wp.project.model.enumerations.Size;
import mk.ukim.finki.wp.project.repository.ProductRepository;
import mk.ukim.finki.wp.project.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Integer price, Integer quantity, Integer numberOfSongs, Integer length) {
        return Optional.of(this.productRepository.save(new CD(name, price, quantity, numberOfSongs, length)));
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Integer price, Integer quantity, Size size) {
        return Optional.of(this.productRepository.save(new T_Shirt(name, price, quantity, size)));
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Integer price, Integer quantity) {
        return Optional.of(this.productRepository.save(new Ticket(name, price, quantity)));
    }
}
