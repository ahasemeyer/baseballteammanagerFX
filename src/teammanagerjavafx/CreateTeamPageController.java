/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teammanagerjavafx;

import Data.Team;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author hasmy
 */
public class CreateTeamPageController implements Initializable {


    @FXML private TextField teamName;
    @FXML private Button createTeamButton;
    @FXML private Label errorMessageLabel; 
    
    @FXML
    void handleTeamCreate(ActionEvent event) 
    {
        EntityManager em = Data.DBUtil.getEM();
        Team newTeam;
        boolean duplicate = false; 
        
        try{
            Query duplicateQuery = em.createNativeQuery("SELECT teamid FROM team WHERE teamname=?");
            duplicateQuery.setParameter(1,teamName.getText());
            Object teamid = duplicateQuery.getSingleResult();
            errorMessageLabel.setText("Team "+teamName.getText()+" already exists.");
            duplicate = true; 
        }catch(Exception e){
            duplicate = false;
        }  
        
        if(!duplicate)
        {
            newTeam = Data.DBUtil.createTeam(teamName.getText());
            errorMessageLabel.setText("");
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("teamCreateConfirmWindow.fxml"));
                Parent root1 = (Parent)fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Team Created!");
                stage.setScene(new Scene(root1));
                stage.show();
            }catch(Exception e){
                System.out.println("Cant load new window");
            } 
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
