/*
 * @author Austin Hasemeyer
 * @document CreatePlayerPageController.java
 * @description This controller page handles all actions within the 
 *      create player window. 
 */

package Controller;

import Model.Player;
import java.io.File;
import java.io.IOException;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CreatePlayerPageController implements Initializable {

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField number;
    @FXML private RadioButton pitcherRadio;
    @FXML private RadioButton hitterRadio;
    @FXML private RadioButton managerRadio;
    @FXML private ComboBox<String> teamCombo;
    @FXML private ComboBox<String> battingStanceCombo;
    @FXML private ComboBox<String> positionCombo;
    @FXML private ComboBox<String> throwingArmCombo;
    @FXML private Label errorMessageLabel;
    @FXML private Label fNameError;
    @FXML private Label lNameError;
    @FXML private Label pNumberError;
    @FXML private Label positionError;
    @FXML private Label tArmError;
    @FXML private Label bStanceError; 
    @FXML private Label tNameError; 
    @FXML private ImageView profilePicture; 
    private Image image = null; 

    //Pre: press "Upload Picture" button
    //Post: File chooser pops up and asks to select a .jpg or .png.
    //      If one is selected it is set to profilePicutre. Otherwise a abort
    //      message is displayed. 
    @FXML 
    void handlePicture(ActionEvent event)
    {
        try{
            FileChooser fileChooser = new FileChooser(); 
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)","*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)","*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
            File file = fileChooser.showOpenDialog(null);
            image = new Image(file.toURI().toString());
            profilePicture.setImage(image);
        }catch(Exception e){
            System.out.println("Choose picture aborted.");
        }
    }
    
    //Pre: Hitter radio selected
    //Post: if Hitter is selected you cannot be a manager
    @FXML
    boolean handleHitterRadio(ActionEvent event)
    {
        if(hitterRadio.isSelected() || pitcherRadio.isSelected())
        {
            managerRadio.setDisable(true);
        }else{
            managerRadio.setDisable(false);
        }
        return hitterRadio.isSelected();
    }
    
    //Pre: Manager radio selected
    //Post: if Manager is selected you cannot be a Hitter or Pitcher
    @FXML
    boolean handleManagerRadio(ActionEvent event) 
    {
        if(managerRadio.isSelected())
        {
            pitcherRadio.setDisable(true);
            hitterRadio.setDisable(true);
        }else{
            pitcherRadio.setDisable(false);
            hitterRadio.setDisable(false);
        }
        return managerRadio.isSelected();
    }

    //Pre: Pitcher radio selected
    //Post: if Pitcher is selected you cannot be a Manager
    @FXML
    boolean handlePitcherRadio(ActionEvent event) 
    {
        if(pitcherRadio.isSelected() || hitterRadio.isSelected())
        {
            managerRadio.setDisable(true);
        }else{
            managerRadio.setDisable(false);
        }
        return pitcherRadio.isSelected();
    }

    
    //Pre: all fields must have valid entries
    //Post: an object of type Player, and possibly Hitter, Pitcher or Manager, is
    //      created and saved in the MySQL database
    @FXML
    void createPlayerButton(ActionEvent event) 
    {  
        boolean numberEntered = true; 
        boolean fNameEntered = true;
        boolean lNameEntered = true;
        boolean positionEntered = true;
        boolean armEntered = true;
        boolean stanceEntered = true;
        boolean teamEntered = true; 
        
        String stringNumber = number.getText(); 
        int inNumber = 0;

        //check for invalid entries
        if(stringNumber.equals(""))
        {
            numberEntered = false;
            pNumberError.setVisible(true);
        }
        else if(!stringNumber.matches("[0-9]*"))
        {
            pNumberError.setVisible(true);
            numberEntered = false;
        }else{
            pNumberError.setVisible(false);
            numberEntered = true;
            inNumber = Integer.parseInt(number.getText());
        }
        
        String stringArm = throwingArmCombo.getSelectionModel().getSelectedItem();
        try{
            stringArm.equals("");
            armEntered = true;
            tArmError.setVisible(false);
        }catch(Exception e){
            armEntered = false;
            tArmError.setVisible(true);
        }

        String inPos = positionCombo.getSelectionModel().getSelectedItem();
        try{
            inPos.equals("");
            positionEntered = true;
            positionError.setVisible(false);
        }catch(Exception e){
            positionEntered = false;
            positionError.setVisible(true);
        }
        
        String stringStance = battingStanceCombo.getSelectionModel().getSelectedItem();
        try{
            stringStance.equals("");
            stanceEntered = true;
            bStanceError.setVisible(false);
        }catch(Exception e){
            stanceEntered = false;
            bStanceError.setVisible(true);
        }

        String comboText = teamCombo.getSelectionModel().getSelectedItem(); 
        if(comboText == null)
        {
            teamEntered = false;
            tNameError.setVisible(true);
        }else{
            teamEntered = true; 
            tNameError.setVisible(false);
        }

        if(firstName.getText().equals(""))
        {
            fNameEntered = false;
            fNameError.setVisible(true);
        }else{
            fNameError.setVisible(false);
        }
        
        if(lastName.getText().equals(""))
        {
            lNameEntered = false;
            lNameError.setVisible(true);
        }else{
            lNameError.setVisible(false);
        }
        
        //display error if necessary
        if(!fNameEntered || !lNameEntered || !numberEntered || !armEntered || !positionEntered
                || !stanceEntered || !teamEntered)
            errorMessageLabel.setVisible(true);
        
        //if all entered create player types
        if(fNameEntered && lNameEntered && numberEntered && armEntered && positionEntered
                && stanceEntered && teamEntered)
        {
            errorMessageLabel.setVisible(false);
            int playerID;
            EntityManager em = Model.DBUtil.getEM();
            Query teamID = em.createNativeQuery("SELECT a.teamid FROM team a WHERE a.teamname=?");
            teamID.setParameter(1,comboText);
            int sqlTeamID = (int)teamID.getSingleResult();
            String inArm = stringArm;
            String inStance = stringStance;
            Player newPlayer = Model.DBUtil.createPlayer(firstName.getText(), lastName.getText(), inPos, inNumber, inArm, inStance, sqlTeamID);
            String confirmMessage =" created as a Player"; 
        
            if(hitterRadio.isSelected())
            {
                System.out.println("Hitter Created As: "+firstName.getText()+" "+lastName.getText());
                String hitterMessage = ", Hitter";
                confirmMessage += hitterMessage; 
                Model.DBUtil.createHitter(newPlayer.getPlayerid());
            }
            if(pitcherRadio.isSelected())
            {
                System.out.println("Pitcher Created As: "+firstName.getText()+" "+lastName.getText());
                String pitcherMessage = ", Pitcher";
                confirmMessage += pitcherMessage;
                Model.DBUtil.createPitcher(newPlayer.getPlayerid());
            }
            if(managerRadio.isSelected())
            {
                System.out.println("Manager Created As: "+firstName.getText()+" "+lastName.getText());
                String managerMessage = ", Manager";
                confirmMessage += managerMessage;
                Model.DBUtil.createManager(newPlayer.getPlayerid());
            } 
            if(image != null)
            {
                File newfile = new File("src/images/profile/"+newPlayer.getPlayerid()+".png"); 
                try{
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", newfile);
                }catch(IOException ex){
                    System.out.println(ex);
                }
            }
            playerID = newPlayer.getPlayerid();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Player Created");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully created "+newPlayer.getFname()+" "+newPlayer.getLname()+", PlayerID: "+playerID+".");
            alert.showAndWait();   
            
            firstName.setText(null);
            lastName.setText(null);
            number.setText(null);
            pitcherRadio.setSelected(false);
            hitterRadio.setSelected(false);
            managerRadio.setSelected(false);
            teamCombo.getSelectionModel().clearSelection();
            positionCombo.getSelectionModel().clearSelection();
            throwingArmCombo.getSelectionModel().clearSelection();
            battingStanceCombo.getSelectionModel().clearSelection();
            File defaultPic = new File("src/images/defaultProfile.jpg");
            image = new Image(defaultPic.toURI().toString());
            profilePicture.setImage(image);
        }
    }

    //setup the window
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        //set default player picture
        File newfile = new File("src/images/defaultProfile.jpg"); 
        image = new Image(newfile.toURI().toString());
        
        //connect to db
        EntityManager em = Model.DBUtil.getEM();
        
        //set team combo to list of teams
        assert teamCombo != null;
        teamCombo.getItems().clear();
        Query teams = em.createNativeQuery("SELECT teamname FROM team");
        List<String> list = teams.getResultList();
        list.forEach((team) -> {
            teamCombo.getItems().add(team);
        });
        
        //set possible batting stances
        assert battingStanceCombo != null;
        battingStanceCombo.getItems().addAll("Right", "Left", "Switch");
        
        //set positions
        assert positionCombo != null;
        positionCombo.getItems().addAll("P", "C", "1B", "2B", "3B", "SS", "LF", "CF", "RF", "DH", "INF", "OUT", "M");
        
        //set throwing arm possibilities
        assert throwingArmCombo != null;
        throwingArmCombo.getItems().addAll("Right", "Left");
    }  
}

