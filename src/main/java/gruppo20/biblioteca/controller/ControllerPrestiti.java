package gruppo20.biblioteca.controller;

import gruppo20.biblioteca.model.Main;
import gruppo20.biblioteca.model.Prestiti.Prestito;
import gruppo20.biblioteca.model.Prestiti.Prestiti;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

/**
 *
 * @author Osv
 */


public class ControllerPrestiti implements Initializable{

    private Contesto co;
    
    
    @FXML
    private TableView<Prestito> tabellaPrestitiAttivi;
    @FXML
    private TableView<Prestito> tabellaPrestitiRitardo;

    
    @FXML
    private TextField nomeUtente;
    @FXML
    private TextField cognomeUtente;
    @FXML
    private TextField matricola;
    @FXML
    private TextField email;
    @FXML
    private TextField barraCercaUtenti;
    @FXML
    private Button aggiungiUtenteButton;
    
    
    //Per tabella Prestiti Attivi
    @FXML
    private TableColumn<Prestito, String> dataPrestitoA;
    @FXML
    private TableColumn<Prestito, String> dataScadenzaA;
    @FXML
    private TableColumn<Prestito, String> matricolaA;
    @FXML
    private TableColumn<Prestito, String> isbnA;
    @FXML
    private TableColumn<Prestito, Void> operazioniA;
    
    //Per tabella Prestiti Ritardo
    @FXML
    private TableColumn<Prestito, String> dataPrestitoR;
    @FXML
    private TableColumn<Prestito, String> dataScadenzaR;
    @FXML
    private TableColumn<Prestito, Integer> giorniRitardo;
    @FXML
    private TableColumn<Prestito, String> matricolaR;
    @FXML
    private TableColumn<Prestito, String> isbnR;
    @FXML
    private TableColumn<Prestito, Void> operazioniR;
    
    
    

    private ControllerPrestiti controllerGenitore;
    private ObservableList<Prestito> listaPerTabellaAttivi;
    private ObservableList<Prestito> listaPerTabellaRitardi;
    
    
    
    
    
    
    
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    

    @FXML
    public void pageDashboard(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Dashboard.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void pageUtenti(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pageUtenti.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void pageLibreria(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pageLibreria.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void pagePrestiti(MouseEvent event) throws IOException {
    }


    public void aggiuntaPrestito(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aggiuntaPrestito.fxml"));
        DialogPane root = loader.load();

        ControllerPrestiti controllerDialog = loader.getController();

        Dialog<ButtonType> a = new Dialog<>();
        a.setDialogPane(root);
        a.setTitle("Inserire nuovo Prestito");

        a.setOnShown(e -> {

            TextField nomeUtente = controllerDialog.nomeUtente;
            TextField isbn = controllerDialog.isbn;

            DatePicker annoP = controllerDialog.annoP;
            annoP.setValue(LocalDate.now());

            DatePicker annoR = controllerDialog.annoR;
            annoR.setValue(LocalDate.now().plusDays(30));

            isbn.disableProperty().bind(nomeUtente.textProperty().isEmpty());
            a.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(isbn.textProperty().isEmpty());

        });
        a.showAndWait();
    }

}
