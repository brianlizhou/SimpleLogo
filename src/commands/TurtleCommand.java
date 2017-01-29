package commands;

import java.util.function.ToDoubleFunction;

import model.Data;
import model.Turtle;

public abstract class TurtleCommand extends Command{

	public TurtleCommand(Data data) {
		super(data);
	}
	
	@Override
	public double execute() throws Exception {
		return myTurtleManager.executeOnAllActive(this::executeOnTurtle);
	}
	
	
	public abstract double executeOnTurtle(Turtle turtle) throws RuntimeException;

}
