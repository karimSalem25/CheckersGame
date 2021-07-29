package exceptions;

public class CheckersException extends Exception {
	
	public CheckersException() {
	}
	public CheckersException(String errMsg) {
		super(errMsg);
	}

}