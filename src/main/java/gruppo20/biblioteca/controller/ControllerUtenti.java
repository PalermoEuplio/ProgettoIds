package gruppo20.biblioteca.controller;

import gruppo20.biblioteca.model.Utenti.*;
import javafx.scene.control.Dialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;



/**
 *
 * @author Osv
 */
public class ControllerUtenti implements Initializable{
    
    private Contesto co =new Contesto();
    @FXML private TableView<Utente> tabellaUtenti;
    @FXML private TextField nomeUtente;
    @FXML private TextField cognomeUtente;
    @FXML private TextField matricola;
    @FXML private TextField email;
    
    
    @FXML private TableColumn<Utente, String> nome;
    @FXML private TableColumn<Utente, String> cognome;
    @FXML private TableColumn<Utente, String> matricola0;
    @FXML private TableColumn<Utente, String> mail;
    @FXML private TableColumn<Utente, Integer> nPrestiti;
    
    
    private ControllerUtenti controllerGenitore;
    private ObservableList<Utente> listaPerTabella;
    
    public void setContesto(Contesto co){
        this.co = co;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        String nomeFile = location.getFile();
        
        if(nomeFile.endsWith("pageUtenti.fxml")){
            
            Utenti u = co.getGestUtenti();
            ObservableSet<Utente> setUtenti = u.getSetUtenti();
            tabellaUtenti.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
            
            if(nome != null) { // Controllo null per evitare errori in altre view che usano questo controller
            nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            cognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
            matricola0.setCellValueFactory(new PropertyValueFactory<>("matricola"));
            mail.setCellValueFactory(new PropertyValueFactory<>("mail")); // getter: getMail()
            nPrestiti.setCellValueFactory(new PropertyValueFactory<>("nPrestiti")); 
        }
            
            
            
            listaPerTabella = FXCollections.observableArrayList(setUtenti);
            

            
            setUtenti.addListener((SetChangeListener<Utente>) change -> {
                    if (change.wasAdded()) {
                        listaPerTabella.add(change.getElementAdded());
                    }
                    if (change.wasRemoved()) {
                        listaPerTabella.remove(change.getElementRemoved());
                    }
                });
            
            tabellaUtenti.setItems(listaPerTabella);
            
        }
        else if(nomeFile.endsWith("aggiuntaUtente.fxml")){
        }
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
    public void pageUtenti(MouseEvent event) throws IOException{}
    
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
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aggiuntaUtente.fxml"));
        DialogPane root = loader.load();
    
        ControllerUtenti controllerDialog = loader.getController();
        controllerDialog.setGenitore(this);

        Dialog<ButtonType> a = new Dialog<>();
        a.setDialogPane(root);
        a.setTitle("Inserire nuovo Utente");

        //Handler della pagina in sovrapposizione
        a.setOnShown(e -> {

                TextField nomeUtente = controllerDialog.nomeUtente;
                TextField cognomeUtente = controllerDialog.cognomeUtente;
                TextField matricola = controllerDialog.matricola;
                TextField email = controllerDialog.email;
        
        
                cognomeUtente.disableProperty().bind(nomeUtente.textProperty().isEmpty());
                matricola.disableProperty().bind(cognomeUtente.textProperty().isEmpty());
                email.disableProperty().bind(matricola.textProperty().isEmpty());
                a.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(email.textProperty().isEmpty());
                
                a.setResultConverter(dialogButton -> {
                     if (dialogButton == ButtonType.OK) {
                            controllerDialog.azioneConferma(); 
                        return dialogButton;
                    }
                    return null;
                });

    });
            
        java.util.Optional<ButtonType> result = a.showAndWait();
        
        // Se l'utente ha premuto OK ed è tornato indietro
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Forziamo l'aggiornamento della lista della tabella prendendo i dati freschi dal Set
            if (listaPerTabella != null && co != null) {
                // Svuota e riempi di nuovo la lista con i dati aggiornati del Set
                listaPerTabella.setAll(co.getGestUtenti().getSetUtenti());
                
                // (Opzionale) Se vuoi essere sicuro che la grafica si ridisegni:
                tabellaUtenti.refresh(); 
            }
        }
        
        
        
        
        
    }
    
    public void setGenitore(ControllerUtenti genitore) {
        this.controllerGenitore = genitore;
    }
    
    @FXML
    public void azioneConferma() {
        if (controllerGenitore != null) {
            // 1. Prendiamo il testo dall'input della Pagina 2
            String nome = nomeUtente.getText();
            String cognome = cognomeUtente.getText();
            String matricola = this.matricola.getText();
            String mail = email.getText();
           
            Utenti u1 = co.getGestUtenti();
            u1.aggiungi(new Utente(nome,cognome,matricola,mail,0));
            
        }
    }
}
