package commands;

import model.Data;
/**
 * Remainder is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class Remainder extends TwoArgumentsCommand{
	
	public Remainder(Data data) {
		super(data);
	}
	@Override
	public double execute() {
		Double arg1 = getArg1Double();
		Double arg2 = getArg2Double();
		return arg1 % arg2;
	}

}
