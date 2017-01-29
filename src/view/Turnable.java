package view;

/**
 * A Turnable object can be have its turn animated by the TurnAnimationHandler class
 * @author Ryan Bergamini
 */
public interface Turnable 
{
	/**
	 * Turns the turnable object by turnRadius
	 * @param turnRadius- distance of the turn
	 */
	public void turn(double turnRadius);
}
