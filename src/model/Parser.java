package model;
import commands.Command;
import communication.Packet;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Data;


public class Parser {

	private String[] command;
	private int currentIndex;
	private String currentArg;
	private TypeChecker checker;
	private TypeChecker commandChecker;
	private List<Packet> packets;
	private Packet currentPacket;
	private Data data;
	private int startIndex; 
	private final String WHITE_SPACE = "\\s+";
	private final String ONE_SPACE = " ";
	private final char COMMENT_SIGN = '#';


	/**
	 * Parser serves as the main back end class that parses the input command
	 * and calls individual command class to run the logic on the command
	 * 
	 * @param language is the language for property file
	 * @param data contains the variable values and turtle object
	 * should only constructs when 
	 */
	public Parser(Data data) {		
		checker = new TypeChecker();
		checker.addPatterns("resources/languages/Syntax");  
		commandChecker = new TypeChecker();
		commandChecker.addPatterns("resources/languages/" + data.getLanguage()); 
		this.data = data;
	}


	public void receiveCommand(String[] command){
		commandChecker = new TypeChecker();
		commandChecker.addPatterns("resources/languages/" + data.getLanguage()); 
		preprocess(command);
		packets = new ArrayList<Packet>();
		currentIndex = 0;
		startIndex = 0;
	}

	public Map<String,Object> getVariableData(){
		return data.getVariableData();
	}

	private String[] grouping(String singleLineCommand){
		String[] roughGroup = singleLineCommand.split(WHITE_SPACE);
		ArrayList<String> result = new ArrayList<String>();
		StringBuilder currentList;
		try{			
			int i = 0;
			while( i < roughGroup.length){
				if(roughGroup[i].charAt(0) == '['){
					currentList = new StringBuilder();
					currentList.append(roughGroup[i] + " ");
					while (roughGroup[i+1].charAt(0) != ']') {	  // can factor out a function
						i++;
						currentList.append(roughGroup[i] + " ");
					}
					i++;
					currentList.append(roughGroup[i]);
					result.add(currentList.toString().trim());
					i++;
				}

				else if(roughGroup[i].charAt(0) == '('){
					//System.out.println("find ( at index " + i );

					currentList = new StringBuilder();
					currentList.append(roughGroup[i] + " ");
					while (roughGroup[i+1].charAt(0) != ')') {	  // can factor out a function
						i++;
						currentList.append(roughGroup[i] + " ");
					}
					i++;
					currentList.append(roughGroup[i]);
					result.add(currentList.toString().trim());
					i++;
				}

				else{
					result.add(roughGroup[i]);
					i++;
				}
			}

		}
		catch(Exception e){
			currentPacket.addErrorMessage(e.getMessage());
		}



		String[] resultCommand = new String[result.size()];
		for(int j = 0 ; j < result.size(); j++){
			resultCommand[j] = result.get(j);
		}

		return resultCommand;
	}

	private String tryToRemoveBracket(String s){
		s = s.trim();
		if(s.length() > 0 && s.charAt(0) == '['){
			s = s.substring(1, s.length() -1).trim();
		}
		return s;
	}

	private void preprocess(String[] command){	
		try{
			StringBuilder commandgroup = new StringBuilder();
			for (String s: command){
				s = tryToRemoveBracket(s);
				if(s.length() > 0 && s.charAt(0) != COMMENT_SIGN){  
					commandgroup.append(s);
					commandgroup.append(ONE_SPACE); // make white space to property file
				}	
			}
			this.command = grouping(commandgroup.toString().trim());
		}catch(Exception e){
			currentPacket.addErrorMessage(e.getMessage());
		}

	}

	private String getCurrentCommand(){
		StringBuilder currentComm = new StringBuilder();
		for(int i = startIndex; i <= currentIndex; i++){
			currentComm.append(command[i]);
			currentComm.append(" ");
		}	
		return currentComm.toString().trim();
	}
	

	private Class<?> nameToClass(String commandName){
		Class<?> comm = null;
		try {
			comm = Class.forName("commands." + commandName);

		} catch (ClassNotFoundException e) {
			currentPacket.addErrorMessage(e.getMessage());
		}
		return comm; 
	}
	

	private Object createInstanceofClass(Class<?> comm){
		Object o = null;
		try {
			Constructor<?> ctor = comm.getDeclaredConstructor(Data.class);
			o = ctor.newInstance(data);
		}		
		catch (Exception e) {
			currentPacket.addErrorMessage(e.getMessage());
		}		
		return o;		
	}



	private Boolean typeMatch(String currentArg, Command currentCommand){
		String actualType = checker.getSymbol(currentArg);
		String expectedType = currentCommand.getNextArgType();
		if(actualType.equals("Variable") && expectedType  == "Constant"){
			try{
				this.currentArg = data.getVariableValue(currentArg).toString();
				return true;
			}catch(Exception e){
				currentPacket.addErrorMessage("cannot get the variable value");
			}
		}


		
		return expectedType.equals(actualType);
	}


	public List<Packet> run(){
		while (currentIndex < command.length){
			currentPacket = new Packet();
			currentPacket.setCommandType(commandChecker.getSymbol(command[currentIndex]));
			currentPacket.setReturnValue(process()); 
			currentPacket.setDisplayingCommand(getCurrentCommand());
			startIndex = currentIndex + 1;
			packets.add(currentPacket);
			currentIndex++;
		}
		return packets;
	}




	private double process(){
		if(command[0].isEmpty()){
			currentPacket.addErrorMessage("Empty Command"); // move to property file
			return 0;
		}

		else if(!checker.getSymbol(command[currentIndex]).equals("Command")){
			if(packets.size() > 0){
				packets.get(packets.size() - 1).addErrorMessage("Syntax Error");
			}
			if(currentIndex + 1 < command.length){
				currentIndex++;
				startIndex++;
				return process();
			}

			return 0;  // skipping an argument
		}

		else{
			String currentRoot = commandChecker.getSymbol(command[currentIndex]);

			if(currentRoot.equals("NO MATCH")){
				currentPacket.addErrorMessage("invalid command name");
				if(currentIndex + 1 < command.length){
					currentIndex++;
					startIndex++;
				}
				return 0;
			}

			Class<?> comm = nameToClass(currentRoot);
			Object obj = createInstanceofClass(comm);
			Command currentCommand = (Command) obj;

			if(!currentCommand.hasArg()){
				while(currentCommand.hasNextArg() && currentIndex < command.length - 1 ){				
					currentIndex++;		
					this.currentArg = command[currentIndex];	
					

					if(typeMatch(this.currentArg, currentCommand)){
						currentCommand.setNextArg(this.currentArg);
					}			
					else{
						currentCommand.setNextArg(Double.toString(process()));
					}
				}
			}



			try {
				return currentCommand.execute();
			} catch (Exception e) {
				currentPacket.addErrorMessage("Syntax error");
				return 0;
			}

		}

	}
}