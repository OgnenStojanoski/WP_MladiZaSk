package mk.ukim.finki.wp.project.service.impl;

import mk.ukim.finki.wp.project.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp.project.exceptions.ProductNotFoundException;
import mk.ukim.finki.wp.project.model.Product;
import mk.ukim.finki.wp.project.model.Products.Ticket;
import mk.ukim.finki.wp.project.model.ShoppingCart;
import mk.ukim.finki.wp.project.model.User;
import mk.ukim.finki.wp.project.model.enumerations.ShoppingCartStatus;
import mk.ukim.finki.wp.project.repository.ProductRepository;
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
    private final ProductRepository productRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
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
    public ShoppingCart addProductToShoppingCart(String username, Long productId) throws Exception {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        if(product.getQuantity() > 0) {
            shoppingCart.getProducts().add(product);
            shoppingCart.setCost(shoppingCart.getCost() + product.getPrice());
            product.setQuantity(product.getQuantity() - 1);
            this.productRepository.save(product);
            return this.shoppingCartRepository.save(shoppingCart);
        }
        else
            throw new Exception();
    }

    @Override
    public void reevaluateCost(ShoppingCart shoppingCart) {
        List<Product> products = shoppingCart.getProducts();
        User user = shoppingCart.getUser();
        boolean flag = false; //are there other products (not tickets)
        Integer discount = 0;
        shoppingCart.setCost(0);

        for(Product p : products){
            if(!(p instanceof Ticket))
                flag = true;
            else{
                discount = p.getPrice();
            }
            shoppingCart.setCost(shoppingCart.getCost() + p.getPrice());
        }
        if(flag){
            shoppingCart.setCost(shoppingCart.getCost() - discount);
        }
        this.userRepository.save(user);
    }
}
