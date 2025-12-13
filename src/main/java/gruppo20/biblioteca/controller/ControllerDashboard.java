/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gruppo20.biblioteca.controller;

import gruppo20.biblioteca.model.Main;
import gruppo20.biblioteca.model.Prestiti.Prestiti;
import gruppo20.biblioteca.model.Prestiti.Prestito;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.css.PseudoClass;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author euppa
 */
public class ControllerDashboard implements Initializable {

    private Contesto co;
    
    @FXML
    private Label clock;
    
    @FXML
    private TableView<Prestito> tabellaPrestitiAttivi;
    @FXML
    private TableColumn<Prestito, String> dataPrestitoA;
    @FXML
    private TableColumn<Prestito, String> matricolaA;
    @FXML
    private TableColumn<Prestito, Integer> giorniRitardoA;
    
    
    private ObservableList<Prestito> listaPerTabellaAttivi;
    
    public void initialize(URL location, ResourceBundle resources) {
        this.co = Main.getContesto();
        System.out.println("Contesto in Libreria: "+ co);
        ObjectProperty<LocalDateTime> dataOraProperty =
        new SimpleObjectProperty<>();
        DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        clock.textProperty().bind(
            Bindings.createStringBinding(
                () -> {
                    LocalDateTime dt = dataOraProperty.get();
                    return dt == null ? "" : dt.format(formatter);
                },
                dataOraProperty
            )
        );
        dataOraProperty.set(LocalDateTime.now());
        
        
        
        Prestiti p = co.getGestPrestiti();
            ObservableSet<Prestito> set = p.getSetPrestiti();
            ArrayList<Prestito> listaAttivi = new ArrayList<>();
            
            if(dataPrestitoA != null) { // Controllo null per evitare errori in altre view che usano questo controller
                dataPrestitoA.setCellValueFactory(new PropertyValueFactory<>("dataPrestito"));
                matricolaA.setCellValueFactory(new PropertyValueFactory<>("matricola"));
                giorniRitardoA.setCellValueFactory(new PropertyValueFactory<>("ritardo"));
                
                
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
                
                for(Prestito b: set){
                if(!b.getEffettivaRestituzione().isRestituito())
                    listaAttivi.add(b);
            }
            listaPerTabellaAttivi = FXCollections.observableArrayList(listaAttivi);
            
            tabellaPrestitiAttivi.setItems(listaPerTabellaAttivi);
            
            //Comando l'ordinamento della tabella. I Prestiti con più giorni di ritardo stanno in cima (Per entrambe)
            giorniRitardoA.setSortType(TableColumn.SortType.DESCENDING);
            tabellaPrestitiAttivi.getSortOrder().clear();
            tabellaPrestitiAttivi.getSortOrder().add(giorniRitardoA);
            tabellaPrestitiAttivi.sort();
        
        
            }

    }
    
    

    @FXML
    public void pageDashboard(MouseEvent event) throws IOException {
    }

    @FXML
    public void pageUtenti(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pageUtenti.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void pageLibreria(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pageLibreria.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void pagePrestiti(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pagePrestiti.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void aggiuntaLibro(MouseEvent event) throws IOException {
        new ControllerLibreria();
    }

    @FXML
    public void aggiuntaPrestito(MouseEvent event) throws IOException {
        new ControllerPrestiti();
    }

}
