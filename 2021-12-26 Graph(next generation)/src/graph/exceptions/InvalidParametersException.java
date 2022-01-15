package graph.exceptions;

@SuppressWarnings("serial")
public class InvalidParametersException extends RuntimeException{
	public InvalidParametersException(String msg) {
		super(msg);
	}
}
