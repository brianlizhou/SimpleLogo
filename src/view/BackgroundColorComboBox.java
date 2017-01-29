package view;

import communication.RunEvent;
import communication.SLogoController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;

public class BackgroundColorComboBox extends ComboBoxColors implements Viewable {
	private final ComboBox<String> colorComboBox = new ComboBox<>();
	private static final String BackgroundColorImage = "RainbowSquare.png";
	private static final double COLUMN_2_POINT = 8;
	
	private double xCoord = SLogoView.PADDING_TOP*COLUMN_2_POINT + gapBetweenImages;
	private double yCoord = SLogoView.PADDING_TOP;
	private double xCoordIcon = SLogoView.PADDING_TOP*COLUMN_2_POINT - (ComboBoxColors.IMAGE_RADIUS);
	private double yCoordIcon = SLogoView.PADDING_TOP + (gapBetweenImages);
	
	private ConsoleTitleView title;
	private ControllerInterface controller;
	
	public BackgroundColorComboBox(ConsoleTitleView title, ControllerInterface controller){  
		this.controller = controller;
		this.title = title;
        setOptions(this.colorComboBox);
        setUpListenerForSelection();
        setBackgroundColorsOfOptions(this.colorComboBox);
        positionViewOnScreen(this.xCoord,this.yCoord,this.colorComboBox);
        setImage(ComboBoxColors.IMAGE_RADIUS,ComboBoxColors.IMAGE_RADIUS, xCoordIcon,yCoordIcon,BackgroundColorImage);
	}
	
	public void updateColor(){
		if(title != null) this.title.updateColor(selectedColor);
	}
	
	public ComboBox<?> getNode(){
		return this.colorComboBox;
	}
	
	private void setUpListenerForSelection(){
		this.colorComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String newString) {                
                selectedColor = Color.valueOf(newString); 
                updateColor();
                int[] newColorRGB = controller.indexNewColor(selectedColor);
                StringBuilder newCommand = buildNewRGBString(newColorRGB);
                colorComboBox.fireEvent(new RunEvent(RunEvent.RUN,newCommand.toString()));  
                colorComboBox.fireEvent(new RunEvent(RunEvent.RUN,"SETBG " + String.valueOf(newColorRGB[0])));
            }    
        });
	}
}