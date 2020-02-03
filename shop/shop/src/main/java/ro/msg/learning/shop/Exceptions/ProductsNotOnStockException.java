package ro.msg.learning.shop.Exceptions;

public class ProductsNotOnStockException extends RuntimeException{
    public ProductsNotOnStockException(){
        super("PRoducts not on stock!");
    }
}
