package commands;

import java.util.ArrayList;
import java.util.LinkedList;

import model.Data;

public class Function1 extends Command{

	public Function1(Data data) {
		super(data);
		myArgs = new ArrayList<String>();
		myArgTypes = new LinkedList<String>();
		
		myArgTypes.add("Constant");
		myArgTypes.add("Constant");
		myArgTypes.add("Constant");

	}

	@Override
	public double execute() throws Exception {
		try{
		return Double.parseDouble(myArgs.get(0)) + Double.parseDouble(myArgs.get(1)) - Double.parseDouble(myArgs.get(2));
		}catch(Exception e){
			throw new Exception("cannot parse the argument's value");
		}
		
	}
	
	

}
