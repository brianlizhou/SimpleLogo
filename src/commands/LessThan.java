package commands;

import model.Data;
/**
 * LessThan is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class LessThan extends TwoArgumentsCommand{

	public LessThan(Data data) {
		super(data);
	}

	@Override
	public double execute() throws Exception {
		return booleanToDouble(getArg1Double() < getArg2Double());
	}

}
