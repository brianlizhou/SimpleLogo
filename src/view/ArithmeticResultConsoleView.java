package view;

import java.util.Map;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ArithmeticResultConsoleView extends ConsoleView implements Viewable{
	private Map<String,String> commands;
	
	
	public ArithmeticResultConsoleView(int width, int height)
	{
		this.width = width;
		this.height = height;
		configureNodes();
	}
	
	public void updateMap(Map<String,String> commands){
		this.commands = commands;
	}
	
	public void alignLeft(){
		viewWindow.setLayoutX(TitleText.CONSOLE_HEIGHT * SLogoView.ROW_MULTIPLE[2]);
		viewWindow.setLayoutY(TitleText.SCREEN_MARGIN * SLogoView.ROW_MULTIPLE[6]);
	}
	
	public void positionViewOnScreen(){
		viewWindow.setLayoutX(SLogoView.WIDTH - TitleText.marginRight);
		viewWindow.setLayoutY(SLogoView.ROWYVALUES[3] - (2*SLogoView.PADDING_TOP));
	}
	
	public void positionAtSpecificLocation(double xCoord, double yCoord){
		viewWindow.setLayoutX(xCoord);
		viewWindow.setLayoutY(yCoord);
	}
	
	public void updateArithmeticConsole(){
		listOfCommands.getChildren().clear();
		for(String elem:commands.keySet()){
			CommandView commandView = createNewCommandView(elem + " : " + commands.get(elem));
			listOfCommands.getChildren().add(commandView.getNode());
		}
	}
	
	public Node getNode()
	{
		return viewWindow;
	}
}
