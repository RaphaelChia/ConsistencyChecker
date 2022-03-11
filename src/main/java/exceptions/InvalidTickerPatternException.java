package exceptions;

public class InvalidTickerPatternException extends Exception{
    public InvalidTickerPatternException(String err){
        super(err);
    }
}
