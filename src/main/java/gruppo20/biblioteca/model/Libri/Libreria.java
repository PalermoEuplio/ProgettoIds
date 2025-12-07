
package gruppo20.biblioteca.model.Libri;

import gruppo20.biblioteca.model.Utility.ControllerFile;
import gruppo20.biblioteca.model.Utility.GestioneSet;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
/**
 * @brief Questo file contiene l'implementazione della classe Libreria.
 * @author Gruppo20
 */
public class Libreria extends GestioneSet<Libro> {
    /**
     * @brief Insieme dei libri presenti in libreria.
     * Si utilizza un HashSet per garantire l'unicità dei libri.
     */
    private HashSet<Libro> listLibreria; 
    /**
     * @brief Controller per la gestione del file associato ai libri.
     */
    private ControllerFile<Libro> file;
    
    
    public Libreria(String filePath){
        listLibreria = new HashSet<>();
        try {
            file = new ControllerFile<>(filePath,listLibreria, new Libro(null,null,null,0,null));
        } catch (IOException ex) {
            System.out.println("Errore IO apertura libreria");
        }

        
    }

    public HashSet<Libro> getListLibreria() {
        return listLibreria;
    }
    
    
    
   /**
    *@brief Aggiunge un libro alla libreria.
    *Se il libro non è presente nella libreria, lo aggiunge.
    * Nel caso in cui il libro è già presente, si andrà a modificare il numero di copie.
    * 
    * Parametro in ingresso:
    *   @param l2 libro da aggiungere alla listLibreria.
    * 
    *   @return restituisce true se il libro è stato inserito. 
    *           false se invece non è stato inserito. 
    */  
    public boolean aggiungi(Libro l2){
        if(listLibreria.contains(l2)){
            Iterator<Libro> it = listLibreria.iterator();
            while(it.hasNext()){
                Libro l1 = it.next();
                if(l2.equals(l1)){
                    l2.setNCopie(l2.getNCopie()+l1.getNCopie());
                    return modifica(l1, l2);
                }
            }  
        }
        return super.aggiungi(file, listLibreria, l2);
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
    public boolean elimina(Libro l){
        return super.elimina(file, listLibreria, l);
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
        return super.modifica(file, listLibreria, l1, l2);
    }   
    
    /**
     * @brief Restituisce una rappresentazione testuale della libreria.
     * Il metodo crea una stringa contenente la lista di tutti i libri della libreria.
     * Ogni elemento su una nuova linea.
     * Per ogni elemento viene utilizzato il metodo toString() della classe Libro.
     * 
     * @return Stringa che contiene tutti i libri della listLibreria.
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        
        for(Libro l : listLibreria){
            builder.append(l+"\n");
        }
        return builder.toString();
    }
}
