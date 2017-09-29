/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Hitter;
import Model.HitterFX;
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

/**
 * FXML Controller class
 *
 * @author hasmy
 */
public class LookupHittersPageController implements Initializable {
    
    ObservableList<HitterFX> data; 
    @FXML private TableView<HitterFX> table;
    @FXML private TableColumn<HitterFX, Number> pIDCol;
    @FXML private TableColumn<HitterFX, Number> abCol;
    @FXML private TableColumn<HitterFX, Number> hCol;
    @FXML private TableColumn<HitterFX, Number> b2Col;
    @FXML private TableColumn<HitterFX, Number> b3Col;
    @FXML private TableColumn<HitterFX, Number> hrCol;
    @FXML private TableColumn<HitterFX, String> avgCol;
    @FXML private TableColumn<HitterFX, String> slugCol;
    @FXML private TableColumn<HitterFX, Number> bbCol;
    @FXML private TableColumn<HitterFX, Number> hbpCol;
    @FXML private TableColumn<HitterFX, Number> tbCol;
    @FXML private TableColumn<HitterFX, Number> sbCol;
    @FXML private TableColumn<HitterFX, Number> csCol;
    @FXML private TableColumn<HitterFX, Number> sacCol;
    @FXML private TableColumn<HitterFX, String> sbpCol;
    @FXML private TableColumn<HitterFX, String> obpCol;
    @FXML private TableColumn<HitterFX, Number> soCol;
    @FXML private TableColumn<HitterFX, Number> rbiCol; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        EntityManager em = Model.DBUtil.getEM();
        data = FXCollections.observableArrayList();
        
        List<Hitter>lst = em.createQuery("SELECT c FROM Hitter c").getResultList();
        
        for(Hitter lst1 : lst)
        {
            data.add(new HitterFX(lst1.getPlayerID(), lst1.getAtbats(), lst1.getBB(), lst1.getBattingAvg(), lst1.getCaughtStealing(), 
            lst1.getDoubles(), lst1.getHBP(), lst1.getOBP(), lst1.getSacFly(), lst1.getSlugging(), lst1.getStealingPerc(),
            lst1.getStolenBases(), lst1.getStrikeouts(), lst1.getTotalBases(), lst1.getTriples(), lst1.getHits(), lst1.getHomeruns(), lst1.getRBI()));
        }

        table.setItems(data);
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        pIDCol.setCellValueFactory(cell -> cell.getValue().getPlayerIDProperty());
        abCol.setCellValueFactory(cell -> cell.getValue().getAtBatsProperty());
        hCol.setCellValueFactory(cell -> cell.getValue().getHitsProperty());
        b2Col.setCellValueFactory(cell -> cell.getValue().getDoublesProperty());
        b3Col.setCellValueFactory(cell -> cell.getValue().getTriplesProperty());
        hrCol.setCellValueFactory(cell -> cell.getValue().getHomerunsProperty());
        avgCol.setCellValueFactory(cell -> Bindings.format("%.3f", cell.getValue().getBattingAvgProperty()));
        slugCol.setCellValueFactory(cell -> Bindings.format("%.3f", cell.getValue().getSluggingProperty()));
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
    }       
}
