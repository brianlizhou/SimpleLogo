package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import model.Data;

import model.Turtle;
import model.TurtleManager;
/**
 * Command objects are called by the user and instantiated by Parser objects
 * Parser objects pass in arguments (read from user input) to the Command object
 * Once all of the arguments are assigned, the execute() method is called and runs the command
 * Commands assume that Parser objects will pass valid values into the arguments
 * Commands can manipulate Data attributes such as the TurtleManager
 * @author Aaron Chang
 *
 */
public abstract class Command {
	protected List<String> myArgs;
	protected Queue<String> myArgTypes;
	//protected int currentIndex; don't need this after refactoring
	protected Data myData; // change back to non static after controller implemented
	protected TurtleManager myTurtleManager;
	
	public Command(Data data) {
		//currentIndex = 0; 
		myData = data;
		myTurtleManager = myData.getTurtleManager();
		
		//initialize myArgTypes here to avoid the null pointer exception in hasNextArg
		//remove initialization in the sub class and get rid of hasArg methods
		
	}
	
	protected double booleanToDouble(boolean boolValue) {
		return (boolValue) ? 1:0;
	}

	/**
	 * returns the type of the next argument
	 * types are the keys of the Syntax.properties file
	 * used by Parser to check if it can assign the next input string to the Command's next argument
	 * @return String - argument type
	 */
	public String getNextArgType() {
		return myArgTypes.poll();
	}
	
	
	/**
	 * by checking if a command has arg
	 * avoid hasNextArg getting an error of null pointer exception
	 * @return if the command requires argument
	 */
	public Boolean hasArg(){
		return myArgTypes == null;
	}
	
	/**
	 * check if there are more unassigned argument
	 * @return Boolean - has more arguments
	 */
	public Boolean hasNextArg(){	
		return !myArgTypes.isEmpty();
	}
	
	/**
	 * sets the next argument to be the specified value
	 * called by Parser.java
	 * assumes that the input value is a valid argument of the correct Type (e.g., constant, variable, etc.)
	 * The Parser only passes in valid input values (by calling the getNextArgType method)
	 * @param String argValue - value of the next argument
	 */
	public void setNextArg(String argValue) {
		myArgs.add(argValue);		
	}
	
	protected int doubleToInt(double dubVal) {
		Long longVal = Math.round(dubVal);
		return longVal.intValue();
	}
	
	/**
	 * takes a String in the form "[ 1 2 3 ]" and adds each corresponding turtle to to the turtleManager
	 * @return List<Integer> - String of indices converted to a List of integers
	 */
	protected List<Integer> addTurtlesToManager(String listString) {
		String[] indexArray = listString.substring(1, listString.length()-1).trim().split(" ");
		ArrayList<Integer> intIndices = new ArrayList<Integer>();
		for (String i : indexArray) {
			int index = Integer.parseInt(i);
			intIndices.add(index);
			if(!myTurtleManager.getAllTurtles().containsKey(index)) {
				myTurtleManager.addTurtle(index);
			}
		}
		return intIndices;
	}
	
	/**
	 * executes the command and returns the correct value
	 * @return double - return value specified in assignment write-up
	 */
	public abstract double execute() throws Exception;
}
