package commands;

import model.Data;
import model.Turtle;
/**
 * TurtleTwoOpCommand objects are a specific type of TwoArgumentsCommands that are executed on turtles
 * It uses the TurtleManager to execute the command on each active turtle
 * It assumes that TurtleManager will throw an exception if the command cannot be executed
 * @author Aaron Chang
 *
 */
public abstract class TurtleTwoOpCommand extends TwoArgumentsCommand{
	
	public TurtleTwoOpCommand(Data data) {
		super(data);
	}

	@Override
	public double execute() throws Exception {
		try {
			return myTurtleManager.executeOnAllActive(this::executeOnTurtle);
		}
		catch (Exception e) {
			throw(e);
		}
	}
	
	public abstract double executeOnTurtle(Turtle turtle) throws RuntimeException;

}
