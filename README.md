# Project Title: JokeApp

## Description:
The JokeApp is a JavaFX application that fetches random jokes from the "icanhazdadjoke" API using GSON for JSON parsing. The application features a graphical user interface (GUI) with a button and a label. When the user clicks the button, the application sends a request to the API, receives a random joke in JSON format, and then parses it into a Java object using GSON. The parsed joke is displayed on the label in the GUI.

## Key Features:

### JavaFX GUI:
The graphical interface is designed using JavaFX, providing an interactive and user-friendly environment.
The GUI includes a button for triggering the joke retrieval and a label for displaying the fetched joke.
API Integration:

### API Integration
The application integrates with the "icanhazdadjoke" API to fetch random jokes.
It uses the HttpURLConnection class to make an HTTP request to the API and retrieves the JSON response.
GSON for JSON Parsing:

### GSON for JSON Parsing
GSON, a Java library by Google, is employed for parsing the JSON response from the API into Java objects.
The application defines a Java class (Joke) that represents the structure of the JSON response, and GSON is used to deserialize the JSON string into an instance of this class.
Dynamic Label Update:

### Dynamic Label update
A button in the GUI triggers the API call and subsequent JSON parsing.
Upon successful retrieval and parsing, the application updates the label in the GUI with the fetched joke.
