/**
 * FXML Controller class
 *
 * @author Austin Hasemeyer
 * @document UpdateTeamPageController.java
 * @description This page will update the statistics for a team by reading them
 *      from several textfields then updating the database record.
 */
package Controller;

import Model.Team;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UpdateTeamPageController implements Initializable 
{
    @FXML private ComboBox<String> chooseTeamCombo;
    @FXML private Button updateTeamButton;
    @FXML private TextField winsText;
    @FXML private TextField tiesText;
    @FXML private TextField lossesText;
    @FXML private Label errorMessageLabel; 

    @FXML
    void handleChooseTeam(ActionEvent event) {}

    @FXML
    void handleUpdateTeam(ActionEvent event) 
    {
        EntityManager em = Model.DBUtil.getEM();
        Query teamIDQuery = em.createNativeQuery("SELECT teamid FROM team WHERE teamname=?");
        String selectedItem = "";
        String errorMessage = "Please fill out missing fields:";
        boolean teamEntered = true, winsEntered = true, lossesEntered = true, tiesEntered = true;
        int inWins = 0, inLosses = 0, inTies = 0, teamID = 0; 
        
        selectedItem = chooseTeamCombo.getSelectionModel().getSelectedItem();
        teamIDQuery.setParameter(1,selectedItem);

        if(selectedItem == null)
        {
            errorMessage += " team.";
            teamEntered = false;
        }
        else
        {
            teamID = (int)teamIDQuery.getSingleResult();
            
            try{
                inWins = Integer.parseInt(winsText.getText());
            }catch(Exception e){
                errorMessage += " wins,";
                winsEntered = false; 
            }
            
            try{
                inLosses = Integer.parseInt(lossesText.getText());
            }catch(Exception e){
                errorMessage += " losses,";
                lossesEntered = false;
            }
            
            try{
                inTies = Integer.parseInt(tiesText.getText());
            }catch(Exception e){
                errorMessage += " ties.";
                tiesEntered = false;
            }
        }
        
        if(teamEntered && winsEntered && lossesEntered && tiesEntered)
        {
            //update team data
            errorMessage = ""; 
            errorMessageLabel.setText(errorMessage);
            Team team1 = new Team(teamID);
            team1 = Team.loadTeamData(teamID);
            team1.updateTeam(inWins, inLosses, inTies);
            team1.setName(selectedItem);
            Model.DBUtil.updateTeam(team1);
            
            //popup confirm window
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Team Updated");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully updated "+team1.getTeamName()+".");
            alert.showAndWait();
            
            //reset values
            winsText.setText("0");
            lossesText.setText("0");
            tiesText.setText("0");
            chooseTeamCombo.getSelectionModel().clearSelection();
        }
        errorMessageLabel.setText(errorMessage);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        EntityManager em = Model.DBUtil.getEM();
        
        assert chooseTeamCombo != null;
        chooseTeamCombo.getItems().clear();
        
        Query teams = em.createNativeQuery("SELECT teamname FROM team");
        List<String> list = teams.getResultList();
        list.forEach((team) -> {
            chooseTeamCombo.getItems().add(team);
        });
    } 
}
