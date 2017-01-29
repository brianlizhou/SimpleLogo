package view;

import javafx.scene.Group;

public class ComboBoxTitles implements TitleText{
	private Group root;
	private ConsoleTitleView backgroundColorText;
	private ConsoleTitleView penColorText;
	private ConsoleTitleView languageText;
	private ConsoleTitleView returnValuesText;
	
	
	public ComboBoxTitles(Group root){
		this.root = root;
		initTitles();
		positionTitlesOnScreen();
		addTitlesToView();
	}

	public void initTitles(){
		this.backgroundColorText = new ConsoleTitleView("Background Color");
		this.penColorText = new ConsoleTitleView("Pen Color");
		this.languageText = new ConsoleTitleView("Language");
		this.returnValuesText = new ConsoleTitleView("Return Values:");
		
	}

	public void positionTitlesOnScreen(){
		backgroundColorText.adjustPosition(CONSOLE_HEIGHT * SLogoView.ROW_MULTIPLE[1],SCREEN_MARGIN);
		penColorText.adjustPosition(CONSOLE_HEIGHT * SLogoView.ROW_MULTIPLE[2],SCREEN_MARGIN);
		languageText.adjustPosition(CONSOLE_HEIGHT * SLogoView.ROW_MULTIPLE[3],SCREEN_MARGIN);
		returnValuesText.adjustPosition(CONSOLE_HEIGHT * SLogoView.ROW_MULTIPLE[1] + (SLogoView.ROW_MULTIPLE[2]*SLogoView.PADDING_TOP) - SCREEN_MARGIN, SCREEN_MARGIN*SLogoView.ROW_MULTIPLE[3] - SCREEN_MARGIN);
	}
	
	public void addTitlesToView(){
		root.getChildren().addAll(backgroundColorText.getNode(),penColorText.getNode(),languageText.getNode(),returnValuesText.getNode());
	}
	
	public ConsoleTitleView getBackgroundColorText(){
		return backgroundColorText;
	}
	
	public ConsoleTitleView getPenColorText(){
		return penColorText;
	}
	
	
}
