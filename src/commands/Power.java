package commands;

import model.Data;
/**
 * Power is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class Power extends TwoArgumentsCommand{

	public Power(Data data) {
		super(data);
	}

	@Override
	public double execute() {
		return Math.pow(getArg1Double(), getArg2Double());
	}
	
}
