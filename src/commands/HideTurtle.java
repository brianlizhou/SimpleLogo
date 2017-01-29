package commands;

import model.Data;
import model.Turtle;
/**
 * HideTurtle is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class HideTurtle extends TurtleCommand{

	public HideTurtle(Data data) {
		super(data);
	}

	@Override
	public double executeOnTurtle(Turtle turtle)  {
		turtle.setVisibility(false);;
		return 0;
	}

}
