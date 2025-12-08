package gruppo20.biblioteca.controller;

import gruppo20.biblioteca.model.Libri.Libreria;
import gruppo20.biblioteca.model.Prestiti.Prestiti;
import gruppo20.biblioteca.model.Utenti.Utenti;

/**
 *
 * @author Osv
 */
//Classe contesto del programma, contiene tutti i dati per non avere un overhead inutile
public class Contesto {
    public Libreria gestLibreria;
    public Prestiti gestPrestiti;
    public Utenti gestUtenti;
    
    public Contesto(String filePath){
        this.gestLibreria = new Libreria(filePath+"Libreria.txt");
        this.gestPrestiti = new Prestiti(filePath+"Prestiti.txt");
        this.gestUtenti = new Utenti(filePath+"Utenti.txt");
    }
    
    
    
}
