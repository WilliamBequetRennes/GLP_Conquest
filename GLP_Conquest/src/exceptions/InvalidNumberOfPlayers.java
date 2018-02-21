package exceptions;

public class InvalidNumberOfPlayers extends Exception{
	public InvalidNumberOfPlayers(int number) {
		super("The number "+number+" is not valid as number of players.");
	}
}
