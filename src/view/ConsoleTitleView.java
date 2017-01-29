package view;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ConsoleTitleView<T> implements Viewable{
	private Text consoleTitle;
	
	public ConsoleTitleView(String s){
		this.consoleTitle = new Text(s);
		this.consoleTitle.setFill(Color.GREEN);
		this.consoleTitle.setFont(Font.font ("Verdana", 15));
	}
	
	public void adjustPosition(double xCoord, double yCoord){
		this.consoleTitle.setLayoutX(xCoord);
		this.consoleTitle.setLayoutY(yCoord);
	}

	@Override
	public Node getNode() {
		return consoleTitle;
	}
	
	public void updateColor(Color selectedColor){
		this.consoleTitle.setFill(selectedColor);
	}
	
	public void updateText(String newText){
		this.consoleTitle.setText(newText);
	}
}
