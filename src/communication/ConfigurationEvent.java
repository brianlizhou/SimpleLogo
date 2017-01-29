package communication;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * Events relating to the Configuration of the SLogo event
 * @author Ryan Bergamini
 */
public class ConfigurationEvent extends Event
{
	/**
	 * Triggered when the Reset button is hit
	 */
	public static final EventType<ConfigurationEvent> RESET = new EventType<>("RESET");

	
	/**
	 * Creates a new ConfigurationEvent
	 * @param eventType- type of event
	 */
	public ConfigurationEvent(EventType<? extends Event> eventType) 
	{
		super(eventType);
	}
	

}
