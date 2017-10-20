/**
 * FXML Controller class
 *
 * @author Austin Hasemeyer
 * @document LookupPlayersByTeamController
 * @description This page will load a TableView of all Players that belong
 *      to the selected team
 */
package Controller;

import Model.Hitter;
import Model.HitterFX;
import Model.Pitcher;
import Model.PitcherFX;
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
import javafx.scene.image.ImageView;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class LookupPlayersByTeamController implements Initializable 
{
    private final static EntityManager em = Model.DBUtil.getEM();
    @FXML private ComboBox<String> teamCombo;
    @FXML private ComboBox<String> typeCombo;
    @FXML private TableView<PitcherFX> pitcherTable;
    @FXML private TableColumn<PitcherFX, Number> pIDCol;
    @FXML private TableColumn<PitcherFX, Number> pWCol;
    @FXML private TableColumn<PitcherFX, Number> pLCol;
    @FXML private TableColumn<PitcherFX, Number> pGCol;
    @FXML private TableColumn<PitcherFX, Number> pGSCol;
    @FXML private TableColumn<PitcherFX, String> pERACol;
    @FXML private TableColumn<PitcherFX, Number> pSOCol;
    @FXML private TableColumn<PitcherFX, Number> pERCol;
    @FXML private TableColumn<PitcherFX, Number> pRCol;
    @FXML private TableColumn<PitcherFX, String> pIPCol;
    @FXML private TableColumn<PitcherFX, Number> pHCol;
    @FXML private TableColumn<PitcherFX, String> pAVGCol;
    @FXML private TableColumn<PitcherFX, Number> pHRCol;
    @FXML private TableColumn<PitcherFX, Number> pBBCol;
    @FXML private TableColumn<PitcherFX, Number> pHBPCol;
    @FXML private TableColumn<PitcherFX, String> pWHIPCol;
    @FXML private TableColumn<PitcherFX, Number> pSVCol;
    @FXML private TableColumn<PitcherFX, Number>pSVOCol;
    @FXML private TableView<HitterFX> hitterTable;
    @FXML private TableColumn<HitterFX, Number> hIDCol;
    @FXML private TableColumn<HitterFX, String> hAVGCol;
    @FXML private TableColumn<HitterFX, String> hOBPCol;
    @FXML private TableColumn<HitterFX, String>hSLGCol;
    @FXML private TableColumn<HitterFX, Number> hRBICol;
    @FXML private TableColumn<HitterFX, Number> hABCol;
    @FXML private TableColumn<HitterFX, Number> hHCol;
    @FXML private TableColumn<HitterFX, Number> h2BCol;
    @FXML private TableColumn<HitterFX, Number> h3BCol;
    @FXML private TableColumn<HitterFX, Number> hHRCol;
    @FXML private TableColumn<HitterFX, Number> hTBCol;
    @FXML private TableColumn<HitterFX, Number> hSOCol;
    @FXML private TableColumn<HitterFX, Number> hBBCol;
    @FXML private TableColumn<HitterFX, Number> hHBPCol;
    @FXML private TableColumn<HitterFX, Number> hSBCol;
    @FXML private TableColumn<HitterFX, Number> hCSCol;
    @FXML private TableColumn<HitterFX, String> hSBPCol;
    @FXML private TableColumn<HitterFX, Number> hSACCol;
    @FXML private ImageView teamAlert;
    @FXML private ImageView typeAlert;
    @FXML private Label missingAlert;
    private String selectedType = "";
    private String selectedTeam = ""; 
    
    private boolean pitcherSelected = false, hitterSelected = false,
            typeSelected = false, teamSelected = false; 

    @FXML
    void handlePrint(ActionEvent event) 
    { }

    @FXML
    void handleSearch(ActionEvent event) 
    {
        String teamChoice = teamCombo.getSelectionModel().getSelectedItem();
        selectedTeam = teamChoice; 
        if(teamChoice == null)
        {
            teamSelected = false;
            System.out.println("Select a Team");
        }else{
            teamSelected = true; 
        }
        
        try{
            String typeChoice = typeCombo.getSelectionModel().getSelectedItem();
            selectedType = typeChoice; 
            switch (typeChoice) {
                case "Hitter":
                    hitterSelected = true;
                    pitcherSelected = false;
                    typeSelected = true;
                    break;
                case "Pitcher":
                    pitcherSelected = true;
                    hitterSelected = false;
                    typeSelected = true;
                    break;
                default: 
                    hitterSelected = false;
                    pitcherSelected = false;
                    break;
            }
        }catch(Exception e){
            typeSelected = false;
            System.out.println("No type seleceted");
        }

        if(teamSelected && typeSelected)
        {
            pitcherTable.setVisible(pitcherSelected);
            hitterTable.setVisible(hitterSelected);
        }
        
        if(hitterSelected)
            loadHitterTable(); 
        if(pitcherSelected)
            loadPitcherTable(); 
        missingAlert.setVisible(!teamSelected || !typeSelected);
        typeAlert.setVisible(!typeSelected);
        teamAlert.setVisible(!teamSelected);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        assert teamCombo != null;
        teamCombo.getItems().clear();
        
        Query teams = em.createNativeQuery("SELECT teamname FROM team");
        List<String> list = teams.getResultList();
        list.forEach((team) -> {
            teamCombo.getItems().add(team);
        });
        
        assert typeCombo != null;
        typeCombo.getItems().addAll("Pitcher", "Hitter");
    }    
    
    private void loadPitcherTable()
    {
        Query teamIDQuery = em.createNativeQuery("SELECT teamid FROM team WHERE teamname=?");
        teamIDQuery.setParameter(1,selectedTeam);
        int teamID = (int)teamIDQuery.getSingleResult();
            
        ObservableList<PitcherFX> pitcherData;
        pitcherData = FXCollections.observableArrayList();
        List<Integer>lst = em.createQuery("SELECT c.playerid FROM Player c WHERE c.teamid="+teamID).getResultList();
        
        lst.forEach((data) -> {
            List<Pitcher>lst1 = em.createQuery("SELECT c FROM Pitcher c WHERE c.playerID="+data).getResultList();
                for(Pitcher lst2 : lst1)
                {
                    pitcherData.add(new PitcherFX(lst2.getPlayerID(), lst2.getWHIP(), lst2.getBaseonBalls(), lst2.getERA(), lst2.getEarnedruns(),
                    lst2.getGames(), lst2.getGames(), lst2.getHitBatters(), lst2.getHits(), lst2.getHomeruns(), lst2.getInningsPitched(),
                    lst2.getLosses(), lst2.getOppAvg(), lst2.getRuns(), lst2.getSaveOpp(), lst2.getSaves(), lst2.getStrikeOuts(), lst2.getWins()));
                }
            });
            
        pitcherTable.setItems(pitcherData);
        pitcherTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        pIDCol.setCellValueFactory(cell -> cell.getValue().getPlayerIDProperty());
        pWHIPCol.setCellValueFactory(cell -> Bindings.format("%.3f", cell.getValue().getWHIPProperty()));
        pBBCol.setCellValueFactory(cell -> cell.getValue().getBaseonBallsProperty());
        pERACol.setCellValueFactory(cell -> Bindings.format("%.2f", cell.getValue().getERAProperty()));
        pERCol.setCellValueFactory(cell -> cell.getValue().getEarnedrunsProperty());
        pGCol.setCellValueFactory(cell -> cell.getValue().getGamesProperty());
        pGSCol.setCellValueFactory(cell -> cell.getValue().getGamesStartedProperty());
        pHBPCol.setCellValueFactory(cell -> cell.getValue().getHitBattersProperty());
        pHCol.setCellValueFactory(cell -> cell.getValue().getHitsProperty());
        pHRCol.setCellValueFactory(cell -> cell.getValue().getHomerunsProperty());
        pIPCol.setCellValueFactory(cell -> Bindings.format("%.1f", cell.getValue().getInningsPitchedProperty()));
        pLCol.setCellValueFactory(cell -> cell.getValue().getLossesProperty());
        pAVGCol.setCellValueFactory(cell -> Bindings.format("%.3f", cell.getValue().getOppAvgProperty()));
        pRCol.setCellValueFactory(cell -> cell.getValue().getRunsProperty());
        pSVOCol.setCellValueFactory(cell -> cell.getValue().getSavesOppProperty());
        pSVCol.setCellValueFactory(cell -> cell.getValue().getSavesProperty());
        pSOCol.setCellValueFactory(cell -> cell.getValue().getStrikeOutsProperty());
        pWCol.setCellValueFactory(cell -> cell.getValue().getWinsProperty());
    }
    
    private void loadHitterTable()
    {
        Query teamIDQuery = em.createNativeQuery("SELECT teamid FROM team WHERE teamname=?");
        teamIDQuery.setParameter(1,selectedTeam);
        int teamID = (int)teamIDQuery.getSingleResult();
            
        ObservableList<HitterFX> hitterData;
        hitterData = FXCollections.observableArrayList();
        List<Integer>lst = em.createQuery("SELECT c.playerid FROM Player c WHERE c.teamid="+teamID).getResultList();
        
        lst.forEach((data) -> {
            List<Hitter>lst1 = em.createQuery("SELECT c FROM Hitter c WHERE c.playerid="+data).getResultList();
                for(Hitter lst2 : lst1)
                {
                    hitterData.add(new HitterFX(lst2.getPlayerID(), lst2.getAtbats(), lst2.getBB(), lst2.getBattingAvg(), lst2.getCaughtStealing(), 
                    lst2.getDoubles(), lst2.getHBP(), lst2.getOBP(), lst2.getSacFly(), lst2.getSlugging(), lst2.getStealingPerc(),
                    lst2.getStolenBases(), lst2.getStrikeouts(), lst2.getTotalBases(), lst2.getTriples(), lst2.getHits(), lst2.getHomeruns(), lst2.getRBI()));
                }
            });
            
        hitterTable.setItems(hitterData);
        hitterTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        hIDCol.setCellValueFactory(cell -> cell.getValue().getPlayerIDProperty());
        hABCol.setCellValueFactory(cell -> cell.getValue().getAtBatsProperty());
        hHCol.setCellValueFactory(cell -> cell.getValue().getHitsProperty());
        h2BCol.setCellValueFactory(cell -> cell.getValue().getDoublesProperty());
        h3BCol.setCellValueFactory(cell -> cell.getValue().getTriplesProperty());
        hHRCol.setCellValueFactory(cell -> cell.getValue().getHomerunsProperty());
        hAVGCol.setCellValueFactory(cell -> Bindings.format("%.3f", cell.getValue().getBattingAvgProperty()));
        hSLGCol.setCellValueFactory(cell -> Bindings.format("%.3f", cell.getValue().getSluggingProperty()));
        hBBCol.setCellValueFactory(cell -> cell.getValue().getBBProperty());
        hHBPCol.setCellValueFactory(cell -> cell.getValue().getHBPProperty());
        hTBCol.setCellValueFactory(cell -> cell.getValue().getTotalBasesProperty());
        hSBCol.setCellValueFactory(cell -> cell.getValue().getStolenBasesProperty());
        hCSCol.setCellValueFactory(cell -> cell.getValue().getCaughtStealingProperty());
        hSACCol.setCellValueFactory(cell -> cell.getValue().getSacFlyProperty());
        hSBPCol.setCellValueFactory(cell -> Bindings.format("%.3f", cell.getValue().getStealingPercProperty()));
        hOBPCol.setCellValueFactory(cell -> Bindings.format("%.3f", cell.getValue().getOBPProperty()));
        hSOCol.setCellValueFactory(cell -> cell.getValue().getStrikeoutsProperty());
        hRBICol.setCellValueFactory(cell -> cell.getValue().getRBIProperty());
    }
    
}
