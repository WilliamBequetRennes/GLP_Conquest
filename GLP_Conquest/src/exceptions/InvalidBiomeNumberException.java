package exceptions;

public class InvalidBiomeNumberException extends Exception{
	public InvalidBiomeNumberException(int number) {
		super("The number "+number+" is not a valid biome number.");
	}
}
