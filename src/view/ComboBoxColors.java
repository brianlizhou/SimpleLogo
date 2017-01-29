package view;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Callback;

//This entire file, along with PenColorComboBox.java is part of my masterpiece.
//BRIAN ZHOU
	/** 
	* This class's purpose is to act as the superclass for the Color ComboBoxes that you see on the main screen (Pen Color & Background Color ComboBox), and it primarily generalizes
	* all of the critical behavior to the comboboxes, in the setting small-icon image, positioning the combobox properly, and finally setting up the background color of all of the color options (personally think it looks better)
	* and so that the user can get a good feel of the color they're picking. 
	* Compared to CellSociety in which I didn't learn much but was exposed to a lot, I feel that I really learned a lot in this project and these two classes
	* encapsulate exactly what I learned. I felt like tossing in Enums just for the sake of "good design" but ended up not doing it because it wasn't necessary for the project.
	* I'm proud of how this class is not only a generic class in that the inputs to the ComboBox options can be other objects (strings usually) to generalize behavior, and both implements an interface
	* while extending another super class. In doing so, most of the general work common to its subclasses is taken care of, and the interface provides consistency to adding the viewable part of the class
	* (the combobox) to the actual View that we see in the GUI.
	* This class also uses abstraction in order to highlight the fundamental methods that must be implemented for it to function, while highlighting the fact that it acts a superclass only for generalization of methods, and cannot
	* be instantiated as it's not a specific UI object that is directly Viewable like the pen color ComboBox or background color ComboBox are.
	* From the hierarchy this class is also a sub-class, so it inherits methods as well; Overall I felt like I learned a tremendous amount about the importance of MVC, as well as how to set up a proper hierarchy for abstraction
	* and generalization of code. In addition, this class uses methods from ALL levels of the inheritance hierarchy, thereby somewhat linking all of the code together and creating some cohesiveness with super and subclasses and generalizing
	* as much code as possible while maintaining functionality.
	* Finally, it doesn't use any setters or getters, and takes in a ControllerInterface (another interface) instead of implementing it, and thus only takes the required methods from the SLogoController class, thereby
	* segmenting off the controller, view, and model so they know as little about each other as possible
	* 
	* TLDR: Class is superclass and abstracts a ton of behavior of sub-classes
	* What I'm Proud Of / What I Learned:
	* 1. Takes in a SLogoController interface as a parameter so that we only take the necessary functions from the SLogoController and not the entire thing, more separation with MVC
	* 2. Finally understand inheritance (kind of) after working with it, and this class generalizes most of the behavior - this class is both a sub and super class in its hierarchy and contains generalized methods that all sub-classes use;
	* The final result (Pen Color ComboBox) uses methods from all of the levels of the inheritance hierarchy, creating cohesiveness while clearly separating out which part/functionality of the ComboBox is located in which super/sub class.
	* 3. Implements an interface such that it separates the actual GUI part that we see from the code, UI stuff in the Viewable and its methods are what we actually see
	* 4. Uses generics to allow user flexibility in passing in ComboBox options - doesn't have to be strings and currently selected values can all be generalized in generics.
	* 5. Uses abstraction in order to clear up the fact that this is an abstract class, won't be instantiated and requires a few abstract methods to be implemented for it to work - generalizes more code while keeping it clear
	* 6. No duplicated code and code (I think) looks relatively clean (no serious code smells) while staying consistent in style, no setters and getters
	* Learned quite a bit about design patterns and importance of separation in MVC, while figuring out and understanding the benefits/beauties of abstraction, inheritance, and generics
*/

//---- End Masterpiece Header

/**
 * Generic Abstract class that all of the Choose-Color ComboBoxes extend, provides general methods 
 * that all ComboBox classes will implement or utilize in View
 * 
 * @author Brian
 * 
 * @method setImage
 * @method positionViewOnScreen
 * @method setBackgroundColorOfOptions
 * @method (Abstract) getNode
 * @method (Abstract) updateColor
 * @extends Colors general variables and adds on all available color options
 * @implements Viewable interface, which requires a Node return value to add to the View
 */

public abstract class ComboBoxColors<T> extends Colors implements Viewable{	
	/**
	 * @param width The desired width of the image
	 * @param height The desired height of the image
	 * @param xCoord x-coordinate of new position
	 * @param yCoord y-coordinate of new position
	 * @param colorWheelImage (String) URL of new image that is being added
     * Sets an the image next to the ComboBox (rainbow square, rainbow circle, etc)
	 */
	public void setImage(int width, int height,double xCoord, double yCoord,String colorWheelImage) {
		Image colorWheel = new Image(getClass().getClassLoader().
				getResourceAsStream(colorWheelImage));
		this.SmallImageIcon = new ImageView(colorWheel);
		setImageSize(width,height);
		positionSmallImage(xCoord,yCoord);
	}
	/**
	 * Gets GUI Node to add to Viewable part of interface
	 * @abstract
	 */
	public abstract Node getNode();	
	
	/**
	 * Updates currently selected color
	 * @abstract
	 */
	public abstract void updateColor();

	/**
	 * Positions the ComboBox at a specific location on the screen
	 * @param xCoord (double) x-coordinate of position
	 * @param yCoord (double) y-coordinate of position
	 * @param colorComboBox(ComboBox<?>) comboBox that we are positioning
	 */
	public void positionViewOnScreen(double xCoord, double yCoord, ComboBox<?> colorComboBox){
		colorComboBox.setLayoutX(xCoord);
		colorComboBox.setLayoutY(yCoord);
	}
	
	/**
	 * Changes the color of all the selections within the ComboBox to be the same color they 
	 * represent in their text; Orange is colored orange.
	 * Sets default color to Green
	 * @param colorComboBox (ComboBox<String>) colorComboBox option that we're manipulating
	 * @return CallBack function returns the cell object in ComboBox to change color
	 */
	public void setBackgroundColorsOfOptions(ComboBox<String> colorComboBox){
		colorComboBox.setValue(TitleText.DEFAULT_STRING);
		colorComboBox.setCellFactory(
				new Callback<ListView<String>, ListCell<String>>() {
                @Override 
                public ListCell<String> call(ListView<String> param) {
                    final ListCell<String> cell = new ListCell<String>() {
                        {
                            super.setPrefWidth(TitleText.CONSOLE_HEIGHT);
                        }                       
                        @Override public void updateItem(String item,boolean empty) {
                                super.updateItem(item, empty);
                                if (item != null) {
                                    setText(item);    
                                    setTextFill(Color.valueOf(item));                     
                                }
                                else {
                                    setText(null);
                                }
                         }
                    };
                    return cell;
                }
        });
	}
	
	/**
	 * Converts the color array into a string version [Index R G B] to IndexRGB
	 * @param newColorRGB (int[]) Integer array representation of RGB value of color and index from mapping
	 * @return newCommand (StringBuilder) SB version of new string created
	 */
	protected StringBuilder buildNewRGBString(int[] newColorRGB){
		StringBuilder newCommand = new StringBuilder(TurtleColorComboBox.SET_PALETTE);
        for(int i=0; i<newColorRGB.length;i++){
        	newCommand.append(" " + newColorRGB[i]);
        }
        return newCommand;
	}
	
	private void setImageSize(int width, int height){
		this.SmallImageIcon.setFitWidth(width);
		this.SmallImageIcon.setFitHeight(height);
	}
	
	private void positionSmallImage(double xCoord, double yCoord){
		this.SmallImageIcon.setLayoutX(xCoord);
		this.SmallImageIcon.setLayoutY(yCoord);
	}
}
