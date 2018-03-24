package exceptions;

public class LeaderException extends Exception {
	public LeaderException() {
		System.out.println("Can't find saved Leader... Hope he's still alive");
	}
}
