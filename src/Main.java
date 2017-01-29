
import java.util.List;
import communication.Packet;
import communication.ReadXML;
import communication.SLogoController;
import communication.SaveCommandToXML;
import javafx.stage.Stage;
import model.Data;
import model.Parser;
import model.Turtle;
import model.TypeChecker;
import javafx.application.Application;


/**
 * The Main class jump starts the application and creates a new SLogoController to manage the application. The sole responsibility
 * of the Main class is to create a SLogoController object then pass all responsibility for the application to it.
 */
public class Main extends Application
{
	public void start(Stage stage)
	{
		SLogoController slogo = new SLogoController(stage);
		slogo.startSLogoSimulation();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}

}