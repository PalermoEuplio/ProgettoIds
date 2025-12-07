/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gruppo20.biblioteca.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;

/**
 *
 * @author euppa
 */
public class ControllerDashboard implements Initializable {
    
    @FXML
    private AnchorPane ap;
    
    @FXML
    private BorderPane bp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        //Todo
    }
    
    @FXML
    public void pageDashboard(MouseEvent event){   
        bp.setCenter(ap);
    }
    
    @FXML
    public void pageUtenti(MouseEvent event) throws IOException{ 
        loadPage("pageUtenti");
    }
    
    @FXML
    public void pageLibreria(MouseEvent event) throws IOException{   
        loadPage("pageLibreria");
    }
    
    @FXML
    public void pagePrestiti(MouseEvent event) throws IOException{ 
        loadPage("pagePrestiti");
    }
    
    public void loadPage(String page) throws IOException{
        Parent root=null;
        
        try{
            root = FXMLLoader.load(getClass().getResource("/fxml/"+page+".fxml"));
        } catch (IOException ex){
            Logger.getLogger(ControllerDashboard.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        bp.setCenter(root);
    }
    
    
    
}
