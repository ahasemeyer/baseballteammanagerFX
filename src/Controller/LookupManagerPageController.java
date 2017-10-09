/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Manager;
import Model.ManagerFX;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
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
public class LookupManagerPageController implements Initializable 
{
    ObservableList<ManagerFX> data; 
    @FXML private TableView<ManagerFX> table;
    @FXML private TableColumn<ManagerFX, Number> idCol;
    @FXML private TableColumn<ManagerFX, Number> wCol;
    @FXML private TableColumn<ManagerFX, Number> lCol;
    @FXML private TableColumn<ManagerFX, Number> tCol;
    @FXML private TableColumn<ManagerFX, String> wpCol;
    @FXML private TableColumn<ManagerFX, Number> tidCol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        EntityManager em = Model.DBUtil.getEM();
        data = FXCollections.observableArrayList();
        List<Manager>lst = em.createQuery("SELECT c FROM Manager c").getResultList();
        for(Manager lst1 : lst)
        {
            data.add(new ManagerFX(lst1.getPlayerID(), lst1.getTeamID(), lst1.getWins(), lst1.getLosses(), lst1.getTies(), lst1.getWinPerc()));
        }
        table.setItems(data);
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        idCol.setCellValueFactory(cell -> cell.getValue().getPlayerIDProperty());
        tidCol.setCellValueFactory(cell -> cell.getValue().getTeamIDProperty());
        wCol.setCellValueFactory(cell -> cell.getValue().getWinsProperty());
        lCol.setCellValueFactory(cell -> cell.getValue().getLossesProperty());
        tCol.setCellValueFactory(cell -> cell.getValue().getTiesProperty());
        wpCol.setCellValueFactory(cell -> Bindings.format("%.3f", cell.getValue().getWinPercProperty())); 
    }    
    
}
