
package gruppo20.biblioteca.model;
import java.util.*;
/**
 * @brief Questo file contiene l'implementazione della classe Prestiti.
 * @author Gruppo20
 */
public class Prestiti implements Gestione<Prestito> {
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
    @Override
    public boolean aggiungi (Prestito p){
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
    @Override
    public boolean elimina (Prestito p){
        return Prestiti.remove(p);
    }

    public boolean hasPrestito (Prestito p){
        return Prestiti.contains(p);
    }
      
}
