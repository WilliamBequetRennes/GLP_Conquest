package exceptions;

import datas.Position;

public class AlreadyMaxLevelException extends Exception{
	public AlreadyMaxLevelException (Position position) {
		super("The square ["+position.getXPosition()+"]["+position.getYPosition()+
				"] is already at maximum level.");
	}
}
