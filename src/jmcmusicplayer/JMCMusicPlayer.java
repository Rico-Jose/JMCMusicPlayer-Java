/*
 * You have been given an example of what your boss is expecting to see.
 * The example they have given is an advanced music player
 * that allows the ability to sort and search the song
 * stored in a binary tree (any sort and search algorithm
 * you select will have to be approved if it is not merge sort and binary search),
 * the GUI should display the sorted track list and highlight and play the searched track,
 * it should save the track list to a csv using a 3rd party library.
 * The music player must load and play files and met the requirements laid out in Question 3.
 * If you choose not to implement this project,
 * you must negotiate a project of equal complexity that meets the requirements in Question 3.
 * Jose Rico Imbang
 * 30019932
 * 03/12/2020
 * AT3 - Project
 * Third party library link - https://www.javatpoint.com/how-to-read-csv-file-in-java
 */

package jmcmusicplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JMCMusicPlayer extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("JMC Music Player");
        
        Parent root = FXMLLoader.load(getClass().getResource("JMCMusicPlayerFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
}
