
package gruppo20.biblioteca.model.PrestitiERestituzioni;
import gruppo20.biblioteca.model.Utility.ControllerFile;
import gruppo20.biblioteca.model.Utility.GestioneSet;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
/**
 * @brief Questo file contiene l'implementazione della classe Prestiti.
 * @author Gruppo20
 */
public class Prestiti extends GestioneSet<Prestito> {
    /**
     * @brief Numero massimo di prestiti attivi che un utente può avere contemporaneamente.
     */
    private static final int maxPrestiti = 3; 
      /**
     * @brief Insieme dei prestiti attivi.
     * Si utilizza un HashSet per garantire l'unicità dei prestiti.
     */
    private HashSet<Prestito> listPrestiti;
     /**
     * @brief Controller per la gestione della lettura e scrittura sul file.
     */
    private ControllerFile<Prestito> file;
   
    
    public Prestiti(String filePath){
        this.listPrestiti = new HashSet<>();
        try {
            file = new ControllerFile<>(filePath,listPrestiti, new Prestito(null,null,null));
        } catch (IOException ex) {
            System.out.println("Errore IO apertura libreria");
        }
    }
    
    /**
     * @brief Registra la restituzione di un prestito.
     * Il metodo cerca il prestito, se lo trova imposta la data di restituzione a quella inserita.
     * Il numero di prestiti relativo all'utente della restituzione viene decrementato di uno.
     * Se non viene trovato il prestito ritorna false.
     *
     * @param p Prestito da restituire.
     * @param dataRestituzione Data di restituzione.
     * @return true se la restituzione è avvenuta, false in caso contrario.
     */
    public boolean restituisci(Prestito p,LocalDate dataRestituzione){
        if(listPrestiti.contains(p)){
            Iterator<Prestito> it = listPrestiti.iterator();
            while(it.hasNext()){
                Prestito pAp = it.next(); //variabile di appoggio
                if(p.equals(pAp)){
                    p.setRestituzione(dataRestituzione);
                    p.getUtente().setnPrestiti(p.getUtente().getnPrestiti()-1);
                    return modifica(pAp, p);
                }
            }
            
        }
        return false;
    }
    
    /**
    *@brief Aggiunge un prestito.
    * Il metodo controlla che l'utente non abbia più prestiti di quelli consentiti.
    * 
    * Parametro in ingresso:
    *   @param p prestito da dover aggiungere.
    * 
    *   @return restituisce true se il prestito è stato inserito. 
    *           false se invece non è stato inserito, è già presente o l'utente ha troppi prestiti. 
    */ 

    public boolean aggiungi (Prestito p){
        if(!listPrestiti.contains(p)){
            return super.aggiungi(file, listPrestiti, p);
            
        }
        return false;
        
    }
        
    
    /**
    *@brief elimina un prestito.
    *Se il prestito esiste, ne effettua l'eliminazione.
    * 
    * Parametro in ingresso:
    *   @param p prestito da dover eliminare.
    * 
    *   @return restituisce true se il prestito è stato eliminato. 
    *           false se invece non è presente.
    */ 

    public boolean elimina (Prestito p){
        return super.elimina(file, listPrestiti, p);
        
    }
    
    /**
     * @brief Modifica del prestito.
     * Se il prestito è presente effettua la modifica di uno o più suoi dati.
     * 
     * Parametro in ingresso:
     *  @param p1 Prestito da modificare.
     *  @param p2 Prestito modificato.
     * 
     *  @return restituisce true se la modifica del prestito è avvenuta correttamente.
     *          false se il prestito non è presente.
     */
    public boolean modifica(Prestito p1, Prestito p2){
        return super.modifica(file, listPrestiti, p1, p2);
    }
   /**
    *@brief Controlla se un prestio è già presente.
    *
    *   @param p prestito da dover controllare.
    * 
    *   @return restituisce true se il prestito è già presente. 
    *           false se invece non è presente.
    */
    public boolean hasPrestito (Prestito p){
        return listPrestiti.contains(p);
    }
      
}
