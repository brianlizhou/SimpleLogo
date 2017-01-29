package commands;

import model.Data;
import java.util.ArrayList;
import java.util.LinkedList;

import model.Turtle;
/**
 * Forward is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class Forward extends TurtleOneOpCommand{

	public Forward(Data data) {
		super(data);
	}
	
	@Override
	public double executeOnTurtle(Turtle turtle) {
		double dist = getArgDouble();
		double thetaRadians = Math.toRadians(turtle.getHeading());
		double deltax = Math.sin(thetaRadians) * dist; //cosine and sine are flipped b/c javafx doesn't follow normal unit circle
		double deltay = Math.cos(thetaRadians) * dist;
		//do we want this to be ints or doubles?
		turtle.setTo(turtle.getxPos() + deltax,turtle.getyPos() + deltay);
		return dist;
	}
	
}
