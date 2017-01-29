package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import communication.Packet;
import model.Data;
import model.Parser;
import model.Turtle;
/**
 * Ask is a command object called by the user and instantiated by a Parser object
 * It sets the active turtles to the list specified by the user and resets the active turtles after the commands are executed
 * @author Aaron Chang
 *
 */
public class Ask extends Command{
	public static final String ARG1_TYPE = "List";

	public Ask(Data data) {
		super(data);
		myArgs = new ArrayList<String>();
		myArgTypes = new LinkedList<String>();
		myArgTypes.add(ARG1_TYPE);
		myArgTypes.add(ARG1_TYPE);	
	}

	@Override
	public double execute() throws Exception {
		List<Integer> oldActive = myTurtleManager.getActiveTurtlesIndices();
		List<Integer> tempActive = addTurtlesToManager(myArgs.get(0));
		myTurtleManager.setActiveTurtles(tempActive);
		
		Parser askParser = new Parser(myData);
		String[] command = {myArgs.get(1)};
		double returnVal = runCommands(askParser, command);
		myTurtleManager.setActiveTurtles(oldActive);
		return returnVal;
}

	
	/**
	 * uses the parser object to execute the commands
	 * @param parser - a Parser object
	 * @param commands - String[] of commands to be executed
	 * @return return value of the last command executed
	 * @throws RuntimeException
	 */
	protected double runCommands(Parser parser, String[] commands) throws RuntimeException {
		List<Packet> resultPacket = new ArrayList<Packet>();
		parser.receiveCommand(commands);
		resultPacket = parser.run();
		try{
			//System.out.println("return value is : " + resultPacket.get(resultPacket.size() - 1).getReturnValue());
			return resultPacket.get(resultPacket.size() - 1).getReturnValue();
		}
		catch(Exception e){
			throw new RuntimeException("do not have a return value");
		}
	}

}
