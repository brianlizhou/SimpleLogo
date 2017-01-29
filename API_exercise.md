 API Exercise

Analysis of CellSociety_Team07

Simulation API
> Internal
	> All of the Structure except for get Location
> External
	> ColorMap class

Configuration API
external
public abstract class Data { 
    public String getMyDataType()
    public String getMyTitle()
    public String getMyAuthor()
    public void setMyDataType (String myDataType) 
    public void setMyTitle (String myTitle) 
    public void setMyAuthor (String myAuthor) 
    public void setMyNumRows (String myNumRows) 
    public void setMyNumCols (String myNumCols) 
    public void setDefaults(String numRows, String numCols)
    public abstract String toString();
}
 
public class XMLParser { 
      public Element getRootElement (String xmlFilename) 
}
 

public class DataXMLFactory extends XMLFactory { 
      public DataXMLFactory () 
    public Data getData (Element root) throws XMLFactoryException
}

public abstract class XMLFactory { 
  }

 

internal


public class FireData extends Data{ 
      public FireData (String title,
    public int getMyNumRows () 
    public int getMyNumCols () 
    public double getMyProbCatch () 
    public Point getMyInitialFire() 
    public String toString () 
}
 

public class LifeData extends Data{ 
      public LifeData (String title,
    public int getMyNumRows () 
    public int getMyNumCols () 
    public String toString () 
}
 

public class PredData extends Data{ 
      public PredData (String title,
    public int getMyNumRows () 
    public int getMyNumCols () 
    public int getMyFishBreed () 
    public int getMySharkBreed () 
    public int getMyFishStarve () 
    public int getMySharkStarve () 
    public String toString () 
}
 

public class SegregationData extends Data{ 
      public SegregationData (String title,
    public int getMyNumRows () 
    public int getMyNumCols () 
    public double getMyThreshold () 
    public String toString () 
}
 

public class SlimeMoldData extends Data{ 
          public SlimeMoldData (String title,
        public int getMyNumRows () 
        public int getMyNumCols () 
        public double getMyDepositRate () 
        public double getMyEvaporationRate () 
        public String toString () 
}

 


public class XMLParserException extends RuntimeException { 
      public XMLParserException (String message, Object ... values) 
    public XMLParserException(String message)
    public XMLParserException (Throwable cause, String message, Object ... values) 
    public XMLParserException (Throwable cause) 
}

Visualization API
> Internal
	> 
> External
	> updateView() 
````
public class Cell { 
// internal stuff
  	public static final Cell OUT_OF_BOUNDS = new Cell(State.OUT_OF_BOUNDS);
	public Cell(State state) 
	public boolean isValid()
	public State getState() 
	public Neighborhood getNeighborhood() 
	public void setState(State state) 
	public boolean equals(Object obj) 
// external stuff
	public void resetNeighborhood()
}
}

// External and Internal because the Controller and the View
public class ColorMap  
  	public ColorMap(Map<State,Color> stateColorPairings)
	public Color getColor(State state)
}

// Internal: Only the Controller needs to know about this
public class SceneManager { 
  	public SceneManager(Stage primaryStage) 
}

// Both: The ColorMap needs the State
public class State 
  	public static final State OUT_OF_BOUNDS = new State("OUT_OF_BOUNDS");
	public State(String s) 
	public String toString()
	// external 
	public int hashCode()
	public boolean equals(Object obj)
}
````


