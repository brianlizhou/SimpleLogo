package view;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class ErrorView implements Viewable {
	private static final Text errorText = new Text("");

	public ErrorView(){
		errorText.setFill(Color.RED);
		errorText.setFont(Font.font ("Verdana", 15));
	}
	
	public Text getNode(){
		return errorText;	
	}
	
	public void positionViewOnScreen(){
		errorText.setLayoutX(SLogoView.WIDTH - TitleText.CONSOLE_WIDTH);
		errorText.setLayoutY(SLogoView.ROWYVALUES[1] + (SLogoView.PADDING_TOP));
	}
	
	public void updateErrorTextMessage(String message){
		errorText.setText(message);
	}
	
	public void reset()
	{
		errorText.setText("");
	}
	
	public  void hideErrorTextMessage(){
		errorText.setVisible(false);
	}
	
	public void showErrorTextMessage(){
		errorText.setVisible(true);
	}
}
