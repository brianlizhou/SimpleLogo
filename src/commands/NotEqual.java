package commands;

import model.Data;
/**
 * NotEqual is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class NotEqual extends TwoArgumentsCommand{

	public NotEqual(Data data) {
		super(data);
	}

	@Override
	public double execute() throws Exception {
		return booleanToDouble(getArg1Double() != getArg2Double());
	}

}
