package za.ac.cput.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(Long id){
        super("Could not find product wih id " +id);
    }
}
