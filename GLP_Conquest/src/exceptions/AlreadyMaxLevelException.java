package exceptions;

import datas.Position;

public class AlreadyMaxLevelException extends Exception{
	public AlreadyMaxLevelException (Position position) {
		super("The square ["+position.getJPosition()+"]["+position.getIPosition()+
				"] is already at maximum level.");
	}
}
