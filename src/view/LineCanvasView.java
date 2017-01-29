package view;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import java.util.HashMap;

import commands.For;
import communication.Coordinate;

/**
 * LineScreen creates a class that manages where the line is drawn. It also 
 * @author Ryan Bergamini
 *
 */
public class LineCanvasView implements Viewable
{
	private int width;
	private int height;
	
	private HashMap<Integer,Coordinate> previousPoints;
	private HashMap<Integer,Boolean> freshStrokes;
	private HashMap<Integer,Double> penSizes;
	private HashMap<Integer,Color> penColors;
	
	
	private final static Color DEFAULT_STROKE_COLOR = Color.GREEN;
	private Color strokeColor;
	private double penSize;
	
	
	private Canvas canvas;
	
	/**
	 * Creates a new LineCanvasView with the dimensions of width by height
	 * @param width
	 * @param height
	 */
	public LineCanvasView(int width, int height)
	{
		this.width = width;
		this.height = height;
		this.strokeColor = DEFAULT_STROKE_COLOR;
		this.penSize = 1.0;
		this.canvas = new Canvas(width,height);
		this.freshStrokes = new HashMap<Integer,Boolean>();
		this.previousPoints = new HashMap<Integer,Coordinate>();
		this.penColors = new HashMap<Integer,Color>();
		this.penSizes = new HashMap<Integer,Double>();

		canvas.getGraphicsContext2D().setFill(Color.TRANSPARENT);
		canvas.getGraphicsContext2D().fillRect(0, 0, width, height);
		canvas.getGraphicsContext2D().setLineCap(StrokeLineCap.ROUND);
	}
	
	/**
	 * @return the width of the LineCanvasView
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * @return the height of the LineCanvasView
	 */
	public int getHeight()
	{
		return height;
	}
	
	/**
	 * @return the node that represents the LineCanvasView
	 */
	public Node getNode()
	{
		return canvas;
	}
	
	/**
	 * Signals the beginning of a new like for the turtle with ID Number of turtle
	 * @param turtle- the turtle ID Number the line is for
	 */
	public void startNewLine(int turtle)
	{
		freshStrokes.put(turtle,true);
	}
	
	/**
	 * Sets the width of the pen for the desired turtle
	 * @param penSize- size of the line stroke
	 * @param turtle- the number for the turtle to set the pensize for
	 */
	public void setPenSize(double penSize, int turtle)
	{
		penSizes.put(turtle,penSize);
	}
	
	/**
	 * Sets the color of the pen for turtle with the ID Number of turtle
	 * @param penColor- color of the pen to be set
	 * @param turtle- the ID Number of the turtle to set
	 */
	public void setPenColor(Color penColor, int turtle)
	{
		penColors.put(turtle,penColor);
	}
	
	/**
	 * @param turtle- the ID Number of the turtle
	 * @return the penSize for the turtle
	 */
	public double getPenSize(int turtle)
	{
		if(!penSizes.containsKey(turtle))
		{
			setPenSize(1.0, turtle);
		}
		return penSizes.get(turtle);
	}
	
	/**
	 * @param turtle- ID of the turtle 
	 * @return the color of the pen to set
	 */
	public Color getPenColor(int turtle)
	{
		if(!penColors.containsKey(turtle))
		{
			setPenColor(DEFAULT_STROKE_COLOR, turtle);
		}
		return penColors.get(turtle);
	}
	
	/**
	 * Draws a line from x to y for the turtle of the ID number turtle
	 * @param x- x location of the new segment of the line (in JavaFx coordinates)
	 * @param y- y location of the new segment of the line (in JavaFx coordinates)
	 * @param turtle- the ID Number of the turtle the segment is adding to
	 */
	public void drawLine(double x, double y, int turtle)
	{	
		if(freshStrokes.get(turtle))
		{
			freshStrokes.put(turtle, false);
		}
		else
		{
			canvas.getGraphicsContext2D().setStroke(getPenColor(turtle));
			canvas.getGraphicsContext2D().setLineWidth(getPenSize(turtle));
			if(!previousPoints.containsKey(turtle))
			{
				previousPoints.put(turtle, new Coordinate(0,0));
			}
			Coordinate previousPoint = previousPoints.get(turtle);
			strokeLine(previousPoint.getX(),previousPoint.getY(),x,y);
		}
		previousPoints.put(turtle, new Coordinate(x,y));
	}
	
	/**
	 * Resets the LineCanvasView
	 */
	public void reset()
	{
		canvas.getGraphicsContext2D().clearRect(0,0,width,height);
	}
	
	
	private void strokeLine(double prevX, double prevY, double x, double y)
	{
		if(!(prevX == x && prevY == y))
		{
			canvas.getGraphicsContext2D().strokeLine(prevX, prevY, x, y);
		}
	}
}
