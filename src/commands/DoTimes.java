package commands;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import communication.Packet;
import model.Data;
import model.Parser;

public class DoTimes extends Command {

	public DoTimes(Data data)  {
		super(data);
		myArgs = new ArrayList<String>();
		myArgTypes = new LinkedList<String>();
		myArgTypes.add("List");
		myArgTypes.add("List");		}


	@Override
	public double execute() throws Exception {
		
		// check if needs to store the variable into data's table
		Parser repeatParser = new Parser(myData);
		//System.out.println("+++++++++++++++++++++++");
		Double rounds = 0.0;
		try{	
			//System.out.println("limit's value: " + myArgs.get(0).split("\\s+")[2]);
			rounds	= Double.parseDouble(myArgs.get(0).split("\\s+")[2]); // second should the limit's value
		} catch(Exception e){
			throw new Exception("first argument cannot be parsed as a double");
		}

		//System.out.println("inside list: " + myArgs.get(1));

		String[] repeatCommand = {myArgs.get(1)};
		repeatParser.receiveCommand(repeatCommand);

		List<Packet> resultPacket = new ArrayList<Packet>();

		int i = 0;
		while (i < rounds){
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


