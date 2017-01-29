package view;

import javafx.scene.control.ComboBox;

public class TurtleColorComboBox extends ComboBoxColors implements Viewable {
	public static final String SET_PALETTE = "SETPALETTE ";
	public static final String SET_PC = "SETPC ";
	public static final String colorWheelImage = "ColorWheel.png";
	private final ComboBox<?> colorComboBox = new ComboBox();
	private static final double COLUMN_3_POINT = 15;
	
	public static double xCoord = SLogoView.PADDING_TOP*COLUMN_3_POINT + gapBetweenImages;
	public static double yCoord = SLogoView.PADDING_TOP;
	public static double xCoordIcon = SLogoView.PADDING_TOP*COLUMN_3_POINT - (ComboBoxColors.IMAGE_RADIUS);
	public static double yCoordIcon = SLogoView.PADDING_TOP + (gapBetweenImages);
	
	private ConsoleTitleView title;
	
	public TurtleColorComboBox(ConsoleTitleView title){
		this.title = title;
        //setOptions(this.colorComboBox);
        setBackgroundColorsOfOptions(this.colorComboBox);
        positionViewOnScreen(this.xCoord,this.yCoord,this.colorComboBox);
        setImage(ComboBoxColors.IMAGE_RADIUS,ComboBoxColors.IMAGE_RADIUS, xCoordIcon,yCoordIcon,colorWheelImage);
	}
	
	public void updateColor(){
		if(title != null) this.title.updateColor(selectedColor);
	}
	
	public ComboBox<?> getNode(){
		return this.colorComboBox;
	}
	

}