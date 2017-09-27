/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teammanagerjavafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author hasmy
 */
public class MainMenuController implements Initializable {

    
    @FXML
    void handleLookupPlayers(ActionEvent event)
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("lookupPlayersPage.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Lookup Players");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            System.out.println(e);
        } 
    }
    
    @FXML
    void handleUpdatePlayer(ActionEvent event)
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updatePlayerPage.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Update Player");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            System.out.println("Cant load new window");
        }         
    }  
    
    @FXML
    void handleUpdateHitter(ActionEvent event) 
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateHitterPage.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Update Hitter");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            System.out.println("Cant load new window");
        } 
    }
    
    @FXML
    void handleUpdatePitcher(ActionEvent event) 
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updatePitcherPage.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Update Pitcher");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            System.out.println("Cant load new window");
        } 
    }
    
    @FXML
    void handleUpdateTeam(ActionEvent event)
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateTeamPage.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Update Team");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            System.out.println("Cant load new window");
        }
    }
    
    @FXML
    void handleUpdateManager(ActionEvent event) 
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateManagerPage.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Update Manager");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            System.out.println("Cant load new window");
        }
    }
    
    @FXML
    void handleLookupPlayerName(ActionEvent event)
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("lookupPlayerPagebyID.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Lookup Player by Name");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            System.out.println("Cant load new window");
        }     
    }
    
    @FXML
    void handleLookupPlayerID(ActionEvent event)
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("lookupPlayerPagebyID.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Lookup Player by ID");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            System.out.println("Cant load new window");
        }        
    }

    @FXML
    void handlePlayerButton(ActionEvent event) 
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createPlayerPage.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Create Player");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            System.out.println("Cant load new window");
        }
    } 

    @FXML
    void handleTeamButton(ActionEvent event) 
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createTeamPage.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Create Team");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            System.out.println("Cant load new window");
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }   
}
