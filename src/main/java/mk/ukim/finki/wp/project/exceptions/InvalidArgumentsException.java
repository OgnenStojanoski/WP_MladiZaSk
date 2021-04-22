package mk.ukim.finki.wp.project.exceptions;

public class InvalidArgumentsException extends RuntimeException{
    public InvalidArgumentsException() {
        super("Invalid arguments exception");
    }
    public InvalidArgumentsException(String msg){
        super(msg);
    }
}
