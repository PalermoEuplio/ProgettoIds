
package gruppo20.biblioteca.model.Libri;

import gruppo20.biblioteca.model.Gestione;
import java.util.*;
/**
 * @brief Questo file contiene l'implementazione della classe Libreria.
 * @author Gruppo20
 */
public class Libreria implements Gestione<Libro> {
    private HashSet<Libro> libreria;
    private String nome;
    
    public Libreria(){
        libreria = new HashSet<>();
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
    public boolean aggiungi(Libro l){
        return libreria.add(l);
        
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
    public boolean elimina(Libro l){
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
    
    public boolean modifica(Libro l1, Libro l2){
        
        if(libreria.contains(l1)){
            libreria.add(l2);
            libreria.remove(l1);
            return true;
        }
        else return false;
    }   
    
    @Override
    public String toString(){
        StringBuilder buffer = new StringBuilder();
        
        for(Libro l : libreria){
            buffer.append("\n"+l);
        }
        return buffer.toString();
    }
}
