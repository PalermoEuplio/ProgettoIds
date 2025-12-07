
package gruppo20.biblioteca.model.PrestitiERestituzioni;
import gruppo20.biblioteca.controller.ControllerFile;
import gruppo20.biblioteca.model.GestioneSet;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
/**
 * @brief Questo file contiene l'implementazione della classe Prestiti.
 * @author Gruppo20
 */
public class Prestiti extends GestioneSet<Prestito> {
    private static final int maxPrestiti = 3;
    private HashSet<Prestito> listPrestiti;
    private ControllerFile<Prestito> file;
   
    public Prestiti(String filePath){
        this.listPrestiti = new HashSet<>();
        try {
            file = new ControllerFile<>(filePath,listPrestiti, new Prestito(null,null,null));
        } catch (IOException ex) {
            System.out.println("Errore IO apertura libreria");
        }
    }

    public boolean Restituisci(Prestito p,LocalDate dataRestituzione){
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
    * 
    * Parametro in ingresso:
    *   @param p prestito da dover aggiungere.
    * 
    *   @return restituisce true se il prestito è stato inserito. 
    *           false se invece non è stato inserito, è già presente o l'utente ha troppi prestiti. 
    */ 

    public boolean aggiungi (Prestito p){
        if(p.getUtente().getnPrestiti()< maxPrestiti && !listPrestiti.contains(p)){
            p.getUtente().setnPrestiti(p.getUtente().getnPrestiti()+1);
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
    
    public boolean modifica(Prestito p1, Prestito p2){
        return super.modifica(file, listPrestiti, p1, p2);
    }

    public boolean hasPrestito (Prestito p){
        return listPrestiti.contains(p);
    }
      
}
