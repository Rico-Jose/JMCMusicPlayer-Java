package jmcmusicplayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import javax.swing.JOptionPane;

public class JMCMusicPlayerController implements Initializable {
    
    User defaultUser = new User("admin", "password");
    
    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password;
    
    @FXML
    void handleLogInButtonAction(ActionEvent event) throws IOException {
        String hashedPW = User.hashPassword(password.getText());
        // System.out.println(defaultUser.getUsername());
        JOptionPane.showMessageDialog(null, "Saved password: " + defaultUser.getPassword()
            + "\nEntered password: " + hashedPW
        );
        
        if ((username.getText().equals(defaultUser.getUsername())) &&
                hashedPW.equals(defaultUser.getPassword())) {
            Parent musicplayerParent = FXMLLoader.load(getClass().getResource("MusicPlayerFXML.fxml"));
            Scene musicplayerScene = new Scene(musicplayerParent);
            
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            stage.setScene(musicplayerScene);
            stage.show();
        } else {
            JOptionPane.showMessageDialog(null, "Please enter the correct login information");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
