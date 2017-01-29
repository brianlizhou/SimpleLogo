package view;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TitleView implements Viewable{

	public static final String TurtleImage = "RainbowTurtle.png";
	private static final Text titleText= new Text("SLOGO");
	private static final HBox container = new HBox();
	private static final double COLUMN_1_POINT = 1;
	private static final int imageSize = 35;
	
	private double xCoord = SLogoView.PADDING_TOP*COLUMN_1_POINT + ComboBoxColors.gapBetweenImages;
	private double yCoord = SLogoView.PADDING_TOP/2;
	private ConsoleTitleView title;
	private ImageView turtleIcon;
	
	public TitleView(){
		Image colorWheel = new Image(getClass().getClassLoader().
				getResourceAsStream(TurtleImage));
		this.turtleIcon = new ImageView(colorWheel);
		turtleIcon.setFitWidth(imageSize);
		turtleIcon.setFitHeight(imageSize);
		this.titleText.setFont(Font.font ("Verdana", 23));
		this.container.getChildren().addAll(titleText,turtleIcon);
	}
	
	public void positionViewOnScreen(){
		container.setLayoutX(xCoord);
		container.setLayoutY(yCoord);
	}

	@Override
	public Node getNode() {
		return this.container;
	}
	

}