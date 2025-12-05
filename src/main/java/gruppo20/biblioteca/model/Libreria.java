
package gruppo20.biblioteca.model;

import java.util.*;
/**
 * @brief Questo file contiene l'implementazione della classe Libreria.
 * @author Gruppo20
 */
public class Libreria implements Comparable<Libro>,Gestione<Libro> {
    
    
    private TreeSet<Libro> libri;
    
    public Libreria(){
        libri = new TreeSet<>();
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
        
    }
    
       /**
     * @brief Modifica Libro.
     * Se il libro è presente effettua la modifica di uno o più suoi dati.
     * 
     * Parametro in ingresso:
     *  @param l libro da modificare.
     * 
     *  @return restituisce true se la modifica del libro è avvenuta correttamente.
     *          false se il libro non è presente.
     */
    
    public boolean modifica(Libro l){
        
    }   
    
    /**
     * @brief Ricerca di un libro per titolo.
     * Ricerca il libro che ha per titolo quello inserito.
     * se il libro è presente in libreria allora restituirà i suoi dati.
     * non restituirà nulla nel caso in cui il libro non è presente in libreria.
     * 
     * @param titolo il titolo del libro da ricercare.
     * @return i dati del libro se è presente nella libreria.
     *         null se il libro non è stato trovato.
     */
    public Libro ricerca(String titolo){
        
    }
    
    /**
     * @brief Ricerca di un libro per autori.
     * Ricerca il libro che ha per autori quelli inseriti.
     * se il libro è presente in libreria allora restituirà i suoi dati.
     * non restituirà nulla nel caso in cui il libro non è presente in libreria.
     * 
     * @param autori gli autori del libro da ricercare.
     * @return i dati del libro se è presente nella libreria.
     *         null se il libro non è stato trovato.
     */
    public Libro ricerca(String[] autori){
        
    }
    
    /**
     * @brief Ricerca di un libro per isbn.
     * Ricerca il libro che ha per isbn quello inserito.
     * se il libro è presente in libreria allora restituirà i suoi dati.
     * non restituirà nulla nel caso in cui il libro non è presente in libreria.
     * 
     * @param isbn il codice identificativo isbn del libro da ricercare.
     * @return i dati del libro se è presente nella libreria.
     *         null se il libro non è stato trovato.
     */
    public Libro ricerca(int isbn){
        
    }
    
    @Override
    public int compareTo(Libro l){
        
    }
    
    @Override
    public String toString(){
        
    }
    
    
    
}
