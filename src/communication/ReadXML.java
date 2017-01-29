package communication;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ReadXML {
	private String dataFilePath;

	public ReadXML(String dataFilePath) {
		this.dataFilePath = dataFilePath;
	}


	public String getDataFilePath() {
		return dataFilePath;
	}

	public void setDataFilePath(String dataFilePath) {
		this.dataFilePath = dataFilePath;
	}


	/**
	 * read in Document from xml
	 */
	public Document setUpDocumentToParse() throws ParserConfigurationException, SAXException, IOException{
		File file = new File (dataFilePath);
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(file);

		return document;
	}


	public String readCommand(){
		try {
			Document document = setUpDocumentToParse();
			return document.getElementsByTagName("command").item(0).getTextContent().trim(); // change command to property file 	
		}
		catch (Exception e) {
			return "command tag is missing";
		}
	}

}
