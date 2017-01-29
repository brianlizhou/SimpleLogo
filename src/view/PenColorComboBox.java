package view;

import communication.RunEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;


//This entire file, along with ComboBoxColors.java is part of my masterpiece.
//BRIAN ZHOU
	/** 
	* I don't want to kill you with words so most of this header is going to be a focused/restated version of my masterpiece header in ComboBoxColors.
	* That being said, what's different and interesting about this class is that I tried to separate different parts of MVC as much as possible, thereby incorporating
	* both Observables and RunEvents in this class. In doing this, the binded values made it so that I didn't need to directly interact with the model, and I could communicate with the controller
	* indirectly by using RunEvents that signaled a change in observable values either by the user interactions(ComboBox selection) or some command.
	* Beyond the usage of Events & Observables for MVC separation, this class also uses the generalization from inheritance as stated in ComboBoxColors to generalize and reuse as much code as possible
	* within the hierarchy of ComboBoxes. Furthermore, in this class the generics are used in that they take in a specific parameter, and can manipulate that parameter in order to get the proper values while returning
	* generic values of the ComboBoxes or other objects. Finally, I feel that the code is relatively organized and clean, in that there are no setters or getters, a good hierarchy with different levels interacting with each other
	* with corresponding methods, no duplicated code, and clear-cut interfaces/extensions to indicate what parts of the code do what.
	* 
	* TLDR: Class is final subclass, implements interface for GUI, and uses methods from all levels of hierarchy to create final color selection functionality
	* What I'm Proud Of / What I Learned:
	* 1. Similarly to ColorComboBox, I'm proud of the inheritance hierarchy and how this class/end result uses all of the classes in the hierarchy while maintaining functionality, thereby separating out which code does what function
	* while duplicating as little code as possible (generalizing most of it)
	* 2. Completely isolate out the MVC (especially view and controller) by using Events and Observables, and further separate by using the absolutely required methods from the interface that is passed in to this class's superclass
	* 3. Uses generics to generalize inputs and behavior to give user more flexibility, while implementing interface to highlight which part of the code is specifically for the Viewable (GUI)
	* 4. No duplicated code, no obvious code smells, clear-cut interfaces/inheritance to show what code does what, no setters/getters, and uses functions from all levels of hierarchy for maximum generalization and minimization of code
	* Learned about Observables and Events to minimize interaction between different levels of MVC, as well as inheritance/interfaces and importance of generalizing code while highlighting the functionality of each level of the hierarchy. Finally,
	* also learned about the power of generics and other concepts of design patterns as stated in the Masterpiece header of ComboBoxColors
*/

//---- End Masterpiece Header


/**
 * Generic class that is responsible for the implementation and addition of the pen color ComboBox as seen
 * in the view, and is connected to the controller through interface in superclass for real-time modification
 * 
 * @author Brian
 * 
 * @method getNode
 * @method updateColor
 * @implements Viewable interface, which requires a Node return value to add to the View
 * @extends ComboBoxColors generic class that holds most generalized methods that all color selection combo boxes will utilize
 */

public class PenColorComboBox<T> extends ComboBoxColors<T> implements Viewable{
	private static final ComboBox<String> colorComboBox = new ComboBox<>();
	private ConsoleTitleView<T> title;
	private ControllerInterface controller;
	
	/**
	 * Constructor
	 * @param title Title text for the color ComboBox - pen color
	 * @param controller Interface of SLogoController that only gives access to a few methods
	 */
	public PenColorComboBox(ConsoleTitleView<T> title, ControllerInterface controller){  
		this.controller = controller;
		this.title = title;
        setOptions(this.colorComboBox);
        setUpListenerForSelection();
        setBackgroundColorsOfOptions(this.colorComboBox);
        positionViewOnScreen(TurtleColorComboBox.xCoord,TurtleColorComboBox.yCoord,this.colorComboBox);
        setImage(IMAGE_RADIUS,IMAGE_RADIUS, TurtleColorComboBox.xCoordIcon,TurtleColorComboBox.yCoordIcon,TurtleColorComboBox.colorWheelImage);
	}
	
	/**
	 * Returns the node for this class (the ComboBox)
	 * @return Node ComboBox for pen color
	 */
	@Override
	public ComboBox<?> getNode(){
		return this.colorComboBox;
	}
	
	/**
	 * Updates the current color state of the ComboBox and state
	 */
	public void updateColor(){
		if(title != null) this.title.updateColor(selectedColor);
	}
	
	private void setUpListenerForSelection(){
		this.colorComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String newString) {                
                selectedColor = Color.valueOf(newString); updateColor();
                int[] newColorRGB = controller.indexNewColor(selectedColor);
                StringBuilder newCommand = buildNewRGBString(newColorRGB);
                fireRunEvent(newCommand.toString(),newColorRGB[0]);
            }    
        });
	}
	
	private void fireRunEvent(String newCommand, int indexOfColor){
		this.colorComboBox.fireEvent(new RunEvent(RunEvent.RUN,newCommand));  
        this.colorComboBox.fireEvent(new RunEvent(RunEvent.RUN,TurtleColorComboBox.SET_PC + String.valueOf(indexOfColor)));
	}
}
