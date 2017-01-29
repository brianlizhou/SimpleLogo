package commands;

import java.util.ArrayList;
import java.util.LinkedList;

import model.Data;

public class MakeUserInstruction extends Command{

	public MakeUserInstruction(Data data) {
		super(data);
		myArgs = new ArrayList<String>();
		myArgTypes = new LinkedList<String>();
		
		myArgTypes.add("Command");
		myArgTypes.add("List");
		myArgTypes.add("List");

	}
	
	@Override
	public double execute() throws Exception {
	try{
		String operation = myArgs.get(1).trim();
		myData.addUserInstruction(myArgs.get(0), operation.substring(1, operation.length()-1));
		//System.out.println("operation: " + operation.substring(1, operation.length()-1));
		return 1;
	}catch(Exception e){
		return 0;
	}
	
		
	}
}
