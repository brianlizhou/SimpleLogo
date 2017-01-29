package view;

/**
 * A Moveable object can move in the x direction and y diraection. Classes that implement this interface can be
 * animated by the MoveAnimationhandler
 * @author Ryan Bergamini
 */
public interface Moveable 
{
	/**
	 * Moves the view in the x direction by xDist
	 * @param xDist- the distance to move the view
	 */
	public void moveX(double xDist);
	
	/**
	 * Moves the view in the y direction by yDist
	 * @param yDist- the distance to move the view
	 */
	public void moveY(double yDist);
}
