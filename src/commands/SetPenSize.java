package commands;

import model.Data;
import model.Turtle;

public class SetPenSize extends TurtleOneOpCommand{

	public SetPenSize(Data data) {
		super(data);
	}

	@Override
	public double executeOnTurtle(Turtle turtle) {
		double pixels = getArgDouble();
		turtle.setPenSize(doubleToInt(pixels));
		return pixels;
	}

}
