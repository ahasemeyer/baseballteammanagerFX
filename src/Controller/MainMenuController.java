/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Hitter;
import Model.Pitcher;
import Model.Player;
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
    @FXML private Label hrlbl;
    @FXML private Label topHRNamelbl;
    @FXML private Label topHRTeamlbl;
    @FXML private Label topHRlbl; 
    @FXML private Label rbilbl;
    @FXML private Label topRBINamelbl;
    @FXML private Label topRBITeamlbl;
    @FXML private Label topRBIlbl;
    @FXML private Label sblbl;
    @FXML private Label topSBNamelbl;
    @FXML private Label topSBTeamlbl;
    @FXML private Label topSBlbl;
    private final String baseballIcon = "/images/Baseball.png";
    
    @FXML
    void handleIndivPlayer(ActionEvent event)
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/lookupIndividualPlayer.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(baseballIcon));
            stage.setTitle("Lookup Individual Player");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            System.out.println(e);
        }        
    }
    
    @FXML
    void handleTopHitters(ActionEvent event)
    {
        this.loadTopHitters();
    }
    
    @FXML
    void handleTopPitchers(ActionEvent event)
    {
        this.loadTopPitchers();
    }
    
    @FXML
    void handleDeletePlayer(ActionEvent event)
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/deletePlayerPage.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(baseballIcon));
            stage.setTitle("Delete Player");
            stage.setScene(new Scene(root1));
            stage.show();
        }catch(Exception e){
            System.out.println("Cant load new window");
        }
    }
    
            
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
    void handleLookupManager(ActionEvent event)
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/lookupManagerPage.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(baseballIcon));
            stage.setTitle("Lookup Managers");
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
    
    private Hitter loadTopHRHitter()
    {
        data = FXCollections.observableArrayList();
        List<Hitter>lst = em.createQuery("SELECT c FROM Hitter c ORDER BY c.homeruns DESC").getResultList();
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
    
    private Hitter loadTopAvgHitter()
    {
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
    
    private Hitter loadTopRBIHitter()
    {
        data = FXCollections.observableArrayList();
        
        List<Hitter>lst = em.createQuery("SELECT c FROM Hitter c ORDER BY c.rbi DESC").getResultList();
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
    
    private Hitter loadTopSBHitter()
    {
        data = FXCollections.observableArrayList();
        
        List<Hitter>lst = em.createQuery("SELECT c FROM Hitter c ORDER BY c.stolenbases DESC").getResultList();
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
    
    private Pitcher loadTopERAPitcher()
    {
        data = FXCollections.observableArrayList();
        
        List<Pitcher>lst = em.createQuery("SELECT c FROM Pitcher c ORDER BY c.earnedRunAverage ASC").getResultList();
        Pitcher retPitcher = null;
        for(Pitcher lst1 : lst)
        {
            if(lst1.getInningsPitched() > 0)
            {
                retPitcher = new Pitcher(lst1.getPlayerID());
                retPitcher.updatePitcher(lst1.getWins(), lst1.getLosses(), lst1.getGames(), lst1.getGamesStarted(), lst1.getSaves(), 
                    lst1.getSaveOpp(), lst1.getInningsPitched(), lst1.getHits(), lst1.getRuns(), lst1.getEarnedruns(), lst1.getHomeruns(),
                    lst1.getBaseonBalls(), lst1.getHitBatters(), lst1.getStrikeOuts());
                break;
            }
        }
        return retPitcher; 
    }
    
    private Pitcher loadTopWinPitcher()
    {
        data = FXCollections.observableArrayList();
        
        List<Pitcher>lst = em.createQuery("SELECT c FROM Pitcher c ORDER BY c.wins DESC").getResultList();
        Pitcher retPitcher = null;
        for(Pitcher lst1 : lst)
        {
            retPitcher = new Pitcher(lst1.getPlayerID());
            retPitcher.updatePitcher(lst1.getWins(), lst1.getLosses(), lst1.getGames(), lst1.getGamesStarted(), lst1.getSaves(), 
                lst1.getSaveOpp(), lst1.getInningsPitched(), lst1.getHits(), lst1.getRuns(), lst1.getEarnedruns(), lst1.getHomeruns(),
                lst1.getBaseonBalls(), lst1.getHitBatters(), lst1.getStrikeOuts());
            break;
        }
        return retPitcher; 
    }
    
    private Pitcher loadTopSVPitcher()
    {
        data = FXCollections.observableArrayList();
        
        List<Pitcher>lst = em.createQuery("SELECT c FROM Pitcher c ORDER BY c.saves DESC").getResultList();
        Pitcher retPitcher = null;
        for(Pitcher lst1 : lst)
        {
            retPitcher = new Pitcher(lst1.getPlayerID());
            retPitcher.updatePitcher(lst1.getWins(), lst1.getLosses(), lst1.getGames(), lst1.getGamesStarted(), lst1.getSaves(), 
                lst1.getSaveOpp(), lst1.getInningsPitched(), lst1.getHits(), lst1.getRuns(), lst1.getEarnedruns(), lst1.getHomeruns(),
                lst1.getBaseonBalls(), lst1.getHitBatters(), lst1.getStrikeOuts());
            break;
        }
        return retPitcher; 
    }
    
    private Pitcher loadTopSOPitcher()
    {
        data = FXCollections.observableArrayList();
        
        List<Pitcher>lst = em.createQuery("SELECT c FROM Pitcher c ORDER BY c.strikeOuts DESC").getResultList();
        Pitcher retPitcher = null;
        for(Pitcher lst1 : lst)
        {
            retPitcher = new Pitcher(lst1.getPlayerID());
            retPitcher.updatePitcher(lst1.getWins(), lst1.getLosses(), lst1.getGames(), lst1.getGamesStarted(), lst1.getSaves(), 
                lst1.getSaveOpp(), lst1.getInningsPitched(), lst1.getHits(), lst1.getRuns(), lst1.getEarnedruns(), lst1.getHomeruns(),
                lst1.getBaseonBalls(), lst1.getHitBatters(), lst1.getStrikeOuts());
            break;
        }
        return retPitcher; 
    }
    
    private void loadTopPitchers()
    {
        Pitcher topERAPitcher = loadTopERAPitcher(); 
        Pitcher topWPitcher = loadTopWinPitcher();
        Pitcher topSVPitcher = loadTopSVPitcher();
        Pitcher topSOPitcher = loadTopSOPitcher(); 
        avglbl.setText("ERA");
        hrlbl.setText("W");
        rbilbl.setText("SV");
        sblbl.setText("SO"); 
        
        //load ERA leader name
        Query eraPitcherFirst = em.createNativeQuery("SELECT a.fname FROM player a WHERE a.playerID=?");
        eraPitcherFirst.setParameter(1,topERAPitcher.getPlayerID());
        String firstName = (String)eraPitcherFirst.getSingleResult(); 
        Query eraPitcherLast = em.createNativeQuery("SELECT a.lname FROM player a WHERE a.playerID=?");
        eraPitcherLast.setParameter(1,topERAPitcher.getPlayerID());
        String lastName = (String)eraPitcherLast.getSingleResult(); 
        topAvgNamelbl.setText(lastName+", "+firstName);
        
        //load ERA leader ERA
        Query topERAQuery = em.createNativeQuery("SELECT a.earnedRunAverage FROM pitcher a WHERE a.playerID=?");
        topERAQuery.setParameter(1,topERAPitcher.getPlayerID());
        double topPitcherERA = (double)topERAQuery.getSingleResult();
        topAvglbl.setText(String.format("%.3f", topPitcherERA));
        
        //load ERA leader team
        Query eraPitcherTeamIDQuery = em.createNativeQuery("SELECT a.teamid FROM player a WHERE a.playerID=?"); 
        eraPitcherTeamIDQuery.setParameter(1,topERAPitcher.getPlayerID());
        int eraPitcherTeamID = (int)eraPitcherTeamIDQuery.getSingleResult();
        Query eraPitcherTeamNameQuery = em.createNativeQuery("SELECT a.teamname FROM team a WHERE a.teamID=?"); 
        eraPitcherTeamNameQuery.setParameter(1,eraPitcherTeamID);
        String eraPitcherTeamName = (String)eraPitcherTeamNameQuery.getSingleResult();
        topAvgTeamlbl.setText(eraPitcherTeamName);
        
        //load W leader name
        Query wPitcherFirst = em.createNativeQuery("SELECT a.fname FROM player a WHERE a.playerID=?");
        wPitcherFirst.setParameter(1,topWPitcher.getPlayerID());
        firstName = (String)wPitcherFirst.getSingleResult(); 
        Query wPitcherLast = em.createNativeQuery("SELECT a.lname FROM player a WHERE a.playerID=?");
        wPitcherLast.setParameter(1,topWPitcher.getPlayerID());
        lastName = (String)wPitcherLast.getSingleResult(); 
        topHRNamelbl.setText(lastName+", "+firstName);
        
        //load W leader W
        Query topWQuery = em.createNativeQuery("SELECT a.wins FROM pitcher a WHERE a.playerID=?");
        topWQuery.setParameter(1,topWPitcher.getPlayerID());
        int topPitcherW = (int)topWQuery.getSingleResult();
        topHRlbl.setText(Integer.toString(topPitcherW));
        
        //load W leader team
        Query wPitcherTeamIDQuery = em.createNativeQuery("SELECT a.teamid FROM player a WHERE a.playerID=?"); 
        wPitcherTeamIDQuery.setParameter(1,topWPitcher.getPlayerID());
        int wPitcherTeamID = (int)wPitcherTeamIDQuery.getSingleResult();
        Query wPitcherTeamNameQuery = em.createNativeQuery("SELECT a.teamname FROM team a WHERE a.teamID=?"); 
        wPitcherTeamNameQuery.setParameter(1,wPitcherTeamID);
        String wPitcherTeamName = (String)wPitcherTeamNameQuery.getSingleResult();
        topHRTeamlbl.setText(wPitcherTeamName);
        
        //load SV leader name
        Query svPitcherFirst = em.createNativeQuery("SELECT a.fname FROM player a WHERE a.playerID=?");
        svPitcherFirst.setParameter(1,topSVPitcher.getPlayerID());
        firstName = (String)svPitcherFirst.getSingleResult(); 
        Query svPitcherLast = em.createNativeQuery("SELECT a.lname FROM player a WHERE a.playerID=?");
        svPitcherLast.setParameter(1,topSVPitcher.getPlayerID());
        lastName = (String)svPitcherLast.getSingleResult(); 
        topRBINamelbl.setText(lastName+", "+firstName);
        
        //load SV leader SV
        Query topSVQuery = em.createNativeQuery("SELECT a.saves FROM pitcher a WHERE a.playerID=?");
        topSVQuery.setParameter(1,topSVPitcher.getPlayerID());
        int topPitcherSV = (int)topSVQuery.getSingleResult();
        topRBIlbl.setText(Integer.toString(topPitcherSV));
        
        //load SV leader team
        Query svPitcherTeamIDQuery = em.createNativeQuery("SELECT a.teamid FROM player a WHERE a.playerID=?"); 
        svPitcherTeamIDQuery.setParameter(1,topWPitcher.getPlayerID());
        int svPitcherTeamID = (int)svPitcherTeamIDQuery.getSingleResult();
        Query svPitcherTeamNameQuery = em.createNativeQuery("SELECT a.teamname FROM team a WHERE a.teamID=?"); 
        svPitcherTeamNameQuery.setParameter(1,svPitcherTeamID);
        String svPitcherTeamName = (String)svPitcherTeamNameQuery.getSingleResult();
        topRBITeamlbl.setText(svPitcherTeamName);
        
        //load SO leader name
        Query soPitcherFirst = em.createNativeQuery("SELECT a.fname FROM player a WHERE a.playerID=?");
        soPitcherFirst.setParameter(1,topSOPitcher.getPlayerID());
        firstName = (String)soPitcherFirst.getSingleResult(); 
        Query soPitcherLast = em.createNativeQuery("SELECT a.lname FROM player a WHERE a.playerID=?");
        soPitcherLast.setParameter(1,topSOPitcher.getPlayerID());
        lastName = (String)soPitcherLast.getSingleResult(); 
        topSBNamelbl.setText(lastName+", "+firstName);
        
        //load SO leader SO
        Query topSOQuery = em.createNativeQuery("SELECT a.strikeouts FROM pitcher a WHERE a.playerID=?");
        topSOQuery.setParameter(1,topSOPitcher.getPlayerID());
        int topPitcherSO = (int)topSOQuery.getSingleResult();
        topSBlbl.setText(Integer.toString(topPitcherSO));
        
        //load SO leader team
        Query soPitcherTeamIDQuery = em.createNativeQuery("SELECT a.teamid FROM player a WHERE a.playerID=?"); 
        soPitcherTeamIDQuery.setParameter(1,topSOPitcher.getPlayerID());
        int soPitcherTeamID = (int)soPitcherTeamIDQuery.getSingleResult();
        Query soPitcherTeamNameQuery = em.createNativeQuery("SELECT a.teamname FROM team a WHERE a.teamID=?"); 
        soPitcherTeamNameQuery.setParameter(1,soPitcherTeamID);
        String soPitcherTeamName = (String)soPitcherTeamNameQuery.getSingleResult();
        topSBTeamlbl.setText(soPitcherTeamName);
        
    }
    
    private void loadTopHitters()
    {
        Hitter topAvgHitter = loadTopAvgHitter();
        Hitter topHRHitter = loadTopHRHitter();
        Hitter topRBIHitter = loadTopRBIHitter(); 
        Hitter topSBHitter = loadTopSBHitter(); 
        avglbl.setText("AVG");
        hrlbl.setText("HR");
        rbilbl.setText("RBI");
        sblbl.setText("SB"); 
        
        
        //load sb leader name
        Query sbHitterFirst = em.createNativeQuery("SELECT a.fname FROM player a WHERE a.playerID=?");
        sbHitterFirst.setParameter(1,topSBHitter.getPlayerID());
        String firstName = (String)sbHitterFirst.getSingleResult(); 
        Query sbHitterLast = em.createNativeQuery("SELECT a.lname FROM player a WHERE a.playerID=?");
        sbHitterLast.setParameter(1,topSBHitter.getPlayerID());
        String lastName = (String)sbHitterLast.getSingleResult(); 
        topSBNamelbl.setText(lastName+", "+firstName);
        
        //load rbi leader name
        Query rbiHitterFirst = em.createNativeQuery("SELECT a.fname FROM player a WHERE a.playerID=?");
        rbiHitterFirst.setParameter(1,topRBIHitter.getPlayerID());
        firstName = (String)rbiHitterFirst.getSingleResult(); 
        Query rbiHitterLast = em.createNativeQuery("SELECT a.lname FROM player a WHERE a.playerID=?");
        rbiHitterLast.setParameter(1,topRBIHitter.getPlayerID());
        lastName = (String)rbiHitterLast.getSingleResult(); 
        topRBINamelbl.setText(lastName+", "+firstName);
        
        //load hr leader name
        Query hrHitterFirst = em.createNativeQuery("SELECT a.fname FROM player a WHERE a.playerID=?");
        hrHitterFirst.setParameter(1,topHRHitter.getPlayerID());
        firstName = (String)hrHitterFirst.getSingleResult(); 
        Query hrHitterLast = em.createNativeQuery("SELECT a.lname FROM player a WHERE a.playerID=?");
        hrHitterLast.setParameter(1,topHRHitter.getPlayerID());
        lastName = (String)hrHitterLast.getSingleResult(); 
        topHRNamelbl.setText(lastName+", "+firstName);
        
        //load avg leader name
        Query avgHitterFirst = em.createNativeQuery("SELECT a.fname FROM player a WHERE a.playerID=?");
        avgHitterFirst.setParameter(1,topAvgHitter.getPlayerID());
        firstName = (String)avgHitterFirst.getSingleResult(); 
        Query avgHitterLast = em.createNativeQuery("SELECT a.lname FROM player a WHERE a.playerID=?");
        avgHitterLast.setParameter(1,topAvgHitter.getPlayerID());
        lastName = (String)avgHitterLast.getSingleResult(); 
        topAvgNamelbl.setText(lastName+", "+firstName); 
        
        //load sb leader sb #
        Query topHitterSBQuery = em.createNativeQuery("SELECT a.stolenbases FROM hitter a WHERE a.playerID=?");
        topHitterSBQuery.setParameter(1,topSBHitter.getPlayerID());
        int topHitterSB = (int)topHitterSBQuery.getSingleResult();
        topSBlbl.setText(Integer.toString(topHitterSB));
        
        //load rbi leader rbi #
        Query topHitterRBIQuery = em.createNativeQuery("SELECT a.rbi FROM hitter a WHERE a.playerID=?");
        topHitterRBIQuery.setParameter(1,topRBIHitter.getPlayerID());
        int topHitterRBI = (int)topHitterRBIQuery.getSingleResult();
        topRBIlbl.setText(Integer.toString(topHitterRBI));
        
        //load hr leader hr #
        Query topHitterHRQuery = em.createNativeQuery("SELECT a.homeruns FROM hitter a WHERE a.playerID=?");
        topHitterHRQuery.setParameter(1,topHRHitter.getPlayerID());
        int topHitterHR = (int)topHitterHRQuery.getSingleResult();
        topHRlbl.setText(Integer.toString(topHitterHR));
        
        //load avg leager avg
        Query avgHitterAvgQuery = em.createNativeQuery("SELECT a.battingavg FROM hitter a WHERE a.playerID=?");
        avgHitterAvgQuery.setParameter(1,topAvgHitter.getPlayerID());
        double topHitterAvg = (double)avgHitterAvgQuery.getSingleResult();
        topAvglbl.setText(String.format("%.3f",topHitterAvg));
        
        //load sb leader team
        Query sbHitterTeamIDQuery = em.createNativeQuery("SELECT a.teamid FROM player a WHERE a.playerID=?"); 
        sbHitterTeamIDQuery.setParameter(1,topSBHitter.getPlayerID());
        int sbHitterTeamID = (int)sbHitterTeamIDQuery.getSingleResult();
        Query sbHitterTeamNameQuery = em.createNativeQuery("SELECT a.teamname FROM team a WHERE a.teamID=?"); 
        sbHitterTeamNameQuery.setParameter(1,sbHitterTeamID);
        String sbHitterTeamName = (String)sbHitterTeamNameQuery.getSingleResult();
        topSBTeamlbl.setText(sbHitterTeamName);
        
        //load rbi leader team
        Query rbiHitterTeamIDQuery = em.createNativeQuery("SELECT a.teamid FROM player a WHERE a.playerID=?"); 
        rbiHitterTeamIDQuery.setParameter(1,topRBIHitter.getPlayerID());
        int rbiHitterTeamID = (int)rbiHitterTeamIDQuery.getSingleResult();
        Query rbiHitterTeamNameQuery = em.createNativeQuery("SELECT a.teamname FROM team a WHERE a.teamID=?"); 
        rbiHitterTeamNameQuery.setParameter(1,rbiHitterTeamID);
        String rbiHitterTeamName = (String)rbiHitterTeamNameQuery.getSingleResult();
        topRBITeamlbl.setText(rbiHitterTeamName);
        
        //load hr leader team
        Query hrHitterTeamIDQuery = em.createNativeQuery("SELECT a.teamid FROM player a WHERE a.playerID=?"); 
        hrHitterTeamIDQuery.setParameter(1,topHRHitter.getPlayerID());
        int topHitterTeamID = (int)hrHitterTeamIDQuery.getSingleResult();
        Query hrHitterTeamNameQuery = em.createNativeQuery("SELECT a.teamname FROM team a WHERE a.teamID=?"); 
        hrHitterTeamNameQuery.setParameter(1,topHitterTeamID);
        String hrHitterTeamName = (String)hrHitterTeamNameQuery.getSingleResult();
        topHRTeamlbl.setText(hrHitterTeamName);
        
        //load avg leader team
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
