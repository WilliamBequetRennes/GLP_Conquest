package exceptions;

public class InvalidMapSizeNumberException extends Exception{

	public InvalidMapSizeNumberException(int number) {
		super("The number "+number+" is not a valid map size number.");
	}
}
