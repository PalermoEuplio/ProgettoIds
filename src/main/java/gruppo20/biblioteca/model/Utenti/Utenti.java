package gruppo20.biblioteca.model.Utenti;
import gruppo20.biblioteca.model.Utility.ControllerFile;
import gruppo20.biblioteca.model.Utility.GestioneSet;
import java.io.IOException;
import java.util.HashSet;

/**
 * @brief Questo file contiene l'implementazione della classe Utenza.
 * @author Gruppo20
 */

public class Utenti extends GestioneSet<Utente>{
    /**
     * @brief Insieme degli utenti presenti nel sistema.
     * Si utilizza un HashSet per garantire l'unicità degli utenti.
     */
    private HashSet<Utente> listUtenti;
    
    /**
     * @brief Controller per la gestione del file associato ai libri.
     */
    private ControllerFile<Utente> file;
    
    /**
     * Costruttore gestione utenti
     * Iniziallizza la struttura
     */
    
    public Utenti(String filePath) {
        this.listUtenti = new HashSet<>();
        try {
            file = new ControllerFile<>(filePath,listUtenti, new Utente(null,null,null,null,0));
        } catch (IOException ex) {
            System.out.println("Errore IO apertura lista utenti");
        }
    }

    public HashSet<Utente> getListUtenti() {
        return listUtenti;
    }

    
    
    /**
    *Aggiunge un utente all'anagrafica.
    * 
    * Parametro in ingresso:
    *   @param u utente da aggiungere all'listUtenti.
    * 
    *   @return restituisce true se l'utente è stato inserito. 
           false se invece non è stato inserito o è già presente in listUtenti. 
    */  
    public boolean aggiungi(Utente u){
        return super.aggiungi(file, listUtenti, u);
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
        return super.elimina(file, listUtenti, u);
    }
    
     /**
     * @brief Modifica l'utente.
     * Se l'utente è presente effettua la modifica di uno o più suoi dati.
     * 
     * Parametro in ingresso:
     *  @param u1 utente da modificare
     *  @param u2 utente con modifiche
     * 
     *  @return restituisce true se la modifica dell'utente è avvenuta correttamente.
     *          false se l'utente non è presente.
     */
    public boolean modifica(Utente u1,Utente u2){
        return super.modifica(file, listUtenti, u1, u2);
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
        for(Utente u : listUtenti){            
            s.append(u.toString()).append("\n");
        }
        return s.toString();
    }
    
    
    
}
