/**
 * FXML Controller class
 *
 * @author Austin Hasemeyer
 * @document UpdateManagerPageController.java
 * @description This page will update a Managers statistics by reading information
 *      from textfields and updating the database record.
 */
package Controller;

import Model.Manager;
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

public class UpdateManagerPageController implements Initializable {

    private int counter; 
    @FXML private ComboBox<String> chooseTeamCombo;
    @FXML private ComboBox<String> chooseManagerCombo;
    @FXML private Button updateManagerButton;
    @FXML private TextField winsText;
    @FXML private TextField tiesText;
    @FXML private TextField lossesText;
    @FXML private Label errorMessageLabel; 
    private String fullName = "";

    @FXML
    void handleChooseManager(ActionEvent event) {}

    @FXML
    void handleChooseTeam(ActionEvent event) 
    {
        EntityManager em = Model.DBUtil.getEM();
        
        String selectedTeam = chooseTeamCombo.getSelectionModel().getSelectedItem();
        
        try{
            Query teamIDQuery = em.createNativeQuery("SELECT teamid FROM team WHERE teamname=?");
            teamIDQuery.setParameter(1,selectedTeam);
            int teamID = (int)teamIDQuery.getSingleResult();
        
            Query playerIDSQL = em.createNativeQuery("SELECT playerid FROM player WHERE teamid=?");
            playerIDSQL.setParameter(1,teamID);
            List<Integer>playerIDList = playerIDSQL.getResultList();

            Query managerIDSQL = em.createNativeQuery("SELECT playerid FROM manager");
            List<Integer>managerIDList = managerIDSQL.getResultList();

            counter = 0; 
            int[] playerIDArray = new int[playerIDList.size()];
            playerIDList.forEach((data) -> {
                playerIDArray[counter]=data;
                counter++;
            });

            counter = 0; 
            int[] managerIDArray = new int[managerIDList.size()];
            managerIDList.forEach((data) -> {
                managerIDArray[counter]=data;
                counter++;
            });

            int counter1 = 0;
            int[] actualManagerID = new int[playerIDList.size()];
            for(int i=0; i<playerIDList.size(); i++)
            {
                for(int j=0; j<managerIDList.size(); j++)
                {
                    if(playerIDArray[i]==managerIDArray[j])
                    {
                        actualManagerID[counter1] = playerIDArray[i];
                        counter1++;
                    }
                }
            }

            chooseManagerCombo.getItems().clear(); 
            for(int i=0; i<counter1;i++)
            {
                Query playerFName = em.createNativeQuery("SELECT fname FROM player WHERE playerid=?");
                playerFName.setParameter(1,actualManagerID[i]);
                String fName = (String)playerFName.getSingleResult();

                Query playerLName = em.createNativeQuery("SELECT lname FROM player WHERE playerid=?");
                playerLName.setParameter(1,actualManagerID[i]);
                String lName = (String)playerLName.getSingleResult();

                chooseManagerCombo.getItems().add(lName+", "+fName+"      ID:"+actualManagerID[i]);
            }
        }catch(Exception e){
            System.out.println("Select Manager");
        }
            
    }

    @FXML
    void handleUpdateManager(ActionEvent event) 
    {
        String selectedItem = chooseManagerCombo.getSelectionModel().getSelectedItem();
        int playerID = 0, inWins = 0, inLosses = 0, inTies = 0;
        boolean pEntered = true, wEntered = true, lEntered = true, tEntered = true;
        String errorMessage = "Please fill out missing fields:";
        
        if(selectedItem == null)
        {
            pEntered = false; 
            errorMessage += " manager.";
        }
        else
        {
            String[] tokens = selectedItem.split(":");
            playerID = Integer.parseInt(tokens[1]);
            
            String[] nameTokens = selectedItem.split(" ");
            fullName = nameTokens[0]+" "+nameTokens[1];
            
            try{
                inWins = Integer.parseInt(winsText.getText());
            }catch(Exception e){
                errorMessage += " wins,";
                wEntered = false;
            }
            
            try{
                inLosses = Integer.parseInt(lossesText.getText());
            }catch(Exception e){
                errorMessage += " losses,";
                lEntered = false;
            }
            
            try{
                inTies = Integer.parseInt(tiesText.getText());
            }catch(Exception e){
                errorMessage += " ties,";
                tEntered = false;
            }   
        }
        
        if(pEntered && wEntered && lEntered && tEntered)
        {
            //update manager data
            errorMessage = "";
            errorMessageLabel.setText(errorMessage);
            Manager manager1 = new Manager(playerID);
            manager1 = Manager.loadManagerData(playerID);
            manager1.updateManager(inWins, inLosses, inTies);
            Model.DBUtil.updateManager(manager1);
            
            //confirm window
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Manager Updated");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully updated "+fullName+".");
            alert.showAndWait();
            
            //reset values
            winsText.setText("0");
            lossesText.setText("0");
            tiesText.setText("0");
            chooseTeamCombo.getSelectionModel().clearSelection();
            chooseManagerCombo.getSelectionModel().clearSelection();
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
