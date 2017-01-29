package commands;

import model.Data;
/**
 * And is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class And extends TwoArgumentsCommand{

	public And(Data data) {
		super(data);
	}

	@Override
	public double execute() throws Exception {
		return booleanToDouble(getArg1Double() != 0
				&& getArg2Double() != 0);
	}

}
