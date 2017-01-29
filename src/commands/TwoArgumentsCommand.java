package commands;

import model.Data;
import java.util.ArrayList;
import java.util.LinkedList;

import model.Turtle;

/**
 * TwoArgumentsCommand is a subclass of Command and a superclass of Sum, Difference, Product, Quotient
 * It has a constructor and instance variables that are common to all of these commands
 * @author Aaron Chang
 *
 */
public abstract class TwoArgumentsCommand extends Command{
	
	public static final String ARG1_TYPE = "Constant";
	public static final String ARG2_TYPE = "Constant";
	
	public TwoArgumentsCommand(Data data) {
		super(data);
		myArgs = new ArrayList<String>();
		myArgTypes = new LinkedList<String>();
		myArgTypes.add(ARG1_TYPE);
		myArgTypes.add(ARG2_TYPE);
	}
	
	public double getArg1Double() {
		return Double.parseDouble(myArgs.get(0));
	}
	
	public double getArg2Double() {
		return Double.parseDouble(myArgs.get(1));
	}

}
