package view;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * 
 * @author Brian Zhou
 *
 */
public abstract class ConsoleView {
	
	protected VBox listOfCommands;
	protected ScrollPane viewWindow;
	protected int width;
	protected int height;
	
	public CommandView createNewCommandView(String command)
	{
		return new CommandView.CommandViewBuilder(command).build();
	}
	
	public void configureNodes()
	{
		listOfCommands = new VBox(5);
		viewWindow = new ScrollPane();
		viewWindow.setPrefViewportWidth(width);
		viewWindow.setPrefViewportHeight(height);
		viewWindow.setContent(listOfCommands);
	}
	
	/**
	 * Adds a command to the Past Commands View to display it
	 * @param command- text that was entered into the command line as a command
	 */
	public void addCommand(String command)
	{
		CommandView commandView = createNewCommandView(command);
		listOfCommands.getChildren().add(commandView.getNode());
	}
	
	public abstract void positionViewOnScreen();
}
