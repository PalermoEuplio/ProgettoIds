package gruppo20.biblioteca.controller;

import com.sun.javafx.binding.BindingHelperObserver;
import gruppo20.biblioteca.model.Libri.Libro;
import gruppo20.biblioteca.model.Main;
import gruppo20.biblioteca.model.Prestiti.Prestito;
import gruppo20.biblioteca.model.Prestiti.Prestiti;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.control.DateCell;
import javafx.scene.control.TableRow;

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

    //      Campi finestra a comparsa
    @FXML
    private ComboBox matricolaUtenteBox;
    @FXML
    private ComboBox isbnBox;
    @FXML
    private DatePicker annoP;
    @FXML
    private DatePicker annoR;
    @FXML
    private Button aggiuntaPrestitoButton;
    
    
    //      Per tabella Prestiti Attivi
    @FXML
    private TableColumn<Prestito, String> dataPrestitoA;
    @FXML
    private TableColumn<Prestito, String> dataScadenzaA;
    @FXML
    private TableColumn<Prestito, Integer> giorniRitardoA;
    @FXML
    private TableColumn<Prestito, String> matricolaA;
    @FXML
    private TableColumn<Prestito, String> isbnA;
    @FXML
    private TableColumn<Prestito, Void> operazioniA;
    
    
    @FXML
    private TableColumn<Prestito, String> dataPrestitoR;
    @FXML
    private TableColumn<Prestito, String> dataScadenzaR;
    @FXML
    private TableColumn<Prestito, Integer> giorniRitardoR;
    @FXML
    private TableColumn<Prestito, String> matricolaR;
    @FXML
    private TableColumn<Prestito, String> isbnR;
    @FXML
    private TableColumn<Prestito, Void> operazioniR;
    
    
    

    private ObservableList<Prestito> listaPerTabellaAttivi;
    private ObservableList<Prestito> listaPerTabellaRitardi;
    
    
    
    
    
    
    
    public void initialize(URL location, ResourceBundle resources) {
        this.co = Main.getContesto();
        System.out.println("Contesto in Libreria: "+ co);
        String nomeFile = location.getFile();
        if(nomeFile.endsWith("pagePrestiti.fxml")){
            
            Prestiti p = co.getGestPrestiti();
            ObservableSet<Prestito> set = p.getSetPrestiti();
            ArrayList<Prestito> listaAttivi = new ArrayList<>();
            ArrayList<Prestito> listaRestituiti = new ArrayList<>();
            
            //  Creazione e Gestione della tabella dei Prestiti Attivi
            tabellaPrestitiAttivi.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
            operazioniA.setCellFactory(col -> new TableCell<Prestito, Void>() {
            
                FontIcon mod = new FontIcon("fa-pencil");
                FontIcon del = new FontIcon("fa-remove");
                FontIcon ok = new FontIcon("fa-check");
                
                Button modifica = new Button("",mod);
                Button elimina = new Button("",del);
                Button conferma = new Button("",ok);
                
                
                
                HBox bottoni = new HBox(3.5);
                {
                
                    bottoni.getChildren().addAll(modifica,elimina,conferma);
                    
                    //Comportamento bottone modifica
                    modifica.setOnAction(e0 -> {
                        Prestito pVecchio = getTableView().getItems().get(getIndex());

                        try {
                            
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aggiuntaPrestito.fxml"));
                            DialogPane root = loader.load();

                            ControllerPrestiti controllerDialog = loader.getController();
                            
                            
                            
                            
                            controllerDialog.matricolaUtenteBox.setValue(pVecchio.getMatricola());
                            controllerDialog.isbnBox.setValue(pVecchio.getIsbn());
                            controllerDialog.annoP.setValue(pVecchio.getDataPrestito());
                            controllerDialog.annoR.setValue(pVecchio.getDataPrevistaRestituzione());

                            
                            Dialog<ButtonType> dialog = new Dialog<>();
                            dialog.setDialogPane(root);
                            dialog.setTitle("Modifica Prestito");

                            
                            ComboBox bmatricolaUtente = controllerDialog.matricolaUtenteBox;
                            bmatricolaUtente.setItems(FXCollections.observableArrayList(co.getGestUtenti().getSetUtenti()));
                            
                            ComboBox bIsbn = controllerDialog.isbnBox;
                            bIsbn.setItems(FXCollections.observableArrayList(co.getGestLibreria().getSetLibreria()));
                            
                            DatePicker tAnnoP = controllerDialog.annoP;
                            DatePicker tAnnoR = controllerDialog.annoR;

                            
                            dialog.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(
                                bmatricolaUtente.valueProperty().isNull()
                                .or(bIsbn.valueProperty().isNull())
                                .or(tAnnoP.valueProperty().isNull())
                                .or(tAnnoR.valueProperty().isNull())
                            ); 

                            
                            dialog.showAndWait().ifPresent(response -> {
                                if (response == ButtonType.OK) {
                                    
                                    String[] campiIsbn = ((String)bIsbn.getValue()).split("; ");
                                    String[] campiMat = ((String)bmatricolaUtente.getValue()).split("; ");
                                    LocalDate nuovaAnnoP= tAnnoP.getValue();
                                    LocalDate nuovaAnnoR= tAnnoR.getValue();
                                    
                                    ObservableSet<Libro> setLibro = co.getGestLibreria().getSetLibreria();
                                    
                                    
                                    
                                    Prestito pNuovo=null;
                                    
                                    // Cerco il Titolo dall'Isbn
                                    for(Libro x: setLibro){
                                        if(x.equals(new Libro(null,null,null,0,campiIsbn[campiIsbn.length-1]))){
                                            
                                             pNuovo = new Prestito(nuovaAnnoP,nuovaAnnoR,"false",x.getTitolo(),campiIsbn[campiIsbn.length-1],campiMat[campiMat.length-1]);
                                             break;
                                        }
                                        // ----------- Possibile Messaggio d'Errore per modifica del Prestito -----------
                                    }
                                    
                                    try{
                                        p.modifica(pVecchio, pNuovo);
                                    }catch(Exception a){}
                                    
                                    int index = listaPerTabellaAttivi.indexOf(pVecchio);
                                    if(index >= 0){
                                        listaPerTabellaAttivi.set(index, pNuovo);
                                        listaAttivi.add(pNuovo);
                                    }
                                    tabellaPrestitiAttivi.refresh();
                                    tabellaPrestitiAttivi.sort();
                                }
                            });

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                
                
                //Comportamento Bottone eliminazione
                    elimina.setOnAction(e1 -> {
                        Prestito preso = getTableView().getItems().get(getIndex());    


                        try{
                            FXMLLoader carica = new FXMLLoader(getClass().getResource("/fxml/ConfermaCancellazione.fxml"));
                            DialogPane rooot = carica.load();

                            Dialog<ButtonType> a = new Dialog<>();
                            a.setDialogPane(rooot);
                            a.setTitle("ATTENZIONE");

                            //Handler della pagina in sovrapposizione
                            a.setOnShown(e -> {

                               Button pulsanteSi = (Button) rooot.lookup("#btnYes");
                               Button pulsanteNo = (Button) rooot.lookup("#btnNo");

                                // Logica dei bottoni (chiudere la finestra)
                                pulsanteSi.setOnAction(erese -> {
                                    p.elimina(preso);
                                    listaPerTabellaAttivi.remove(preso);
                                    listaAttivi.remove(preso);
                                    // Chiudi la finestra prendendo lo Stage dal bottone stesso
                                    ((Stage) pulsanteSi.getScene().getWindow()).close();
                                });

                                pulsanteNo.setOnAction(efds -> {
                                    ((Stage) pulsanteNo.getScene().getWindow()).close();
                                });



                            });    

                            a.showAndWait();

                        }catch (IOException a){}
                    });
                    
                    conferma.setOnAction(dsa ->{
                       
                        Prestito preso = getTableView().getItems().get(getIndex());
                        preso.setEffettivaRestituzione(LocalDate.now());
                        
                        listaPerTabellaRitardi.add(preso);
                        listaPerTabellaAttivi.remove(preso);
                        
                        p.restituisci(preso, LocalDate.MIN);
                        tabellaPrestitiRitardo.sort();
                        
   
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
            
            
            operazioniR.setCellFactory(col -> new TableCell<Prestito, Void>() {
                
                FontIcon del = new FontIcon("fa-remove");
                
                Button elimina = new Button("",del);
                
                
                {
    
                //Comportamento Bottone eliminazione
                    elimina.setOnAction(e1 -> {
                        Prestito preso = getTableView().getItems().get(getIndex());    


                        try{
                            FXMLLoader carica = new FXMLLoader(getClass().getResource("/fxml/ConfermaCancellazione.fxml"));
                            DialogPane rooot = carica.load();

                            Dialog<ButtonType> a = new Dialog<>();
                            a.setDialogPane(rooot);
                            a.setTitle("ATTENZIONE");

                            //Handler della pagina in sovrapposizione
                            a.setOnShown(e -> {

                               Button pulsanteSi = (Button) rooot.lookup("#btnYes");
                               Button pulsanteNo = (Button) rooot.lookup("#btnNo");

                                // Logica dei bottoni (chiudere la finestra)
                                pulsanteSi.setOnAction(erese -> {
                                    p.elimina(preso);
                                    listaPerTabellaRitardi.remove(preso);
                                    // Chiudi la finestra prendendo lo Stage dal bottone stesso
                                    ((Stage) pulsanteSi.getScene().getWindow()).close();
                                });

                                pulsanteNo.setOnAction(efds -> {
                                    ((Stage) pulsanteNo.getScene().getWindow()).close();
                                });



                            });    

                            a.showAndWait();

                        }catch (IOException a){}
                    });
            }

                //Aggiunta bottone a tabella
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(elimina);
                    }
                }
            });
            
            
            if(dataPrestitoA != null) { // Controllo null per evitare errori in altre view che usano questo controller
                dataPrestitoA.setCellValueFactory(new PropertyValueFactory<>("dataPrestito"));
                dataScadenzaA.setCellValueFactory(new PropertyValueFactory<>("dataPrevistaRestituzione"));
                matricolaA.setCellValueFactory(new PropertyValueFactory<>("matricola"));
                giorniRitardoA.setCellValueFactory(new PropertyValueFactory<>("ritardo"));
                isbnA.setCellValueFactory(new PropertyValueFactory<>("isbn"));
                
                
                PseudoClass evidenziataClass = PseudoClass.getPseudoClass("evidenziata");
                
                tabellaPrestitiAttivi.setRowFactory(dsa -> {
                    TableRow<Prestito> row = new TableRow<>();
                    
                    row.itemProperty().addListener((obs,vecchio,nuovo)->{
                        if(nuovo==null)
                            row.pseudoClassStateChanged(evidenziataClass, false);
                        else {
                            boolean condizione = nuovo.getRitardo()>3;
                            row.pseudoClassStateChanged(evidenziataClass, condizione);
                        }
                    });
                    
                    return row;
                });
                
                
                
                
                
                
            }
            
            if(dataPrestitoR != null) { // Controllo null per evitare errori in altre view che usano questo controller
                dataPrestitoR.setCellValueFactory(new PropertyValueFactory<>("dataPrestito"));
                dataScadenzaR.setCellValueFactory(new PropertyValueFactory<>("dataPrevistaRestituzione"));
                matricolaR.setCellValueFactory(new PropertyValueFactory<>("matricola"));
                giorniRitardoR.setCellValueFactory(new PropertyValueFactory<>("ritardo"));
                isbnR.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            }
            
            
            //Ciclo per filtrare gli elementi non restituiti
            
            for(Prestito b: set){
                if(!b.getEffettivaRestituzione().isRestituito())
                    listaAttivi.add(b);
            }
            listaPerTabellaAttivi = FXCollections.observableArrayList(listaAttivi);
            
            
            
            
            //Ciclo per elementi restituiti
            
            for(Prestito b : set){
                if(b.getEffettivaRestituzione().isRestituito()){
                    
                    listaRestituiti.add(b);
                }
            }
            listaPerTabellaRitardi = FXCollections.observableArrayList(listaRestituiti);
            
            
            tabellaPrestitiAttivi.setItems(listaPerTabellaAttivi);
            
            //Comando l'ordinamento della tabella. I Prestiti con più giorni di ritardo stanno in cima (Per entrambe)
            giorniRitardoA.setSortType(TableColumn.SortType.DESCENDING);
            tabellaPrestitiAttivi.getSortOrder().clear();
            tabellaPrestitiAttivi.getSortOrder().add(giorniRitardoA);
            tabellaPrestitiAttivi.sort();
            
            tabellaPrestitiRitardo.setItems(listaPerTabellaRitardi);
            
            giorniRitardoR.setSortType(TableColumn.SortType.DESCENDING);
            tabellaPrestitiRitardo.getSortOrder().clear();
            tabellaPrestitiRitardo.getSortOrder().add(giorniRitardoR);
            tabellaPrestitiRitardo.sort();
            
            
                aggiuntaPrestitoButton.setOnAction(ds -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aggiuntaPrestito.fxml"));
                        DialogPane root = loader.load();

                        ControllerPrestiti controllerDialog = loader.getController();

                        Dialog<ButtonType> a = new Dialog<>();
                        a.setDialogPane(root);
                        a.setTitle("Inserire nuovo Prestito");

                        //Handler della pagina in sovrapposizione
                        a.setOnShown(e -> {
                            
                            ComboBox bmatricolaUtente = controllerDialog.matricolaUtenteBox;
                            bmatricolaUtente.setItems(FXCollections.observableArrayList(co.getGestUtenti().getSetUtenti()));
                            
                            
                            ComboBox bIsbn = controllerDialog.isbnBox;
                            bIsbn.setItems(FXCollections.observableArrayList(co.getGestLibreria().getSetLibreria()));
                            
                            DatePicker tAnnoP = controllerDialog.annoP;
                            tAnnoP.setValue(LocalDate.now());
                            DatePicker tAnnoR = controllerDialog.annoR;
                            
                            BooleanBinding datanonValida = Bindings.createBooleanBinding(
                                () -> tAnnoR.getValue().isBefore(tAnnoP.getValue()),
                                tAnnoR.valueProperty()
                            );

                            a.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(
                                bmatricolaUtente.valueProperty().isNull()
                                .or(bIsbn.valueProperty().isNull())
                                .or(tAnnoP.valueProperty().isNull())
                                .or(datanonValida)
                            ); 
                            
                            PseudoClass evidenziataClass = PseudoClass.getPseudoClass("errato");
                            
                            tAnnoR.valueProperty().addListener((obs, oldV, newV) -> {
                                LocalDate fine = tAnnoP.getValue();

                                boolean nonValida =
                                        newV != null &&
                                        fine != null &&
                                        newV.isBefore(fine);

                                tAnnoR.pseudoClassStateChanged(evidenziataClass, nonValida);
                            }); 

                            a.setResultConverter(dialogButton -> {
                                if (dialogButton == ButtonType.OK) {
                                    ObservableSet<Libro> setLibro = co.getGestLibreria().getSetLibreria();
                                
                                    String[] campiIsbn = ((String)bIsbn.getValue()).split("; ");
                                    String[] campiMat = ((String)bmatricolaUtente.getValue()).split("; ");
                                    
                                    
                                    for(Libro temp : setLibro){
                                            if(temp.equals(new Libro(null,null,null,0,campiIsbn[campiIsbn.length-1]))){
                                                
                                                Prestito x = new Prestito((LocalDate)tAnnoP.getValue(),(LocalDate)tAnnoR.getValue(),
                                                        "false",temp.getTitolo(),campiIsbn[campiIsbn.length-1],campiMat[campiMat.length-1]);
                                                listaAttivi.add(x);
                                                listaPerTabellaAttivi.add(x);
                                                tabellaPrestitiAttivi.sort();
                                                p.aggiungi(x);
                                                break;
                                            }
                                    }
                                    
                                    return dialogButton;
                                     
                                }
                                return null;
                            });
                        });
                        
                        a.showAndWait();
                    } catch (Exception e) {
                }
            });
        }
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

}
