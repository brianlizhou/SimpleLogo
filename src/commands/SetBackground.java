package commands;

import model.Data;

public class SetBackground extends OneArgumentCommand{

	public SetBackground(Data data) {
		super(data);
	}

	@Override
	public double execute() throws Exception {
		double d = getArgDouble();
		Long l = Math.round(d);
		int index = l.intValue();
		try {
			myData.setBackgroundColor(index);
			return d;
		}
		catch(Exception e) {
			throw(e);
		}
	}

}
