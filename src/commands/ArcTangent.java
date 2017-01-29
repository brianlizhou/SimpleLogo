package commands;

import model.Turtle;
import model.Data;

/**
 * ArcTangent is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class ArcTangent extends OneArgumentCommand{
	
	public ArcTangent(Data data) {
		super(data);
	}

	@Override
	public double execute() {
		return Math.atan(Math.toRadians(getArgDouble()));
	}

}
