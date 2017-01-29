package view;

import communication.AnimationEvent;
import communication.LoadCommandEvent;
import communication.Packet;
import communication.RunEvent;
import javafx.scene.Scene;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
/**
 * The SLogoView class is the main class for managing the graphics of the application.
 * @author ?????
 */
public class SLogoView 
{
	public static final int WIDTH = 1250;
	public static final int HEIGHT = 700;
	
	public static final double[] ROW_MULTIPLE = {0.3,2,4,6.5,10.5,6.5,3.5};
	public static final int PADDING_TOP = 25;
	
	private static final int INPUT_TERMINAL_HEIGHT = 150;
	private static final int INPUT_TERMINAL_WIDTH = 265;
	private static final int TURTLE_CANVAS_WIDTH = 925;
	private static final int TURTLE_CANVAS_HEIGHT = 535;
	private static final int TABLE_VALUES = 200;
	
	public static final int[] ROWYVALUES = {PADDING_TOP,TABLE_VALUES,TABLE_VALUES*2, TABLE_VALUES*3,TABLE_VALUES*4};
	
	private CommandLineView commandLine;
	private PastCommandsView pastCommands;
	private ButtonGroup viewButtons;	
	private SLogoScreenView screen;
	private ErrorView errorText;
	private ArithmeticResultConsoleView consoleUserDefinedInstructions;
	private ArithmeticResultConsoleView consoleReturnValues;
	private VariablesView consoleWithVariables;
	private ComboBoxColors<?> backgroundColorComboBox;
	private ComboBoxColors<?> penColorComboBox;	
	private LanguageComboBox languageComboBox;
	private Scene scene;
	private Group root;
	private ArithmeticResultConsoleView consoleForArithmetic;
	private CurrentTurtleStatusTableView statusTable;
	private ControllerInterface controller;
	private TitleView titleView;
	private ComboBoxTitles comboBoxTitles;
	private TerminalTitles terminalTitles;
	
	//Note: SimpleDoubleProperty objects are not doubles, in order to get the double value, call the .doubleValue() method	
	public SimpleDoubleProperty heading = new SimpleDoubleProperty();
	public SimpleBooleanProperty isVisible = new SimpleBooleanProperty();
	public SimpleBooleanProperty penDown = new SimpleBooleanProperty();
	public TurtleView turtleView;

	public SLogoView(ControllerInterface controller)//DoubleProperty modelHeading, SimpleObjectProperty modelPosition, BooleanProperty modelIsVisible, BooleanProperty modelIsPenDown)
	{
		this.controller = controller;
		configureSLogoView();
		configureEventListeners();
	}
	
	/**
	 * Called by the SLogoController to reset the user interface. This includes:
	 * Repositioning the turtle back to (0,0), clearing the command line window,
	 * and clearing the screen the turtle is drawing on
	 */
	public void reset()
	{
		screen.reset();
	}
	
	/**
	 * Updates UDI map when a new variable is added
	 */
	public void updateDefinedInstructions(Map<String,String> definedInstructions){
		consoleUserDefinedInstructions.updateMap(definedInstructions);
		consoleUserDefinedInstructions.updateArithmeticConsole();
	}
	
	/**
	 * Called by the SLogoController to send a Packet of data to the SLogoView. The SLogoView then peeks into this packet to determine
	 * how to represent it on the view. For example, the Packet can contain the previous commands called, which the SLogoView will then
	 * animate and add to the previous commands called options on the screen.
	 * @param data- the information the SLogoView needs to interpret and display
	 */
	public void loadPacket(Packet data)
	{
		List<String> errorMessages = data.getErrorMessages();
		if(errorMessages.size() != 0){
			errorText.updateErrorTextMessage(data.getErrorMessages().get(0));
			errorText.showErrorTextMessage();
		}
		
		//Console For User Defined Instructions adds command
		consoleReturnValues.addCommand(String.valueOf(data.getReturnValue()));
		
	}
	
	/**
	 * Updates variable map when a new variable is added
	 */
	public void updateVariables(Map<String,Object> variableMap){
		consoleWithVariables.updateMap(variableMap);
		consoleWithVariables.updateMapConsole();
	}
	
	/**
	 * Allows the SLogoController to add the Scene for the SLogoView to the Stage
	 * @return reference to the Scene for the SLogoView
	 */
	public Scene getScene()
	{
		return scene;
	}
	
	/**
	 * Adds Turtle to screen
	 */
	public void addTurtleView(TurtleView turtleView)
	{
		screen.addTurtleView(turtleView);
	}
	
	/**
	 * Changes direction of turtle movement
	 */
	public void updateHeadingStatus(double newHeading){
		statusTable.updateHeading(newHeading);
	}
	
	/**
	 * Changes new position for turtle
	 */
	public void updatePositionProperty(double newX, double newY){
		statusTable.updatePosition(newX,newY);
	}
	
	/**
	 * Modifies state of pen (up or down)
	 */
	public void updatePenStatus(boolean newStatus){
		statusTable.updatePen(newStatus);
	}
	
	/**
	 * Gets all commands executed so far
	 * @return (String[]) Array of all commands
	 */
	public String[] getCurrentCommandsList(){
		return pastCommands.getAllCommands();
	}
	
	/**
	 * Changes color of background real-time
	 */	
	public void updateBackgroundColor(Color newColor) {
		screen.updateBackgroundColor(newColor);
	}

	private void configureSLogoView()
	{
		root = new Group();
		scene = new Scene(root,WIDTH,HEIGHT);
		commandLine = new CommandLineView(INPUT_TERMINAL_WIDTH,INPUT_TERMINAL_HEIGHT);
		
		titleView = new TitleView();
		screen = new SLogoScreenView(TURTLE_CANVAS_WIDTH,TURTLE_CANVAS_HEIGHT);
		errorText = new ErrorView();

		statusTable = new CurrentTurtleStatusTableView();
		comboBoxTitles = new ComboBoxTitles(root);
		terminalTitles = new TerminalTitles(root);
		viewButtons = new ButtonGroup(this,commandLine,root);
		
		//Combo Boxes		
		languageComboBox = new LanguageComboBox(controller);
		backgroundColorComboBox = new BackgroundColorComboBox(comboBoxTitles.getBackgroundColorText(), controller);
		penColorComboBox = new PenColorComboBox(comboBoxTitles.getPenColorText(), controller);	
		pastCommands = new PastCommandsView(TitleText.CONSOLE_WIDTH,TitleText.CONSOLE_HEIGHT);
		consoleWithVariables = new VariablesView(TitleText.CONSOLE_WIDTH,TitleText.CONSOLE_HEIGHT);
		consoleForArithmetic = new ArithmeticResultConsoleView(TitleText.CONSOLE_WIDTH,TitleText.CONSOLE_HEIGHT);
		consoleReturnValues = new ArithmeticResultConsoleView(TitleText.CONSOLE_WIDTH,TitleText.CONSOLE_HEIGHT/2);
		consoleUserDefinedInstructions = new ArithmeticResultConsoleView(TitleText.CONSOLE_WIDTH,TitleText.CONSOLE_HEIGHT);

		positionNodesInView();
	}
	
	private void configureEventListeners()
	{
		root.addEventHandler(RunEvent.RUN,new EventHandler<RunEvent>(){
			public void handle(RunEvent re)
			{
				errorText.reset();
				pastCommands.addCommand(re.getUserInput());
			}
		});
		
		root.addEventHandler(AnimationEvent.START, e -> commandLine.disableRun());
		
		root.addEventHandler(LoadCommandEvent.PAST_COMMAND,new EventHandler<LoadCommandEvent>(){
			public void handle(LoadCommandEvent lce)
			{
				commandLine.setCommandLineContent(lce.getCommandBlock());
			}
		});
		
		root.addEventHandler(AnimationEvent.END, e -> commandLine.enableRun());
	}
	
	
	private void positionNodesInView()
	{
		addViewableNodesToScene();
		screen.positionViewOnScreen();
		consoleReturnValues.alignLeft();
		titleView.positionViewOnScreen();
		statusTable.positionViewOnScreen();
		languageComboBox.positionViewOnScreen();
		commandLine.positionViewOnScreen();
		pastCommands.positionViewOnScreen();
		errorText.positionViewOnScreen();
		consoleWithVariables.positionViewOnScreen();
		consoleUserDefinedInstructions.positionViewOnScreen();

	}
	
	private void addViewableNodesToScene()
	{
		root.getChildren().addAll(commandLine.getNode(),
				commandLine.getRunButton(), errorText.getNode(),
				pastCommands.getNode(),consoleWithVariables.getNode(),
				consoleUserDefinedInstructions.getNode(), screen.getNode(),
				consoleReturnValues.getNode(), languageComboBox.getNode(),backgroundColorComboBox.getNode(),backgroundColorComboBox.getSmallIconImage(),penColorComboBox.getNode(),
				penColorComboBox.getSmallIconImage(),statusTable.getNode(), titleView.getNode());

	}
}