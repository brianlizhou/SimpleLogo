package commands;

import java.util.ArrayList;
import java.util.List;

import communication.Packet;
import model.Data;
import model.Parser;
import model.Turtle;
/**
 * 
 * @author Aaron Chang
 * AskWith is a command object called by the user and instantiated by a Parser object
 * Similar to the Ask command, checks each active turtle to see if it fits the condition
 * all active turtles that fit the condition will be used to execute the commands
 * inherits the runCommands method from the Ask command
 */
public class AskWith extends Ask{

	public AskWith(Data data) {
		super(data);
	}
	
	@Override 
	public double execute() throws Exception {
		List<Integer> oldActive = myTurtleManager.getActiveTurtlesIndices();
		List<Integer> tempActives = new ArrayList<Integer>();
		Parser askWithParser = new Parser(myData);
		
		String[] conditions = {myArgs.get(0)};
		String[] commands = {myArgs.get(1)};
		
		//check if turtles fit condition
		for (Integer pturtleID : oldActive) {
			myTurtleManager.setActiveTurtle(pturtleID);
			double isTrue = runCommands(askWithParser, conditions);
			if (!(isTrue == 0.0)) tempActives.add(pturtleID);
		}
		
		//run commands for turtles that fit condition
		myTurtleManager.setActiveTurtles(tempActives);
		double returnVal = runCommands(askWithParser, commands);
		//reset activeTUrtles
		myTurtleManager.setActiveTurtles(oldActive);
		return returnVal;
		
	}

}
