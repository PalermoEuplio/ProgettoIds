/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gruppo20.biblioteca.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Osv
 */
public class ControllerPrestiti {
    
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
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    @FXML
    public void pageUtenti(MouseEvent event) throws IOException{ 

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pageUtenti.fxml"));
        Parent root = loader.load();
        ControllerUtenti controller =  loader.getController();
        controller.setContesto(co);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    @FXML
    public void pageLibreria(MouseEvent event) throws IOException{   
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pageLibreria.fxml"));
        Parent root = loader.load();
        ControllerLibreria controller =  loader.getController();
        controller.setContesto(co);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
    
    @FXML
    public void pagePrestiti(MouseEvent event) throws IOException{ 
        
        
    }
    
    public void aggiuntaPrestito(MouseEvent event) throws IOException{
        
        DialogPane root=null;
        
            try{
               root = FXMLLoader.load(getClass().getResource("/fxml/aggiuntaPrestito.fxml"));
            } catch (IOException ex){
                Logger.getLogger(ControllerDashboard.class.getName()).log(Level.SEVERE,null,ex);
            }
        
            Dialog<ButtonType> a = new Dialog<>();
            a.setDialogPane(root);
            a.setTitle("Inserire nuovo Utente");
            a.showAndWait();
    }
    
    
}
