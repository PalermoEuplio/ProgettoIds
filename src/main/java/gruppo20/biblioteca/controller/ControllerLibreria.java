/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gruppo20.biblioteca.controller;
import gruppo20.biblioteca.model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.beans.binding.Bindings.bindBidirectional;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;


/**
 *
 * @author euppa
 */
public class ControllerLibreria {
    
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
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pageUtenti.fxml"));
        Parent root = loader.load();
        ControllerUtenti controller =  loader.getController();
        controller.setContesto(co);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root,stage.getScene().getWidth(),stage.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
        
    }
    
    @FXML
    public void pageLibreria(MouseEvent event) throws IOException{   
        
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
    
    
    @FXML public TextField titoloLibro;
    @FXML public TextField listaAutori;
    @FXML public TextField isbn;
    @FXML public DatePicker annoP;
    @FXML public TextField NCopie;
    
    public void aggiuntaLibro(MouseEvent event) throws IOException {

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aggiuntaLibro.fxml"));
    DialogPane root = loader.load();
    
    ControllerLibreria controllerDialog = loader.getController();

    Dialog<ButtonType> a = new Dialog<>();
    a.setDialogPane(root);
    a.setTitle("Inserire nuovo Utente");

    a.setOnShown(e -> {

        TextField titoloLibro = controllerDialog.titoloLibro;
        TextField listaAutori = controllerDialog.listaAutori;
        TextField isbn = controllerDialog.isbn;
        DatePicker annoP = controllerDialog.annoP;
        annoP.setValue(LocalDate.now());
        TextField NCopie = controllerDialog.NCopie;
        
        IntegerProperty numero = new SimpleIntegerProperty(0);
        NCopie.textProperty().bindBidirectional(numero, new NumberStringConverter());
        
        
        listaAutori.disableProperty().bind(titoloLibro.textProperty().isEmpty());
        isbn.disableProperty().bind(listaAutori.textProperty().isEmpty());
        NCopie.disableProperty().bind(isbn.textProperty().isEmpty());
        a.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(numero.isEqualTo(0));
        
        
        
        
        
    });

    a.showAndWait();
}

}