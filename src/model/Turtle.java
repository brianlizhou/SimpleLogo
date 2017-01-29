package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import communication.Coordinate;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Turtle objects represent the turtles on the screen
 * Most methods are getters and setters for the turtle properties
 * almost all Turtle properties are ObservableProperties, which allows the SLogoController to set change listeners
 * This makes it easy for changes in the backend to be automatically updated on the front end
 * Turtle properties are manipulated/changed by Command objects
 * @author Aaron Chang, Yumin Zhang
 *
 */
public class Turtle {

	private BooleanProperty isVisible;
	private BooleanProperty penDown;
	private DoubleProperty heading;
	private SimpleObjectProperty<Coordinate> position;
	private IntegerProperty penColor;
	private IntegerProperty penSize;
	private int ID;
	private IntegerProperty shape;
	
	
	public Turtle(int id) {
		isVisible = new SimpleBooleanProperty();
		isVisible.set(true);
		heading = new SimpleDoubleProperty(0.0);
		position = new SimpleObjectProperty<Coordinate>(new Coordinate(0.0,0.0));
		penDown = new SimpleBooleanProperty();
		penDown.set(true);
		penColor = new SimpleIntegerProperty();
		penSize = new SimpleIntegerProperty();
		shape = new SimpleIntegerProperty();
		ID = id;
	}
	
	public IntegerProperty getShapeProperty() {
		return shape;
	}

	public void setShape(int index) {
		shape.set(index);
	}
	
	/**
	 * returns boolean value of Visibility
	 * value is true if turtle is visible
	 * @return boolean
	 */
	public boolean getVisibility() {
		return this.isVisible.get();
	}
	
	
	/**
	 * returns the observable Visibility property, used by the Controller for binding
	 * this is not an actual boolean value, but the property used for binding
	 * @return BooleanProperty
	 */
	public BooleanProperty getVisibilityProperty() {
		return isVisible;
	}

	
	public void setVisibility(boolean visibility) {
		this.isVisible.set(visibility);
	}
	
	public IntegerProperty getPenSize() {
		return penSize;
	}

	public void setPenSize(int penSize) {
		this.penSize.set(penSize);
	}

	public IntegerProperty getPenColor() {
		return penColor;
	}

	public void setPenColor(int penColor) {
		this.penColor.set(penColor);;
	}

	/**
	 * returns boolean value of penDown
	 * value is true if pen is down
	 * @return boolean
	 */
	public boolean getPenDown() {
		return this.penDown.get();
	}
	
	/**
	 * returns the observable penDown property, used by the Controller for binding
	 * this is not an actual boolean value, but the property used for binding
	 * @return BooleanProperty
	 */
	public BooleanProperty getPenDownProperty() {
		return penDown;
	}


	public void setPenDown(boolean pen) {
		this.penDown.set(pen);
	}

	/**
	 * returns double value of the turtle's Heading, used for calculations
	 * @return double
	 */
	public double getHeading() {
		return heading.get();
	}
	
	/**
	 * returns the observable Heading property, used by the Controller for binding
	 * this is not the actual double value, but the property used for binding
	 * @return DoubleProperty
	 */
	public DoubleProperty getHeadingProperty() {
		return heading;
	}

	/**
	 * sets the heading of the turtle to the specified angle
	 * if the angle is greater than 360 or less than 0, it will convert the heading to the equivalent angle within that range
	 * @param direction - angle the turtle is facing (degrees)
	 */
	public void setHeading(double direction) {
		double newHeading = direction % 360; //keep heading under 360 degrees
		while (newHeading < 0) {
			newHeading += 360; //convert negative values to positive
		}
		this.heading.set(newHeading);
	}

	/**
	 * returns double value of the turtle's xPos, used for calculations
	 * @return double
	 */
	public double getxPos() {
		return this.position.get().getX();
	}
	
	
	/**
	 * returns the observable CoordinateProperty of the turtle, used by the Controller for binding
	 * this is not an actual list of coordinates, but the property used for binding
	 * @return the position of the turtle
	 */
	public SimpleObjectProperty<Coordinate> getPositionProperty()
	{
		return position;
	}
	
	/**
	 * 
	 * @param xPosition
	 * @param yPosition
	 */
	public void setTo(double xPosition, double yPosition)
	{
		this.position.set(new Coordinate(xPosition,yPosition));
	}
	/**
	 * returns double value of the turtle's yPos, used for calculations
	 * @return double
	 */
	public double getyPos() {
		return this.position.get().getY();
	}
	
	public int getID() {
		return this.ID;
	}
	
}
