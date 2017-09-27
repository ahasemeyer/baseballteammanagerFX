/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teammanagerjavafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hasmy
 */
public class ConfirmWindowController implements Initializable {

    @FXML private Label confirmMessageLabel;
    @FXML private Button closeButton; 
    @FXML void handleCloseButton(ActionEvent event) 
    {
//        Stage stage = (Stage) closeButton.getScene().getWindow();
//        stage.close();
        //((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close(); 
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
