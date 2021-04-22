package mk.ukim.finki.wp.project.service;

import mk.ukim.finki.wp.project.model.Product;
import mk.ukim.finki.wp.project.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService{
    List<Product> listAllProductsInShoppingCart(Long cartId) throws Exception;
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addProductToShoppingCart(String username, Long productId);
}

