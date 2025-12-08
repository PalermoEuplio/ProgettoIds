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
    private final String filePath = "/data/";
    private Libreria gestLibreria;
    private Prestiti gestPrestiti;
    private Utenti gestUtenti;
    
    public Contesto(){
        this.gestLibreria = new Libreria(filePath+"Libreria.txt");
        this.gestPrestiti = new Prestiti(filePath+"Prestiti.txt");
        this.gestUtenti = new Utenti(filePath+"Utenti.txt");
    }

    public Libreria getGestLibreria() {
        return gestLibreria;
    }

    public Prestiti getGestPrestiti() {
        return gestPrestiti;
    }

    public Utenti getGestUtenti() {
        return gestUtenti;
    }
    
    
    
}
