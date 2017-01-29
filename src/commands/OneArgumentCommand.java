package commands;

import model.Data;
import java.util.ArrayList;
import java.util.LinkedList;
import model.Turtle;

/**
 * OneArgumentCommands are Command objects that only take one argument, which is a Constant
 * @author Aaron Chang
 *
 */
public abstract class OneArgumentCommand extends Command{
	public static final String ARG1_TYPE = "Constant";
	protected String myArg;
	
	
	// check if pass turtle as a parameter for this constructor
	public OneArgumentCommand(Data data) {
		super(data);
		myArgs = new ArrayList<String>();
		myArgTypes = new LinkedList<String>();
		myArgTypes.add(ARG1_TYPE);
	}
	
	public double getArgDouble() {
		return Double.parseDouble(myArgs.get(0));
	}
}
