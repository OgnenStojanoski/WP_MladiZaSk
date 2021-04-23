package mk.ukim.finki.wp.project.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long productId){
        super(String.valueOf(productId));
    }
}
