/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author hasmy
 */
public class DeletePlayerPageController implements Initializable 
{
    private int counter; 
    private int selectedPlayerID; 
    @FXML private ComboBox<String> selectTeamCombo;
    @FXML private ComboBox<String> selectPlayerCombo;

    @FXML 
    void handleDeletePlayer(ActionEvent event) 
    {    
        try{
            String selectedItem = selectPlayerCombo.getSelectionModel().getSelectedItem();; 
            String[] tokens = selectedItem.split(":");
            selectedPlayerID = Integer.parseInt(tokens[1]);
            Model.DBUtil.deletePlayer(selectedPlayerID);
        }catch(Exception e){
            System.out.println("Select Player");
        }
    }
    
    @FXML
    void handleTeamCombo(ActionEvent event)
    {
        String selectedTeam = selectTeamCombo.getSelectionModel().getSelectedItem();
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
        selectPlayerCombo.getItems().clear();
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
            selectPlayerCombo.getItems().add(nameCombined[counter]);
            counter++;
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        assert selectTeamCombo != null;
        selectTeamCombo.getItems().clear();
        
        EntityManager em = Model.DBUtil.getEM();
        
        Query teams = em.createNativeQuery("SELECT teamname FROM team");
        List<String> list = teams.getResultList();
        list.forEach((team) -> {
            selectTeamCombo.getItems().add(team);
        });
    }    
    
}
