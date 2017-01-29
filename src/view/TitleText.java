package view;

public interface TitleText {
	public static final int SCREEN_MARGIN = 20;
	public static final int marginRight = 275;
	public static final String DEFAULT_STRING = "Green";
	
	public static final int CONSOLE_WIDTH = 250;
	public static final int CONSOLE_HEIGHT = 100;
	
	public void positionTitlesOnScreen();
	public void initTitles();
	public void addTitlesToView();

}
