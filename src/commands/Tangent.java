package commands;

import model.Turtle;
import model.Data;

public class Tangent extends OneArgumentCommand{
	
	public Tangent(Data data) {
		super(data);
	}

	@Override
	public double execute() {
		return Math.tan(Math.toRadians(getArgDouble()));
	}

}
