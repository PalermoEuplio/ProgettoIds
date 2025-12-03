/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gruppo20.biblioteca;
import java.util.TreeSet;

/**
 *Classe per la gestione dell'utenza
 * @author Riccardo
 */
public class GestioneUtenti {
    /**
     * Struttura dati che contiene gli utenti
     * TreeSet
     */
    private TreeSet<Utente> anagrafica = new TreeSet<>();
    
    public boolean aggiungiUtente(Utente u){
        return false;
    }
    public boolean eliminazioneUtente(Utente u){
        return false;
    }
    public boolean modificaUtente(Utente u){
        return false;
    }

    @Override
    public String toString() {
        return "GestioneUtenti{" + "anagrafica=" + anagrafica + '}';
    }
    
    
    
}
