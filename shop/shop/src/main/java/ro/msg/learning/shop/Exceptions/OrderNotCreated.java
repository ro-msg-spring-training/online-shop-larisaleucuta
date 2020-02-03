package ro.msg.learning.shop.Exceptions;

public class OrderNotCreated extends RuntimeException{
    public OrderNotCreated(){
        super("Could not create order" );
    }
}
