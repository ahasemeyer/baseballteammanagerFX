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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
        EntityManager em = Data.DBUtil.getEM();
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
            errorMessage = ""; 
            Team team1 = new Team(teamID);
            team1 = Team.loadTeamData(teamID);
            team1.updateTeam(inWins, inLosses, inTies);
            team1.setName(selectedItem);
            Data.DBUtil.updateTeam(team1);
            
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updatePlayerConfirmWindow.fxml"));
                Parent root1 = (Parent)fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Team Updated!");
                stage.setScene(new Scene(root1));
                stage.show();
            }catch(Exception e){
                System.out.println("Cant load new window");
            }     
        }
        errorMessageLabel.setText(errorMessage);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        EntityManager em = Data.DBUtil.getEM();
        
        assert chooseTeamCombo != null;
        chooseTeamCombo.getItems().clear();
        
        Query teams = em.createNativeQuery("SELECT teamname FROM team");
        List<String> list = teams.getResultList();
        list.forEach((team) -> {
            chooseTeamCombo.getItems().add(team);
        });
    } 
}
