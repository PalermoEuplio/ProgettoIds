package gruppo20.biblioteca.model;

import gruppo20.biblioteca.controller.*;
import javafx.application.Application;

/**
 * @brief Questo file contiene l'implementazione del Main.
 * @author Gruppo20
 */

public class Main{
    
    private static final Contesto co = new Contesto();
    
    public static Contesto getContesto(){
        return co;
    }
   
    public static void main(String args[]){
        Application.launch(App.class, args);
    }
}
