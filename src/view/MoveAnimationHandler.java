package view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

public class MoveAnimationHandler 
{
	private Moveable moveable;
	private double endX;
	private double endY;
	
	private double currentX;
	private double currentY;
	
	private double xStep;
	private double yStep;
	
	private static final double UNIT_STEP = 1.0;
	
	
	public MoveAnimationHandler(Moveable moveable)
	{
		this.moveable = moveable;
		xStep = 1.0;
		yStep = 1.0;
		currentX = 0;
		currentY = 0;
		endX = 0;
		endY = 0;
	}
	
	public void setMoveBy(double xDistance, double yDistance)
	{
		endX = xDistance;
		endY = yDistance;
		currentX = 0;
		currentY = 0;
		calculateStep(xDistance,yDistance);
	}
	
	public void move()
	{
		if(!isInRange(currentX,endX,1.0))
		{
			moveX(xStep);
		}
		else
		{
			moveX(endX-currentX);
		}
		
		if(!isInRange(currentY,endY,1.0))
		{
			moveY(yStep);
		}
		else
		{
			moveY(endY-currentY);
		}
		
	}
	
	public Animation animateMove(double durationPerFrameInMillis)
	{
		Timeline action = new Timeline();
		KeyFrame move = new KeyFrame(Duration.millis(durationPerFrameInMillis),
        		e -> move());
        action.getKeyFrames().add(move);
        action.setCycleCount(calculateFramesForMove());
        return action;
	}
	
	private boolean isInRange(double a, double b, double error)
	{
		return a >= (b - error) && a <= (b + error);
	}
	
	private void calculateStep(double xDistance, double yDistance)
	{
		double distance = Math.sqrt(endX*endX + endY*endY);
		xStep =  (xDistance / distance) * UNIT_STEP; 
		yStep =  (yDistance / distance) * UNIT_STEP; 
	}
	
	
	public int calculateFramesForMove()
	{
		double distance = Math.sqrt(endX*endX + endY*endY);
		
		return (int)(distance/UNIT_STEP) + 1; // The +1 accounts for the truncating error when going from double to int
		
	}
	
	private void moveX(double xDist)
	{
		currentX += xDist;
		moveable.moveX(xDist);
	}
	
	private void moveY(double yDist)
	{
		currentY += yDist;
		moveable.moveY(yDist);
	}
}
