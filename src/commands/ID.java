package commands;

import model.Data;
import model.Turtle;
/**
 * ID is a command object called by the user and instantiated by a Parser object
 * it is a query, so it is only executed on the active turtle
 * @author Aaron Chang
 *
 */
public class ID extends Command{

	public ID(Data data) {
		super(data);
	}

	@Override
	public double execute() {
		return myTurtleManager.getActiveTurtle().getID();
	}
	
}
