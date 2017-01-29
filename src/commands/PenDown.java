package commands;

import model.Data;
import model.Turtle;
/**
 * PenDown is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class PenDown extends TurtleCommand{

	public PenDown(Data data) {
		super(data);
	}

	@Override
	public double executeOnTurtle(Turtle turtle){
		turtle.setPenDown(true);
		return 1;
	}

}
