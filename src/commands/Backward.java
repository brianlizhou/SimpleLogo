package commands;

import model.Data;
import model.Turtle;
/**
 * Backward is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class Backward extends TurtleOneOpCommand{

	public Backward(Data data) {
		super(data);
	}

	@Override
	public double executeOnTurtle(Turtle turtle) {
		double dist = getArgDouble();
		double thetaRadians = Math.toRadians(turtle.getHeading());
		//same as forward, but reversed
		double deltax = -1 * Math.sin(thetaRadians) * dist; //cosine and sine are flipped b/c javafx doesn't follow normal unit circle
		double deltay = -1 * Math.cos(thetaRadians) * dist;
		//do we want this to be ints or doubles?
		turtle.setTo(turtle.getxPos() + deltax,turtle.getyPos() + deltay);
		return dist;
	}

}
