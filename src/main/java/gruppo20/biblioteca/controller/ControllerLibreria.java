/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gruppo20.biblioteca.controller;
import gruppo20.biblioteca.model.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.BorderPane;


/**
 *
 * @author euppa
 */
public class ControllerLibreria {
    
    public ControllerLibreria(BorderPane bp) throws IOException{
        Parent root=null;
        
        try{
            root = FXMLLoader.load(getClass().getResource("/fxml/pageLibreria.fxml"));
        } catch (IOException ex){
            Logger.getLogger(ControllerDashboard.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        bp.setCenter(root);
    }
    
    public ControllerLibreria(){
        
        DialogPane root=null;
        
            try{
               root = FXMLLoader.load(getClass().getResource("/fxml/aggiuntaLibro.fxml"));
            } catch (IOException ex){
                Logger.getLogger(ControllerDashboard.class.getName()).log(Level.SEVERE,null,ex);
            }
        
            Dialog<ButtonType> a = new Dialog<>();
            a.setDialogPane(root);
            a.setTitle("Inserire nuovo Libro");
            a.showAndWait();
    }
    
    
    
}