# Seminar

This project is my seminar. The goal of this project is support future students at learning advanced concepts of the Java programming language. 
The relevant concepts are Java Database Control (JDBC) and XML.

## Project Structure
The project is structured into the following five packages
- data: this package contains the central object of the application. The class Frage represents a question of the Quiz. The class Antwort an answer and the class Player a player of the quiz.
- db: this package contains classes that manage the connection to the connection to the database and execute queries.
- general: this package contains a class with multiple constant values such as paths to files and sizes of the application window.
- gui: this package contains classes that manage the user interaction  
- io: this package contains classes that parse the XML file 

The data for the quiz is given in an XML format in the resources folder. 


## Implementation
The students will get this project partially implemented. All the packages and classes will be given as a Java project. Specific classes and methods will be either empty
or will return a dummy value. The students will have to implement this classes and methods based on my descriptions. The entire description can be 
found in this project under the name aufgaben.pdf.

### Exercises
This section includes the functionalities that the students need to implement
- Parse the XML file with the data
- Create the database tables
- Fill the tables with the data from the XML file
- Get distinct (questions) categories and difficulty levels
- Get the questions that match to the selected category and difficulty level
- Get the four answers for the quiz
- Get the correct answer for the quiz
- Save the player's performance in the database
- Get all the player's from the database to show some statistics
	
