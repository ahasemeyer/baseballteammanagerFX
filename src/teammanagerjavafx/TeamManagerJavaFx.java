/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

/**
 *
 * @author hasmy
 */
public class TeamManagerJavaFx extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/mainMenu.fxml"));
        //Image test = ImageIO.read(getClass().getResource("Baseball.jpg")); 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image("/images/Baseball.png"));
        stage.setTitle("Baseball League Manager");
        stage.show();
        stage.setOnCloseRequest(e -> Platform.exit());
        
        
       // Image test = ImageIO.read(getClass().getResource("Baseball.jpg")); 
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
