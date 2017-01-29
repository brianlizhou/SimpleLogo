# slogo
### Aaron Chang, Yumin Zhang, Brian Zhou, Ryan Bergamini
A development environment that helps users write SLogo programs.

##### Project Dates/Times

1. Start Date: 10/13/16
2. End Date: 11/1/16
3. Estimate of Hours Worked: ~80

##### Roles
1.  Aaron Chang: Implement Commands and SLogoController
	* My primary role throughout the project was implementing the commands. I worked on the backend with Yumin, so I helped her design the parser but I spent most of my time creating the Command hierarchy and implementing each one. Because many of the commands required updating the turtle, I also worked the Turtle and TurtleManager classes. I also spent some time working on the SLogoController (also written by Ryan). In the controller, I mostly set change listeners on all of the turtle and environment properties.
2.  Yumin Zhang: Implement Parser, Commands and Configurations
    * I worked with Aaron on back end so we together came up with the design of back end. My main role is the Parser which parses the command and calls individual command class to execute. I also did xml input and output part. I have implemented several challenging commands including the control command and creted the feature of allowing infinite parameters. I also had the sketch of the data class, and abstract command class for back end design. 
3.  Brian Zhou: UI Structure and Features Minus Turtle Movement/Canvas & Command Line
    * I worked on the front-end with Ryan, and my primary role was establishing the structure/appearnace of the UI as well as the features on the front-end minus the Command Line input and Turtle canvas stuff. I also worked with Aaron in setting up observables for color-picking within the comboboxes, as well as with Yumin for the XML data and information passing. 
4.  Ryan Bergamini
    * I worked on the front-end with Brian, and did most of the Front-End interaction with the Controller. I spent the majority of my time on this project trying to figure out animation and Change listeners. I also was resposible for how the Turtle was displayed in the Front end, along with how user input would be communicated to the backend.

##### Resources Used 
We use the following resources in the completion of this assignment:

1.  [Regular Expression Parser](https://git.cs.duke.edu/CompSci308_2016Fall/example_advanced)
2.  [Java Math Documentation](https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html)
3.  [Java Change Listeners](https://docs.oracle.com/javase/tutorial/uiswing/events/propertychangelistener.html)


##### Data and Resource Files required by the project
*  Resource files containing all of the regular expression data for different languages, located in resources/languages
*  Image files in the images folder

##### Program Use
The SLogo environment is initialized with English as the default language, green as the default background, and one turtle. The background color, pen color, and language can be changed via text command or from the dropdown menus. Variable created by the user are displayed in the Variables box, and past commands are displayed in the Previous Commands box. Users can click on previous commands to load them into the input terminal and run them.

##### Known Bugs
No. 

##### Extra features
*  Toroidal screen
*  infinite parameters 

##### Impressions
1.  Aaron Chang
	*  I thought this assignment was a good way to practice using the Model-View-Controller design.  The turtle objects were also a great example of when to use the observable design principle.  Also, the variety of commands pushed the backend to write flexible code that could support all the different types.  This required a lot of design discussions on the parser and the Command hierarchy.
2.  Yumin Zhang
    * I think this assignment makes me learn and practice a lot refeatures in java, including lambda function, binding, reflection, etc. We have also though a lot in terms of design principle. We had a very clear MVC structure. We kept consistent with most of our external API and the back end is very encapsulated. 
3.  Brian Zhou
    * I felt the assignment taught me a lot in the benefits of the inheritance hierarchy, as well as the general isolation of the different components of the MVC. Finally, it was good practice in creating interfaces/abstraction as well as passing of generalized data to avoid setters/getters and compartmentalization of functionality.
4.  Ryan Bergamini 
    * I felt the assignment helped me learn a lot about animation and Java change listeners. It also gave me an opportunity to practice writing classes that could be reused in other projects. I was also to apply more of the Deisgn patterns we learned in class than in the previous project. Also felt my group did a better job of making APIs and sticking to them.
