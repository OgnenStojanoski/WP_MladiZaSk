package mk.ukim.finki.wp.project.service.impl;

import mk.ukim.finki.wp.project.model.Product;
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
    @Transactional
    public Optional<Product> save(String name, Double price, Integer quantity, Long eventId) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Product> edit(Long id, String name, Double price, Integer quantity, Long eventId) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
