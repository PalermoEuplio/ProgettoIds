
package gruppo20.biblioteca.model;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @brief Questo file contiene l'implementazione della classe Utenza.
 * @author Gruppo20
 */

public class Utenza implements Gestione<Utente>{
    /**
     * Struttura dati che contiene gli utenti
     * TreeSet
     */
    private TreeSet<Utente> anagrafica;
    /**
     * Costruttore gestione utenti
     * Iniziallizza la struttura
     */
    public Utenza() {
        this.anagrafica = new TreeSet<Utente>();
    }    
    
    /**
    *Aggiunge un utente all'anagrafica.
    * 
    * Parametro in ingresso:
    *   @param u utente da aggiungere all'anagrafica.
    * 
    *   @return restituisce true se l'utente è stato inserito. 
    *           false se invece non è stato inserito o è già presente in anagrafica. 
    */  
    @Override
    public boolean aggiungi(Utente u){
        return anagrafica.add(u);
    }
    
    /**
     * @brief Elimina l'utente.
     * Se l'utente è presente effettua l'eliminazione.
     * 
     * Parametro in ingresso:
     *  @param u utente da eliminare
     * 
     *  @return restituisce true se elimina l'utente. false se non è già presente.
     */
    @Override
    public boolean elimina(Utente u){
        return anagrafica.remove(u);
    }
    
     /**
     * @brief Modifica l'utente.
     * Se l'utente è presente effettua la modifica di uno o più suoi dati.
     * 
     * Parametro in ingresso:
     *  @param u utente da modificare
     * 
     *  @return restituisce true se la modifica dell'utente è avvenuta correttamente.
     *          false se l'utente non è presente.
     */
    
    
    
    public boolean modifica(Utente u){
        /*if(anagrafica.contains(u)){
            anagrafica.remove(u);
            anagrafica.add(modified);
            return true;
        }
        return false;*/
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
