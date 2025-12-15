package gruppo20.biblioteca.controller;

import com.sun.prism.shader.Solid_TextureYV12_Loader;
import gruppo20.biblioteca.model.Main;
import gruppo20.biblioteca.model.Utenti.*;
import javafx.scene.control.Dialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.*;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.transformation.FilteredList;
import javafx.css.PseudoClass;
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
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 *
 * @author Osv
 */
public class ControllerUtenti implements Initializable {

    private Contesto co;
    
    @FXML
    private TableView<Utente> tabellaUtenti;

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

    @FXML
    private TableColumn<Utente, String> nome;
    @FXML
    private TableColumn<Utente, String> cognome;
    @FXML
    private TableColumn<Utente, String> matricola0;
    @FXML
    private TableColumn<Utente, String> mail;
    @FXML
    private TableColumn<Utente, Integer> nPrestiti;
    @FXML
    private TableColumn<Utente, Void> operazioni;

    private ObservableList<Utente> listaPerTabella;



    //Inizializzo la pagina
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.co = Main.getContesto();
        System.out.println("Contesto in Utenti: "+ co);
        String nomeFile = location.getFile();

        //Verifico di essere nella pagina Principale
        if (nomeFile.endsWith("pageUtenti.fxml")) {

            Utenti u = co.getGestUtenti();
            ObservableSet<Utente> setUtenti = u.getSetUtenti();
            tabellaUtenti.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            operazioni.setCellFactory(col -> new TableCell<Utente, Void>() {

                FontIcon mod = new FontIcon("fa-pencil");
                FontIcon del = new FontIcon("fa-remove");

                Button modifica = new Button("", mod);
                Button elimina = new Button("", del);

                HBox bottoni = new HBox(5);

                {

                    bottoni.getChildren().addAll(modifica, elimina);

                    //Comportamento bottone modifica
                    modifica.setOnAction(e0 -> {
                        Utente uVecchio = getTableView().getItems().get(getIndex());

                        try {

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aggiuntaUtente.fxml"));
                            DialogPane root = loader.load();

                            ControllerUtenti controllerDialog = loader.getController();
                            
                            String testoOriginale = uVecchio.getMail();
                            testoOriginale = testoOriginale.substring(0,testoOriginale.length()-18);
                            

                            controllerDialog.nomeUtente.setText(uVecchio.getNome());
                            controllerDialog.cognomeUtente.setText(uVecchio.getCognome());
                            controllerDialog.matricola.setText(uVecchio.getMatricola());
                            controllerDialog.email.setText(testoOriginale);

                            Dialog<ButtonType> dialog = new Dialog<>();
                            dialog.setDialogPane(root);
                            dialog.setTitle("Modifica Utente");

                            TextField tNome = controllerDialog.nomeUtente;
                            TextField tCognome = controllerDialog.cognomeUtente;
                            TextField tMatr = controllerDialog.matricola;
                            TextField tMail = controllerDialog.email;

                            BooleanBinding matricolaNonValida = Bindings.createBooleanBinding(
                                () -> !tMatr.getText().matches("[0-9]{10}"), 
                                tMatr.textProperty()
                            );


                            dialog.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(
                                tNome.textProperty().isEmpty()
                                .or(tCognome.textProperty().isEmpty())
                                .or(tMail.textProperty().isEmpty())
                                .or(matricolaNonValida) 
                            ); 



                            PseudoClass evidenziataClass = PseudoClass.getPseudoClass("errati");

                            tMatr.textProperty().addListener((obs, vecchioValore, nuovoValore) -> {

                                boolean isError = !nuovoValore.matches("[0-9]{10}");

                                boolean mostraRosso = isError && !nuovoValore.isEmpty(); 
                                tMatr.pseudoClassStateChanged(evidenziataClass, mostraRosso);
                            });
                            
                            
                            
                            dialog.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION, event ->{
                            
                            Utente x = new Utente(tNome.getText(),tCognome.getText(),
                                                        tMatr.getText(),tMail.getText()+"@studenti.unisa.it",0);
                            
                            if(setUtenti.contains(x) && !uVecchio.getMatricola().equals(tMatr.getText())){
                                    event.consume();
                                    tMatr.setText("");
                                    tMatr.pseudoClassStateChanged(evidenziataClass, true);
                                    tMatr.setPromptText("Matricola già esistente in database!");
                                }
                             });
                            
                            
                            

                            dialog.showAndWait().ifPresent(response -> {
                                if (response == ButtonType.OK) {

                                    String nuovoNome = tNome.getText();
                                    String nuovoCognome = tCognome.getText();
                                    String nuovaMatricola = tMatr.getText();
                                    String nuovaMail = tMail.getText();

                                    Utente uNuovo = new Utente(nuovoNome, nuovoCognome, nuovaMatricola, nuovaMail+"@studenti.unisa.it", uVecchio.getNPrestiti());
                                    
                                    try {
                                        u.modifica(uVecchio, uNuovo);
                                    } catch (Exception a) {
                                    }

                                    int index = listaPerTabella.indexOf(uVecchio);
                                    if (index >= 0) {
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
                        Utente u0 = getTableView().getItems().get(getIndex());

                        try {
                            FXMLLoader carica = new FXMLLoader(getClass().getResource("/fxml/ConfermaCancellazione.fxml"));
                            DialogPane rooot = carica.load();

                            Dialog<ButtonType> a = new Dialog<>();
                            a.setDialogPane(rooot);
                            a.setTitle("ATTENZIONE");

                            //Handler della pagina in sovrapposizione
                            a.setOnShown(e -> {

                                Button pulsanteSi = (Button) rooot.lookup("#btnYes");
                                Button pulsanteNo = (Button) rooot.lookup("#btnNo");

                                // Logica dei bottoni 
                                pulsanteSi.setOnAction(ered -> {
                                    u.elimina(u0);
                                    listaPerTabella.remove(u0);
                                    ((Stage) pulsanteSi.getScene().getWindow()).close();
                                });

                                pulsanteNo.setOnAction(efds -> {
                                    ((Stage) pulsanteNo.getScene().getWindow()).close();
                                });

                            });

                            a.showAndWait();

                        } catch (IOException a) {
                        }
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

            if (nome != null) { // Controllo null per evitare errori in altre view che usano questo controller
                nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
                cognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
                matricola0.setCellValueFactory(new PropertyValueFactory<>("matricola"));
                mail.setCellValueFactory(new PropertyValueFactory<>("mail")); 
                nPrestiti.setCellValueFactory(new PropertyValueFactory<>("nPrestiti"));
            }

            //Lista Osservabile dalla Tabella
            listaPerTabella = FXCollections.observableArrayList(setUtenti);

            //Filtraggio per Cognome o Matricola
            FilteredList<Utente> datiFiltrati = new FilteredList<>(listaPerTabella, p -> true);

            barraCercaUtenti.textProperty().addListener((osservabile, vecchio, nuovo) -> {
                datiFiltrati.setPredicate(utente -> {
                    if (nuovo == null || nuovo.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = nuovo.toLowerCase();

                    return utente.getCognome().toLowerCase().contains(lowerCaseFilter) || utente.getMatricola().toLowerCase().contains(lowerCaseFilter);
                });
            });

            tabellaUtenti.setItems(datiFiltrati);

            aggiungiUtenteButton.setOnAction(ds -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aggiuntaUtente.fxml"));
                    DialogPane root = loader.load();

                    ControllerUtenti controllerDialog = loader.getController();

                    Dialog<ButtonType> a = new Dialog<>();
                    a.setDialogPane(root);
                    a.setTitle("Inserire nuovo Utente");

                    //Handler della pagina in sovrapposizione
                    a.setOnShown(e -> {

                        TextField nomeUtente = controllerDialog.nomeUtente;
                        TextField cognomeUtente = controllerDialog.cognomeUtente;
                        TextField matricola = controllerDialog.matricola;
                        TextField email = controllerDialog.email;
                        
                        
                        
                        BooleanBinding matricolaNonValida = Bindings.createBooleanBinding(
                            () -> !matricola.getText().matches("[0-9]{10}"), 
                            matricola.textProperty()
                        );
                        BooleanBinding mailNonValida = Bindings.createBooleanBinding(
                            () -> email.getText().contains("@"), 
                            matricola.textProperty()
                        );

                        
                        a.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(
                            nomeUtente.textProperty().isEmpty()
                            .or(cognomeUtente.textProperty().isEmpty())
                            .or(email.textProperty().isEmpty())
                            .or(matricolaNonValida)
                            .or(mailNonValida)
                        ); 
                        
                        
                        
                        PseudoClass evidenziataClass = PseudoClass.getPseudoClass("errati");
                
                        matricola.textProperty().addListener((obs, vecchioValore, nuovoValore) -> {
                            
                            boolean isError = !nuovoValore.matches("[0-9]{10}");
                            
                            boolean mostraRosso = isError && !nuovoValore.isEmpty(); 
                            matricola.pseudoClassStateChanged(evidenziataClass, mostraRosso);
                        });
                        
                        email.textProperty().addListener((obs, vecchioValore, nuovoValore) -> {
                            
                            boolean isError = nuovoValore.contains("@");
                            
                            boolean mostraRosso = isError && !nuovoValore.isEmpty(); 
                            email.pseudoClassStateChanged(evidenziataClass, mostraRosso);
                        });
                        
                        
                        a.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION, event ->{
                            
                            Utente x = new Utente(nomeUtente.getText(),cognomeUtente.getText(),
                                                        matricola.getText(),email.getText()+"@studenti.unisa.it",0);
                            
                            if(setUtenti.contains(x)){
                            
                                    event.consume();
                                    matricola.setText("");
                                    matricola.pseudoClassStateChanged(evidenziataClass, true);
                                    matricola.setPromptText("Matricola già esistente in database!");
                                }
                        }); 

                        a.setResultConverter(dialogButton -> {
                            if (dialogButton == ButtonType.OK) {
                                
                                Utente x = new Utente(nomeUtente.getText(),cognomeUtente.getText(),
                                                        matricola.getText(),email.getText()+"@studenti.unisa.it",0);
                                listaPerTabella.add(x);
                                u.aggiungi(x);
                                return dialogButton;
                            }
                            return null;
                        });
                    });

                    a.showAndWait();
                } catch (Exception e) {
                }
            });
        } else if (nomeFile.endsWith("aggiuntaUtente.fxml")) {}
    }

    @FXML
    public void pageDashboard(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Dashboard.fxml"));
        Parent root = loader.load();
        ((Node)event.getSource()).getScene().setRoot(root);

    }

    @FXML
    public void pageUtenti(MouseEvent event) throws IOException {
    }

    @FXML
    public void pageLibreria(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pageLibreria.fxml"));
        Parent root = loader.load();
        ((Node)event.getSource()).getScene().setRoot(root);

    }

    @FXML
    public void pagePrestiti(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pagePrestiti.fxml"));
        Parent root = loader.load();
        ((Node)event.getSource()).getScene().setRoot(root);

    }
}
