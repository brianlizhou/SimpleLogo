package commands;

import model.Data;
import model.Turtle;

public class SetPosition extends TurtleTwoOpCommand{

	public SetPosition(Data data) {
		super(data);
	}

	@Override
	public double executeOnTurtle(Turtle turtle){
		double deltaX = turtle.getxPos() - getArg1Double();
		double deltaY = turtle.getyPos() - getArg2Double();
		turtle.setTo(getArg1Double(),getArg2Double());
		double distTraveled = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
		return distTraveled;
	}

}
