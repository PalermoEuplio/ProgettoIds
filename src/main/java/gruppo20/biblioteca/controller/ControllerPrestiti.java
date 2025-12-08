/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gruppo20.biblioteca.controller;

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
 * @author Osv
 */
public class ControllerPrestiti {
    
    public ControllerPrestiti(BorderPane bp){
        Parent root=null;
        
        try{
            root = FXMLLoader.load(getClass().getResource("/fxml/pagePrestiti.fxml"));
        } catch (IOException ex){
            Logger.getLogger(ControllerDashboard.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        bp.setCenter(root);
    }
    
    public ControllerPrestiti(){
        DialogPane root=null;
        
            try{
               root = FXMLLoader.load(getClass().getResource("/fxml/aggiuntaPrestito.fxml"));
            } catch (IOException ex){
                Logger.getLogger(ControllerDashboard.class.getName()).log(Level.SEVERE,null,ex);
            }
        
            Dialog<ButtonType> a = new Dialog<>();
            a.setDialogPane(root);
            a.setTitle("Inserire nuovo Prestito");
            a.showAndWait();
    }
    
    
}
