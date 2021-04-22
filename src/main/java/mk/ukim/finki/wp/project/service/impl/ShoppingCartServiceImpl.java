package mk.ukim.finki.wp.project.service.impl;

import mk.ukim.finki.wp.project.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp.project.model.Product;
import mk.ukim.finki.wp.project.model.ShoppingCart;
import mk.ukim.finki.wp.project.model.User;
import mk.ukim.finki.wp.project.model.enumerations.ShoppingCartStatus;
import mk.ukim.finki.wp.project.repository.ShoppingCartRepository;
import mk.ukim.finki.wp.project.repository.UserRepository;
import mk.ukim.finki.wp.project.service.ProductService;
import mk.ukim.finki.wp.project.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductService productService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long id) throws Exception {
        if(!this.shoppingCartRepository.findById(id).isPresent())
            throw new Exception();
        return this.shoppingCartRepository.findById(id).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidArgumentsException(username));

        return this.shoppingCartRepository
                .findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });

    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        return null;
    }
}
