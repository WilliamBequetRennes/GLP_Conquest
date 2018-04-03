package exceptions;

public class UnitException extends Exception{
	public UnitException(int type) {
		System.out.println("Oops... your unit lost her way and can't find the battlefield. Type "+type+" doesn't exists.");
	}
}
