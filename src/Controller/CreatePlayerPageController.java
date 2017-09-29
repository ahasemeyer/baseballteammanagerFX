package Controller;

import Model.Player;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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
        String errorMessage = "Please fill out missing fields:"; 
        
        String stringNumber = number.getText(); 
        int inNumber = 0;

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
            inNumber = Integer.parseInt(number.getText());
        }
        
        String stringArm = throwingArmCombo.getSelectionModel().getSelectedItem();
        try{
            stringArm.equals("");
            armEntered = true;
        }catch(Exception e){
            armEntered = false;
            errorMessage += " throwing arm,";
        }      

        String inPos = positionCombo.getSelectionModel().getSelectedItem();
        try{
            inPos.equals("");
            positionEntered = true;
        }catch(Exception e){
            positionEntered = false;
            errorMessage += " position,";
        }
        
        String stringStance = battingStanceCombo.getSelectionModel().getSelectedItem();
        try{
            stringStance.equals("");
            stanceEntered = true;
        }catch(Exception e){
            stanceEntered = false;
            errorMessage += " stance,";
        }

        String comboText = teamCombo.getSelectionModel().getSelectedItem(); 
        if(comboText == null)
        {
            teamEntered = false;
            errorMessage += " team,";
        }else{
            teamEntered = true;     
        }

        if(firstName.getText().equals(""))
        {
            fNameEntered = false;
            errorMessage += " first name,";
        }
        
        if(lastName.getText().equals(""))
        {
            lNameEntered = false;
            errorMessage += " last name,";
        }
        
        if(!fNameEntered || !lNameEntered || !numberEntered || !armEntered || !positionEntered
                || !stanceEntered || !teamEntered)
            errorMessageLabel.setText(errorMessage);
        
        if(fNameEntered && lNameEntered && numberEntered && armEntered && positionEntered
                && stanceEntered && teamEntered)
        {
            int playerID;
            EntityManager em = Model.DBUtil.getEM();
            Query teamID = em.createNativeQuery("SELECT a.teamid FROM team a WHERE a.teamname=?");
            teamID.setParameter(1,comboText);
            int sqlTeamID = (int)teamID.getSingleResult();

            
            errorMessageLabel.setText("");
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
        
        playerID = newPlayer.getPlayerid();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Player Created");
        alert.setHeaderText("You have successfully created "+newPlayer.getFname()+" "+newPlayer.getLname()+", PlayerID: "+playerID);
        alert.setContentText(newPlayer.getFname()+" "+newPlayer.getLname()+confirmMessage+" on team "+comboText+".");
        alert.showAndWait();   
        }
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
        
        assert battingStanceCombo != null;
        battingStanceCombo.getItems().addAll("Right", "Left", "Switch");
        
        assert positionCombo != null;
        positionCombo.getItems().addAll("P", "C", "1B", "2B", "3B", "SS", "LF", "CF", "RF", "M");
        
        assert throwingArmCombo != null;
        throwingArmCombo.getItems().addAll("Right", "Left");
    }  
}

