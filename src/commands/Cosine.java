package commands;

import model.Data;
import model.Turtle;
/**
 * Cosine is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class Cosine extends OneArgumentCommand{
	
	public Cosine(Data data) {
		super(data);
	}

	@Override
	public double execute() {
		return Math.cos(Math.toRadians(getArgDouble()));
	}
	
	

}
