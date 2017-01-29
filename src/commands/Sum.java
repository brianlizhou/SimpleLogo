package commands;

import model.Turtle;
import model.Data;

public class Sum extends InfiniteParameter{
	
	public Sum(Data data) {
		super(data);
		this.runningValue = 0;
	}
	
	
	@Override
	public void setNextArg(String argValue) {
		this.runningValue += Double.parseDouble(argValue);
	}
	

	

}
