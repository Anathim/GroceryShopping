package za.ac.cput.exception;

public class TransactionNotFoundException extends RuntimeException{

    public TransactionNotFoundException(Long id){
        super("Could not find transaction wih id " +id);
    }
}
