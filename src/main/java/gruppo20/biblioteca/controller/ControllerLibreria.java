package gruppo20.biblioteca.controller;

import gruppo20.biblioteca.model.*;
import gruppo20.biblioteca.model.Utenti.Utente;
import java.io.IOException;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;


/**
 *
 * @author euppa
 */
public class ControllerLibreria {
    
    private Contesto co;
    
    @FXML private TableView<Utente> tabellaLibri;
    
    @FXML private TextField nomeUtente;
    @FXML private TextField cognomeUtente;
    @FXML private TextField matricola;
    @FXML private TextField email;
    @FXML private TextField barraCercaUtenti;
    @FXML private Button aggiungiUtenteButton;
    
    
    @FXML private TableColumn<Utente, String> titolo;
    @FXML private TableColumn<Utente, String> autori;
    @FXML private TableColumn<Utente, String> annoPublicazione;
    @FXML private TableColumn<Utente, String> isbn;
    @FXML private TableColumn<Utente, Integer> copie;
    @FXML private TableColumn<Utente, Void> operazioni;
    
    
    
    private ControllerUtenti controllerGenitore;
    private ObservableList<Utente> listaPerTabella;
    
    
    
    
    
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
    public void pageLibreria(MouseEvent event) throws IOException{}
    
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
    
    
    @FXML private TextField titoloLibro;
    @FXML private TextField listaAutori;
    @FXML private TextField isbn;
    @FXML private DatePicker annoP;
    @FXML private TextField NCopie;
    
    public void aggiuntaLibro(MouseEvent event) throws IOException {

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aggiuntaLibro.fxml"));
    DialogPane root = loader.load();
    
    ControllerLibreria controllerDialog = loader.getController();

    Dialog<ButtonType> a = new Dialog<>();
    a.setDialogPane(root);
    a.setTitle("Inserire nuovo Libro");

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