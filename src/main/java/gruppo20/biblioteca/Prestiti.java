
package gruppo20.biblioteca;
import java.util.*;
/**
 * @brief Questo file contiene l'implementazione della classe Prestiti.
 * @author Gruppo20
 */
public class Prestiti {
    private TreeSet<Prestito> Prestiti;
   
    public Prestiti(){
        this.Prestiti = new TreeSet<>();
    }
    
    //restituisce direttamente una stringa con la libreria
    public String getPrestiti() {
        return Prestiti.toString();//fare override toString
    }
    
    /**
    *@brief Aggiunge un prestito.
    * 
    * Parametro in ingresso:
    *   @param p prestito da dover aggiungere.
    * 
    *   @return restituisce true se il prestito è stato inserito. 
    *           false se invece non è stato inserito o è già presente. 
    */  
    public boolean aggiungiPrestito (Prestito p){
        return Prestiti.add(p);
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
    public boolean eliminaPrestito (Prestito p){
        return Prestiti.remove(p);
    }

    public boolean hasPrestito (Prestito p){
        return Prestiti.contains(p);
    }
      
}
