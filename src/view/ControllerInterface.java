package view;

import javafx.scene.paint.Color;
/**
 * This is an interface of the SLogoController that gives access only to a small subset of required methods
 * @author Brian
 */
public interface ControllerInterface {
	public void setLanguage(String language);
	public int[] indexNewColor(Color color);
	
}
