package commands;

import model.Turtle;
import model.Data;
/**
 * Random is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class Random extends OneArgumentCommand{
	
	public Random(Data data) {
		super(data);
	}
	
	@Override
	public double execute() throws Exception {
		if (getArgDouble() <= 0) {
			throw new Exception("Argument must be greater than 0");
		}
		else{
			return Math.random() * getArgDouble();
		}
	}
	
}
