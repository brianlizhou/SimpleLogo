package view;

import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class VariablesView extends ConsoleView implements Viewable{
	private Map<String,Object> variableList;
	/**
	 * Creates a new PastCommandsView
	 */
	public VariablesView(int width, int height)
	{
		this.width = width;
		this.height = height;
		configureNodes();
	}
	
	public void positionViewOnScreen(){
		viewWindow.setLayoutX(SLogoView.WIDTH - TitleText.marginRight);
		viewWindow.setLayoutY(SLogoView.ROWYVALUES[2]);
	}
	
	public void updateMap(Map<String,Object> variableList){
		this.variableList = variableList;
	}
	
	/**
	 * Returns the Node object that represents the PastCommandsView
	 */
	public Node getNode()
	{
		return viewWindow;
	}
	
	public void updateMapConsole(){
		listOfCommands.getChildren().clear();
		for(String elem:variableList.keySet()){
			CommandView commandView = createNewCommandView(elem + " : " + variableList.get(elem));
			listOfCommands.getChildren().add(commandView.getNode());
		}
	}
}
