package jungle;

public class IllegalMoveException extends RuntimeException{
    public IllegalMoveException(String message){
        super(message);
    }
    public IllegalMoveException(String message, Throwable cause){
        super(message,cause);
    }
}
