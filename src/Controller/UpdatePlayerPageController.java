/**
 * FXML Controller class
 *
 * @author Austin Hasemeyer
 * @document UpdatePlayerPageController.java
 * @description This page will update a Player's basic information, such as name,
 *      number, picture, etc. This information will update the database record as 
 *      well as the profile picture. 
 */
package Controller;

import Model.Player;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UpdatePlayerPageController implements Initializable {

    private int counter; 
    private int selectedPlayerID = 0;
    @FXML private ComboBox<String> teamCombo;
    @FXML private ComboBox<String> positionCombo;
    @FXML private ComboBox<String> armCombo;
    @FXML private ComboBox<String> choosePlayerCombo;
    @FXML private ComboBox<String> stanceCombo;
    @FXML private TextField firstNameText;
    @FXML private TextField lastNameText;
    @FXML private TextField playerNumberText;
    @FXML private CheckBox pitcherCheck;
    @FXML private CheckBox hitterCheck;
    @FXML private CheckBox managerCheck;
    @FXML private Label errorMessageLabel; 
    @FXML private ImageView profilePicture; 
    private Image image = null; 
    private boolean isPitcher = false;
    private boolean isHitter = false;
    private boolean isManager = false;
    private String fullName = "";

    @FXML 
    void handlePicture(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser(); 
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)","*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)","*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        image = new Image(file.toURI().toString());
        profilePicture.setImage(image);
    }
    
    @FXML
    void handleHitterCheck(ActionEvent event) 
    {
        if(hitterCheck.isSelected())
            managerCheck.setDisable(true);
        
        if(!hitterCheck.isSelected() && !isPitcher)
            managerCheck.setDisable(false);
    }

    @FXML
    void handleManagerCheck(ActionEvent event) 
    {
        if(managerCheck.isSelected())
        {
            pitcherCheck.setDisable(true);
            hitterCheck.setDisable(true);
        }
        if(!managerCheck.isSelected())
        {
            pitcherCheck.setDisable(false);
            hitterCheck.setDisable(false);
        }
    }

    @FXML
    void handlePitcherCheck(ActionEvent event) 
    {
        if(pitcherCheck.isSelected())
            managerCheck.setDisable(true);
        if(!pitcherCheck.isSelected() && !isPitcher && !isHitter)
            managerCheck.setDisable(false);
    }
    
    @FXML
    void handleFirstName(ActionEvent event)
    {}
    
    @FXML
    void handleUpdatePlayer(ActionEvent event) 
    {      
        String errorMessage = "Please fill out missing fields:";
        boolean playerSelected = true; 
        boolean numberEntered = true; 
        boolean fNameEntered = true;
        boolean lNameEntered = true;
        boolean armSelected = true; 
        
        if(selectedPlayerID == 0)
        {
            playerSelected = false;
            errorMessage += " player,";
        }else
            playerSelected = true;
            
        int inNumber = 0;
        String stringNumber = playerNumberText.getText(); 
        
        if(stringNumber.equals(""))
        {
            numberEntered = false;
            errorMessage += " number,";
        }
        else if(!stringNumber.matches("[0-9]*"))
        {
            errorMessage += " number,";
            numberEntered = false;
        }else{
            numberEntered = true;
            inNumber = Integer.parseInt(playerNumberText.getText());
        }

        if(firstNameText.getText().equals(""))
        {
            fNameEntered = false;
            errorMessage += " first name,";
        }
        
        if(lastNameText.getText().equals(""))
        {
            lNameEntered = false;
            errorMessage += " last name,";
        }
     

        if(!playerSelected || !numberEntered || !fNameEntered || !lNameEntered)
        {
            errorMessageLabel.setText(errorMessage);
        }
        
        if(playerSelected && numberEntered && fNameEntered && lNameEntered)
        {
            errorMessageLabel.setText("");
            String inArm = armCombo.getValue();
            String inStance = stanceCombo.getValue();
            inNumber = Integer.valueOf(playerNumberText.getText());
            Player updatedPlayer = new Player(selectedPlayerID);
            updatedPlayer.loadPlayerData(selectedPlayerID);
            EntityManager em = Model.DBUtil.getEM();
            Query teamQuery = em.createNativeQuery("SELECT teamid FROM player WHERE playerid=?");
            teamQuery.setParameter(1,selectedPlayerID);
            int teamID = (int)teamQuery.getSingleResult();        
            teamID = (int)teamQuery.getSingleResult();      
            updatedPlayer.updatePlayer(firstNameText.getText(), lastNameText.getText(), positionCombo.getValue(), 
                    inNumber, inArm, inStance, teamID);
            Model.DBUtil.updatePlayer(updatedPlayer);
            
            if(managerCheck.isSelected() && !isManager && !isHitter && !isPitcher)
            {
                Model.DBUtil.createManager(selectedPlayerID);
            }
            
            if(hitterCheck.isSelected() && !isHitter & !isManager)
            {
                Model.DBUtil.createHitter(selectedPlayerID);
            }
            
            if(pitcherCheck.isSelected() && !isPitcher && !isManager)
            {
                Model.DBUtil.createPitcher(selectedPlayerID);
            }
            
            if(image != null)
            {
                File newfile = new File("src/images/profile/"+selectedPlayerID+".png"); 
                try{
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", newfile);
                }catch(IOException ex){
                    System.out.println(ex);
                }
            }
            
            //pop up a confirm window
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Player Updated");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully updated "+fullName+".");
            alert.showAndWait();
        } 
    }
    
    @FXML 
    void handlePlayerCombo(ActionEvent event)
    {
        try{
            String selectedItem = choosePlayerCombo.getSelectionModel().getSelectedItem();
            String[] tokens = selectedItem.split(":");
            selectedPlayerID = Integer.parseInt(tokens[1]);
            String[] nameTokens = selectedItem.split(" ");
            fullName = nameTokens[0]+" "+nameTokens[1];

            EntityManager em = Model.DBUtil.getEM();
            Query fNameQuery = em.createNativeQuery("SELECT fname FROM player WHERE playerid=?");
            fNameQuery.setParameter(1,selectedPlayerID);
            String fName = (String)fNameQuery.getSingleResult();
            firstNameText.setText(fName);

            Query lNameQuery = em.createNativeQuery("SELECT lname FROM player WHERE playerid=?");
            lNameQuery.setParameter(1,selectedPlayerID);
            String lName = (String)lNameQuery.getSingleResult();
            lastNameText.setText(lName);

            Query numberQuery = em.createNativeQuery("SELECT playernumber FROM player WHERE playerid=?");
            numberQuery.setParameter(1,selectedPlayerID);
            int playerNumber = (int)numberQuery.getSingleResult();
            playerNumberText.setText(String.valueOf(playerNumber));

            Query positionQuery = em.createNativeQuery("SELECT position FROM player WHERE playerid=?");
            positionQuery.setParameter(1,selectedPlayerID);
            String playerPosition = (String)positionQuery.getSingleResult();
            positionCombo.setValue(playerPosition);

            Query armQuery = em.createNativeQuery("SELECT throwingarm FROM player WHERE playerid=?");
            armQuery.setParameter(1,selectedPlayerID);
            String playerArm = (String)armQuery.getSingleResult();
            armCombo.setValue(playerArm);

            Query stanceQuery = em.createNativeQuery("SELECT battingstance FROM player WHERE playerid=?");
            stanceQuery.setParameter(1,selectedPlayerID);
            String playerStance = (String)stanceQuery.getSingleResult();
            stanceCombo.setValue(playerStance);

            Query pitcherQuery = em.createNativeQuery("SELECT games FROM pitcher WHERE playerid=?");
            pitcherQuery.setParameter(1,selectedPlayerID);

            try{
                int pitcherCheck = (int)pitcherQuery.getSingleResult();
                isPitcher = true;
            }catch(Exception e){
                isPitcher = false; 
            }

            Query hitterQuery = em.createNativeQuery("SELECT hits FROM hitter WHERE playerid=?");
            hitterQuery.setParameter(1,selectedPlayerID);
            try{
                int hitterCheck = (int)hitterQuery.getSingleResult();
                isHitter = true;
            }catch(Exception e){
                isHitter = false;
            }

            Query managerQuery = em.createNativeQuery("SELECT wins FROM manager WHERE playerid=?");
            managerQuery.setParameter(1,selectedPlayerID);
            try{
                int managerCheck = (int)managerQuery.getSingleResult();
                isManager = true;
            }catch(Exception e){
                isManager = false; 
            }
            
            pitcherCheck.setDisable(isPitcher);
            hitterCheck.setDisable(isHitter);
            managerCheck.setDisable(isManager);
            
            if(isManager)
            {
                pitcherCheck.setDisable(true);
                hitterCheck.setDisable(true);
            }
            if(isPitcher || isHitter)
            {
                managerCheck.setDisable(true);
            }

            File file = new File("src/images/profile/"+selectedPlayerID+".png");
            Image image = new Image(file.toURI().toString());
            profilePicture.setImage(image);

        }catch(Exception e){
            System.out.println("Select a Player"); 
            File newfile = new File("src/images/defaultProfile.jpg"); 
            image = new Image(newfile.toURI().toString());
            profilePicture.setImage(image);
            choosePlayerCombo.getItems().clear();
        }
    }
    
    @FXML
    void handleTeamCombo(ActionEvent event) 
    {
        String selectedTeam = teamCombo.getSelectionModel().getSelectedItem();
        EntityManager em = Model.DBUtil.getEM();
        Query teamIDQuery = em.createNativeQuery("SELECT teamid FROM team WHERE teamname=?");
        teamIDQuery.setParameter(1,selectedTeam);
        int teamID = (int)teamIDQuery.getSingleResult();
        
        Query playersFirst = em.createNativeQuery("SELECT fname FROM player WHERE teamid=?");
        playersFirst.setParameter(1,teamID);
        List<String>firstList = playersFirst.getResultList();
        
        Query playersLast = em.createNativeQuery("SELECT lname FROM player WHERE teamid=?");
        playersLast.setParameter(1,teamID);
        List<String> lastList = playersLast.getResultList();
        
        Query playersID = em.createNativeQuery("SELECT playerID FROM player WHERE teamid=?");
        playersID.setParameter(1,teamID);
        List<Integer> idList = playersID.getResultList();
        
        String[] nameCombined = new String[lastList.size()];
        choosePlayerCombo.getItems().clear();
        counter = 0;
        firstList.forEach((firstName) -> {
            nameCombined[counter]=firstName;
            counter++;
        });
        
        counter = 0; 
        lastList.forEach((lastName) -> {
            nameCombined[counter]=lastName+", "+nameCombined[counter];
            counter++;
        });
        
        counter = 0; 
        idList.forEach((id) -> {
            nameCombined[counter]=nameCombined[counter]+"      ID:"+id;
            choosePlayerCombo.getItems().add(nameCombined[counter]);
            counter++;
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        assert teamCombo != null;
        teamCombo.getItems().clear();
        
        EntityManager em = Model.DBUtil.getEM();
        
        Query teams = em.createNativeQuery("SELECT teamname FROM team");
        List<String> list = teams.getResultList();
        list.forEach((team) -> {
            teamCombo.getItems().add(team);
        });
        
        assert stanceCombo != null;
        stanceCombo.getItems().addAll("Right", "Left", "Switch");
        
        assert positionCombo != null;
        positionCombo.getItems().addAll("P", "C", "1B", "2B", "3B", "SS", "LF", "CF", "RF", "DH", "INF", "OUT", "M");
        
        assert armCombo != null;
        armCombo.getItems().addAll("Right", "Left");
    }   
}
