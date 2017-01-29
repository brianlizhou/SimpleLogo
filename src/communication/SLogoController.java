package communication;

import model.Data;
import model.Parser;
import javafx.stage.Stage;
import model.Turtle;
import model.TurtleManager;
import view.SimpleImage;
import view.ControllerInterface;
import view.SLogoView;
import view.TurtleView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.property.MapProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

/**
 * The SLogoController class acts as a mediator between the SLogoView and the SLogoLogic class. It passes information back and forth between
 * the two classes. The SLogoController will also have a listener that waits for the "enter command" in the SLogoView to be triggered. At that
 * instant the SLogoController will get the input from the user.
 * One of the main responsibilities of the SLogoController is to set change listeners for turtle properties and environment properties
 * Change listeners are the primary method for passing information from the backend to front end
 * When properties are modified in the backend, the frontend objects associated with those properties are automatically updated
 * @author Aaron Chang, Ryan Bergamini
 */
public class SLogoController implements ControllerInterface
{
	private Stage stage;
	private SLogoView view;
	private Data data;
	private Parser myParser;
	//private Turtle myTurtle; //create hashmap mapping Turtles to 
	private TurtleManager turtleManager;
	private HashMap<Turtle, TurtleView> turtles;
	//private TurtleView myTurtleView;
	private Map<String,Object> variables;
	private Map<String,String> definedInstructions;
	private Map<Integer, int[]> colorPalette; //keys are index, values are rgb values
	private Map<Integer, SimpleImage> turtleImages;
	
	
	/**
	 * Creates a new SLogoController
	 * @param stage- the Stage object for the application
	 */
	public SLogoController(Stage stage)
	{
		data = new Data();
		data.setLanguage("English"); //change so that language can be selected via combo box
		myParser = new Parser(data); //change so the language can be chosen by user
		turtles = new HashMap<Turtle, TurtleView>();
		configureSLogoController(stage);
		turtleManager = data.getTurtleManager();
		colorPalette = new HashMap<Integer, int[]>();
		setEnvironmentListeners();
		Turtle firstTurtle = turtleManager.getFirstTurtle();
		addTurtle(firstTurtle);
		turtleImages = new HashMap<Integer, SimpleImage>();
	}
	
	public void setLanguage(String language){
		data.setLanguage(language);
	}
	
	
	/**
	 * Called by the Main class to start running the SLogoSimulation
	 */
	public void startSLogoSimulation()
	{
		stage.show();
		startUserInputListener();
	}
	
	private void addTurtle(Turtle turt)
	{
		TurtleView turtleView = new TurtleView(turt.getID());
		turtles.put(turt, turtleView);
		setTurtleListeners(turt);
		view.addTurtleView(turtleView);
	}
	
	private int checkIfColorAlreadyIndexed(int[] RGB){
		if(colorPalette == null) colorPalette = new HashMap<>();
		for(Integer elem:colorPalette.keySet()){
			outerloop:
			for(int i = 0;i < colorPalette.get(elem).length; i++){
				//System.out.println("Running " + colorPalette.get(elem)[i]);
				//System.out.println("Running2 " + RGB[i]);
				if(colorPalette.get(elem)[i] == RGB[i]){
					if(i == colorPalette.get(elem).length-1) {
						return elem;
					}
				}
				else{
					break outerloop;
				}
					
			}
		}
		return -1;
		
	}
	
	public int[] indexNewColor(Color newColor){
		//System.out.println(newColor.getRed());
		int red = (int)(newColor.getRed() * 255 );
		int blue =  (int)(newColor.getBlue() * 255 );
		int green =  (int)(newColor.getGreen() * 255 );
		int[] colorArray = {red,green,blue};
		int indexOfColor = checkIfColorAlreadyIndexed(colorArray);
		int[] result = new int[4];
		result[1] = red; result[2] = green; result[3] = blue;
		if(indexOfColor == -1){
			int newIndex = colorPalette.keySet().size()+1;
			result[0] = newIndex;
			colorPalette.put(newIndex, colorArray);
			return result;
		}
		result[0] = indexOfColor;
		return result;
	}
	
	/**
	 * sets ChangeListeners for objects in the development environment (e.g., turtleList, variables, background color, etc.)
	 */
	private void setEnvironmentListeners() {
		//listener on list of all turtles
		turtleManager.getAllTurtles().addListener(new MapChangeListener<Integer, Turtle>() {
			@Override
			public void onChanged(javafx.collections.MapChangeListener.Change<? extends Integer, ? extends Turtle> c) {
				Turtle turt = c.getValueAdded();
				addTurtle(turt);
			}
		});
		//listener on the list of variables
		data.getVariableData().addListener(new MapChangeListener<String, Object>() {

			@Override
			public void onChanged(
					javafx.collections.MapChangeListener.Change<? extends String, ? extends Object> change) {
				updateVariableData();	
			}
			
		});
		
		data.getDefinedInstructions().addListener(new MapChangeListener<String, String>() {

			@Override
			public void onChanged(
					javafx.collections.MapChangeListener.Change<? extends String, ? extends String> change) {
				updateMethodData();	
			}
			
		});
		
		data.getColorMap().addListener(new MapChangeListener<Integer, int[]>() {

			@Override
			public void onChanged(
					javafx.collections.MapChangeListener.Change<? extends Integer, ? extends int[]> change) {
				colorPalette = data.getColorMap();
			}
			
		});
		
		data.getTurtleImageMap().addListener(new MapChangeListener<Integer, SimpleImage>() {

			@Override
			public void onChanged(
					javafx.collections.MapChangeListener.Change<? extends Integer, ? extends SimpleImage> change) {
				turtleImages = data.getTurtleImageMap();	
			}
			
		});
		
		data.getBackgroundColorIndex().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				int[] rgb = colorPalette.get(newValue.intValue());
				Color newColor = Color.rgb(rgb[0], rgb[1], rgb[2]);
				view.updateBackgroundColor(newColor);
			}
			
		});
	}
	
	/**
	 * After retrieving the input from the SLogoView, the SLogoController will convert the input into Commands in the form of strings that
	 * the SLogoLogic will apply to the Applicaiton's internals.
	 * @param input- the raw String input retrieved from the SLogoView
	 * @return the command or error that is parsed from the input
	 */
	private List<Packet> parse(String[] input)
	{
		myParser.receiveCommand(input);
		List<Packet> packets = myParser.run();
		return packets;
	}
	
	private void updateVariableData(){
		variables = data.getVariableData();
		view.updateVariables(variables);
	}
	
	private void updateMethodData(){
		definedInstructions = data.getDefinedInstructions();
		view.updateDefinedInstructions(definedInstructions);
	}
	
	
	public String[] formatUserInputIntoArray(String userInput)
	{
		return userInput.split("\n");
	}
	
	private void configureSLogoController(Stage stage)
	{
		this.stage = stage;
		view = new SLogoView(this);
		this.stage.setScene(view.getScene());
	}
	/**
	 * takes a Turtle as an input and sets change listeners on all the Turtle properties
	 * updates the TurtleView objects associated with each Turtle turtle object 
	 * it is called whenever a new turtle is created (and assumes that a TurtleView object is created and they are added to the turtles hashmap)
	 * @param turtle - new Turtle to be added
	 */
	private void setTurtleListeners(Turtle turtle) {
		turtle.getHeadingProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				turtles.get(turtle).updateHeading(oldValue.doubleValue(), newValue.doubleValue());	
				view.updateHeadingStatus(newValue.doubleValue());
			}
		});
		
		turtle.getPositionProperty().addListener(new ChangeListener<Coordinate>(){
			@Override
			public void changed(ObservableValue<? extends Coordinate> observable, Coordinate oldValue, Coordinate newValue) {
				turtles.get(turtle).updatePosition(oldValue, newValue);	
				view.updatePositionProperty(newValue.getX(),newValue.getY());
			}
			
		});
		
		turtle.getPenDownProperty().addListener(new ChangeListener<Boolean>(){
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				turtles.get(turtle).updatePenUpDown(oldValue.booleanValue(), newValue.booleanValue());	
				view.updatePenStatus(newValue);
			}
			
		});
		
		turtle.getVisibilityProperty().addListener(new ChangeListener<Boolean>(){
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				turtles.get(turtle).updateVisibility(oldValue.booleanValue(), newValue.booleanValue());	
			}
		});
		turtle.getPenColor().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				int index = newValue.intValue();
				int[] rgb = data.getColorMap().get(index);
				Color newColor = Color.rgb(rgb[0], rgb[1], rgb[2]);
				turtles.get(turtle).updatePenColor(newColor);
			}
			
		});
		turtle.getPenSize().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) 
			{	
				turtles.get(turtle).updatePenSize(newValue.doubleValue());
			}
		});
		
		turtle.getShapeProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				SimpleImage newImage = turtleImages.get(newValue.intValue());
				turtles.get(turtle).updateTurtleViewImage(newImage);
			}
			
		});
	}
	
	
	private void startUserInputListener()
	{
		stage.addEventHandler(RunEvent.RUN,new EventHandler<RunEvent>(){
			public void handle(RunEvent re)
			{
				interpretUserInput(re.getUserInput());
			}
		});
		
		stage.addEventHandler(ConfigurationEvent.RESET, e -> resetSimulation());
	}
	
	private void resetSimulation()
	{
		view.reset();
		data.reinitialize();
		setEnvironmentListeners();
		addTurtle(data.getTurtleManager().getFirstTurtle());
	}
	
	private void interpretUserInput(String userInput)
	{
		String[] inputArray = formatUserInputIntoArray(userInput);
		List<Packet> commandResults = parse(inputArray);
		//loops through each packet and calls view.loadPacket on that packet

		commandResults.forEach(view::loadPacket);
		
		//updateVariableData();
		//view.updateVariables(variables);
		//System.out.println(input);
		//System.out.println(Arrays.toString(formatUserInputIntoArray(input)));
	}

}
