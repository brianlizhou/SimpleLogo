package communication;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * A RunEvent is fired when the Run button in the command is pressed. The SLogoController
 * listens for RunEvents and loads the User Input from the view when it hears a RunEvent.
 * @author Ryan Bergamini
 */
public class RunEvent extends Event
{
	/**
	 * This event is typically triggered 
	 */
	public static final EventType<RunEvent> RUN = new EventType<>("RUN");
	private String userInput;
	
	/**
	 * Creates a new RunEvent
	 * @param eventType- type of event
	 */
	public RunEvent(EventType<? extends Event> eventType, String userInput) 
	{
		super(eventType);
		this.userInput = userInput;
	}
	
	/**
	 * Called by the SLogoController class to getUserInput()
	 * @return the user Input
	 */
	public String getUserInput()
	{
		return userInput;
	}

}
