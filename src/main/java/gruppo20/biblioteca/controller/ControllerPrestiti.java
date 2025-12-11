package gruppo20.biblioteca.controller;

import java.io.IOException;
import java.time.LocalDate;
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
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author Osv
 */
public class ControllerPrestiti {

    private Contesto co;

    public void setContesto(Contesto co) {
        this.co = co;
    }

    @FXML
    public void pageDashboard(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Dashboard.fxml"));
        Parent root = loader.load();
        ControllerDashboard controller = loader.getController();
        controller.setContesto(co);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void pageUtenti(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pageUtenti.fxml"));
        Parent root = loader.load();
        ControllerUtenti controller = loader.getController();
        //controller.setContesto(co);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void pageLibreria(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pageLibreria.fxml"));
        Parent root = loader.load();
        ControllerLibreria controller = loader.getController();
        //controller.setContesto(co);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void pagePrestiti(MouseEvent event) throws IOException {
    }

    @FXML
    private TextField nomeUtente;
    @FXML
    private TextField isbn;
    @FXML
    private DatePicker annoP;
    @FXML
    private DatePicker annoR;

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
