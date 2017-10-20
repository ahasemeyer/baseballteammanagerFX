/**
 * FXML Controller class
 *
 * @author Austin Hasemeyer
 * @document UpdateHitterPageController.java
 * @description this page will handle updating a Hitters statistics by reading
 *      the data from several textfields then updating the database record.
 */
package Controller;

import Model.Hitter;
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
    @FXML private TextField rbiText;
    @FXML private Label errorMessageLabel; 



    @FXML
    void handleUpdateHitter(ActionEvent event) 
    {
        int inAB = 0, inH = 0, inB2 = 0, inB3 = 0, inHR = 0, inBB = 0, inHBP = 0,
            inSac = 0, inSO = 0, inCS = 0, inSB = 0, inRBI = 0;
        boolean abEntered = true, hEntered = true, b2Entered = true, b3Entered = true,
            hrEntered = true, bbEntered = true, hbpEntered = true, sacEntered = true, 
            soEntered = true, csEntered = true, sbEntered = true, playerEntered = true,
            rbiEntered = true;
        String errorMessage = "Please fill out missing fields: ";
        Hitter hitter1;
        String fullName = "";
        
        try{
            String selectedItem = chooseHitterCombo.getSelectionModel().getSelectedItem();; 
            String[] tokens = selectedItem.split(":");
            String[] nameTokens = selectedItem.split(" "); 
            fullName = nameTokens[0]+" "+nameTokens[1];
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
                errorMessage += " SB,";
                sbEntered = false; 
            }
            
            try{
                inRBI = Integer.parseInt(rbiText.getText());
            }catch(Exception e){
                errorMessage += " RBI.";
                rbiEntered = false;
            }

        }catch(Exception e){
            errorMessage += " player,";
            playerEntered = false;
        }
        
        if(playerEntered && abEntered && hEntered && b2Entered && b3Entered && hrEntered
                && bbEntered && hbpEntered && sacEntered && soEntered && csEntered && sbEntered && rbiEntered)
        {
            errorMessage = "";
            
            //Perform update
            hitter1 = new Hitter(selectedPlayer);
            hitter1 = Hitter.loadHitterData(selectedPlayer);
            hitter1.updateHitter(inAB, inH, inB2, inB3, inHR, inBB, inHBP, inSac, inSO, inCS, inSB, inRBI);
            Model.DBUtil.updateHitter(hitter1);
            
            //confirmation window
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Hitter Updated");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully updated "+fullName+".");
            alert.showAndWait();
            
            //reset values
            abText.setText("0");
            soText.setText("0");
            csText.setText("0");
            sbText.setText("0");
            sacsText.setText("0");
            hbpText.setText("0");
            bbText.setText("0");
            hrText.setText("0");
            b3Text.setText("0");
            b2Text.setText("0");
            hText.setText("0");
            rbiText.setText("0");
            chooseTeamCombo.getSelectionModel().clearSelection();
            chooseHitterCombo.getSelectionModel().clearSelection();    
        }
        
        errorMessageLabel.setText(errorMessage);
    }

    @FXML
    void handleChooseHitter(ActionEvent event) {}

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
        }catch(Exception e){
            System.out.println("Select a team");
        }
            
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
