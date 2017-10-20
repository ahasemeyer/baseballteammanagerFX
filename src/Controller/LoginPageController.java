/**
 * FXML Controller class
 *
 * @author hasmy
 * @document LoginPageController.java
 * @description will as a player to login then verify their credentials. If
 *      they do not yet have credentials they can be created here. 
 */
package Controller;

import Model.AppUsers;
import Model.DBUtil;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.persistence.EntityManager;

public class LoginPageController implements Initializable {
    
    private static final EntityManager em = Model.DBUtil.getEM();
    @FXML Button btn; 
    @FXML private Button loginBtn;
    @FXML private Button createBtn;
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Label errorlbl; 
    private static int loginID;

    //Pre: Username and password must be filled out
    //Post: Create a user with the given username and password
    @FXML
    void handleCreateBtn(ActionEvent event) 
    {
        AppUsers createdUser = new AppUsers(username.getText(), password.getText());
        DBUtil.createUser(createdUser);
    }
    
    //Pre: username and password must be entered
    //Post: Check database if credentials valid, if valid will return true.
    //      if invalid will return false.
    private boolean loginCheck()
    {
        boolean loginSuccess = false; 
        AppUsers loginUser = new AppUsers(); 
        List<AppUsers>lst = em.createQuery("SELECT u FROM AppUsers u WHERE u.username='"+username.getText()+"'").getResultList();
        lst.forEach((data)-> {
            loginUser.setUsername(data.getUsername());
            loginUser.setPassword(data.getPassword());
            loginUser.setID(data.getID());
        });
        
        if(loginUser.getUsername().equals(""))
            loginSuccess = false;
        else if(loginUser.getPassword().equals(password.getText()))
        {
            loginSuccess = true;
            loginID = loginUser.getID(); 
        }
        else
            loginSuccess = false;

        return loginSuccess; 
    }

    //Pre: must pass login check
    //Post: if login check is passed you will enter the main menu. If check fails
    //      an error message is displayed. 
    @FXML
    void handleLoginBtn(ActionEvent event) 
    {
        if(loginCheck())
        {
            errorlbl.setVisible(false);
            System.out.println("Successful login");
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/mainMenu.fxml"));
                Parent root1 = (Parent)fxmlLoader.load();
                Stage stage = new Stage();
                stage.getIcons().add(new Image("/images/Baseball.png"));
                stage.setTitle("Baseball League Manager");
                stage.setScene(new Scene(root1));
                stage.show();
                stage.setOnCloseRequest(e -> Platform.exit());
                Stage close = (Stage) loginBtn.getScene().getWindow();
                close.close();
            }catch(Exception e){
                System.out.println(e);
            }
        }
        else{
            System.out.println("Username or Password incorrect.");
            errorlbl.setVisible(true);
        }
        
    }
    
    //override button/currently hidden
    @FXML
    public void handleButton(ActionEvent event)
    {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/mainMenu.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image("/images/Baseball.png"));
            stage.setTitle("Baseball League Manager");
            stage.setScene(new Scene(root1));
            stage.show();
            stage.setOnCloseRequest(e -> Platform.exit());
        }catch(Exception e){
            System.out.println(e);
        }
        
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }
    
    //returns loginID
    public static int getUserID()
    {
        return loginID; 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {}    
    
}
