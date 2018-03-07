package exceptions;

public class InvalidMapNumberException extends Exception {
	public InvalidMapNumberException(int number) {
		super("The number "+number+" is not a valid map's number.");
	}
}
