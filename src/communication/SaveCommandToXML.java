package communication;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SaveCommandToXML {
	private String singleLineCommand;
	
	public SaveCommandToXML(String[] commands, String outputFileName) {
		singleLineCommand = arrayToSingle(commands);
		writeXML(outputFileName);
	}

	private String arrayToSingle(String[] commands){
		StringBuilder result = new StringBuilder();
		for(String s : commands){
			result.append(s);
			result.append(" ");
		}
		return result.toString().trim();
	}
	
	
	/**
	 * create a new xml in data folder 
	 * write the command into xml file created as a single line
	 */
	public void writeXML(String fileName){
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("root");
			doc.appendChild(rootElement);

			Element currentState = doc.createElement("command");
			currentState.appendChild(doc.createTextNode(this.singleLineCommand));
			rootElement.appendChild(currentState);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("data/"+fileName));

			transformer.transform(source, result);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe){
			tfe.printStackTrace();
		}

	}
	
}
