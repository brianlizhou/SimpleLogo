# BACKEND API PEER REVIEW
## PART 1
##### 1.  What about your API is intended to be flexible?
The communication between the frontend and backend is very minimal. Most of the communication is through observable properties, so when a property in the backend changes, the frontend will be updated automatically. This allows for flexibility because it doesn't matter how the updates occur, as long as the properties get updated correctly.
##### 2. How is your API encapsulating your implementation decisions?
By using the observable design pattern, it does not matter how I am updating the values. As long as the values are updated, the front end properties are also updated.
##### 3. What exceptions might occur in your part and how will you handle them?
One possible exception is an invalid command from the user. If the user enters an invalid command, the command parser will recognize this and throw an error to the front end. The front end will then display an error message to the user.

Or if the user tries to move the turtle to an invalid position (e.g., out of bounds), the backend will just move the turtle to the position closest to the edge.
##### 4. Why do you think your API is *good*?
My API is good because the communication between the front end and back end is very limited. The back end simply needs to make updates to an observable value, and the front end values will also change. This encapsulates the back end implementation is makes it flexible.

## Part 2
##### 1. Come up with at least 5 use cases
*  User enters a valid command (e.g., ```fd 50```)
*  User enters a command with a syntax error
* User enters a command with no syntax errors, but moves turtle to invalid position
* User enters a Math command
* User accesses a command from the terminal history
##### 2. How do you think at least one of the "advanced" Java features will help you *implement* your design?
I plan on using reflection to be able to instantiate objects and/or call methods without knowing their name. For example, the backend will have different methods to implement different commands, so I will use reflection to call the right methods.
##### 3.  What feature/design are you most excited to work on?
I'm most interested in parsing commands and constructing the expression tree.
##### 4. What feature are you most worried about working on?
I am most worried about parsing commands.

