import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author ghost
 */
public class FinalProject_Jokes extends Application {
    
    // Helper method to parse the JSON response using GSON
    private static Joke parseJoke(String jsonResponse) 
    {
        Gson gson = new Gson();
        return gson.fromJson(jsonResponse, Joke.class);
    }

    // Joke class representing the structure of the JSON response
    private static class Joke 
    {
        private String id;
        private String joke;

        public String getJoke() {
            return joke;
        }
    }
    
    @Override
    public void start(Stage primaryStage) {
        //Create a Label
        Label label = new Label();
        label.setText("");
        label.relocate(10,175);
        //Create a Button
        Button btn = new Button();
        btn.relocate(125, 125);
        btn.setText("Get a Joke");
        //Create an Event Handler for button
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Set up the URL for the "icanhazdadjoke" API
                    URL url = new URL("https://icanhazdadjoke.com/");

                    // Open a connection to the URL
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    // Set request headers
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Accept", "application/json");
                    connection.setRequestProperty("User-Agent", "JokeFetcher");

                    // Get the response code
                    int responseCode = connection.getResponseCode();

                    // Check if the request was successful (HTTP 200 OK)
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // Read the response from the API
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        StringBuilder response = new StringBuilder();
                        String line;

                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }

                        reader.close();

                        // Parse the JSON response using GSON
                        Joke joke = parseJoke(response.toString());

                        // Print the joke and update label
                        System.out.println("Random Joke:");
                        String j = joke.getJoke();
                        System.out.println(j);
                        label.setText(j);
                    } else {
                        System.out.println("Failed to fetch joke. Response Code: " + responseCode);
                    }

                    // Close the connection
                    connection.disconnect();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        });
        //Create a Pane
        Pane root = new Pane();
        //Add buttons and labels
        root.getChildren().addAll(label,btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Joke App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
