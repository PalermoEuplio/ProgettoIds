package gruppo20.biblioteca.controller;

import gruppo20.biblioteca.model.Utenti.*;
import javafx.scene.control.Dialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.layout.HBox;
import org.kordamp.ikonli.javafx.FontIcon;



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
    @FXML private TextField barraCercaUtenti;
    @FXML private Button aggiungiUtenteButton;
    
    
    @FXML private TableColumn<Utente, String> nome;
    @FXML private TableColumn<Utente, String> cognome;
    @FXML private TableColumn<Utente, String> matricola0;
    @FXML private TableColumn<Utente, String> mail;
    @FXML private TableColumn<Utente, Integer> nPrestiti;
    @FXML private TableColumn<Utente, Void> operazioni;
    
    
    
    private ControllerUtenti controllerGenitore;
    private ObservableList<Utente> listaPerTabella;
    
    
    public void setContesto(Contesto co){
        this.co = co;
    }
    
    //Inizializzo la pagina
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        String nomeFile = location.getFile();
        
        //Verifico di essere nella pagina Principale
        if(nomeFile.endsWith("pageUtenti.fxml")){
            
            Utenti u = co.getGestUtenti();
            ObservableSet<Utente> setUtenti = u.getSetUtenti();
            tabellaUtenti.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
            operazioni.setCellFactory(col -> new TableCell<Utente, Void>() {
            
                FontIcon mod = new FontIcon("fa-pencil");
                FontIcon del = new FontIcon("fa-remove");
                
                Button modifica = new Button("",mod);
                Button elimina = new Button("",del);
                
                HBox bottoni = new HBox(5);
                {
                
                    bottoni.getChildren().addAll(modifica,elimina);
                    
                    //Comportamento bottone modifica
                    modifica.setOnAction(e0 -> {
                        Utente uVecchio = getTableView().getItems().get(getIndex());

                        try {
                            
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aggiuntaUtente.fxml"));
                            DialogPane root = loader.load();

                            ControllerUtenti controllerDialog = loader.getController();
                            
                            controllerDialog.nomeUtente.setText(uVecchio.getNome());
                            controllerDialog.cognomeUtente.setText(uVecchio.getCognome());
                            controllerDialog.matricola.setText(uVecchio.getMatricola());
                            controllerDialog.email.setText(uVecchio.getMail());

                            
                            Dialog<ButtonType> dialog = new Dialog<>();
                            dialog.setDialogPane(root);
                            dialog.setTitle("Modifica Utente");

                            
                            TextField tNome = controllerDialog.nomeUtente;
                            TextField tCognome = controllerDialog.cognomeUtente;
                            TextField tMatr = controllerDialog.matricola;
                            TextField tMail = controllerDialog.email;

                            
                            dialog.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(
                                tNome.textProperty().isEmpty()
                                .or(tCognome.textProperty().isEmpty())
                                .or(tMatr.textProperty().isEmpty())
                                .or(tMail.textProperty().isEmpty())
                            );

                            
                            dialog.showAndWait().ifPresent(response -> {
                                if (response == ButtonType.OK) {
                                    
                                    String nuovoNome = tNome.getText();
                                    String nuovoCognome = tCognome.getText();
                                    String nuovaMatricola = tMatr.getText();
                                    String nuovaMail = tMail.getText();

                                    
                                    Utente uNuovo = new Utente(nuovoNome, nuovoCognome, nuovaMatricola, nuovaMail, uVecchio.getNPrestiti());

                                    
                                    Utenti u = co.getGestUtenti();
                                    try{
                                    u.modifica(uVecchio, uNuovo);
                                    }catch(Exception a){}
                                    
                                    int index = listaPerTabella.indexOf(uVecchio);
                                    if(index >= 0){
                                        listaPerTabella.set(index, uNuovo);
                                    }
                                    tabellaUtenti.refresh();
                                }
                            });

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                
                
                //Comportamento Bottone eliminazione
                elimina.setOnAction(e1 -> {
                    Utente u = getTableView().getItems().get(getIndex());
                    Utenti u1 = co.getGestUtenti();    
                    
                    
                    try{
                    FXMLLoader carica = new FXMLLoader(getClass().getResource("/fxml/ConfermaCancellazione.fxml"));
                    DialogPane rooot = carica.load();

                    ControllerUtenti controllerDialog = carica.getController();

                    Dialog<ButtonType> a = new Dialog<>();
                    a.setDialogPane(rooot);
                    a.setTitle("ATTENZIONE");

                    //Handler della pagina in sovrapposizione
                    a.setOnShown(e -> {
                            
                       Button pulsanteSi = (Button) rooot.lookup("#btnYes");
                       Button pulsanteNo = (Button) rooot.lookup("#btnNo");

                        // Logica dei bottoni (chiudere la finestra)
                        pulsanteSi.setOnAction(ered -> {
                            u1.elimina(u);
                            // Chiudi la finestra prendendo lo Stage dal bottone stesso
                            ((Stage) pulsanteSi.getScene().getWindow()).close();
                        });

                        pulsanteNo.setOnAction(efds -> {
                            ((Stage) pulsanteNo.getScene().getWindow()).close();
                        });

                        
  
                    });    

                    a.showAndWait();
                    
                    }catch (IOException a){}
                    

                    
                    
                    
                    
                    //Ricarico la pagina ed Aggiorno il contesto
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pageUtenti.fxml"));
                    try{
                    Parent root = loader.load();
                    
                    ControllerUtenti controller =  loader.getController();
                    controller.setContesto(co);
                    Stage stage = (Stage)((Node)elimina).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    }catch (Exception eccezione){}
                });
            }

            //Aggiunta bottoni a tabella
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(bottoni);
                }
            }
        });   
            
            
            if(nome != null) { // Controllo null per evitare errori in altre view che usano questo controller
            nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            cognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
            matricola0.setCellValueFactory(new PropertyValueFactory<>("matricola"));
            mail.setCellValueFactory(new PropertyValueFactory<>("mail")); // getter: getMail()
            nPrestiti.setCellValueFactory(new PropertyValueFactory<>("nPrestiti")); 
        }
            
            
            //Lista Osservabile dalla Tabella
            listaPerTabella = FXCollections.observableArrayList(setUtenti);
            
            
            
            //Filtraggio per Cognome o Matricola
            FilteredList<Utente> datiFiltrati = new FilteredList<>(listaPerTabella, p -> true);
            
            
            barraCercaUtenti.textProperty().addListener((osservabile, vecchio, nuovo) ->{
                datiFiltrati.setPredicate(utente ->{
                    if(nuovo==null || nuovo.isEmpty())
                        return true;
                     String lowerCaseFilter = nuovo.toLowerCase();
                     
                     return utente.getCognome().toLowerCase().contains(lowerCaseFilter) || utente.getMatricola().toLowerCase().contains(lowerCaseFilter);
                });
            });
            
            tabellaUtenti.setItems(datiFiltrati);
            
            
            
            aggiungiUtenteButton.setOnAction(ds ->{
                                try {
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
                                            Utente x = controllerDialog.azioneConferma();
                                            listaPerTabella.add(x); 
                                        return dialogButton;
                                    }
                                    return null;
                                });   
                        });    

                        a.showAndWait();
                } catch (Exception e) {}
            });   
        }
        else if(nomeFile.endsWith("aggiuntaUtente.fxml")){}
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
    
    public void setGenitore(ControllerUtenti genitore) {
        this.controllerGenitore = genitore;
    }
    
    @FXML
    public Utente azioneConferma() {
        if (controllerGenitore != null) {
            // 1. Prendiamo il testo dall'input della Pagina 2
            String nome = nomeUtente.getText();
            String cognome = cognomeUtente.getText();
            String matricola = this.matricola.getText();
            String mail = email.getText();
           
            Utenti u1 = co.getGestUtenti();
            Utente temp = new Utente(nome,cognome,matricola,mail,0);
            u1.aggiungi(temp);
            return temp;
        }
        return null;
    }  
}
