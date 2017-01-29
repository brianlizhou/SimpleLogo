package commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import communication.Packet;
import model.Data;
import model.Parser;

public class If extends Command {

	public If (Data data) {
		super(data);
		myArgs = new ArrayList<String>();
		myArgTypes = new LinkedList<String>();
		myArgTypes.add("Constant");
		myArgTypes.add("List");	
	}


	@Override
	public double execute() throws Exception {
		Parser currentParser = new Parser(myData);
		// try catch here
		
		double booleanValue = 0;
		try{
			booleanValue = Double.parseDouble(myArgs.get(0));
		} catch(Exception e){
			throw new Exception("first argument cannot be parsed as a double");
		}

		//System.out.println("inside list: " + myArgs.get(1));

		String[] curCommand = {myArgs.get(1)};
		currentParser.receiveCommand(curCommand);

		List<Packet> resultPacket = new ArrayList<Packet>();

		if(booleanValue == 0){
			resultPacket = currentParser.run();
			return resultPacket.get(resultPacket.size() - 1).getReturnValue();
			//System.out.println("resultPacket i: " + i);
		}

		else{
			return 0;
		}
	}
}
