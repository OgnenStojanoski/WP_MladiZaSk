package mk.ukim.finki.wp.project.exceptions;

public class ProductAlreadyInShoppingCartException extends RuntimeException{
    public ProductAlreadyInShoppingCartException(Long productId, String username) {
        System.out.println("Product with id " + productId + " already in cart.");
    }
}
