package commands;

import model.Data;
import model.Turtle;

public class ShowTurtle extends TurtleCommand{

	public ShowTurtle(Data data) {
		super(data);
	}

	@Override
	public double executeOnTurtle(Turtle turtle){
		turtle.setVisibility(true);
		return 1;
	}

}
