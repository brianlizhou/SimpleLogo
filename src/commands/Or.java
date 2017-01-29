package commands;

import model.Data;
/**
 * Or is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class Or extends TwoArgumentsCommand{

	public Or(Data data) {
		super(data);
	}

	@Override
	public double execute() throws Exception {
		return booleanToDouble(getArg1Double() != 0
				|| getArg2Double() != 0);
	}

}
