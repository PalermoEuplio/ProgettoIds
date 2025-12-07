
package gruppo20.biblioteca.model.Utility;

import java.io.IOException;
import java.util.HashSet;

/**
 * @brief Questo file contiene l'implementazione della superclasse GestioneSet.
 * @author Gruppo20
 */
public class GestioneSet<T extends FileFormat> {
    
    /**
     * @brief Aggiungi oggetto.
     * Se l'oggetto è presente non effettua modifiche.
     * Se non è presente lo aggiuge.
     * 
     * Parametro in ingresso:
     *  @param file controller del file in cui aggiungere l'oggetto.
     *  @param set HashSet in cui aggiungere l'oggetto.
     *  @param o2 oggetto da aggiungere.
     * 
     *  @return restituisce true se l'aggiunta è avvenuta correttamente.
     *          false se l'oggetto è già contenuto.
     */
    public boolean aggiungi(ControllerFile<T> file,HashSet<T> set,T o2){
        if(set.contains(o2)) return false;

        try {
            file.aggiungi(o2);
        } catch (IOException ex) {
            System.out.println("Errore IO aggiunta "+o2);
        }
        return set.add(o2);
    }
    
    /**
     * @brief Elimina oggetto.
     * Se l'oggetto è presente lo elimina.
     * 
     * Parametro in ingresso:
     *  @param file controller del file in cui eliminare l'oggetto.
     *  @param set HashSet in cui eliminare l'oggetto.
     *  @param o oggetto da eliminare.
     * 
     *  @return restituisce true se l'eliminazione è avvenuta correttamente.
     *          false se l'oggetto non è già contenuto.
     */
    public boolean elimina(ControllerFile<T> file,HashSet<T> set,T o){
        if (set.remove(o)) {
            try {
                file.elimina(o);
            } catch (IOException ex) {
                System.out.println("Errore IO elimina "+o);
            }
            return true;
        }
        return false;
    }
    /**
     * @brief Modifica oggetto.
     * Se l'oggetto è presente lo elimina.
     * 
     * Parametro in ingresso:
     *  @param file controller del file in cui modificare l'oggetto.
     *  @param set HashSet in cui modificare l'oggetto.
     *  @param o1 oggetto pre-modifica.
     *  @param o2 oggetto post-modifica.
     * 
     *  @return restituisce true se la modifica è avvenuta correttamente.
     *          false se l'oggetto non è già contenuto.
     */
    public boolean modifica(ControllerFile<T> file,HashSet<T> set,T o1, T o2){
        if(set.contains(o1)){
            try {
                file.modifica(o1, o2);
            } catch (IOException ex) {
                System.out.println("Errore IO modifica "+ o1);
            }
            set.remove(o1);
            return set.add(o2);
        }
        return false;
    }
    
    
    
}
