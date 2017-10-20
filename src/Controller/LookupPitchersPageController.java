/**
 * FXML Controller class
 *
 * @author Austin Hasemeyer
 * @document LookupPitchersPageController.java
 * @description Will load a TableView with all Pitches and their given stats
 */
package Controller;

import Model.Pitcher;
import Model.PitcherFX;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javax.persistence.EntityManager;

public class LookupPitchersPageController implements Initializable {
    
    ObservableList<PitcherFX> data; 
    @FXML private TableView<PitcherFX> table;
    @FXML private TableColumn<PitcherFX, Number> idCol;
    @FXML private TableColumn<PitcherFX, Number> wCol;
    @FXML private TableColumn<PitcherFX, Number> lCol;
    @FXML private TableColumn<PitcherFX, Number> gCol;
    @FXML private TableColumn<PitcherFX, Number> gsCol;
    @FXML private TableColumn<PitcherFX, String> eraCol;
    @FXML private TableColumn<PitcherFX, Number> erCol;
    @FXML private TableColumn<PitcherFX, Number> rCol;
    @FXML private TableColumn<PitcherFX, String> ipCol;
    @FXML private TableColumn<PitcherFX, Number> hCol;
    @FXML private TableColumn<PitcherFX, Number> hrCol;
    @FXML private TableColumn<PitcherFX, Number> bbCol;
    @FXML private TableColumn<PitcherFX, Number> hbpCol;
    @FXML private TableColumn<PitcherFX, String> whipCol;
    @FXML private TableColumn<PitcherFX, String> opaCol;
    @FXML private TableColumn<PitcherFX, Number> svCol;
    @FXML private TableColumn<PitcherFX, Number> svoCol;
    @FXML private TableColumn<PitcherFX, Number> soCol;
    
    //Load a TableView with all Pitchers and their given stats
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        EntityManager em = Model.DBUtil.getEM();
        data = FXCollections.observableArrayList();
        
        List<Pitcher>lst = em.createQuery("SELECT c FROM Pitcher c").getResultList(); 
        for(Pitcher lst1 : lst)
        {
            data.add(new PitcherFX(lst1.getPlayerID(), lst1.getWHIP(), lst1.getBaseonBalls(), lst1.getERA(), lst1.getEarnedruns(),
                lst1.getGames(), lst1.getGames(), lst1.getHitBatters(), lst1.getHits(), lst1.getHomeruns(), lst1.getInningsPitched(),
                lst1.getLosses(), lst1.getOppAvg(), lst1.getRuns(), lst1.getSaveOpp(), lst1.getSaves(), lst1.getStrikeOuts(), lst1.getWins()));
        }
        
        table.setItems(data);
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        idCol.setCellValueFactory(cell -> cell.getValue().getPlayerIDProperty());
        whipCol.setCellValueFactory(cell -> Bindings.format("%.3f", cell.getValue().getWHIPProperty()));
        bbCol.setCellValueFactory(cell -> cell.getValue().getBaseonBallsProperty());
        eraCol.setCellValueFactory(cell -> Bindings.format("%.2f", cell.getValue().getERAProperty()));
        erCol.setCellValueFactory(cell -> cell.getValue().getEarnedrunsProperty());
        gCol.setCellValueFactory(cell -> cell.getValue().getGamesProperty());
        gsCol.setCellValueFactory(cell -> cell.getValue().getGamesStartedProperty());
        hbpCol.setCellValueFactory(cell -> cell.getValue().getHitBattersProperty());
        hCol.setCellValueFactory(cell -> cell.getValue().getHitsProperty());
        hrCol.setCellValueFactory(cell -> cell.getValue().getHomerunsProperty());
        ipCol.setCellValueFactory(cell -> Bindings.format("%.1f", cell.getValue().getInningsPitchedProperty()));
        lCol.setCellValueFactory(cell -> cell.getValue().getLossesProperty());
        opaCol.setCellValueFactory(cell -> Bindings.format("%.3f", cell.getValue().getOppAvgProperty()));
        rCol.setCellValueFactory(cell -> cell.getValue().getRunsProperty());
        svoCol.setCellValueFactory(cell -> cell.getValue().getSavesOppProperty());
        svCol.setCellValueFactory(cell -> cell.getValue().getSavesProperty());
        soCol.setCellValueFactory(cell -> cell.getValue().getStrikeOutsProperty());
        wCol.setCellValueFactory(cell -> cell.getValue().getWinsProperty());
    }
}
