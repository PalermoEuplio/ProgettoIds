package gruppo20.biblioteca.model;

import gruppo20.biblioteca.controller.*;
import javafx.scene.*;
import javafx.stage.*;
import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;



/**
 * @brief Questo file contiene l'implementazione del Main.
 * @author Gruppo20
 */

public class Main extends Application {
    
    private Contesto co = new Contesto();
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Dashboard.fxml"));
        Parent root = loader.load();
        ControllerDashboard controller =  loader.getController();
        controller.setContesto(co);
        Scene scene = new Scene(root);
        stage.setTitle("Biblioteca Universitaria");
        stage.setScene(scene);
        stage.show();
        
    }
    
    
    
    public static void main(String args[]){
        launch(args);
    }
}
