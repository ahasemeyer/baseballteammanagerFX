/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Hitter;
import Model.Player;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author hasmy
 */
public class LookupIndividualPlayerController implements Initializable 
{
    @FXML private ComboBox<String> teamCombo;  
    @FXML private ComboBox<String> typeCombo; 
    @FXML private ComboBox<String> playerCombo; 
    @FXML private ImageView profilePicture; 
    @FXML private Label playerNamelbl;
    @FXML private Label playerIDlbl;
    @FXML private Label teamNamelbl;
    @FXML private Label positionlbl;
    private int counter = 0;
    private int playerID; 
    private String type;
    private final EntityManager em = Model.DBUtil.getEM();
    
    @FXML
    void handleLookup(ActionEvent event)
    {
        try{
            //check to see if a player is selected
            String selectedItem = playerCombo.getSelectionModel().getSelectedItem();
            String[] tokens = selectedItem.split(":");
            playerID = Integer.parseInt(tokens[1]);
            
            //load profile picture
            File file = new File("src/images/profile/"+playerID+".png");
            Image image = new Image(file.toURI().toString());
            profilePicture.setImage(image);
            
            //load player information and set labels
            Player player = new Player(playerID);
            player = Model.Player.loadPlayerData(playerID); 
            playerNamelbl.setText(player.getLname()+", "+player.getFname());
            playerIDlbl.setText(Integer.toString(playerID));
            positionlbl.setText(player.getPosition());
            
            //load team name
            Query teamNameQuery = em.createNativeQuery("SELECT teamname FROM team WHERE teamid=?");
            teamNameQuery.setParameter(1,player.getTeamid());
            String teamName = (String)teamNameQuery.getSingleResult();
            teamNamelbl.setText(teamName);
            
            
            if(type.equalsIgnoreCase("hitter"))
            {
                Hitter hitter = new Hitter(playerID);
                hitter = Model.Hitter.loadHitterData(playerID);
                //String fullName = player.
            }
            
            if(type.equalsIgnoreCase("pitcher"))
            {
                System.out.println("pitcher");
            }
                
            if(type.equalsIgnoreCase("manager"))
            {
                System.out.println("manager");
            }
            
            
            
            
            
            
            
            
            
            
        }catch(Exception e){
            System.out.println("Select a Player");
        }
    }
    
    @FXML
    void handleTeam(ActionEvent event)
    {
        List<Integer>pitcherIDList = null; 
        List<Integer>playerIDList =  null;
        int counter1 = 0;
        try{
            String selectedTeam = teamCombo.getSelectionModel().getSelectedItem();
            Query teamIDQuery = em.createNativeQuery("SELECT teamid FROM team WHERE teamname=?");
            teamIDQuery.setParameter(1,selectedTeam);
            int teamID = (int)teamIDQuery.getSingleResult();
            Query playerIDSQL = em.createNativeQuery("SELECT playerid FROM player WHERE teamid=?");
            playerIDSQL.setParameter(1,teamID);
            playerIDList = playerIDSQL.getResultList();
            String selectedType = typeCombo.getSelectionModel().getSelectedItem(); 
            type = selectedType; 
            Query pitcherIDSQL = em.createNativeQuery("SELECT playerid FROM "+selectedType);
            pitcherIDList = pitcherIDSQL.getResultList();

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

            counter1 = 0;
            int[] actualPlayerID = new int[playerIDList.size()];
            for(int i=0; i<playerIDList.size(); i++)
            {
                for(int j=0; j<pitcherIDList.size(); j++)
                {
                    if(playerIDArray[i]==pitcherIDArray[j])
                    {
                        actualPlayerID[counter1] = playerIDArray[i];
                        counter1++;
                    }
                }
            }     
            playerCombo.getItems().clear(); 
            for(int i=0; i<counter1;i++)
            {
                Query playerFName = em.createNativeQuery("SELECT fname FROM player WHERE playerid=?");
                playerFName.setParameter(1,actualPlayerID[i]);
                String fName = (String)playerFName.getSingleResult();
                Query playerLName = em.createNativeQuery("SELECT lname FROM player WHERE playerid=?");
                playerLName.setParameter(1,actualPlayerID[i]);
                String lName = (String)playerLName.getSingleResult();
                playerCombo.getItems().add(lName+", "+fName+"      ID:"+actualPlayerID[i]);
            } 
        }catch(Exception e){
            System.out.println("Select a Team");
        }
    }
    
    @FXML
    void handleType(ActionEvent event)
    {
        type = null; 
        playerCombo.getItems().clear();
        teamCombo.getItems().clear(); 
        Query teams = em.createNativeQuery("SELECT teamname FROM team");
        List<String> list = teams.getResultList();
        list.forEach((team) -> {
            teamCombo.getItems().add(team);
        });
    }
    
    @FXML
    void handlePlayer(ActionEvent event)
    {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {     
        assert typeCombo != null;
        typeCombo.getItems().addAll("Hitter", "Pitcher", "Manager");
    }    
    
}
