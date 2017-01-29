package commands;

import model.Data;
import model.Turtle;

public class SetPenColor extends TurtleOneOpCommand{

	public SetPenColor(Data data) {
		super(data);
	}

	@Override
	public double executeOnTurtle(Turtle turtle) throws RuntimeException{
		int colorIndex = doubleToInt(getArgDouble());
		if (!myData.getColorMap().containsKey(colorIndex)) {
			throw new RuntimeException("Index " + colorIndex + " has not been added to the palette");
		}
		turtle.setPenColor(colorIndex);
		return getArgDouble();
	}

}
