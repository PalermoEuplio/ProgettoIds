package gruppo20.biblioteca.model.Utenti;
import gruppo20.biblioteca.model.GestioneSet;
import java.util.HashSet;

/**
 * @brief Questo file contiene l'implementazione della classe Utenza.
 * @author Gruppo20
 */

public class Utenti extends GestioneSet<Utente>{
    /**
     * @brief Insieme degli utenti presenti nel sistema.
     * Si utilizza un TreeSet per garantire l'ordinamento degli utenti.
     * Ordinamento effettuato in base al criterio definito nella classe Utente.
     */
    private HashSet<Utente> anagrafica;
    /**
     * Costruttore gestione utenti
     * Iniziallizza la struttura
     */
    public Utenti() {
        this.anagrafica = new HashSet<>();
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
        }*/
        //da implementare
        return false;
    }
    
     /**
     * @brief Ricerca di un utente per cognome.
     * Ricerca l'utente che ha per cognome quello inserito.
     * se l'utente è presente, è stato precedentemente registrato, allora restituirà i suoi dati.
     * non restituirà nulla nel caso in cui l'utente non è presente.
     * 
     * @param cognome il cognome dell'utente da ricercare.
     * @return i dati dell'utente se è presente nella lista utenti.
     *         null se l'utente non è stato trovato.
     */
    public Utente ricercaC(String cognome){
        //da implementare
        return null;
    }
    
     /**
     * @brief Ricerca di un utente per cognome.
     * Ricerca l'utente che ha per cognome quello inserito.
     * se l'utente è presente, è stato precedentemente registrato, allora restituirà i suoi dati.
     * non restituirà nulla nel caso in cui l'utente non è presente.
     * 
     * @param matricola la matricola dell'utente da ricercare.
     * @return i dati dell'utente se è presente nella lista utenti.
     *         null se l'utente non è stato trovato.
     */
    public Utente ricercaM(String matricola){
        //da implementare
        return null;
        
    }


     /**
     * @brief Restituisce una rappresentazione testuale dell'insieme degli Utenti.
     * Il metodo crea una stringa contenente la lista di tutti gli utenti presenti nell'anagrafica.
     * Ogni elemento su una nuova linea.
     * Per ogni elemento viene utilizzato il metodo toString() della classe Utente.
     * 
     * @return Stringa che contiene tutti gli utenti della struttura dati.
     */
    @Override
    public String toString() {
        //corretto
        StringBuilder s = new StringBuilder();
        for(Utente u : anagrafica){            
            s.append(u.toString()).append("\n");
        }
        return s.toString();
    }
    
    
    
}
