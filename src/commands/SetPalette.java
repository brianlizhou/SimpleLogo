package commands;

import java.util.ArrayList;
import java.util.LinkedList;

import model.Data;

public class SetPalette extends Command{
	public static final String ARG_TYPE = "Constant";

	public SetPalette(Data data) {
		super(data);
		myArgs = new ArrayList<String>();
		myArgTypes = new LinkedList<String>();
		myArgTypes.add(ARG_TYPE);
		myArgTypes.add(ARG_TYPE);
		myArgTypes.add(ARG_TYPE);
		myArgTypes.add(ARG_TYPE);
	}

	@Override
	public double execute() throws Exception {
		int[] rgb = new int[3];
		for(int i = 0; i < rgb.length; i++) {
			double arg = Double.parseDouble(myArgs.get(i+1));
			if (arg < 0 || arg > 255) {
				throw new RuntimeException("RGB values must a nonnegative integer less than 256");
			}
			rgb[i] = doubleToInt(arg);
		}
		double index = Double.parseDouble(myArgs.get(0));
		myData.addColor(doubleToInt(index), rgb[0], rgb[1], rgb[2]);
		return index;
	}
	

}
