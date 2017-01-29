package view;

import javafx.scene.Group;

public class TerminalTitles implements TitleText{
	private Group root;
	private ConsoleTitleView prevCommands;
	private ConsoleTitleView variableTitle;
	private ConsoleTitleView prevFunctions;
	private ConsoleTitleView commandTerminalText;

	public TerminalTitles(Group root){
		this.root = root;
		initTitles();
		positionTitlesOnScreen();
		addTitlesToView();
	}
	
	public void positionTitlesOnScreen() {
		prevCommands.adjustPosition(SLogoView.WIDTH-marginRight, SLogoView.ROWYVALUES[1] + (2*SLogoView.PADDING_TOP)-5);
		variableTitle.adjustPosition(SLogoView.WIDTH-marginRight, SLogoView.ROWYVALUES[2] - (SLogoView.PADDING_TOP/2));
		prevFunctions.adjustPosition(SLogoView.WIDTH-marginRight, SLogoView.ROWYVALUES[3] - (SLogoView.PADDING_TOP*(2.5)));
		commandTerminalText.adjustPosition(CONSOLE_HEIGHT * SLogoView.ROW_MULTIPLE[4],SCREEN_MARGIN);
		
	}

	public void initTitles(){
		prevCommands = new ConsoleTitleView("Previous Commands:");
		variableTitle = new ConsoleTitleView("Variables:");
		prevFunctions = new ConsoleTitleView("User Defined Instructions:");
		commandTerminalText = new ConsoleTitleView("Input Terminal:");
	}
	
	public void addTitlesToView(){
		root.getChildren().addAll(variableTitle.getNode(),prevCommands.getNode(),prevFunctions.getNode(),commandTerminalText.getNode());
	}
}
