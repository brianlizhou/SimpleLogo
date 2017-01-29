package view;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class HelpButton implements Viewable{
	private static final Button helpButton = new Button("HELP: Commands List");
	private static final double xCoord = SLogoView.PADDING_TOP * 31;
	private static final double yCoord = SLogoView.PADDING_TOP;
	
	public HelpButton(){
		setOnClickListenerForButton();	
	}
	
	public void positionViewOnScreen(){
		this.helpButton.setLayoutX(xCoord);
		this.helpButton.setLayoutY(yCoord);
	}
	
	@Override
	public Node getNode(){
		return this.helpButton;
	}
	
	private void setOnClickListenerForButton(){
		this.helpButton.setOnAction(new EventHandler<ActionEvent>() {
			   @Override public void handle(ActionEvent e) {
				        try {
				            Desktop.getDesktop().browse(new URI("https://www.cs.duke.edu/courses/compsci308/fall16/assign/03_slogo/commands.php"));
				        } catch (IOException e1) {
				            e1.printStackTrace();
				        } catch (URISyntaxException e1) {
				            e1.printStackTrace();
				        }
			        }
			    }
			);
	}	
	
}
