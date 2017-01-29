package view;

import java.util.List;
import java.util.ArrayList;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Turtle;

/**
 * A SLogoScreenView creates the graphical representation of the Turtle acting out
 * command on a screen
 * @author Ryan Bergamini
 */
public class SLogoScreenView implements Viewable
{
	private int width;
	private int height;
	
	private static final Color DEFAULT_BACKGROUND_COLOR = Color.GREEN;
	
	private Color backgroundColor;
	
	private Pane screen;
	private LineCanvasView lineCanvas;
	private List<TurtleView> turtleViews;
	
	private Rectangle background;
	
	/**
	 * Creates a new SLogoScreenView
	 */
	public SLogoScreenView(int width, int height)
	{
		this.width = width;
		this.height = height;
		this.turtleViews = new ArrayList<TurtleView>();
		screen = new Pane();
		screen.setMaxSize(width, height);
		configureScreen();
	}
	
	/**
	 * Adds a new TurtleView to the SLogoScreenView
	 * @param heading- bounded heading of the TurtleView
	 * @param position- bounded position of the TurtleView
	 * @param isVisible- bounded visibility of the TurtleView
	 * @param penDown- bounded isPenDown boolean of the TurtleView
	 */
	public void addTurtleView(TurtleView turtleView)
	{
		turtleViews.add(turtleView);
		configureTurtleView(turtleView);
	}
	/**
	 * Positions view (canvas that turtle moves on) on screen
	 */
	public void positionViewOnScreen(){
		screen.setLayoutY(150);
		screen.setLayoutX(SLogoView.PADDING_TOP);
	}
	
	/**
	 * Resets the SlogoScreen View
	 */
	public void reset()
	{
		lineCanvas.reset();
		screen.getChildren().clear();
		screen.getChildren().add(background);
		screen.getChildren().add(lineCanvas.getNode());
		turtleViews.clear();
	}
	
	/**
	 * Sets the background color to newColor
	 * @param newColor- color the background is being set to
	 */
	public void updateBackgroundColor(Color newColor) {
		background.setFill(newColor);
	}
	
	/**
	 * Returns the Node that represents the SlogoScreenView
	 */
	@Override
	public Node getNode()
	{
		return screen;
	}
	
	private void configureTurtleView(TurtleView turtleView)
	{
		turtleView.addLineCanvas(lineCanvas);
		screen.getChildren().add(turtleView.getNode());
		turtleView.positionNode(width/2,height/2);
	}
	
	private void configureScreen()
	{
		lineCanvas = new LineCanvasView(width,height);
		background = new Rectangle(0,0,width,height);
		background.setFill(DEFAULT_BACKGROUND_COLOR);
		screen.getChildren().add(background);
		screen.getChildren().add(lineCanvas.getNode());
	}
}
