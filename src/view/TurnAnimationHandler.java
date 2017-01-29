package view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * The TurnAnimationHandler handles the animation of a turn for a
 * Turnable object
 * @author Ryan Bergamini
 */
public class TurnAnimationHandler 
{
	private Turnable turnable;
	
	private double currentHeading;
	private double headingStep;
	private double endHeading;
	
	private static final double UNIT_TURN = 1.0;
	
	/**
	 * Creates a new turn animation
	 * @param turnable
	 */
	public TurnAnimationHandler(Turnable turnable)
	{
		this.turnable = turnable;
		this.currentHeading = 0;
		this.endHeading = 0;
		this.headingStep = 0;
	}
	
	/**
	 * Animates the turn
	 * @param durationPerFrameInMillis - the duration of millis of each frame
	 * @return
	 */
	public Animation animateTurn(double durationPerFrameInMillis)
	{
		Timeline action = new Timeline();
		KeyFrame turn = new KeyFrame(Duration.millis(durationPerFrameInMillis),
        		e -> turn());
        action.getKeyFrames().add(turn);
        action.setCycleCount(calculateFramesForMove());
        return action;
	}
	
	/**
	 * Defines the turn degrees the animation will run for
	 * @param turnDiff
	 */
	public void setTurnBy(double turnDiff)
	{
		endHeading = turnDiff;
		currentHeading = 0;
		calculateHeadingStep(turnDiff);
	}
	
	private void calculateHeadingStep(double turnDiff)
	{
		headingStep = (turnDiff / calculateFramesForMove()) * UNIT_TURN;
	}
	
	private int calculateFramesForMove()
	{
		return (int)(Math.abs(endHeading/UNIT_TURN)) + 1; // The +1 accounts for the truncating error when going from double to in
	}
	
	private boolean isInRange(double a, double b, double error)
	{
		return a >= (b - error) && a <= (b + error);
	}
	
	private void turn(double turnDiff)
	{
		currentHeading += turnDiff;
		turnable.turn(turnDiff);
	}
	
	private void turn()
	{
		if(!isInRange(currentHeading,endHeading,1.0))
		{
			turn(headingStep);
		}
		else
		{
			turn(endHeading-currentHeading);
		}
	}
	
	
}
