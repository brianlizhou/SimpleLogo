package commands;
import model.Data;
import java.util.ArrayList;
import java.util.LinkedList;

import model.Turtle;

public abstract class InfiniteParameter extends Command {

	protected double runningValue;
	
	public InfiniteParameter(Data data){
		super(data);
		myArgs = new ArrayList<String>();
		myArgTypes = new LinkedList<String>();
		}
	
	//public setRunningValue
	
	@Override
	public String getNextArgType() {
		return "Constant";
	}
	
	@Override
	public Boolean hasNextArg(){	
		return true;
	}

	@Override
	public double execute() {
		return this.runningValue;
	}
	


}





