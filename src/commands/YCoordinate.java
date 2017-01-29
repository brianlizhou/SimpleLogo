package commands;

import model.Data;
import model.Turtle;

public class YCoordinate extends Command{

	public YCoordinate(Data data) {
		super(data);
	}

	@Override
	public double execute() {
		return myTurtleManager.getActiveTurtle().getyPos();
	}
	
}
