package commands;

import model.Data;
import model.Turtle;

public class SetShape extends TurtleOneOpCommand{

	public SetShape(Data data) {
		super(data);
	}

	@Override
	public double executeOnTurtle(Turtle turtle) throws RuntimeException {
		int shapeIndex = doubleToInt(getArgDouble());
		if (!myData.getTurtleImageMap().containsKey(shapeIndex)) {
			throw new RuntimeException("Shape does not exist");
		}
		turtle.setShape(shapeIndex);
		return getArgDouble();
	}

}
