package commands;

import model.Data;
/**
 * Pi is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class Pi extends Command{

	public Pi(Data data) {
		super(data);
	}

	@Override
	public double execute() {
		return Math.PI;
	}
	
}
