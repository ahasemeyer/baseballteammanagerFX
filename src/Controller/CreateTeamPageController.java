/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Team;
import java.net.URL;
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
        EntityManager em = Model.DBUtil.getEM();
        Team newTeam;
        boolean duplicate = false; 
        
        if(teamName.getText()==null || teamName.getText().equals(" "))
        {
            errorMessageLabel.setText("Please enter a team name.");
        }
        
        try{
            Query duplicateQuery = em.createNativeQuery("SELECT teamid FROM team WHERE teamname=?");
            duplicateQuery.setParameter(1,teamName.getText());
            Object teamid = duplicateQuery.getSingleResult();
            errorMessageLabel.setText("Team "+teamName.getText()+" already exists.");
            duplicate = true; 
        }catch(Exception e){
            duplicate = false;
        }  
        
        if(!duplicate && teamName.getText()!=null)
        {
            newTeam = Model.DBUtil.createTeam(teamName.getText());
            errorMessageLabel.setText("");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Team Created");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully created team "+newTeam.getTeamName()+", TeamID: "+newTeam.getTeamID()+".");
            alert.showAndWait();
            
            teamName.setText(null);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
