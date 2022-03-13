package exceptions;

public class MissingParamsException extends Exception {
    public MissingParamsException(String err){
        super(err);
    }
}
