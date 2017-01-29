package view;

import javafx.scene.Node;

/**
 * A class is deemed Viewable if it contains an underlying JavaFX Node, that most
 * frequently needs to be accessed in order to add that Node to the root Node of
 * an encapsulating view
 * @author Ryan Bergamini
 */
public interface Viewable
{
	/**
	 * @return a reference to the Viewable object's underlying JavaFX Node
	 */
	public Node getNode();
}
