package commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import communication.Packet;
import model.Data;
import model.Parser;

public class For extends Command {

	public For (Data data) {
		super(data);
		myArgs = new ArrayList<String>();
		myArgTypes = new LinkedList<String>();
		myArgTypes.add("List");
		myArgTypes.add("List");	
	}


	@Override
	public double execute() throws Exception {
		Parser currentParser = new Parser(myData);
		// try catch here
		String[] conditions= myArgs.get(0).split("\\s+");
		double start = 0;
		double end = 0;
		double increment = 0;
		try{
			start = Double.parseDouble(conditions[2]);
			end = Double.parseDouble(conditions[3]);
			increment = Double.parseDouble(conditions[4]);
		} catch(Exception e){
			throw new Exception("first argument cannot be parsed as a double");
		}

		//System.out.println("inside list: " + myArgs.get(1));

		String[] curCommand = {myArgs.get(1)};
		currentParser.receiveCommand(curCommand);

		List<Packet> resultPacket = new ArrayList<Packet>();

		for(double i = start; i < end; i+= increment){
			resultPacket = currentParser.run();
			//System.out.println("resultPacket i: " + i);

		}

		try{
			//System.out.println("return value is : " + resultPacket.get(resultPacket.size() - 1).getReturnValue());
			return resultPacket.get(resultPacket.size() - 1).getReturnValue();
		}catch(Exception e){
			throw new Exception("do not have a return value");
		}
	}
}


