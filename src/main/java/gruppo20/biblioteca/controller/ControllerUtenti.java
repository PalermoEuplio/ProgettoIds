/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gruppo20.biblioteca.controller;

import gruppo20.biblioteca.model.Utenti.Utente;
import javafx.scene.control.Dialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.fxml.Initializable;
import javafx.stage.Stage;



/**
 *
 * @author Osv
 */
public class ControllerUtenti extends Dialog<Utente> {
    
    private Contesto co;
    
    public void setContesto(Contesto co){
        this.co = co;
    }
    
    @FXML
    public void pageDashboard(MouseEvent event) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Dashboard.fxml"));
        Parent root = loader.load();
        ControllerDashboard controller =  loader.getController();
        controller.setContesto(co);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root,stage.getScene().getWidth(),stage.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
        
    }
    
    @FXML
    public void pageUtenti(MouseEvent event) throws IOException{ 
        
    }
    
    @FXML
    public void pageLibreria(MouseEvent event) throws IOException{   
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pageLibreria.fxml"));
        Parent root = loader.load();
        ControllerLibreria controller =  loader.getController();
        controller.setContesto(co);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root,stage.getScene().getWidth(),stage.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
        
    }
    
    @FXML
    public void pagePrestiti(MouseEvent event) throws IOException{ 
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pagePrestiti.fxml"));
        Parent root = loader.load();
        ControllerPrestiti controller =  loader.getController();
        controller.setContesto(co);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root,stage.getScene().getWidth(),stage.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
        
    }
    
    
    public void aggiuntaUtente(MouseEvent event) throws IOException{
        
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
