package exceptions;

public class InvalidUnitNumberException extends Exception{
	public InvalidUnitNumberException(int number) {
		super("The unit number "+number+" isn't valid.");
	}
}
