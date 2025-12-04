/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gruppo20.biblioteca;
import java.util.Iterator;
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
    private TreeSet<Utente> anagrafica;
    /**
     * Costruttore gestione utenti
     * Iniziallizza la struttura
     */
    public GestioneUtenti() {
        this.anagrafica = new TreeSet<Utente>();
    }    
    
    /**
    *Aggiunge un un utente all'anagrafica
     * @param u utente da aggiungere all'anagrafica
    *@return restituisce true se l'utente è stato inserito
    * false se invece non è stato inserito o è già presente 
    *in anagrafica 
    */    
    public boolean aggiungiUtente(Utente u){
        return anagrafica.add(u);
    }
    
    /**
     * Elimina l'utente se presente
     * @param u utente da eliminare
     * @return restituisce true se elimina l'utente
     * false se è già presente
     */
    public boolean eliminazioneUtente(Utente u){
        return anagrafica.remove(u);
    }
    public boolean modificaUtente(Utente u,Utente modified){
        if(anagrafica.contains(u)){
            anagrafica.remove(u);
            anagrafica.add(modified);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        //da correggere
        StringBuilder s = new StringBuilder();
        for(Utente u : anagrafica){
            s.append(u.getNome()).append(" ").append(u.getCognome()).append(" ");
            s.append(u.getMatricola()).append(" ").append(" ").append(u.getMail());
            s.append("\n");

        }
        return s.toString();
    }
    
    
    
}
