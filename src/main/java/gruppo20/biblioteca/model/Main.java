/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gruppo20.biblioteca.model;
import gruppo20.biblioteca.controller.*;

import javafx.scene.*;
import javafx.stage.*;
import static javafx.application.Application.launch;
import javafx.application.Application;

/**
 * @brief Questo file contiene l'implementazione del Main.
 * @author Gruppo20
 */

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage){
        
        //Aggiungere chiamate al view e controller
        new ControllerLibreria();
        
    }
    
    
    
    
    
    
    public static void main(String args[]){
        launch(args);
    }
}
