package commands;

import model.Data;
import model.Turtle;
/**
 * Left is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class Left extends TurtleOneOpCommand{

	public Left(Data data) {
		super(data);
	}

	@Override
	public double executeOnTurtle(Turtle turtle){
		double degrees = getArgDouble();
		//break up the turn into increments of less than 180
		while (degrees >= 180) {
			turtle.setHeading(turtle.getHeading()-179);
			degrees -= 179;
		}
		double newHeading = turtle.getHeading() - degrees; //subtraction b/c moving counterclockwise
		turtle.setHeading(newHeading);
		return degrees;
	}

}
