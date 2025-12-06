
package gruppo20.biblioteca.model.Libri;

import gruppo20.biblioteca.controller.ControllerFile;
import gruppo20.biblioteca.model.Gestione;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    
    public Libreria(String filePath){
        libreria = new HashSet<>();
        try {
            file = new ControllerFile<>(filePath,libreria, new Libro(null,null,null,0,null));
        } catch (IOException ex) {
            System.out.println("Errore IO apertura libreria");
        }

        
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
    public boolean aggiungi(Libro l2){
        if(libreria.contains(l2)){
            for(Libro l1 : libreria){ //da rifare con iterator?
                if(l2.equals(l1)){
                    l2.setNCopie(l2.getNCopie()+l1.getNCopie());
                    try{
                        file.elimina(l1);
                        file.aggiungi(l2);
                        }
                    catch(IOException e){System.out.println("Errore IO aggiunta libro già presente");}
    
                    return this.modifica(l1, l2);
                }
            }
            
        }
        else{
            try {
                file.aggiungi(l2);
            } catch (IOException ex) {
                System.out.println("Errore IO aggiunta libro non presente");;
            }
            return libreria.add(l2);
        }
        return false;
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
        if (libreria.remove(l)) {
            try {
                file.elimina(l);
            } catch (IOException ex) {
                System.out.println("Errore IO elimina libro");
            }
            return true;
        }
        return false;
    }
    
       /**
     * @brief Modifica Libro.
     * Se il libro è presente effettua la modifica.
     * 
     * Parametro in ingresso:
     *  @param l1 libro originale da modificare.
     *  @param l2 libro aggiornato da inserire.
     * 
     *  @return restituisce true se la modifica del libro è avvenuta correttamente.
     *          false se il libro non è presente.
     */
    
    public boolean modifica(Libro l1, Libro l2){
        
        if(libreria.contains(l1)){
            try {
                file.modifica(l1, l2);
            } catch (IOException ex) {
                System.out.println("Errore IO modifica libro");
            }
            libreria.remove(l1);
            return libreria.add(l2);
        }
        return false;
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
        StringBuilder builder = new StringBuilder();
        
        for(Libro l : libreria){
            builder.append(l+"\n");
        }
        return builder.toString();
    }
}
