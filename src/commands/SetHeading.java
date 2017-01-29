package commands;

import model.Data;
import model.Turtle;

public class SetHeading extends TurtleOneOpCommand{

	public SetHeading(Data data) {
		super(data);
	}

	@Override
	public double executeOnTurtle(Turtle turtle) {
		double newHeading = getArgDouble();
		turtle.setHeading(newHeading);
		//assuming turtle always turns to the right
		double degreesMoved;
		if (newHeading > turtle.getHeading()) {
			degreesMoved = newHeading - turtle.getHeading();
		}
		else {
			degreesMoved = (360 - turtle.getHeading()) + newHeading;
		}
		return degreesMoved;
	}

}
