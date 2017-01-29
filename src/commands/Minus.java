package commands;

import model.Turtle;
import model.Data;
/**
 * Minus is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class Minus extends OneArgumentCommand{
	
	public Minus(Data data) {
		super(data);
	}

	@Override
	public double execute() {
		return -1 * getArgDouble();
	}
}
