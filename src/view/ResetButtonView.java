package view;

import communication.ConfigurationEvent;
import javafx.scene.control.Button;
import javafx.scene.Node;

/**
 * The Reset button view resets the SLogo simulation
 * @author Ryan Bergamini
 */
public class ResetButtonView implements Viewable
{
	private Button resetButton;
	
	/**
	 * Creates a new Reset button view
	 */
	public ResetButtonView()
	{
		resetButton = new Button("Reset");
		resetButton.setOnAction(e -> fireResetEvent());
	}
	
	/**
	 * Returns a node that represents the  Reset Button
	 */
	public Node getNode()
	{
		return resetButton;
	}
	
	private void fireResetEvent()
	{
		resetButton.fireEvent(new ConfigurationEvent(ConfigurationEvent.RESET));
	}
	

}
