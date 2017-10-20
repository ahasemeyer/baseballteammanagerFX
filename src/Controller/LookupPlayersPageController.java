/**
 * FXML Controller class
 *
 * @author Austin Hasemeyer
 * @document LookupPlayersPageController.java
 * @description This class will control the lookup of players and populate
 *      a TableView with all Players and their given statistics
 */
package Controller;

import Model.Player;
import Model.PlayerFX;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javax.persistence.EntityManager;

public class LookupPlayersPageController implements Initializable {

    ObservableList<PlayerFX> data; 
    @FXML private TableColumn<PlayerFX, Number> id;
    @FXML private TableColumn<PlayerFX, String> fName;
    @FXML private TableColumn<PlayerFX, String> lName;
    @FXML private TableColumn<PlayerFX, Number> playerNumber;
    @FXML private TableColumn<PlayerFX, String> positionCol;
    @FXML private TableColumn<PlayerFX, String> armCol;
    @FXML private TableColumn<PlayerFX, String> stanceCol;
    @FXML private TableColumn<PlayerFX, Number> teamIDCol;
    @FXML private TableView<PlayerFX> table;

    @FXML
    private void handlePrint(ActionEvent event)
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/lookupPlayerPrint.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            Util.printerUtil.printLandscape(root1);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        EntityManager em = Model.DBUtil.getEM();
        data = FXCollections.observableArrayList();

        List<Player>lst = em.createQuery("SELECT c FROM Player c").getResultList();
        for(Player lst1 : lst)
        {
            data.add(new PlayerFX(lst1.getPlayerid(), lst1.getFname(), lst1.getLname(), lst1.getPosition(), lst1.getPlayernumber(), lst1.getThrowingarm(), lst1.getBattingstance(), lst1.getTeamid()));
        }

        table.setItems(data);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        id.setCellValueFactory(cell -> cell.getValue().getPlayeridProperty());
        fName.setCellValueFactory(cell -> cell.getValue().getFnameProperty());
        lName.setCellValueFactory(cell -> cell.getValue().getLnameProperty()); 
        playerNumber.setCellValueFactory(cell -> cell.getValue().getPlayernumberProperty());  
        positionCol.setCellValueFactory(cell -> cell.getValue().getPositionProperty());  
        armCol.setCellValueFactory(cell -> cell.getValue().getThrowingarmProperty());  
        stanceCol.setCellValueFactory(cell -> cell.getValue().getBattingstanceProperty());  
        teamIDCol.setCellValueFactory(cell -> cell.getValue().getTeamidProperty());  

    }    
}
