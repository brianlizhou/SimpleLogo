package commands;

import model.Data;
import model.Turtle;

public class SetTowards extends TurtleTwoOpCommand{

	public SetTowards(Data data) {
		super(data);
	}

	@Override
	public double executeOnTurtle(Turtle turtle){
		double newHeading;
		if (getArg2Double() == 0) {
			if (getArg1Double() > 0) newHeading = 90;
			else newHeading = 270;
		}
		else {
			newHeading = Math.toDegrees(Math.atan2(getArg1Double(), getArg2Double()));
		}
		turtle.setHeading(newHeading);
		double degreesMoved;
		if (newHeading > turtle.getHeading()) {
			degreesMoved = newHeading - turtle.getHeading();
		}
		else {
			degreesMoved = (360 - turtle.getHeading()) + newHeading;
		}
		return degreesMoved;
	}

}
