package commands;

import model.Data;
/**
 * GetPenColor is a command object called by the user and instantiated by a Parser object
 * it is a query, so it is only executed on the active turtle
 * @author Aaron Chang
 *
 */
public class GetPenColor extends Command{

	public GetPenColor(Data data) {
		super(data);
	}

	@Override
	public double execute() throws Exception {
		return myTurtleManager.getActiveTurtle().getPenColor().doubleValue();
	}

}
