package commands;

import model.Data;
/**
 * GetShape is a command object called by the user and instantiated by a Parser object
 * it is a query, so it only returns the value for the active turtle
 * @author Aaron Chang
 *
 */
public class GetShape extends Command{

	public GetShape(Data data) {
		super(data);
	}

	@Override
	public double execute() throws Exception {
		return myTurtleManager.getActiveTurtle().getShapeProperty().doubleValue();
	}

}
