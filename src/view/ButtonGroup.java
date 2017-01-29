package view;

import javafx.scene.Group;
import javafx.scene.layout.VBox;

public class ButtonGroup {
	private HelpButton helpButton;
	private XMLSelectorButton xmlButton;
	private SaveXMLButtonView saveXMLButton;
	private ResetButtonView resetButton;
	private Group root;
	private VBox buttonContainer;
	
	private static final int MARGIN_LEFT = 75;
	
	public ButtonGroup(SLogoView view,CommandLineView commandLine,Group root){
		this.root = root;
		this.saveXMLButton = new SaveXMLButtonView(view);
		this.helpButton = new HelpButton();
		this.xmlButton = new XMLSelectorButton(commandLine);
		this.resetButton = new ResetButtonView();
		this.buttonContainer = new VBox();
		
		addAllButtons();
		positionAndAddButtonsToScreen();
	}
	
	private void addAllButtons(){
		buttonContainer.getChildren().addAll(xmlButton.getNode(),helpButton.getNode(),resetButton.getNode(),saveXMLButton.getNode());	
	}
	
	private void positionAndAddButtonsToScreen(){
		buttonContainer.setLayoutX(MARGIN_LEFT * SLogoView.ROW_MULTIPLE[4]);
		buttonContainer.setLayoutY(SLogoView.PADDING_TOP/2);
		root.getChildren().add(buttonContainer); 
	}
}
