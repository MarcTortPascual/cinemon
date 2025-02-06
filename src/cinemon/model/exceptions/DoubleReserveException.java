package cinemon.model.exceptions;

public class DoubleReserveException extends Exception {
    public DoubleReserveException(){

    }
    public DoubleReserveException(String log){
        super(log);
    }
}
