package commands;

import model.Data;
/**
 * Equal is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class Equal extends TwoArgumentsCommand{

	public Equal(Data data) {
		super(data);
	}

	@Override
	public double execute() throws Exception {
		return booleanToDouble(getArg1Double() == getArg2Double());
	}

}
