package commands;

import model.Data;

public class Turtles extends Command{

	public Turtles(Data data) {
		super(data);
	}

	@Override
	public double execute() throws Exception {
		return myTurtleManager.getAllTurtles().size();
	}

}
