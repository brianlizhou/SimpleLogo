package view;

import javafx.scene.Node;

/**
 * This class contains a Node and it width and height. The width and height are make
 * instance fields in this class because they are difficult to deal with in JavaFx
 * @author Ryan Bergamini
 */
public class SimpleImage implements Turnable, Viewable
{
	private Node node;
	private double width;
	private double height;

	/**
	 * Creates a new SimpleImage class
	 * @param node- Returns node for the Image
	 * @param width- Returns the width of the Image
	 * @param height- Returns the height of the Image
	 */
	public SimpleImage(Node node, double width, double height)
	{
		this.node = node;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Turns the image
	 * @param turnDiff- distance the turn
	 */
	@Override
	public void turn(double turnDiff)
	{
		node.setRotate(validateHeading(node.getRotate() + turnDiff));
	}
	
	/**
	 * Set's the visibility of the SimpleImage
	 * @param isVisible- true if the Image isVisible, false if the image is not Visible
	 */
	public void setVisible(boolean isVisible)
	{
		node.setVisible(isVisible);
	}
	
	/**
	 * @return the node that represents the Image
	 */
	@Override
	public Node getNode()
	{
		return node;
	}
	
	/**
	 * @return the width of the Image
	 */
	public double getWidth()
	{
		return width;
	}
	
	/**
	 * @return the x value the SimpleImage is centereed around
	 */
	public double getCenterX()
	{	
		return node.getLayoutX() + width/2.0;
	}
	
	/**
	 * @return the y value the image is centered around
	 */
	public double getCenterY()
	{
		return node.getLayoutY() + height/2.0;
	}
	
	/**
	 * Centers the image around x
	 * @param x- x value
	 */
	public void setCenterX(double x)
	{
		node.setLayoutX(x - width/2.0);
	}
	
	/**
	 * Centers the image around y
	 * @param y- y value
	 */
	public void setCenterY(double y)
	{
		node.setLayoutY(y - height/2.0);
	}
	/**
	 * @return the height of the Node
	 */
	public double getHeight()
	{
		return height;
	}
	
	private double validateHeading(double heading)
	{
		heading = heading % 360;
		
		if(heading < 0)
		{
			heading += 360;
		}
		
		return heading;
	}
}
