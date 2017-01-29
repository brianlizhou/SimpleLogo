package commands;

import model.Data;
/**
 * Quotient is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class Quotient extends TwoArgumentsCommand{
	
	public Quotient(Data data) {
		super(data);
	}
	
	@Override
	public double execute() throws Exception{
		Double arg1 = getArg1Double();
		Double arg2 = getArg2Double();
		if (arg2 == 0) {
			throw new Exception("Cannot divide by 0");
		}
		else {
			return arg1 / arg2;
		}
		
	}

}
