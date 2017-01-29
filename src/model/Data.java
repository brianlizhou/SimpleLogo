package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import view.SimpleImage;
/**
 * Data objects store all of the data belonging to a specific SLogo environment
 * It has methods for getting and setting these attributes
 * Each SLogo environment has exactly one Data object which is passed to each Command so they can potentially change the data and affect the environment
 * Data objects are created by the SLogoController
 * Most of the Data attributes are ObservableProperties so that the Controller can set change listeners on them
 * This follows the observable design patterns so that updates in the backend are automatically registered on the frontend
 * @author Aaron Chang, Yumin Zhang
 *
 */
public class Data {

	private ObservableMap<String, Object> variableData;
	private TurtleManager turtleManager;
	private String language;
	private ObservableMap<Integer, int[]> colorMap;
	private ObservableMap<Integer, SimpleImage> turtleImageMap;
	private SimpleIntegerProperty backgroundColor; 
	
	private ObservableMap<String, String> userDefinedInstruction; // mapping command to the actual operation
	public Data() {	
		reinitialize();
	}
	
	/**
	 * essentially a constructor, it resets all of the data as new
	 * This is called whenever the user wants to reset the environment
	 */
	public void reinitialize(){
		variableData = FXCollections.observableHashMap();
		turtleManager = new TurtleManager();
		colorMap = FXCollections.observableHashMap();
		userDefinedInstruction = FXCollections.observableHashMap();
		backgroundColor = new SimpleIntegerProperty();
		backgroundColor.set(0);
		turtleImageMap = FXCollections.observableHashMap();
		//populate turtleImageMap with initial values
	}

	public ObservableMap<String,Object> getVariableData(){
		return variableData;
	}
	
	public ObservableMap<String,String> getDefinedInstructions(){
		return userDefinedInstruction;
	}
	
	public ObservableMap<Integer, int[]> getColorMap() {
		return colorMap;
	}
	
	public ObservableMap<Integer, SimpleImage> getTurtleImageMap() {
		return turtleImageMap;
	}
	
	
	/**
	 * assumes r,g, and b are valid ints
	 * the Command that calls this method does the error checking
	 */
	public void addColor(int index, int r, int g, int b) {
		int[] rgb = {r,g,b};
		colorMap.put(index, rgb);
	}
	
	public IntegerProperty getBackgroundColorIndex() {
		return backgroundColor;
	}
	public void setBackgroundColor(int newColorIndex) {
		if (colorMap.containsKey(newColorIndex)) {
			backgroundColor.set(newColorIndex);
		}
		else {
			throw new RuntimeException("Index " + newColorIndex + " has not been added to the palette");
		}
	}

	public Object getVariableValue(String variableName){
		if(!variableData.keySet().contains(variableName))
			return "No such vairable";   // make this a constant in command class, add to property file

		return variableData.get(variableName);
	}

	public void setVariableValue(String variableName, Object value){
		//System.out.println("--------setVariable----------");
		//System.out.println(variableName + " " + value);
		//System.out.println(variableData.keySet().isEmpty());
		
		variableData.put(variableName, value);		
		//System.out.println("getValue: " + getVariableValue( variableName));
	}


	public void addUserInstruction(String userCommand, String operation){
		userDefinedInstruction.put(userCommand, operation);
	}
	
	public void setLanguage(String s){
		this.language = s;
	}

	public String getLanguage(){
		return this.language;
	}

	public TurtleManager getTurtleManager() {
		return this.turtleManager;
	}




}
