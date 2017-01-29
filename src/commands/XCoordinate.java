package commands;

import model.Data;
import model.Turtle;

public class XCoordinate extends Command{

	public XCoordinate(Data data) {
		super(data);
	}

	@Override
	public double execute() {
		return myTurtleManager.getActiveTurtle().getxPos();
	}
	
}
