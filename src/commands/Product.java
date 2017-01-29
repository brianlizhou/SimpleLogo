package commands;

import model.Data;
/**
 * Product is a command object called by the user and instantiated by a Parser object
 * @author Aaron Chang
 *
 */
public class Product extends InfiniteParameter{
	
	public Product(Data data) {
		super(data);
		this.runningValue = 1;

	}

	@Override
	public void setNextArg(String argValue) {
		this.runningValue *= Double.parseDouble(argValue);
	}
	

	
}
