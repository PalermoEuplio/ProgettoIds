/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gruppo20.biblioteca.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import gruppo20.biblioteca.model.Main;

/**
 *
 * @author euppa
 */
public class ControllerDashboard implements Initializable {
    
    @FXML
    public AnchorPane ap;
    
    @FXML
    public BorderPane bp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }
    
    @FXML
    public void pageDashboard(MouseEvent event) throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Dashboard.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    @FXML
    public void pageUtenti(MouseEvent event) throws IOException{ 
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/pageUtenti.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    @FXML
    public void pageLibreria(MouseEvent event) throws IOException{   
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/pageLibreria.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    @FXML
    public void pagePrestiti(MouseEvent event) throws IOException{ 
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/pagePrestiti.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    @FXML
    public void aggiuntaLibro(MouseEvent event) throws IOException{
        new ControllerLibreria();
    }
    
    @FXML
    public void aggiuntaPrestito(MouseEvent event) throws IOException{
        new ControllerPrestiti();
    }
    
}
