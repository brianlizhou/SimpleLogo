package commands;

import model.Data;
/**
 * Not is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class Not extends OneArgumentCommand{

	public Not(Data data) {
		super(data);
	}

	@Override
	public double execute() throws Exception {
		return booleanToDouble(getArgDouble() == 0);
	}

}
