package exceptions;

public class LeaderException extends Exception {
	public LeaderException(int leader) {
		System.out.println("Can't find saved Leader n°"+leader+" ... Hope he's still alive");
	}
}
