package commands;

import model.Data;
import model.Turtle;
/**
 * Home is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class Home extends TurtleCommand{

	public Home(Data data) {
		super(data);
	}

	@Override
	public double executeOnTurtle(Turtle turtle) {
		turtle.setTo(0,0);
		double dist = Math.sqrt(Math.pow(turtle.getxPos(), 2) + Math.pow(turtle.getyPos(), 2));
		return dist;
	}
	
}
