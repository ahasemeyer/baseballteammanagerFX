/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Hitter;
import Model.HitterFX;
import Model.Manager;
import Model.ManagerFX;
import Model.Pitcher;
import Model.PitcherFX;
import Model.Player;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    @FXML private TableView<HitterFX> hitterTable; 
    @FXML private TableColumn<HitterFX, String> avgCol;
    @FXML private TableColumn<HitterFX, String> obpCol;
    @FXML private TableColumn<HitterFX, String> slgCol;
    @FXML private TableColumn<HitterFX, Number> rbiCol;
    @FXML private TableColumn<HitterFX, Number> abCol;
    @FXML private TableColumn<HitterFX, Number> hCol;
    @FXML private TableColumn<HitterFX, Number> b2Col;
    @FXML private TableColumn<HitterFX, Number> b3Col;
    @FXML private TableColumn<HitterFX, Number> hrCol;
    @FXML private TableColumn<HitterFX, Number> tbCol;
    @FXML private TableColumn<HitterFX, Number> soCol;
    @FXML private TableColumn<HitterFX, Number> bbCol;
    @FXML private TableColumn<HitterFX, Number> hbpCol;
    @FXML private TableColumn<HitterFX, Number> sbCol;
    @FXML private TableColumn<HitterFX, Number> csCol;
    @FXML private TableColumn<HitterFX, String> sbpCol;
    @FXML private TableColumn<HitterFX, Number> sacCol;
    @FXML private TableView<PitcherFX> pitcherTable;
    @FXML private TableColumn<PitcherFX, Number> wCol;
    @FXML private TableColumn<PitcherFX, Number> lCol;
    @FXML private TableColumn<PitcherFX, Number> gCol;
    @FXML private TableColumn<PitcherFX, Number> gsCol;
    @FXML private TableColumn<PitcherFX, Number> pbbCol;
    @FXML private TableColumn<PitcherFX, Number> phbpCol;
    @FXML private TableColumn<PitcherFX, Number> phrCol;
    @FXML private TableColumn<PitcherFX, Number> psoCol;
    @FXML private TableColumn<PitcherFX, Number> phCol;
    @FXML private TableColumn<PitcherFX, String> eraCol;
    @FXML private TableColumn<PitcherFX, String> pavgCol;
    @FXML private TableColumn<PitcherFX, Number> erCol;
    @FXML private TableColumn<PitcherFX, Number> rCol;
    @FXML private TableColumn<PitcherFX, String> ipCol;
    @FXML private TableColumn<PitcherFX, String> whipCol;
    @FXML private TableColumn<PitcherFX, Number> svCol;
    @FXML private TableColumn<PitcherFX, Number> svoCol;
    @FXML private TableView<ManagerFX> managerTable;
    @FXML private TableColumn<ManagerFX, Number> mwCol;
    @FXML private TableColumn<ManagerFX, Number> mlCol;
    @FXML private TableColumn<ManagerFX, Number> mtCol;
    @FXML private TableColumn<ManagerFX, String> mwpCol;
    @FXML private ImageView profilePicture; 
    @FXML private Label playerNamelbl;
    @FXML private Label playerIDlbl;
    @FXML private Label teamNamelbl;
    @FXML private Label positionlbl;
    private File file = new File("src/images/defaultProfile.jpg");
    private Image defaultPic = new Image(file.toURI().toString());
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
            
            //load hitter table view
            if(type.equalsIgnoreCase("hitter"))
            {
                ObservableList<HitterFX> hitterData;
                hitterData = FXCollections.observableArrayList();
        
                List<Hitter>lst = em.createQuery("SELECT c FROM Hitter c WHERE c.playerid="+playerID).getResultList();

                for(Hitter lst1 : lst)
                {
                    hitterData.add(new HitterFX(lst1.getPlayerID(), lst1.getAtbats(), lst1.getBB(), lst1.getBattingAvg(), lst1.getCaughtStealing(), 
                    lst1.getDoubles(), lst1.getHBP(), lst1.getOBP(), lst1.getSacFly(), lst1.getSlugging(), lst1.getStealingPerc(),
                    lst1.getStolenBases(), lst1.getStrikeouts(), lst1.getTotalBases(), lst1.getTriples(), lst1.getHits(), lst1.getHomeruns(), lst1.getRBI()));
                }
                
                hitterTable.setItems(hitterData);
                hitterTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
                abCol.setCellValueFactory(cell -> cell.getValue().getAtBatsProperty());
                hCol.setCellValueFactory(cell -> cell.getValue().getHitsProperty());
                b2Col.setCellValueFactory(cell -> cell.getValue().getDoublesProperty());
                b3Col.setCellValueFactory(cell -> cell.getValue().getTriplesProperty());
                hrCol.setCellValueFactory(cell -> cell.getValue().getHomerunsProperty());
                avgCol.setCellValueFactory(cell -> Bindings.format("%.3f", cell.getValue().getBattingAvgProperty()));
                slgCol.setCellValueFactory(cell -> Bindings.format("%.3f", cell.getValue().getSluggingProperty()));
                bbCol.setCellValueFactory(cell -> cell.getValue().getBBProperty());
                hbpCol.setCellValueFactory(cell -> cell.getValue().getHBPProperty());
                tbCol.setCellValueFactory(cell -> cell.getValue().getTotalBasesProperty());
                sbCol.setCellValueFactory(cell -> cell.getValue().getStolenBasesProperty());
                csCol.setCellValueFactory(cell -> cell.getValue().getCaughtStealingProperty());
                sacCol.setCellValueFactory(cell -> cell.getValue().getSacFlyProperty());
                sbpCol.setCellValueFactory(cell -> Bindings.format("%.3f", cell.getValue().getStealingPercProperty()));
                obpCol.setCellValueFactory(cell -> Bindings.format("%.3f", cell.getValue().getOBPProperty()));
                soCol.setCellValueFactory(cell -> cell.getValue().getStrikeoutsProperty());
                rbiCol.setCellValueFactory(cell -> cell.getValue().getRBIProperty());
                
                 //pIDCol.setCellValueFactory(cell -> cell.getValue().getPlayerIDProperty());
                hitterTable.setVisible(true);
            }
            
            //load pitcher table view
            if(type.equalsIgnoreCase("pitcher"))
            {
                ObservableList<PitcherFX> pitcherData;
                pitcherData = FXCollections.observableArrayList();
                List<Pitcher>lst = em.createQuery("SELECT c FROM Pitcher c WHERE c.playerID="+playerID).getResultList(); 
                for(Pitcher lst1 : lst)
                {
                    pitcherData.add(new PitcherFX(lst1.getPlayerID(), lst1.getWHIP(), lst1.getBaseonBalls(), lst1.getERA(), lst1.getEarnedruns(),
                        lst1.getGames(), lst1.getGames(), lst1.getHitBatters(), lst1.getHits(), lst1.getHomeruns(), lst1.getInningsPitched(),
                        lst1.getLosses(), lst1.getOppAvg(), lst1.getRuns(), lst1.getSaveOpp(), lst1.getSaves(), lst1.getStrikeOuts(), lst1.getWins()));
                }
                pitcherTable.setItems(pitcherData);
                pitcherTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
                whipCol.setCellValueFactory(cell -> Bindings.format("%.3f", cell.getValue().getWHIPProperty()));
                pbbCol.setCellValueFactory(cell -> cell.getValue().getBaseonBallsProperty());
                eraCol.setCellValueFactory(cell -> Bindings.format("%.2f", cell.getValue().getERAProperty()));
                erCol.setCellValueFactory(cell -> cell.getValue().getEarnedrunsProperty());
                gCol.setCellValueFactory(cell -> cell.getValue().getGamesProperty());
                gsCol.setCellValueFactory(cell -> cell.getValue().getGamesStartedProperty());
                phbpCol.setCellValueFactory(cell -> cell.getValue().getHitBattersProperty());
                phCol.setCellValueFactory(cell -> cell.getValue().getHitsProperty());
                phrCol.setCellValueFactory(cell -> cell.getValue().getHomerunsProperty());
                ipCol.setCellValueFactory(cell -> Bindings.format("%.1f", cell.getValue().getInningsPitchedProperty()));
                lCol.setCellValueFactory(cell -> cell.getValue().getLossesProperty());
                pavgCol.setCellValueFactory(cell -> Bindings.format("%.3f", cell.getValue().getOppAvgProperty()));
                rCol.setCellValueFactory(cell -> cell.getValue().getRunsProperty());
                svoCol.setCellValueFactory(cell -> cell.getValue().getSavesOppProperty());
                svCol.setCellValueFactory(cell -> cell.getValue().getSavesProperty());
                psoCol.setCellValueFactory(cell -> cell.getValue().getStrikeOutsProperty());
                wCol.setCellValueFactory(cell -> cell.getValue().getWinsProperty());  
                pitcherTable.setVisible(true);
            }
                
            //load manager table view
            if(type.equalsIgnoreCase("manager"))
            {
                ObservableList<ManagerFX> managerData;
                EntityManager em = Model.DBUtil.getEM();
                managerData = FXCollections.observableArrayList();
                List<Manager>lst = em.createQuery("SELECT c FROM Manager c WHERE c.playerID="+playerID).getResultList();
                for(Manager lst1 : lst)
                {
                    managerData.add(new ManagerFX(lst1.getPlayerID(), lst1.getTeamID(), lst1.getWins(), lst1.getLosses(), lst1.getTies(), lst1.getWinPerc()));
                }
                managerTable.setItems(managerData);
                managerTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
                mtCol.setCellValueFactory(cell -> cell.getValue().getTeamIDProperty());
                mwCol.setCellValueFactory(cell -> cell.getValue().getWinsProperty());
                mlCol.setCellValueFactory(cell -> cell.getValue().getLossesProperty());
                mwpCol.setCellValueFactory(cell -> Bindings.format("%.3f", cell.getValue().getWinPercProperty())); 
                managerTable.setVisible(true);
            }
            
            
            
            
            
            
            
            
            
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    @FXML
    void handleTeam(ActionEvent event)
    {
        playerNamelbl.setText("");
        playerIDlbl.setText("");
        teamNamelbl.setText("");
        positionlbl.setText("");
        profilePicture.setImage(defaultPic);
        hitterTable.setVisible(false);
        pitcherTable.setVisible(false);
        managerTable.setVisible(false);
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
        playerNamelbl.setText("");
        playerIDlbl.setText("");
        teamNamelbl.setText("");
        positionlbl.setText("");
        playerCombo.getItems().clear();
        teamCombo.getItems().clear(); 
        profilePicture.setImage(defaultPic);
        hitterTable.setVisible(false);
        pitcherTable.setVisible(false);
        managerTable.setVisible(false);
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
