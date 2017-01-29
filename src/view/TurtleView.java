package view;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import communication.Coordinate;
import communication.AnimationEvent;
import javafx.util.Duration;
import javafx.scene.paint.Color;

/**
 * Defines how a turtle will be displayed in the SLogoSimulation
 * @author golf70797
 *
 */
public class TurtleView implements Viewable, Moveable, Turnable
{
	private static final String TURTLE_IMAGE = "turtle_image.png";
	private LineCanvasView lineCanvas;
	
	private SimpleImage turtleImage;
	
	private final int TURTLE_NUM;
	private boolean isPenDown;
	
	private AnimationQueue animationQueue;

	/**1
	 * Creates a new TurtleView
	 * @param turtleNum
	 */
	public TurtleView(int turtleNum)
	{
		TURTLE_NUM = turtleNum;
		isPenDown = true;
		animationQueue = new AnimationQueue();
		configureTurtleViewImage();
	}
	
	/**
	 * 
	 * @param lineCanvas
	 */
	public void addLineCanvas(LineCanvasView lineCanvas)
	{
		this.lineCanvas = lineCanvas;
		lineCanvas.startNewLine(TURTLE_NUM);
	}
	
	/**
	 * Centers the Turtle Node at (x,y)
	 * @param x- x coordinate
	 * @param y- y coordinate
	 */
	public void positionNode(int x, int y)
	{
		turtleImage.getNode().setLayoutX(x - (turtleImage.getWidth()/2));
		turtleImage.getNode().setLayoutY(y - (turtleImage.getHeight()/2));
	}
	
	/**
	 * Returns the Node that represents the view of the turtle Model
	 */
	public Node getNode()
	{
		return turtleImage.getNode();
	}
	
	/**
	 * Turns the image by turnDiff
`	 */
	public void turn(double turnDiff)
	{
		turtleImage.turn(turnDiff);
	}
	
	/**
	 * Animates the move of the TurtleView
	 * @param xDistance
	 * @param yDistance
	 */
	public void animateMove(double xDistance, double yDistance)
	{
		MoveAnimationHandler ah = new MoveAnimationHandler(this);
		ah.setMoveBy(xDistance, yDistance);
		double frameDurationInMillis = 2;
		signalBeginningOfAnimation();
		Timeline drawAnimation = makeDrawAnimation(frameDurationInMillis,ah.calculateFramesForMove());
		ParallelTransition pt = new ParallelTransition(ah.animateMove(frameDurationInMillis),drawAnimation);
		pt.setOnFinished(e -> signalEndOfAnimation());
		animationQueue.addAnimation(pt);
	}
	

	/**
	 * Animates the turn of the TurtleView
	 * @param turnDistance
	 */
	public void animateTurn(double turnDistance)
	{
		TurnAnimationHandler ah = new TurnAnimationHandler(this);
		ah.setTurnBy(turnDistance);
		double frameDurationInMillis = 2;
		signalBeginningOfAnimation();
		Animation turn = ah.animateTurn(frameDurationInMillis);
		turn.setOnFinished(e -> signalEndOfAnimation());
		animationQueue.addAnimation(turn);
		
	}
	
	/**
	 * Updates the heading to newVal
	 * @param oldVal
	 * @param newVal
	 */
	public void updateHeading(double oldVal, double newVal) {
		animateTurn(calculateTurnDistance(oldVal,newVal));
	}
	
	/**
	 * Updates the position value
	 * @param oldVal
	 * @param newVal
	 */
	public void updatePosition(Coordinate oldVal, Coordinate newVal) {
		double xDistance = (newVal.getX()-oldVal.getX());// This is the same to convert from SLogo to JavaFX coordinates
		double yDistance = -(newVal.getY()-oldVal.getY());// This is negative to convert from SLogo to JavaFX coordinates
		animateMove(xDistance,yDistance);
	}
	
	/**
	 * Updates the penDown value
	 * @param oldVal
	 * @param newVal
	 */
	public void updatePenUpDown(boolean oldVal, boolean newVal) {
		isPenDown = newVal;
		
		if(isPenDown == newVal)
		{
			lineCanvas.startNewLine(TURTLE_NUM);
		}
	}
	
	/**
	 * Updates the visibility of the TurtleView
	 * @param oldVal
	 * @param newVal
	 */
	public void updateVisibility(boolean oldVal, boolean newVal) {
		turtleImage.setVisible(newVal);
	}	
	
	/**
	 * This moves the TurtleView in the x direction by xDistS
	 */
	public void moveX(double xDist)
	{
		double potentialX = turtleImage.getCenterX() + xDist;
		if(potentialX < 0)
		{
			turtleImage.setCenterX(lineCanvas.getWidth());
			lineCanvas.startNewLine(TURTLE_NUM);
		}
		else if (potentialX > lineCanvas.getWidth())
		{
			turtleImage.setCenterX(0);
			lineCanvas.startNewLine(TURTLE_NUM);
		}
		else
		{
			turtleImage.setCenterX(potentialX);
		}
	}
	
	/**
	 * This moves the TurtleView in the y direction by yDist
	 */
	public void moveY(double yDist)
	{
		double potentialY = turtleImage.getCenterY() + yDist;
		if(potentialY < 0)
		{
			turtleImage.setCenterY(lineCanvas.getHeight());
			lineCanvas.startNewLine(TURTLE_NUM);
		}
		else if (potentialY > lineCanvas.getHeight())
		{
			turtleImage.setCenterY(0);
			lineCanvas.startNewLine(TURTLE_NUM);
		}
		else
		{
			turtleImage.setCenterY(potentialY);
		}
	}
	
	/**
	 * Sets the color of the pen to newColor
	 * @param newColor
	 */
	public void updatePenColor(Color newColor) {
		lineCanvas.setPenColor(newColor,TURTLE_NUM);
	}
	
	/**
	 * Sets the width of teh pen to pixels
	 * @param pixels
	 */
	public void updatePenSize(double pixels) {
		lineCanvas.setPenSize(pixels,TURTLE_NUM);
	}
	
	/**
	 * Sets the current image of the TurtleView to image
	 * @param image
	 */
	public void updateTurtleViewImage(SimpleImage image) {
		double heading = turtleImage.getNode().getRotate();
		turtleImage = image;
		turtleImage.getNode().setRotate(heading);
	}
	
	/**
	 * Creates a new Timeline that will animate the line drawing
	 * @param frameDurationInMillis
	 * @param cycles
	 * @return
	 */
	private Timeline makeDrawAnimation(double frameDurationInMillis, int cycles)
	{
		Timeline da = new Timeline();
		da.getKeyFrames().add(new KeyFrame(Duration.millis(frameDurationInMillis/2.0), e -> drawLine()));
		da.setCycleCount(2*cycles);
		return da;
	}
	
	private void drawLine()
	{
		if(isPenDown)
		{
			lineCanvas.drawLine(turtleImage.getCenterX(), turtleImage.getCenterY(), TURTLE_NUM);
		}
	}
	
	private void signalBeginningOfAnimation()
	{
		turtleImage.getNode().fireEvent(new AnimationEvent(AnimationEvent.START));
	}
	
	private void signalEndOfAnimation()
	{
		turtleImage.getNode().fireEvent(new AnimationEvent(AnimationEvent.END));
	}

	private void configureTurtleViewImage()
	{
		Image turtle = new Image(getClass().getClassLoader().
				getResourceAsStream(TURTLE_IMAGE));
		ImageView turtleView = new ImageView(turtle);
		turtleImage = new SimpleImage(turtleView,turtle.getWidth(),turtle.getHeight());
	}
	
	private double calculateTurnDistance(double oldVal, double newVal)
	{
		double turnDistance = newVal - oldVal;
		
		if(turnDistance > 180)
		{
			turnDistance -= 360;
		}
		
		if(turnDistance < -180)
		{
			turnDistance += 360;
		}

		return turnDistance;
	}
}
