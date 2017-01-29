package commands;

import model.Turtle;
import model.Data;
/**
 * NaturalLog is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class NaturalLog extends OneArgumentCommand{
	
	public NaturalLog(Data data) {
		super(data);
	}

	@Override
	public double execute() throws Exception {
		return Math.log(getArgDouble());
	}

}
