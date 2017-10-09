/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Pitcher;
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
public class UpdatePitcherPageController implements Initializable {

    private int counter;  
    @FXML private ComboBox<String> teamCombo;
    @FXML private Button updatePitcherButton;
    @FXML private ComboBox<String> choosePlayerCombo;
    @FXML private TextField winsText;
    @FXML private TextField lossesText;
    @FXML private TextField gamesText;
    @FXML private TextField gamesStartedText;
    @FXML private TextField savesText;
    @FXML private TextField saveOppText;
    @FXML private TextField inningsPitchedText;
    @FXML private TextField hitsText;
    @FXML private TextField runsText;
    @FXML private TextField earnedRunsText;
    @FXML private TextField homerunsText;
    @FXML private TextField baseOnBallsText;
    @FXML private TextField hitByPitchText;
    @FXML private TextField strikeoutsText;
    @FXML private Label errorMessageLabel;
    
    @FXML
    void handleUpdatePitcher(ActionEvent event) 
    {       
        String errorMessage = "Please fill out missing fields:"; 
        int playerID = 0, inWins = 0, inLosses = 0, inGames = 0, inGS = 0, inSaves = 0,
                inSVO = 0, inHits = 0, inRuns = 0, inER = 0, inHR = 0, inBB = 0,
                inHBP = 0, inSO = 0;
        double inIP = 0.0;
        boolean wEntered = true, lEntered = true, gEntered = true, gsEntered = true, svEntered = true,
                svoEntered = true, hEntered = true, rEntered = true, erEntered = true, hrEntered = true,
                bbEntered = true, hbpEntered = true, soEntered = true, ipEntered = true, pEntered = true; 
        String fullName = "";
        
        
        try{
            String selectedItem = choosePlayerCombo.getSelectionModel().getSelectedItem();; 
            String[] tokens = selectedItem.split(":");
            playerID = Integer.parseInt(tokens[1]);
            
            String[] nameTokens = selectedItem.split(" ");
            fullName = nameTokens[0]+" "+nameTokens[1];
            
            try{
                inWins = Integer.parseInt(winsText.getText());
            }catch(Exception e){
                errorMessage += " W,";
                wEntered = false; 
            }
            
            try{
                inLosses = Integer.parseInt(lossesText.getText());
            }catch(Exception e){
                errorMessage += " L,";
                lEntered = false;
            }
            
            try{
                inGames = Integer.parseInt(gamesText.getText());
            }catch(Exception e){
                errorMessage += " G,";
                gEntered = false;
            }
            
            try{
                inGS = Integer.parseInt(gamesStartedText.getText());
            }catch(Exception e){
                errorMessage += " GS,";
                gsEntered = false;
            }
            
            try{
                inSaves = Integer.parseInt(savesText.getText());
            }catch(Exception e){
                errorMessage += " SV,";
                svEntered = false;
            }
            
            try{
                inSVO = Integer.parseInt(saveOppText.getText());
            }catch(Exception e){
                errorMessage += " SVO,";
                svoEntered = false;
            }
            
            try{
                inIP = Double.parseDouble(inningsPitchedText.getText());
            }catch(Exception e){
                errorMessage += " IP,";
                ipEntered = false;
            }
            
            try{
                inHits = Integer.parseInt(hitsText.getText());
            }catch(Exception e){
                errorMessage += " H,";
                hEntered = false;
            }
            
            try{
                inRuns = Integer.parseInt(runsText.getText());
            }catch(Exception e){
                errorMessage += " R,";
                rEntered = false;
            }
            
            try{
                inER = Integer.parseInt(earnedRunsText.getText());
            }catch(Exception e){
                errorMessage += " ER,";
                erEntered = false;
            }
            
            try{
                inHR = Integer.parseInt(homerunsText.getText());
            }catch(Exception e){
                errorMessage += " HR,";
                hrEntered = false;
            }
            
            try{
                inBB = Integer.parseInt(baseOnBallsText.getText());
            }catch(Exception e){
                errorMessage += " BB,";
                bbEntered = false;
            }
            
            try{
                inHBP = Integer.parseInt(hitByPitchText.getText());
            }catch(Exception e){
                errorMessage += " HBP,";
                hbpEntered = false;
            }
            
            try{
                inSO = Integer.parseInt(strikeoutsText.getText());
            }catch(Exception e){
                errorMessage += " SO.";
                soEntered = false; 
            }
        }catch(Exception e){
            errorMessage += " player.";
            pEntered = false;
        }
        
        
        if( pEntered && wEntered && lEntered && gEntered && gsEntered && svEntered && svoEntered 
                && ipEntered && hEntered && rEntered && erEntered && hrEntered && bbEntered
                && hbpEntered && soEntered)
        {
            //update Pitcher data
            errorMessage = ""; 
            errorMessageLabel.setText(errorMessage);
            Pitcher pitcher1 = new Pitcher(playerID); 
            pitcher1 = Pitcher.loadPitcherData(playerID);
            pitcher1.updatePitcher(inWins, inLosses, inGames, inGS, inSaves, inSVO, inIP, 
                    inHits, inRuns, inER, inHR, inBB, inHBP, inSO);
            Model.DBUtil.updatePitcher(pitcher1);
            
            //pop up a confirm window
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Pitcher Updated");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully updated "+fullName+".");
            alert.showAndWait();
            
            //reset values
            winsText.setText("0");
            lossesText.setText("0");
            gamesText.setText("0");
            gamesStartedText.setText("0");
            savesText.setText("0");
            saveOppText.setText("0");
            inningsPitchedText.setText("0.0");
            hitsText.setText("0");
            runsText.setText("0");
            earnedRunsText.setText("0");
            homerunsText.setText("0");
            baseOnBallsText.setText("0");
            hitByPitchText.setText("0");
            strikeoutsText.setText("0");
            teamCombo.getSelectionModel().clearSelection();
            choosePlayerCombo.getSelectionModel().clearSelection();

        }
        errorMessageLabel.setText(errorMessage);
    }
    
    @FXML
    void handleTeamCombo(ActionEvent event) 
    {
        String selectedTeam = teamCombo.getSelectionModel().getSelectedItem();
        EntityManager em = Model.DBUtil.getEM();
        
        try{
            Query teamIDQuery = em.createNativeQuery("SELECT teamid FROM team WHERE teamname=?");
            teamIDQuery.setParameter(1,selectedTeam);
            int teamID = (int)teamIDQuery.getSingleResult();

            Query playerIDSQL = em.createNativeQuery("SELECT playerid FROM player WHERE teamid=?");
            playerIDSQL.setParameter(1,teamID);
            List<Integer>playerIDList = playerIDSQL.getResultList();

            Query pitcherIDSQL = em.createNativeQuery("SELECT playerid FROM pitcher");
            List<Integer>pitcherIDList = pitcherIDSQL.getResultList();

            counter = 0; 
            int[] playerIDArray = new int[playerIDList.size()];
            playerIDList.forEach((data) -> {
                playerIDArray[counter]=data;
                counter++;
            });

            counter = 0; 
            int[] pitcherIDArray = new int[pitcherIDList.size()];
            pitcherIDList.forEach((data) -> {
                pitcherIDArray[counter]=data;
                counter++;
            });

            int counter1 = 0;
            int[] actualPitcherID = new int[playerIDList.size()];
            for(int i=0; i<playerIDList.size(); i++)
            {
                for(int j=0; j<pitcherIDList.size(); j++)
                {
                    if(playerIDArray[i]==pitcherIDArray[j])
                    {
                        actualPitcherID[counter1] = playerIDArray[i];
                        counter1++;
                    }
                }
            }

            choosePlayerCombo.getItems().clear(); 
            for(int i=0; i<counter1;i++)
            {
                Query playerFName = em.createNativeQuery("SELECT fname FROM player WHERE playerid=?");
                playerFName.setParameter(1,actualPitcherID[i]);
                String fName = (String)playerFName.getSingleResult();

                Query playerLName = em.createNativeQuery("SELECT lname FROM player WHERE playerid=?");
                playerLName.setParameter(1,actualPitcherID[i]);
                String lName = (String)playerLName.getSingleResult();

                choosePlayerCombo.getItems().add(lName+", "+fName+"      ID:"+actualPitcherID[i]);
            } 
        }catch(Exception e){
            System.out.println("Select a Pitcher");
        }
    }
    
    public void initialize(URL url, ResourceBundle rb) 
    {        
        EntityManager em = Model.DBUtil.getEM();
        
        assert teamCombo != null;
        teamCombo.getItems().clear();
        
        Query teams = em.createNativeQuery("SELECT teamname FROM team");
        List<String> list = teams.getResultList();
        list.forEach((team) -> {
            teamCombo.getItems().add(team);
        });
    }       
    
}
