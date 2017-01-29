package view;

import communication.SLogoController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

public class LanguageComboBox implements Viewable{
	private static final ComboBox<String> languageComboBox = new ComboBox<String>();
	private static final double xCoord = SLogoView.PADDING_TOP * 25;
	private static final double yCoord = SLogoView.PADDING_TOP;
	private String language = "English"; // defaulted to English
	private ControllerInterface controller;
	public LanguageComboBox(ControllerInterface controller){  
		this.controller = controller;
        setOptions();
        setUpListenerForSelection();
	}
	
	@Override
	public Node getNode() {
		return languageComboBox;
	}
	
	public void positionViewOnScreen(){
		this.languageComboBox.setLayoutX(xCoord);
		this.languageComboBox.setLayoutY(yCoord);
	}
	
	private void setOptions(){
		languageComboBox.getItems().addAll(
                "English","Chinese","French","German","Italian","Portuguese","Russian","Spanish"
            );  
	}
	
	private void setUpListenerForSelection(){
		languageComboBox.setValue("English");
		languageComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String newString) {  
            	language = newString; 
            	controller.setLanguage(language);
            }    
        });
	}
}
