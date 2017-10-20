/**
 * FXML Controller class
 *
 * @author hasmy
 * @document CreateTeamPageController.java
 * @description This handles the Create Team Page
 */
package Controller;

import Model.Team;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class CreateTeamPageController implements Initializable {

    @FXML private TextField teamName;
    @FXML private Label errorMessageLabel; 
    
    //Pre: Textfield must be filled out
    //Post: Will create a team if the team name does not already exist
    @FXML
    void handleTeamCreate(ActionEvent event) 
    {
        EntityManager em = Model.DBUtil.getEM();
        Team newTeam;
        boolean duplicate = false; 
        
        //check for blank entries
        if(teamName.getText()==null || teamName.getText().equals(" "))
        {
            errorMessageLabel.setText("Please enter a team name.");
        }
        
        //check if teamname already exists
        try{
            Query duplicateQuery = em.createNativeQuery("SELECT teamid FROM team WHERE teamname=?");
            duplicateQuery.setParameter(1,teamName.getText());
            Object teamid = duplicateQuery.getSingleResult();
            errorMessageLabel.setText("Team "+teamName.getText()+" already exists.");
            duplicate = true; 
        }catch(Exception e){
            duplicate = false;
        }  
        
        //If teamname does not already exist create team
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
    public void initialize(URL url, ResourceBundle rb) { }    
}
