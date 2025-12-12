package gruppo20.biblioteca.controller;

import gruppo20.biblioteca.model.Libri.Libreria;
import gruppo20.biblioteca.model.Prestiti.Prestiti;
import gruppo20.biblioteca.model.Utenti.Utenti;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @brief Classe contesto, contiene tutti i dati necessari durante l'esecuzione.
 * Per avere persistenza dei dati in memoria durante tutta l'esecuzione, altrimenti
 * ricaricherebbe il database ogni volta che cambia schermata.
 * @author Gruppo20
 */
public class Contesto {

    private Libreria gestLibreria;
    private Prestiti gestPrestiti;
    private Utenti gestUtenti;

    public Contesto() {
        String userHome = System.getProperty("user.home"); //ricerca la home relativa al sistema operativo
        Connection conn;

        File Dir = new File(userHome + File.separator + "Documents" + File.separator + "Biblioteca"); //genera il path 
        
        //verifica se esiste già, in caso lo crea
        if (!Dir.exists()) {
            boolean status = Dir.mkdirs();
            if (!status) {
                System.out.println("Impossibile creare la directory: " + Dir.getAbsolutePath());
            }
        }
        
        //prova a connettersi al database 
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + Dir.getAbsolutePath()+File.separator+"Biblioteca.db");
        } catch (SQLException ex) {
            throw new RuntimeException("Impossibile connettersi al database : "+Dir.getAbsolutePath()+File.separator+"Biblioteca.db");
        }
        
        //inizializza i gestori
        try {
            this.gestLibreria = new Libreria(conn);
            this.gestUtenti = new Utenti(conn);
            this.gestPrestiti = new Prestiti(conn, gestUtenti, gestLibreria);
        } catch (SQLException e) {
            throw new RuntimeException("Errore fatale nel caricamento del database");
        }
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
