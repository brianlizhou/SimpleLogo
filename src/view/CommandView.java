package view;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import communication.LoadCommandEvent;
import communication.RunEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

//This entire file is part of my masterpiece.
//RYAN BERGAMINI

// (See ConsoleView to find an implementation of the Builder pattern in use)
// I didn't actually write that class so I didn't include it in my masterpiece

/**
 * This class determines how a past command is viewed in the PastCommandsView
 * @author Ryan Bergamini
 * Recieved help on the Builder pattern from:
 * http://javarevisited.blogspot.com/2012/06/builder-design-pattern-in-java-example.html
 */
public class CommandView implements Viewable
{
	private Color backgroundColor;
	private Color textColor;
	private Color highlightColor;
	
	private Group root;
	private Rectangle background; 
	
	private String commandBlock;
	
	/**
	 * The builder method returns a CommandViewBuilder to build a commandView
	 * @param commandBlock- the command that is being displayed
	 * @return a CommandViewBuilder to build the CommandView for commandBlock
	 */
	public static CommandViewBuilder builder(String commandBlock)
	{
		return new CommandViewBuilder(commandBlock);
	}
	
	/**
	 * Creates a CommandViewBuilder used to build a CommandView
	 * @author Ryan Bergamini
	 */
	public static class CommandViewBuilder
	{
		private Color backgroundColor = Color.GRAY;
		private Color textColor = Color.BLACK;
		private Color highlightColor = Color.YELLOW;
		private String commandBlock;
		
		/**
		 * Creates a new CommandViewBuilder. This constructor takes in a commandBlock because the
		 * commandBlock is absolutely necessary to build a CommandView
		 * @param commandBlock- the command that is being displayed
		 */
		public CommandViewBuilder(String commandBlock)
		{
			this.commandBlock = commandBlock;
		}
		
		/**
		 * Sets the background color of the CommandView to backgroundColor
		 * @param backgroundColor- color to set background to
		 * @return the current CommandViewBuilder
		 */
		public CommandViewBuilder setBackgroundColor(Color backgroundColor)
		{
			this.backgroundColor = backgroundColor;
			return this;
		}
		
		/**
		 * Sets the text color of the CommandView to textColor
		 * @param textColor- color to set text to
		 * @return the current CommandViewBuilder
		 */
		public CommandViewBuilder setTextColor(Color textColor)
		{
			this.textColor = textColor;
			return this;
		}
		
		/**
		 * Sets the highlight color of the CommandView to highlightColor
		 * @param highlightColor- color to set highlight to
		 * @return the current CommandViewBuilder
		 */
		public CommandViewBuilder setHighlightColor(Color highlightColor)
		{
			this.highlightColor = highlightColor;
			return this;
		}
		
		/**
		 * @return a CommandView with the parameters set in the builder
		 */
		public CommandView build()
		{
			return new CommandView(this);
		}
		
	}
	
	
	/**
	 * Creates a new CommandView based on a CommandViewBuilder builder
	 */
	private CommandView(CommandViewBuilder builder)
	{
		this.commandBlock = builder.commandBlock;
		this.textColor = builder.textColor;
		this.backgroundColor = builder.backgroundColor;
		this.highlightColor = builder.highlightColor;
		
		this.root = new Group();
		
		configureViewAppearance();
		configureEventListeners();
	}
	
	/**
	 * Returns the Node that represents the command (typically called by the PastCommandsView)
	 */
	public Node getNode()
	{
		return root;
	}
	
	private void configureViewAppearance()
	{
		Label textLabel = new Label(commandBlock);
		textLabel.setTextFill(textColor);
		Font font = textLabel.getFont();
		background = new Rectangle(getCommandViewWidth(font),getCommandViewHeight(font));
		background.setFill(backgroundColor);
		root.getChildren().addAll(background,textLabel);
	}

	
	private double getCommandViewHeight(Font font)
	{
		Text text = new Text(commandBlock);
		text.setFont(font);
		return text.layoutBoundsProperty().getValue().getHeight();
	}
	
	private double getCommandViewWidth(Font font)
	{
		Text text = new Text(commandBlock);
		text.setFont(font);
		return text.layoutBoundsProperty().getValue().getWidth();
	}

	
	private void highlightView()
	{
		background.setFill(highlightColor);
	}
	
	private void dehighlightView()
	{
		background.setFill(backgroundColor);
	}
	
	private void configureEventListeners()
	{
		root.addEventHandler(MouseEvent.MOUSE_ENTERED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent re)
			{
				highlightView();
			}
		});
		
		root.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent re)
			{
				dehighlightView();
			}
		});
		
		root.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent re)
			{
				root.fireEvent(new LoadCommandEvent(LoadCommandEvent.PAST_COMMAND,commandBlock));
			}
		});
	}
	
}
