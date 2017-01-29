package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.Data;

public class Tell extends Command{
	public static final String ARG1_TYPE = "List";

	public Tell(Data data) {
		super(data);
		myArgs = new ArrayList<String>();
		myArgTypes = new LinkedList<String>();
		myArgTypes.add(ARG1_TYPE);
	}

	@Override
	public double execute() throws Exception {
		String listString = myArgs.get(0);
		List<Integer> intVals = addTurtlesToManager(listString);
		myTurtleManager.setActiveTurtles(intVals);
		return intVals.get(intVals.size()-1);
	}

}
