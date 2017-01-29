package commands;

import model.Data;
import model.Turtle;
/**
 * IsShowing is a command object called by the user and instantiated by a Parser object
 * it is a query so it is only executed on the active turtle
 * @author Aaron Chang
 *
 */
public class IsShowing extends Command{

	public IsShowing(Data data) {
		super(data);
	}

	@Override
	public double execute() {
		return (myTurtleManager.getActiveTurtle().getVisibility()) ? 1:0;
	}

}
