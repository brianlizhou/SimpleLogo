package communication;

/**
 * Contains a read-only coordinate with an x and a y value. This class is read-only to force any changes to be registered by
 * the change listener on SimpleObjectProperty
 * @author Rysn Bergamini
 */
public class Coordinate
{
	private double x;
	private double y;
	
	/**
	 * Creates a new Coordinate
	 * @param x- x value for that coordinate
	 * @param y0 y value for that coordinate
	 */
	public Coordinate(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns the x value for the Coordinate
	 * @return x value
	 */
	public double getX()
	{
		return x;
	}
	
	/**
	 * Returns the y value for the Coordinate
	 * @return y value
	 */
	public double getY()
	{
		return y;
	}

	@Override
	/**
	 * Returns the (x,y)
	 */
	public String toString()
	{
		return "(" + getX() +"," + getY() + ")";
	}
}
