package commands;

import model.Data;
import model.Turtle;

public class Right extends TurtleOneOpCommand{

	public Right(Data data) {
		super(data);
	}

	@Override
	public double executeOnTurtle(Turtle turtle) {
		double degrees = getArgDouble();
		//break up the turn into small increments
		while (degrees >= 180) {
			turtle.setHeading(turtle.getHeading()+179);
			degrees -= 179;
		}
		double newHeading = turtle.getHeading() + degrees; 
		turtle.setHeading(newHeading);
		return degrees;
	}

}
