package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.ToDoubleFunction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
/**
 * TurtleManager objects contain all Turtles for a SLogo environment
 * It has methods to set the active Turtles and execute Commands on each active turtle
 * It is an attribute of Data objects, so it is passed into each Command
 * @author Aaron Chang, Yumin Zhang
 *
 */
public class TurtleManager {
	public static final int FIRST_INDEX = 1;
	
	private ArrayList<Turtle> activeTurtles;
	private ObservableMap<Integer, Turtle> allTurtles;
	private Turtle activeTurtle;
	
	public TurtleManager() {
		allTurtles = FXCollections.observableHashMap();
		activeTurtles = new ArrayList<Turtle>();
		//create initial turtle
		Turtle firstTurtle = new Turtle(FIRST_INDEX);
		allTurtles.put(FIRST_INDEX, firstTurtle);
		activeTurtles.add(allTurtles.get(FIRST_INDEX));
		activeTurtle = firstTurtle;
	}
	
	public Turtle getFirstTurtle() {
		return allTurtles.get(1);
	}
	
	public Turtle getActiveTurtle() {
		return activeTurtle;
	}
	
	/**
	 * called by AskWith command to temporarily set active turtle
	 * assumes that turtle exists already in the turtleManager
	 * @param turtleID
	 */
	public void setActiveTurtle(int turtleID) {
		if (allTurtles.containsKey(turtleID)) {
			activeTurtle = allTurtles.get(turtleID);
		}
	}
	
	/**
	 * gets an ObservableList of all turtles in the TurtleManager
	 * Used by Controller for binding
	 */
	public ObservableMap<Integer, Turtle> getAllTurtles() {
		return this.allTurtles;
	}
	
	
	public void addTurtle(int index) {
		allTurtles.put(index, new Turtle(index));
	}
	
	public void setActiveTurtles(Collection<Integer> newTurtles) {
		activeTurtles.clear();
		for (int index : newTurtles) {
			activeTurtles.add(allTurtles.get(index));
		}
	}
	
	public List<Integer> getActiveTurtlesIndices() {
		List<Integer> indices = new ArrayList<Integer>();
		for (Turtle turtle : activeTurtles) {
			indices.add(turtle.getID());
		}
		return indices;
	}
	
	/**
	 * this method takes a functional interface and applies it to each Turtle in the 'active' list
	 * takes in a functional interface parameter 
	 * used by Turtle Commands to apply the executeOnTurtle method to each active Turtle
	 * @param ToDoubleFunction<Turtle> - a method
	 * @return double - return value of the command on the last active turtle
	 */
	public double executeOnAllActive(ToDoubleFunction<Turtle> command) throws Exception{
		double returnVal = 0;
		for (int i = 0; i < activeTurtles.size(); i++) {
			activeTurtle = activeTurtles.get(i);
			returnVal = command.applyAsDouble(activeTurtles.get(i));
		}
		
		//returns the value from the last turtle 
		return returnVal;
	}
}

