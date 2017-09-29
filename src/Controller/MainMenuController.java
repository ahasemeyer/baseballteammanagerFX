/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Hitter;
import Model.Team;
import Model.TeamFX;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author hasmy
 */
public class MainMenuController implements Initializable {

    private static final EntityManager em = Model.DBUtil.getEM();
    ObservableList<TeamFX> data; 
    @FXML private TableView<TeamFX> lsTable;
    @FXML private TableColumn<TeamFX, String> lsteamCol;
    @FXML private TableColumn<TeamFX, Number> lswCol;
    @FXML private TableColumn<TeamFX, String> lswpCol;
    @FXML private TableColumn<TeamFX, Number> lslCol;
    @FXML private TableColumn<TeamFX, Number> lstCol; 
    @FXML private Label avglbl;
    @FXML private Label topAvgNamelbl;
    @FXML private Label topAvgTeamlbl;
    @FXML private Label topAvglbl; 
    private String baseballIcon = "/images/Baseball.png";
            
    
    
    @FXML 
    void handleLookupPitchers(ActionEvent event)
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/lookupPitchersPage.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(baseballIcon));
            stage.setTitle("Lookup Pitchers");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            System.out.println(e);
        } 
    }
    
    @FXML
    void handleLookupHitters(ActionEvent event) 
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/lookupHittersPage.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(baseballIcon));
            stage.setTitle("Lookup Hitters");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            System.out.println(e);
        } 
    }
    
    
    @FXML
    void handleLookupPlayers(ActionEvent event)
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/lookupPlayersPage.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(baseballIcon));
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/updatePlayerPage.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(baseballIcon));
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/updateHitterPage.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(baseballIcon));
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/updatePitcherPage.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(baseballIcon));
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/updateTeamPage.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(baseballIcon));
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/updateManagerPage.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(baseballIcon));
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/lookupPlayerPagebyID.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(baseballIcon));
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/lookupPlayerPagebyID.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(baseballIcon));
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/createPlayerPage.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(baseballIcon));
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/createTeamPage.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(baseballIcon));
            stage.setTitle("Create Team");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            System.out.println("Cant load new window");
        }
    }
    
    @FXML
    public void handlelsRefresh(ActionEvent event)
    {
        loadLeagueStandings(); 

    }
    
    private Hitter loadTopAvgHitter()
    {
        EntityManager em = Model.DBUtil.getEM();
        data = FXCollections.observableArrayList();
        
        List<Hitter>lst = em.createQuery("SELECT c FROM Hitter c ORDER BY c.battingavg DESC").getResultList();
        Hitter retHitter = null;
        for(Hitter lst1 : lst)
        {
            retHitter = new Hitter(lst1.getPlayerID());
            retHitter.updateHitter(lst1.getAtbats(), lst1.getHits(), lst1.getDoubles(), lst1.getTriples(), lst1.getHomeruns(), 
                    lst1.getBB(), lst1.getHBP(), lst1.getSacFly(), lst1.getStrikeouts(), lst1.getCaughtStealing(),
                    lst1.getStolenBases(), lst1.getRBI());
            break;
        }
        return retHitter; 
    }
    
    private void loadTopHitters()
    {
        Hitter topAvgHitter = loadTopAvgHitter();
        
        Query avgHitterFirst = em.createNativeQuery("SELECT a.fname FROM player a WHERE a.playerID=?");
        avgHitterFirst.setParameter(1,topAvgHitter.getPlayerID());
        String firstName = (String)avgHitterFirst.getSingleResult(); 
        Query avgHitterLast = em.createNativeQuery("SELECT a.lname FROM player a WHERE a.playerID=?");
        avgHitterLast.setParameter(1,topAvgHitter.getPlayerID());
        String lastName = (String)avgHitterLast.getSingleResult(); 
        topAvgNamelbl.setText(lastName+", "+firstName); 
        
        Query avgHitterAvgQuery = em.createNativeQuery("SELECT a.battingavg FROM hitter a WHERE a.playerID=?");
        avgHitterAvgQuery.setParameter(1,topAvgHitter.getPlayerID());
        double topHitterAvg = (double)avgHitterAvgQuery.getSingleResult();
        topAvglbl.setText(String.format("%.3f",topHitterAvg));
        
        Query avgHitterTeamIDQuery = em.createNativeQuery("SELECT a.teamid FROM player a WHERE a.playerID=?"); 
        avgHitterTeamIDQuery.setParameter(1,topAvgHitter.getPlayerID());
        int avgHitterTeamID = (int)avgHitterTeamIDQuery.getSingleResult();
        Query avgHitterTeamNameQuery = em.createNativeQuery("SELECT a.teamname FROM team a WHERE a.teamID=?"); 
        avgHitterTeamNameQuery.setParameter(1,avgHitterTeamID);
        String avgHitterTeamName = (String)avgHitterTeamNameQuery.getSingleResult();
        topAvgTeamlbl.setText(avgHitterTeamName);
        
        
    }
    
    private void loadLeagueStandings()
    {
        //EntityManager em = Model.DBUtil.getEM();
        data = FXCollections.observableArrayList();
        
        List<Team>lst = em.createQuery("SELECT c FROM Team c ORDER BY c.winPerc DESC").getResultList();
        for(Team lst1 : lst)
        {
            data.add(new TeamFX(lst1.getTeamID(), lst1.getLosses(), lst1.getTeamName(), lst1.getWinPerc(), lst1.getWins(), lst1.getTies()));
        }
        
        lsTable.setItems(data);
        lsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        lswCol.setCellValueFactory(cell -> cell.getValue().getWinsProperty());
        lslCol.setCellValueFactory(cell -> cell.getValue().getLossesProperty());
        lstCol.setCellValueFactory(cell -> cell.getValue().getTiesProperty());
        lswpCol.setCellValueFactory(cell -> Bindings.format("%.3f", cell.getValue().getWinPercProperty()));
        lsteamCol.setCellValueFactory(cell -> cell.getValue().getTeamNameProperty());        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        loadLeagueStandings();
        loadTopHitters(); 
    }   
}
