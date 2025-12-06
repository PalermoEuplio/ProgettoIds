
package gruppo20.biblioteca.model.Libri;

import gruppo20.biblioteca.controller.ControllerFile;
import gruppo20.biblioteca.model.Gestione;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
/**
 * @brief Questo file contiene l'implementazione della classe Libreria.
 * @author Gruppo20
 */
public class Libreria implements Gestione<Libro> {
    /**
     * @brief Insieme dei libri presenti in libreria.
     * Si utilizza un HashSet per garantire l'unicità dei libri.
     */
    private HashSet<Libro> libreria; 
    /**
     * @brief Controller per la gestione del file associato ai libri.
     */
    private ControllerFile<Libro> file;
    
    
    public Libreria(String filePath) throws IOException{
        libreria = new HashSet<>();
        file = new ControllerFile<>(filePath,libreria, new Libro(null,null,null,0,null));

        
    }
   /**
    *@brief Aggiunge un libro alla libreria.
    *Se il libro non è presente nella libreria, lo aggiunge.
    * Nel caso in cui il libro è già presente, si andrà a modificare il numero di copie.
    * 
    * Parametro in ingresso:
    *   @param l libro da aggiungere alla libreria.
    * 
    *   @return restituisce true se il libro è stato inserito. 
    *           false se invece non è stato inserito. 
    */  
    @Override
    public boolean aggiungi(Libro l2) throws IOException{
        if(libreria.contains(l2)){
            for(Libro l1 : libreria){
                if(l2.equals(l1)){
                    l2.setNCopie(l2.getNCopie()+l1.getNCopie());
                    file.aggiungi(l2);
                    return this.modifica(l1, l2);
                }
            }
            
        }
        
        return libreria.add(l2);
    }
    
       /**
     * @brief Elimina Libro.
     * Se il libro è presente, effettua la sua eliminazione.
     * 
     * Parametro in ingresso:
     *  @param l libro da eliminare.
     * 
     *  @return restituisce true se l'eliminazione è avvenuta correttamente.
     *          false se il libro non è presente.
     */
    
    
    @Override
    public boolean elimina(Libro l) throws IOException{
        file.elimina(l);
        return libreria.remove(l);
    }
    
       /**
     * @brief Modifica Libro.
     * Se il libro è presente effettua la modifica di uno o più suoi dati.
     * 
     * Parametro in ingresso:
     *  @param l1 libro originale da modificare.
     *  @param l2 libro aggiornato da inserire nell'albero
     * 
     *  @return restituisce true se la modifica del libro è avvenuta correttamente.
     *          false se il libro non è presente.
     */
    
    public boolean modifica(Libro l1, Libro l2)throws IOException{
        
        if(libreria.contains(l1)){
            file.modifica(l1, l2);
            libreria.add(l2);
            libreria.remove(l1);
            return true;
        }
        else return false;
    }   
    
    /**
     * @brief Restituisce una rappresentazione testuale della libreria.
     * Il metodo crea una stringa contenente la lista di tutti i libri della libreria.
     * Ogni elemento su una nuova linea.
     * Per ogni elemento viene utilizzato il metodo toString() della classe Libro.
     * 
     * @return Stringa che contiene tutti i libri della libreria.
     */
    @Override
    public String toString(){
        StringBuilder buffer = new StringBuilder();
        
        for(Libro l : libreria){
            buffer.append("\n"+l);
        }
        return buffer.toString();
    }
}
