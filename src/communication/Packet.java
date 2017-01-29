package communication;

import java.util.ArrayList;
import java.util.List;

/**
 * The Packet class contains data that needs to be passed between different parts of the application
 * @author Yumin Zhang
 */
public class Packet 
{
	
	private List<String> errorMessages;
	private String displayingCommands;
	private double returnValue;
	private String commandType;
	

	
	public Packet(){
		errorMessages = new ArrayList<String>();
		displayingCommands = "";
	}
	
	public void addErrorMessage(String message){
		errorMessages.add(message);
	}
	
	public void setDisplayingCommand(String nestedCommand){
		displayingCommands = nestedCommand;
	}
	
	
	public void setReturnValue(double returnValue){
		this.returnValue = returnValue;
	}
	
	public void setCommandType(String commandType){
		this.commandType = commandType;
	}
	
	public List<String> getErrorMessages(){
		return errorMessages;
	}
	
	public String getCommands(){
		return displayingCommands;
	}
	
	public double getReturnValue(){
		return this.returnValue;
	}
	
	public String getCommandType(){
		return this.commandType;
	}
	
}
