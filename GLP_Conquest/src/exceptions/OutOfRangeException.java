package exceptions;

public class OutOfRangeException extends Exception{
	private static final long serialVersionUID = 18022018L;
	
	public OutOfRangeException() {
		System.out.println("Can't reach chosen square");
	}
	
	
}
