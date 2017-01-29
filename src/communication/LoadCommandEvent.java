
package communication;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * A LoadCommandEvent is fired by the PastCommandview or ComboBox when a previous command was selected to run.
 * The command is then loaded into the command line then waits for the run button in the commands line to run the command.
 * Similar to the structure of the RunEvent, except this command only loads a command block into the commandline, it does not run it
 * @author Ryan Bergamini
 */
public class LoadCommandEvent extends Event
{
	public static final EventType<LoadCommandEvent> PAST_COMMAND = new EventType<>("PAST_COMMAND");
	
	private String commandBlock;
	
	/**
	 * Creates a new LoadCommandEvent that contains the String of the command that needs
	 * to be loaded
	 * @param eventType- type of event
	 * @param command- the command that is being loaded
	 */
	public LoadCommandEvent(EventType<? extends Event> eventType, String command) 
	{
		super(eventType);
		commandBlock = command;
	}
	
	/**
	 * Called by the SLogoView class to load into the command line
	 * @return the commandBlock that needs to be run
	 */
	public String getCommandBlock()
	{
		return commandBlock;
	}
	
	

}