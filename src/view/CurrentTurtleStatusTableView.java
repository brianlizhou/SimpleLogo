package view;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class CurrentTurtleStatusTableView implements Viewable{
	
	private ConsoleTitleView turtleNumber;
	private ConsoleTitleView turtlePosition;
	private ConsoleTitleView turtleHeading;
	private ConsoleTitleView penStatusView;
	
	private static final VBox container = new VBox();
	
	private static final String turtleNumText = "Turtle Number: ";
	private static final String turtlePosText = "Turtle Position: ";
	private static final String turtleHeadingText = "Heading: ";
	private static final String penStatusText = "Pen Status: ";
	
	
	
	public CurrentTurtleStatusTableView(){
		this.turtleNumber = new ConsoleTitleView(turtleNumText + "1");
		this.turtlePosition = new ConsoleTitleView(turtlePosText + "(0.0,0.0)");
		this.turtleHeading = new ConsoleTitleView(turtleHeadingText + "0.0");
		this.penStatusView = new ConsoleTitleView(penStatusText + "DOWN");//DEFAULT UP
		container.getChildren().addAll(turtleNumber.getNode(),turtlePosition.getNode(),turtleHeading.getNode(),penStatusView.getNode());
		
	}
	
	public void positionViewOnScreen(){
		container.setLayoutX(SLogoView.PADDING_TOP);
		container.setLayoutY(SLogoView.PADDING_TOP * SLogoView.ROW_MULTIPLE[1] * (1.25));
	}
	
	@Override
	public Node getNode() {
		return container;
	}

	public void updateHeading(double newHeading) {
		
		turtleHeading.updateText(turtleHeadingText + String.valueOf(Math.round(newHeading*100.0)/100.0));
		
	}

	public void updatePosition(double newX, double newY) {
		turtlePosition.updateText(turtlePosText + "(" + String.valueOf(Math.round(newX*100.0)/100.0) + ", " + String.valueOf(Math.round(newY*100.0)/100.0) + ")");	
	}

	public void updatePen(boolean penStatus) {
		String newPenStatusText = penStatus ? "DOWN" : "UP";
		penStatusView.updateText(penStatusText + newPenStatusText);
	}
	

}
