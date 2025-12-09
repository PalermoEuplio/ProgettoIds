package gruppo20.biblioteca.controller;

import gruppo20.biblioteca.model.Libri.Libreria;
import gruppo20.biblioteca.model.Prestiti.Prestiti;
import gruppo20.biblioteca.model.Utenti.Utenti;
import java.io.File;

/**
 *
 * @author Osv
 */
//Classe contesto del programma, contiene tutti i dati per non avere un overhead inutile
public class Contesto {
    private Libreria gestLibreria;
    private Prestiti gestPrestiti;
    private Utenti gestUtenti;
    
    
    public Contesto(){
        String userHome = System.getProperty("user.home");
        String documents;

        // Individua nome corretto della cartella Documents
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            documents = userHome + File.separator + "Documents";
        } else {
            // Linux e macOS usano normalmente "Documents"
            documents = userHome + File.separator + "Documents";
        }
        
        File Dir = new File(documents + File.separator + "Biblioteca");
        
        // Creazione della directory (anche annidata)
        if (!Dir.exists()) {
            boolean ok = Dir.mkdirs();
            if (!ok) {
                System.out.println("Impossibile creare la directory: " + Dir.getAbsolutePath());
            }
        }
        
        
        this.gestLibreria = new Libreria(Dir.getAbsolutePath()+"/Biblioteca.db");
        this.gestPrestiti = new Prestiti(Dir.getAbsolutePath()+"/Biblioteca.db");
        this.gestUtenti = new Utenti(Dir.getAbsolutePath()+"/Biblioteca.db");
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
