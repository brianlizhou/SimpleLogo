package communication;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * An AnimationEvent is used to communicate when an animation starts running (Animation.START)
 * or when an animation ends (Animation.END)
 * @author Ryan Bergamini
 */
public class AnimationEvent extends Event
{
	/**
	 * Signals when an animation ends
	 */
	public static final EventType<AnimationEvent> END = new EventType<>("END");
	/**
	 * Signals when an animation begins
	 */
	public static final EventType<AnimationEvent> START = new EventType<>("START");

	/**
	 * Creates a new RunEvent
	 * @param eventType- type of event
	 */
	public AnimationEvent(EventType<? extends Event> eventType) 
	{
		super(eventType);
	}
	

}

