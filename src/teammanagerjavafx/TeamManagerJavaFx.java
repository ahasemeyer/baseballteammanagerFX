/*
 * @author Austin Hasemeyer
 * @document TeamManagerJavaFX.java
 * @description This is the main launch class. This will open the program to 
        the login page which will handle entering the program.
 */
package teammanagerjavafx;

import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class TeamManagerJavaFx extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/loginPage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image("/images/Baseball.png"));
        stage.setTitle("Login!");
        stage.show();
        stage.setOnCloseRequest(e -> Platform.exit());
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
