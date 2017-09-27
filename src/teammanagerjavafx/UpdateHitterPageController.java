/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teammanagerjavafx;

import Data.Hitter;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author hasmy
 */
public class UpdateHitterPageController implements Initializable {

    private int counter; 
    private int selectedPlayer = 0;
    @FXML private ComboBox<String> chooseTeamCombo;
    @FXML private ComboBox<String> chooseHitterCombo;
    @FXML private Button updateHitterButton;
    @FXML private TextField abText;
    @FXML private TextField soText;
    @FXML private TextField csText;
    @FXML private TextField sbText;
    @FXML private TextField sacsText;
    @FXML private TextField hbpText;
    @FXML private TextField bbText;
    @FXML private TextField hrText;
    @FXML private TextField b3Text;
    @FXML private TextField b2Text;
    @FXML private TextField hText;
    @FXML private Label errorMessageLabel; 



    @FXML
    void handleUpdateHitter(ActionEvent event) 
    {
        int inAB = 0, inH = 0, inB2 = 0, inB3 = 0, inHR = 0, inBB = 0, inHBP = 0,
            inSac = 0, inSO = 0, inCS = 0, inSB = 0;
        boolean abEntered = true, hEntered = true, b2Entered = true, b3Entered = true,
            hrEntered = true, bbEntered = true, hbpEntered = true, sacEntered = true, 
            soEntered = true, csEntered = true, sbEntered = true, playerEntered = true;
        String errorMessage = "Please fill out missing fields: ";
        Hitter hitter1;
        
        try{
            String selectedItem = chooseHitterCombo.getSelectionModel().getSelectedItem();; 
            String[] tokens = selectedItem.split(":");
            selectedPlayer = Integer.parseInt(tokens[1]);

            try{
                inAB = Integer.parseInt(abText.getText());
            }catch(Exception e){
                errorMessage += " AB,";
                abEntered = false; 
            }
            
            try{
                inH = Integer.parseInt(hText.getText());
            }catch(Exception e){
                errorMessage += " H,";
                hEntered = false; 
            }
            
            try{
                inB2 = Integer.parseInt(b2Text.getText());
            }catch(Exception e){
                errorMessage += " 2B,";
                b2Entered = false; 
            }
            
            try{
                inB3 = Integer.parseInt(b3Text.getText());
            }catch(Exception e){
                errorMessage += " 3B,";
                b3Entered = false; 
            }
            
            try{
                inHR = Integer.parseInt(hrText.getText());
            }catch(Exception e){
                errorMessage += " HR,";
                hrEntered = false; 
            }
            
            try{
                inBB = Integer.parseInt(bbText.getText());
            }catch(Exception e){
                errorMessage += " BB,";
                bbEntered = false; 
            }
            
            try{
                inHBP = Integer.parseInt(hbpText.getText());
            }catch(Exception e){
                errorMessage += " HBP,";
                hbpEntered = false; 
            }
            
            try{
                inSac = Integer.parseInt(sacsText.getText());
            }catch(Exception e){
                errorMessage += " Sac,";
                sacEntered = false; 
            }
            
            try{
                inSO = Integer.parseInt(soText.getText());;
            }catch(Exception e){
                errorMessage += " SO,";
                soEntered = false; 
            }
            
            try{
                inCS = Integer.parseInt(csText.getText());
            }catch(Exception e){
                errorMessage += " CS,";
                csEntered = false; 
            }
            
            try{
                inSB = Integer.parseInt(sbText.getText());
            }catch(Exception e){
                errorMessage += " SB.";
                sbEntered = false; 
            }

        }catch(Exception e){
            errorMessage += " player,";
            playerEntered = false;
        }
        
        if(playerEntered && abEntered && hEntered && b2Entered && b3Entered && hrEntered
                && bbEntered && hbpEntered && sacEntered && soEntered && csEntered && sbEntered)
        {
            errorMessage = "";
            
            hitter1 = new Hitter(selectedPlayer);
            hitter1 = Hitter.loadHitterData(selectedPlayer);
            hitter1.updateHitter(inAB, inH, inB2, inB3, inHR, inBB, inHBP, inSac, inSO, inCS, inSB);

            Data.DBUtil.updateHitter(hitter1);
            
        }
        
        errorMessageLabel.setText(errorMessage);
    }

    @FXML
    void handleChooseHitter(ActionEvent event) {}

    @FXML
    void handleChooseTeam(ActionEvent event) 
    {
        EntityManager em = Data.DBUtil.getEM();

        String selectedTeam = chooseTeamCombo.getSelectionModel().getSelectedItem();

        Query teamIDQuery = em.createNativeQuery("SELECT teamid FROM team WHERE teamname=?");
        teamIDQuery.setParameter(1,selectedTeam);
        int teamID = (int)teamIDQuery.getSingleResult();
        
        Query playerIDSQL = em.createNativeQuery("SELECT playerid FROM player WHERE teamid=?");
        playerIDSQL.setParameter(1,teamID);
        List<Integer>playerIDList = playerIDSQL.getResultList();
        
        Query hitterIDSQL = em.createNativeQuery("SELECT playerid FROM hitter");
        List<Integer>hitterIDList = hitterIDSQL.getResultList();
        
        counter = 0; 
        int[] playerIDArray = new int[playerIDList.size()];
        playerIDList.forEach((data) -> {
            playerIDArray[counter]=data;
            counter++;
        });
        
        counter = 0; 
        int[] hitterIDArray = new int[hitterIDList.size()];
        hitterIDList.forEach((data) -> {
            hitterIDArray[counter]=data;
            counter++;
        });
        
        int counter1 = 0;
        int[] actualHitterID = new int[playerIDList.size()];
        for(int i=0; i<playerIDList.size(); i++)
        {
            for(int j=0; j<hitterIDList.size(); j++)
            {
                if(playerIDArray[i]==hitterIDArray[j])
                {
                    actualHitterID[counter1] = playerIDArray[i];
                    counter1++;
                }
            }
        }
         
        chooseHitterCombo.getItems().clear(); 
        for(int i=0; i<counter1;i++)
        {
            Query playerFName = em.createNativeQuery("SELECT fname FROM player WHERE playerid=?");
            playerFName.setParameter(1,actualHitterID[i]);
            String fName = (String)playerFName.getSingleResult();
            
            Query playerLName = em.createNativeQuery("SELECT lname FROM player WHERE playerid=?");
            playerLName.setParameter(1,actualHitterID[i]);
            String lName = (String)playerLName.getSingleResult();
            
            chooseHitterCombo.getItems().add(lName+", "+fName+"      ID:"+actualHitterID[i]);
            
        }
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
