package view;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.Node;
/**
 * Creates a view of past commands that were called in the command line
 * @author Ryan Bergamini
 */
public class PastCommandsView extends ConsoleView implements Viewable
{
	private Set<String> allCommands;
	/**
	 * Creates a new PastCommandsView
	 */
	public PastCommandsView(int width, int height)
	{	
		this.allCommands = new HashSet<String>();
		this.width = width;
		this.height = height;
		configureNodes();
	}
	
	/**
	 * Positions the PastCommandsView
	 */
	public void positionViewOnScreen(){
		viewWindow.setLayoutX(SLogoView.WIDTH - TitleText.marginRight);
		viewWindow.setLayoutY(SLogoView.ROWYVALUES[1] + (SLogoView.PADDING_TOP*2));
	}
	
	/**
	 * @return returns all the commands in the past command view
	 */
	public String[] getAllCommands(){
		return this.allCommands.toArray(new String[allCommands.size()]);
	}
	
	/**
	 * Returns the Node object that represents the PastCommandsView
	 */
	public Node getNode()
	{
		return viewWindow;
	}
	
	/**
	 * Adds a command to the Past Commands View to display it
	 * @param command- text that was entered into the command line as a command
	 */
	public void addCommand(String command)
	{	
		allCommands.add(command);
		CommandView commandView = createNewCommandView(command);
		listOfCommands.getChildren().add(commandView.getNode());
	}
}
