package model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.*;
import java.util.AbstractMap.SimpleEntry;

public class TypeChecker {
	private List<Entry<String, Pattern>> mySymbols;
	
	public TypeChecker() {
		mySymbols = new ArrayList<Entry<String,Pattern>>();
	}
	
	/**
	 * takes regex patterns from a resource file and adds them to mySymbols
	 * keys of mySymbols are strings representing symbol
	 * values are regex expression for each symbol
	 * @param syntax
	 */
	public void addPatterns(String language) {
		ResourceBundle resources = ResourceBundle.getBundle(language);
		Enumeration<String> iter = resources.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			String regex = resources.getString(key);
			//compiles regex string into a Pattern so the matches() method can be used
			mySymbols.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
		}
	}

	
	public String getSymbol(String text) {
		//System.out.println("text: "+ text);
		final String ERROR = "NO MATCH";
		for (Entry<String,Pattern> symbol : mySymbols) {
			if (match(text, symbol.getValue())) {
				return symbol.getKey();
			}
		}
		return ERROR;
	}
	
	//returns true of the text matches the regular expression
    private boolean match (String text, Pattern regex) {
        return regex.matcher(text).matches();
    }
}
