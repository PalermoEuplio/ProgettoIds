package gruppo20.biblioteca.controller;

import gruppo20.biblioteca.model.Main;
import gruppo20.biblioteca.model.Prestiti.Prestito;
import gruppo20.biblioteca.model.Prestiti.Prestiti;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

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
    private ComboBox nomeUtente;
    @FXML
    private ComboBox isbnBox;
    @FXML
    private TextField matricola;
    @FXML
    private DatePicker annoP;
    @FXML
    private DatePicker annoR;
    
    //private Button aggiuntaPrestitoButton;
    
    
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
        this.co = Main.getContesto();
        System.out.println("Contesto in Libreria: "+ co);/*
        String nomeFile = location.getFile();
        if(nomeFile.endsWith("pagePrestiti.fxml")){
            Libreria l = co.getGestLibreria();
            ObservableSet<Libro> setLibri = l.getSetLibreria();
            tabellaLibri.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
            operazioni.setCellFactory(col -> new TableCell<Libro, Void>() {
            
                FontIcon mod = new FontIcon("fa-pencil");
                FontIcon del = new FontIcon("fa-remove");
                
                Button modifica = new Button("",mod);
                Button elimina = new Button("",del);
                
                HBox bottoni = new HBox(5);
                {
                
                    bottoni.getChildren().addAll(modifica,elimina);
                    
                    //Comportamento bottone modifica
                    modifica.setOnAction(e0 -> {
                        Libro lVecchio = getTableView().getItems().get(getIndex());

                        try {
                            
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aggiuntaLibro.fxml"));
                            DialogPane root = loader.load();

                            ControllerLibreria controllerDialog = loader.getController();
                            
                            controllerDialog.titoloLibro.setText(lVecchio.getTitolo());
                            controllerDialog.listaAutori.setText(lVecchio.getAutori());
                            controllerDialog.isbn.setText(lVecchio.getIsbn());
                            controllerDialog.annoP.setValue(lVecchio.getAnno());
                            controllerDialog.NCopie.setText(String.valueOf(lVecchio.getNCopie()));

                            
                            Dialog<ButtonType> dialog = new Dialog<>();
                            dialog.setDialogPane(root);
                            dialog.setTitle("Modifica Libro");

                            
                            TextField tTitoloLibro = controllerDialog.titoloLibro;
                            TextField tListaAutori = controllerDialog.listaAutori;
                            TextField tIsbn = controllerDialog.isbn;
                            DatePicker tAnnoP = controllerDialog.annoP;
                            TextField tNCopie = controllerDialog.NCopie;

                            
                            dialog.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(
                                tTitoloLibro.textProperty().isEmpty()
                                .or(tListaAutori.textProperty().isEmpty())
                                .or(tIsbn.textProperty().isEmpty())
                                .or(tAnnoP.valueProperty().isNull())
                                    .or(tNCopie.textProperty().isEmpty())
                            );

                            
                            dialog.showAndWait().ifPresent(response -> {
                                if (response == ButtonType.OK) {
                                    
                                    String nuovoTitolo = tTitoloLibro.getText();
                                    String nuovoListaAutori = tListaAutori.getText();
                                    String nuovaIsbn = tIsbn.getText();
                                    LocalDate nuovaAnnoP= tAnnoP.getValue();
                                    Integer nuovoNCopie = Integer.parseInt(tNCopie.getText());
                                    
                                    Libro lNuovo = new Libro(nuovoTitolo, nuovoListaAutori, nuovaAnnoP, nuovoNCopie, nuovaIsbn);

                                    
                                    Libreria l = co.getGestLibreria();
                                    try{
                                    l.modifica(lVecchio, lNuovo);
                                    }catch(Exception a){}
                                    
                                    int index = listaPerTabella.indexOf(lVecchio);
                                    if(index >= 0){
                                        listaPerTabella.set(index, lNuovo);
                                    }
                                    tabellaLibri.refresh();
                                }
                            });

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                
                
                //Comportamento Bottone eliminazione
                elimina.setOnAction(e1 -> {
                    Libro l = getTableView().getItems().get(getIndex());
                    Libreria l1 = co.getGestLibreria();    
                    
                    
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
                                l1.elimina(l);
                                listaPerTabella.remove(l);
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
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pageLibreria.fxml"));
                    try{
                    Parent root = loader.load();
                    
                    ControllerUtenti controller =  loader.getController();
                    //controller.setContesto(co);
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
            
            
            if(titolo != null) { // Controllo null per evitare errori in altre view che usano questo controller
            titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
            autori.setCellValueFactory(new PropertyValueFactory<>("autori"));
            annoPublicazione.setCellValueFactory(new PropertyValueFactory<>("anno"));
            isbn0.setCellValueFactory(new PropertyValueFactory<>("isbn")); // getter: getMail()
            nCopie.setCellValueFactory(new PropertyValueFactory<>("NCopie")); 
            
        }
            
            
            //Lista Osservabile dalla Tabella
            listaPerTabella = FXCollections.observableArrayList(setLibri);
            
            
            
            //Filtraggio per Cognome o Matricola
            FilteredList<Libro> datiFiltrati = new FilteredList<>(listaPerTabella, p -> true);
            
            
            barraCercaLibri.textProperty().addListener((osservabile, vecchio, nuovo) ->{
                datiFiltrati.setPredicate(libro ->{
                    if(nuovo==null || nuovo.isEmpty())
                        return true;
                     String lowerCaseFilter = nuovo.toLowerCase();
                     
                     return libro.getTitolo().toLowerCase().contains(lowerCaseFilter) || libro.getIsbn().toLowerCase().contains(lowerCaseFilter)
                             || libro.getAutori().toLowerCase().contains(lowerCaseFilter);
                });
            });
            
            tabellaLibri.setItems(datiFiltrati);
            
        }
     */   
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
    public void pagePrestiti(MouseEvent event) throws IOException {}


    public void aggiuntaPrestito(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aggiuntaPrestito.fxml"));
        DialogPane root = loader.load();

        ControllerPrestiti controllerDialog = loader.getController();

        Dialog<ButtonType> a = new Dialog<>();
        a.setDialogPane(root);
        a.setTitle("Inserire nuovo Prestito");

        a.setOnShown(e -> {

            ComboBox nomeUtente = controllerDialog.nomeUtente;
            ComboBox isbn = controllerDialog.isbnBox;

            DatePicker annoP = controllerDialog.annoP;
            annoP.setValue(LocalDate.now());

            DatePicker annoR = controllerDialog.annoR;
            annoR.setValue(LocalDate.now().plusDays(30));

            isbn.disableProperty().bind(nomeUtente.valueProperty().isNull());
            a.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(isbn.valueProperty().isNull());

        });
        a.showAndWait();
    }

}
