package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import communication.Packet;
import model.Data;
import model.Parser;
/**
 * Repeat is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class Repeat extends Command {

	public Repeat(Data data) {
		super(data);
		myArgs = new ArrayList<String>();
		myArgTypes = new LinkedList<String>();
		myArgTypes.add("Constant");
		myArgTypes.add("List");	
	}

	
	// can factor out by creating parser in the constructor
	@Override
	public double execute() throws Exception {
		Parser repeatParser = new Parser(myData);
		// try catch here
		Double rounds = 0.0;
		try{	
			rounds	= Double.parseDouble(myArgs.get(0));
		} catch(Exception e){
			throw new Exception("first argument cannot be parsed as a double");
		}
		
		//System.out.println("inside list: " + myArgs.get(1));
		
		
		String[] repeatCommand = {myArgs.get(1)}; //repeatCommand is now ["[ rt 45 fd 20 ]"]
	
		//System.out.println("RepeatCommand: " + repeatCommand[0]);
		
		List<Packet> resultPacket = new ArrayList<Packet>();
		
		int i = 0;
		while (i < rounds){
			repeatParser.receiveCommand(repeatCommand);
			//System.out.println("RepeatCommand: " + repeatCommand[0]);

			resultPacket = repeatParser.run();
			//System.out.println("resultPacket i: " + i);
			i++;
		}
		
		try{
			//System.out.println("return value is : " + resultPacket.get(resultPacket.size() - 1).getReturnValue());
			return resultPacket.get(resultPacket.size() - 1).getReturnValue();
		}catch(Exception e){
			throw new Exception("do not have a return value");
		}
	}

}
