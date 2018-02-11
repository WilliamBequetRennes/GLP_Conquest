package exceptions;

public class InvalidSizeMapNumberException extends Exception{

	public InvalidSizeMapNumberException(int number) {
		super("The number "+number+" is not a valid size map number.");
	}
}
