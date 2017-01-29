package commands;

import model.Turtle;
import model.Data;
/**
 * Difference is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class Difference extends InfiniteParameter{
	
	public Difference(Data data) {
		super(data);
		this.runningValue = 0;
	}

	@Override
	public void setNextArg(String argValue) {
		this.runningValue -= Double.parseDouble(argValue);
	}
	

	
}
