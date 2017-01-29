package commands;

import java.util.ArrayList;
import java.util.LinkedList;

import model.Data;

public class MakeVariable extends Command{


	public MakeVariable(Data data){
		super(data);
		
		//System.out.println("======making variable=========");
		myArgs = new ArrayList<String>();
		myArgTypes = new LinkedList<String>();
		myArgTypes.add("Variable");
		myArgTypes.add("Constant");
	}
	
	
	@Override
	public double execute() throws Exception {
		//System.out.println("value: " + myArgs.get(1) );

		try{

			double valueOfExpression = Double.parseDouble(myArgs.get(1));
			//System.out.println("variable name : " +  myArgs.get(0));

			myData.setVariableValue(myArgs.get(0), valueOfExpression);
			//System.out.println("get variable: in command class " + myData.getVariableValue(myArgs.get(0)));
			return valueOfExpression;

	
		}
		catch(Exception e){
			throw new Exception("Expression value cannot be parsed");
		}
		
	
	}



}
