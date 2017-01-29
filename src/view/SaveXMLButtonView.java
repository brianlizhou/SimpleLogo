package view;

import java.io.File;

import communication.ReadXML;
import communication.SaveCommandToXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SaveXMLButtonView implements Viewable{
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final double xCoord = SLogoView.PADDING_TOP * 31;
	private static final double yCoord = SLogoView.PADDING_TOP * 4.5;
	
	private Button xmlButton;
	private SLogoView view;


	public SaveXMLButtonView(SLogoView view) {
		this.view = view;
		this.xmlButton = new Button("Save XML File");
		setOnClickListenerForButton();
	}
	
	public void positionViewOnScreen(){
		this.xmlButton.setLayoutX(xCoord);
		this.xmlButton.setLayoutY(yCoord);
	}	

	@Override
	public Node getNode() {
		return this.xmlButton;
	}
	
	private void setOnClickListenerForButton(){
		this.xmlButton.setOnAction(new EventHandler<ActionEvent>() {
			   @Override public void handle(ActionEvent e) {
				       SaveCommandToXML saver = new SaveCommandToXML(view.getCurrentCommandsList(),"pastCommands.xml");
			        }
			    }
			);
	}	
}
