package gruppo20.biblioteca.controller;

import gruppo20.biblioteca.model.Libri.Libro;
import gruppo20.biblioteca.model.Libri.Libreria;
import gruppo20.biblioteca.model.Main;
import gruppo20.biblioteca.model.Utenti.Utente;
import javafx.scene.control.Dialog;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.converter.NumberStringConverter;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 *
 * @author Gruppo20
 */
public class ControllerLibreria implements Initializable{
    
    private Contesto co;   
 
    @FXML 
    private TableView<Libro> tabellaLibri;
    
    
    @FXML 
    private TextField titoloLibro;
    @FXML 
    private TextField listaAutori;
    @FXML 
    private TextField isbn;
    @FXML 
    private DatePicker annoP;
    @FXML 
    private TextField NCopie;
    @FXML 
    private TextField barraCercaLibri;
    @FXML 
    private Button aggiungiLibroButton;
    
    
    @FXML 
    private TableColumn<Libro, String> titolo;
    @FXML 
    private TableColumn<Libro, String> autori;
    @FXML 
    private TableColumn<Libro, LocalDate> annoPublicazione;
    @FXML 
    private TableColumn<Libro, String> isbn0;
    @FXML 
    private TableColumn<Libro, Integer> nCopie;
    @FXML 
    private TableColumn<Libro, Void> operazioni;
    
    
    private ObservableList<Libro> listaPerTabella;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.co = Main.getContesto();
        String nomeFile = location.getFile();
        
        //Verifico di essere nella pagina Principale
        if(nomeFile.endsWith("pageLibreria.fxml")){
            
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
                            SimpleIntegerProperty x = new SimpleIntegerProperty(Integer.valueOf(tNCopie.getText()));
                            tNCopie.textProperty().bindBidirectional(x, new NumberStringConverter());
                             
                            
                            BooleanBinding isbnNonValido = Bindings.createBooleanBinding(
                                () -> !tIsbn.getText().matches("[0-9]{3}[-]{1}[0-9]{2}[-]{1}[0-9]{5}[-]{1}[0-9]{2}[-]{1}[0-9]{1}"), 
                                tIsbn.textProperty()
                            );

                            dialog.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(
                                tTitoloLibro.textProperty().isEmpty()
                                    .or(tListaAutori.textProperty().isEmpty())
                                    .or(tAnnoP.valueProperty().isNull())
                                    .or(x.isEqualTo(0))
                                    .or(isbnNonValido) 
                            ); 

                            PseudoClass evidenziataClass = PseudoClass.getPseudoClass("errati");

                            tIsbn.textProperty().addListener((obs, vecchioValore, nuovoValore) -> {

                                boolean isError = !nuovoValore.matches("[0-9]{3}[-]{1}[0-9]{2}[-]{1}[0-9]{5}[-]{1}[0-9]{2}[-]{1}[0-9]{1}");

                                boolean mostraRosso = isError && !nuovoValore.isEmpty(); 
                                tIsbn.pseudoClassStateChanged(evidenziataClass, mostraRosso);
                            });
                            
                            
                            dialog.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION, event ->{
                                
                                if(!tListaAutori.getText().contains(" ")){
                                    event.consume();
                                    tListaAutori.pseudoClassStateChanged(evidenziataClass, true);
                                }else{
                                    for(Libro temp : setLibri){
                                        if((!temp.getTitolo().equals(tTitoloLibro.getText()) || !temp.getAutori().equals(tListaAutori.getText())) && temp.getIsbn().equals(tIsbn.getText())){
                                                event.consume();
                                                tIsbn.setText("");
                                                tIsbn.pseudoClassStateChanged(evidenziataClass, true);
                                                tIsbn.setPromptText("ISbn già esistente in libreria!");
                                                break;
                                        }
                                    }
                                }
                            });

                            
                            dialog.showAndWait().ifPresent(response -> {
                                if (response == ButtonType.OK) {
                                    
                                    String nuovoTitolo = tTitoloLibro.getText();
                                    String nuovoListaAutori = tListaAutori.getText();
                                    String nuovaIsbn = tIsbn.getText();
                                    LocalDate nuovaAnnoP= tAnnoP.getValue();
                                    Integer nuovoNCopie = Integer.parseInt(tNCopie.getText());
                                    
                                    Libro lNuovo = new Libro(nuovoTitolo, nuovoListaAutori, nuovaAnnoP, nuovoNCopie, nuovaIsbn);
                                    
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
                    Libro l0 = getTableView().getItems().get(getIndex());    
                    
                    
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
                            pulsanteSi.setOnAction(ered -> {
                                l.elimina(l0);
                                listaPerTabella.remove(l0);
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
            isbn0.setCellValueFactory(new PropertyValueFactory<>("isbn")); 
            nCopie.setCellValueFactory(new PropertyValueFactory<>("NCopie")); 
            
        }
            
            
            //Lista Osservabile dalla Tabella
            listaPerTabella = FXCollections.observableArrayList(setLibri);
            
            
            
            //Filtraggio per Cognome o Matricola
            FilteredList<Libro> datiFiltrati = new FilteredList<>(listaPerTabella, p -> true);
            
            SortedList<Libro> datiOrdinati = new SortedList<>(datiFiltrati);
            
            datiOrdinati.comparatorProperty().bind(tabellaLibri.comparatorProperty());
            
            barraCercaLibri.textProperty().addListener((osservabile, vecchio, nuovo) ->{
                datiFiltrati.setPredicate(libro ->{
                    if(nuovo==null || nuovo.isEmpty())
                        return true;
                     String lowerCaseFilter = nuovo.toLowerCase();
                     
                     return libro.getTitolo().toLowerCase().contains(lowerCaseFilter) || libro.getIsbn().toLowerCase().contains(lowerCaseFilter)
                             || libro.getAutori().toLowerCase().contains(lowerCaseFilter);
                });
            });
            
            tabellaLibri.setItems(datiOrdinati);
            
            aggiungiLibroButton.setOnAction(ds -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/aggiuntaLibro.fxml"));
                    DialogPane root = loader.load();

                    ControllerLibreria controllerDialog = loader.getController();

                    Dialog<ButtonType> a = new Dialog<>();
                    a.setDialogPane(root);
                    a.setTitle("Inserire nuovo Libro");

                    // --- INIZIO CONFIGURAZIONE UI DIALOG ---
                    // (Questa parte serve solo per validare i campi, come avevi fatto tu)
                    a.setOnShown(e -> {
                        
                        TextField tTitolo = controllerDialog.titoloLibro;
                        TextField tAutori = controllerDialog.listaAutori;
                        TextField tIsbn = controllerDialog.isbn;
                        DatePicker tAnno = controllerDialog.annoP;
                        tAnno.setValue(LocalDate.now());
                        TextField tCopie = controllerDialog.NCopie;

                        // Binding per validazione
                        SimpleIntegerProperty numero = new SimpleIntegerProperty(1);
                        try {
                             tCopie.textProperty().bindBidirectional(numero, new NumberStringConverter());
                        } catch (Exception ex) {}

                        BooleanBinding isbnNonValido = Bindings.createBooleanBinding(
                            () -> !tIsbn.getText().matches("[0-9]{3}[-]{1}[0-9]{2}[-]{1}[0-9]{5}[-]{1}[0-9]{2}[-]{1}[0-9]{1}"), 
                            tIsbn.textProperty()
                        );
                        
                        a.getDialogPane().lookupButton(ButtonType.OK).disableProperty().bind(
                            tTitolo.textProperty().isEmpty()
                                .or(tAutori.textProperty().isEmpty())
                                .or(tAnno.valueProperty().isNull())
                                .or(numero.isEqualTo(0))
                                .or(isbnNonValido) 
                        ); 
                        
                        
                        
                        PseudoClass evidenziataClass = PseudoClass.getPseudoClass("errati");
                
                        tIsbn.textProperty().addListener((obs, vecchioValore, nuovoValore) -> {
                            
                            boolean isError = !nuovoValore.matches("[0-9]{3}[-]{1}[0-9]{2}[-]{1}[0-9]{5}[-]{1}[0-9]{2}[-]{1}[0-9]{1}");
                            
                            boolean mostraRosso = isError && !nuovoValore.isEmpty(); 
                            tIsbn.pseudoClassStateChanged(evidenziataClass, mostraRosso);
                        });
                        
                       
                        a.getDialogPane().lookupButton(ButtonType.OK).addEventFilter(ActionEvent.ACTION, event ->{
                            if(tAutori.getText().indexOf(" ")==-1){
                                    event.consume();
                                    tAutori.pseudoClassStateChanged(evidenziataClass, true);
                            }else{
                                for(Libro temp : setLibri){
                                    if((!temp.getTitolo().equals(tTitolo.getText()) || !temp.getAutori().equals(tAutori.getText())) && temp.getIsbn().equals(tIsbn.getText())){
                                            event.consume();
                                            tIsbn.setText("");
                                            tIsbn.pseudoClassStateChanged(evidenziataClass, true);
                                            tIsbn.setPromptText("ISbn già esistente in libreria!");
                                            break;
                                    }
                                }
                            }
                        });

                         
                        a.setResultConverter(response -> {
                            if (response == ButtonType.OK) {
                                boolean flag = false;
                                Libro nuovoLibro = new Libro(tTitolo.getText(), tAutori.getText(), 
                                        tAnno.getValue(), numero.get(), tIsbn.getText());
                                
                                for(Libro temp : setLibri){
                                    if(temp.getTitolo().equals(tTitolo.getText()) && temp.getAutori().equals(tAutori.getText()) && temp.getIsbn().equals(tIsbn.getText())){
                                        int x = nuovoLibro.getNCopie()+temp.getNCopie();
                                        l.aggiungi(nuovoLibro);
                                        listaPerTabella.remove(temp);
                                        nuovoLibro.setNCopie(x);
                                        listaPerTabella.add(nuovoLibro);
                                        flag=true;
                                        break;
                                    }
                                }
                                if(flag==false){
                                    listaPerTabella.add(nuovoLibro);
                                    l.aggiungi(nuovoLibro);
                                }
                                return response;

                            }
                            return null;
                        });
                    });
                    a.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        else if(nomeFile.endsWith("aggiuntaLibro.fxml")){}
    }
    
    
    

    
    @FXML
    public void pageDashboard(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Dashboard.fxml"));
        Parent root = loader.load();
        ((Node)event.getSource()).getScene().setRoot(root);

    }

    @FXML
    public void pageUtenti(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pageUtenti.fxml"));
        Parent root = loader.load();
        ((Node)event.getSource()).getScene().setRoot(root);

    }

    @FXML
    public void pageLibreria(MouseEvent event) throws IOException {
    }

    @FXML
    public void pagePrestiti(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pagePrestiti.fxml"));
        Parent root = loader.load();
        ((Node)event.getSource()).getScene().setRoot(root);

    }
}
