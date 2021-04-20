# Seminar

This project is my seminar. The goal of this project is to support future students in learning advanced concepts of the Java programming language.
The relevant concepts are Java Database Control (JDBC) and XML. 

<img src="/docs/Main-Gui.png" width="600" height="400">
<img src="/docs/Film-Quiz.png" width="600" height="400">
<img src="/docs/Statistik.png" width="600" height="400">

## Implementation
The students will get a partially implemented version of this project. All the packages and classes will be given. Specific classes and methods will be either empty or will return a dummy value. The students will have to implement these classes and methods based on the task description. The task description can be found in this repository in the file aufgaben.pdf. This repository contains the complete project, thus the solutions of the exercises.

## Project Structure
The project is structured into the following five packages
- data: this package contains the central objects of the application. The class Frage represents a question of the Quiz. The class Antwort an answer and the class Player a player of the quiz.
- db: this package contains classes that manage the connection to the database and execute queries.
- general: this package contains a class with multiple constant values such as paths to files and sizes of the application window.
- gui: this package contains classes that manage the user interaction  
- io: this package contains classes that parse the XML file 

The data for the quiz is given in an XML format in the resources folder. 

### Tasks
This section includes the functionalities that the students need to implement.
1. Parse the XML file with the data
2. Create the database tables
3. Fill the tables with the data from the XML file
4. Get distinct (question) categories and difficulty levels
5. Get the questions that match to the selected category and difficulty level
6. Get the four answers for each question
7. Get the correct answer for the current question
8. Save the player's performance in the database
9. Get the player's data from the database to show some statistics

For task 1 XML is relevant.
For tasks 2-10 JDBC is relevant.
