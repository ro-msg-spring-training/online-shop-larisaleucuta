package ro.msg.learning.shop.Exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Integer id){
        super("Could not find product with id: " + id);
    }
}
