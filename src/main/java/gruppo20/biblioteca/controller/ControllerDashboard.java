/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gruppo20.biblioteca.controller;

import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public void pageDashboard(MouseEvent event){   
        bp.setCenter(ap);
    }
    
    @FXML
    public void pageUtenti(MouseEvent event) throws IOException{ 
        new ControllerUtenti(bp);
    }
    
    @FXML
    public void pageLibreria(MouseEvent event) throws IOException{   
        new ControllerLibreria(bp);
    }
    
    @FXML
    public void pagePrestiti(MouseEvent event) throws IOException{ 
        new ControllerPrestiti(bp);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    public void aggiuntaUtente(MouseEvent event) throws IOException{
        ControllerUtenti x = new ControllerUtenti();
        x.aggiungiUtente(bp);
    }
    
}
