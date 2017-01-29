package view;

import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public abstract class Colors {
	protected static final int IMAGE_RADIUS = 20;
	protected static final int gapBetweenImages = 5;
	protected Color selectedColor;
	
	protected ImageView SmallImageIcon;
	
	protected ImageView getSmallIconImage(){
		return this.SmallImageIcon;
	}
	
	protected void setOptions(ComboBox<String> colorComboBox){
		colorComboBox.getItems().addAll(
                "Aqua","Azure","Blue", "Black", "Beige", "Brown","Coral", "Crimson","Cyan","Fuchsia","Gold","Green","Grey","Honeydew",
                "Indigo","Ivory","Magenta","Navy","Orange","Olive", "Orchid","Pink",
                "Purple","Red","Salmon","Snow","Teal", "Turquoise","Violet","Yellow"
            );  
	}
}
