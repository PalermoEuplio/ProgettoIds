/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gruppo20.biblioteca.controller;

import gruppo20.biblioteca.model.Utenti.Utente;
import javafx.scene.control.Dialog;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;



/**
 *
 * @author Osv
 */
public class ControllerUtenti extends Dialog<Utente> {
    
    public ControllerUtenti(BorderPane bp) throws IOException{
        Parent root=null;
        
        try{
            root = FXMLLoader.load(getClass().getResource("/fxml/pageUtenti.fxml"));
        } catch (IOException ex){
            Logger.getLogger(ControllerDashboard.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        bp.setCenter(root);
        
    }
    
    public ControllerUtenti(){
        
        DialogPane root=null;
        
            try{
               root = FXMLLoader.load(getClass().getResource("/fxml/aggiuntaUtente.fxml"));
            } catch (IOException ex){
                Logger.getLogger(ControllerDashboard.class.getName()).log(Level.SEVERE,null,ex);
            }
        
            Dialog<ButtonType> a = new Dialog<>();
            a.setDialogPane(root);
            a.setTitle("Inserire nuovo Utente");
            a.showAndWait();
    }
    
    
    
}
