package commands;

import model.Turtle;
import model.Data;

public class Sine extends OneArgumentCommand{
	
	public Sine(Data data) {
		super(data);
	}

	@Override
	public double execute() {
		return Math.sin(Math.toRadians(getArgDouble()));
	}
	
}
