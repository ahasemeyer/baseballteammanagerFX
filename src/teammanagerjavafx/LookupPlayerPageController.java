/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teammanagerjavafx;

import Data.Hitter;
import Data.Manager;
import Data.Pitcher;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hasmy
 */
public class LookupPlayerPageController implements Initializable {

    @FXML
    private Button playerByIDButton;

    @FXML
    private TextField playerIDText;

    @FXML
    private TextArea displayText;
    
    @FXML
    private ComboBox<String> playerTypeCombo;

    @FXML
    void handlerPlayerByID(ActionEvent event)
    {
        int playerID = Integer.parseInt(playerIDText.getText());
        
        if(playerTypeCombo.getSelectionModel().getSelectedItem().equalsIgnoreCase("hitter"))
        {   
            Hitter lookUp = Data.Hitter.loadHitterData(playerID);
            displayText.setText(lookUp.returnStats());
            lookUp.printStats();
        }
        if(playerTypeCombo.getSelectionModel().getSelectedItem().equalsIgnoreCase("pitcher"))
        {
            Pitcher lookUp = Data.Pitcher.loadPitcherData(playerID);
            displayText.setText(lookUp.returnStats());
            lookUp.printStats();
        }
        if(playerTypeCombo.getSelectionModel().getSelectedItem().equalsIgnoreCase("manager"))
        {
            Manager lookUp = Data.Manager.loadManagerData(playerID);
            displayText.setText(lookUp.returnStats());
            lookUp.printStats();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        assert playerTypeCombo != null;
        playerTypeCombo.getItems().addAll("Pitcher", "Hitter", "Manager");
    }    
    
}
