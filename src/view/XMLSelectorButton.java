package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import communication.ReadXML;



public class XMLSelectorButton implements Viewable{
	private static final File INITIAL_DIRECTORY = new File("data");
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	private static final double xCoord = SLogoView.PADDING_TOP * 31;
	private static final double yCoord = SLogoView.PADDING_TOP * 3;
	
	private String fileName;
	private ReadXML xmlData;
	private Button xmlButton;
	private CommandLineView commandLine;


	public XMLSelectorButton(CommandLineView commandLine) {
		this.commandLine = commandLine;
		this.xmlButton = new Button("Import XML File");
		initXMLButton();
	}
	
	public void positionViewOnScreen(){
		this.xmlButton.setLayoutX(xCoord);
		this.xmlButton.setLayoutY(yCoord);
	}	

	@Override
	public Node getNode() {
		return this.xmlButton;
	}

	/**
	 * Creates the file directory to choose an xml simulation file to run
	 */
	public void initXMLButton() {
		setOnClickListenerForButton();
	}

	/**
	 * make sure change from the cell society's code
	 */
	public String getFileName() {
		return fileName;
	}

	private void createFileDirectory() {
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(INITIAL_DIRECTORY);
		chooser.setTitle("Choose File");
		File selectedFile = chooser.showOpenDialog(new Stage());
		if (selectedFile != null) {
			fileName = selectedFile.toString();
			xmlData = new ReadXML(fileName);
			commandLine.setCommandLineContent(xmlData.readCommand());
		}
	}
	
	private void setOnClickListenerForButton(){
		this.xmlButton.setOnAction(new EventHandler<ActionEvent>() {
			   @Override public void handle(ActionEvent e) {
				       createFileDirectory();
			        }
			    }
			);
	}	
}
