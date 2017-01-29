package commands;

import model.Data;
import model.Turtle;
/**
 * PenUp is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class PenUp extends TurtleCommand{

	public PenUp(Data data) {
		super(data);
	}

	@Override
	public double executeOnTurtle(Turtle turtle) {
		turtle.setPenDown(false);
		return 0;
	}

}
