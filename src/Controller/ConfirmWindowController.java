/* 
    @author Austin Hasemeyer
    @document ConfirmWindowController.java
    @description This class will handle the confirm window which appears
        after successful operations. Currently not in use
*/
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class ConfirmWindowController implements Initializable {

    @FXML private Label confirmMessageLabel;
    @FXML private Button closeButton; 
    
    //close window on button press
    @FXML void handleCloseButton(ActionEvent event) 
    {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close(); 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
