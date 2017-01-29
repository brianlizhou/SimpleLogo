package view;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import communication.RunEvent;

/**
 * This classes manages and displays User input
 * @author Ryan Bergamini
 */
public class CommandLineView implements Viewable
{
	private int width;
	private int height;
	
	private TextArea textArea;
	private Button runButton;
	
	private static final double RUN_BUTTON_HEIGHT_PERCENTAGE = 0.2;//1 - TEXT_BOX_WIDTH_PERCENTAGE
	
	
	/**
	 * Creates a new CommandLine object with a TextField at (x,y) that has 
	 * the dimensions of width by height
	 * @param x- x position of the command line
	 * @param y- y position of the command line
	 * @param width- width of the command line
	 * @param height- height of the command line
	 */
	public CommandLineView(int width, int height)
	{
		this.width = width;
		this.height = height;
	
		configuretextArea();
		configureRunButton();
	}
	
	/**
	 * Positions the CommandLineView on the SlogoView
	 */
	public void positionViewOnScreen(){
		textArea.setLayoutX(SLogoView.WIDTH - TitleText.marginRight);
		textArea.setLayoutY(SLogoView.ROWYVALUES[0]);
		runButton.setLayoutX(SLogoView.WIDTH - TitleText.marginRight);
		runButton.setLayoutY(SLogoView.ROWYVALUES[1] - SLogoView.PADDING_TOP);
	}
	
	/**
	 * @return the input from the CommandLine
	 */
	public String getCommandLineInput()
	{
		return textArea.getText();
	}
	
	/**
	 * Prepares the CommandLine to receive the next command from the user
	 */
	public void resetCommandLineForNextCommand()
	{
		textArea.setText("");
	}
	
	/**
	 * Grants the SLogoView class access to the CommandLine node in order to add it to 
	 * root Node
	 * @return reference to the TextField textArea
	 */
	@Override
	public Node getNode() {
		return textArea;
	}
	
	/**
	 * Returns the RunButton part of the CommandLine View
	 * @return reference to the run Button
	 */
	public Node getRunButton(){
		return runButton;
	}
	
	/**
	 * Sets the content of the command line to the commandBlock
	 * @param commandBlock- the command that is set to the command line's content
	 */
	public void setCommandLineContent(String commandBlock)
	{
		textArea.setText(commandBlock);
	}
	
	/**
	 * Disables the run button
	 */
	public void disableRun()
	{
		runButton.setDisable(true);
	}
	
	/**
	 * Enables the run button
	 */
	public void enableRun()
	{
		runButton.setDisable(false);
	}
	
	
	private void configureRunButton()
	{
		runButton = new Button();
		runButton.setMinWidth(width);
		runButton.setMinHeight(getRunButtonHeight());
		runButton.setText("Run");
		runButton.setOnAction(e -> triggerRunEvent());
	}
	
	private void triggerRunEvent()
	{
		runButton.fireEvent(new RunEvent(RunEvent.RUN,getCommandLineInput()));
		resetCommandLineForNextCommand();
	}
	
	private void configuretextArea()
	{
		textArea = new TextArea();
		textArea.setPrefWidth(width);
		textArea.setPrefHeight(height);
		textArea.setPromptText("Enter commands here");
	}

	private double getRunButtonHeight()
	{
		return RUN_BUTTON_HEIGHT_PERCENTAGE * height;
	}

}
