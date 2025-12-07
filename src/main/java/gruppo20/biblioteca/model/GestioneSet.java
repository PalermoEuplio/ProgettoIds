
package gruppo20.biblioteca.model;

import gruppo20.biblioteca.controller.ControllerFile;
import gruppo20.biblioteca.model.Libri.Libro;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @brief Questo file contiene l'implementazione dell'interfaccia Gestione.
 * @author Gruppo20
 */
public class GestioneSet<T extends FileFormat> {
    
    public boolean aggiungi(ControllerFile<T> file,HashSet<T> set,T o2){
        if(set.contains(o2)){
            if(o2 instanceof Libro){
                Iterator<T> it = set.iterator();
                while(it.hasNext()){
                    T o1 = it.next();
                    if(o1.equals(o2)){
                        try{
                            file.elimina(o1);
                            file.aggiungi(o2);
                            }
                        catch(IOException e){System.out.println("Errore IO aggiunta elemento"+o2);}

                        return this.modifica(file,set,o1, o2);
                    }
                }
            }
            else return false;
        }
        else{
            try {
                file.aggiungi(o2);
            } catch (IOException ex) {
                System.out.println("Errore IO aggiunta "+o2);
            }
            return set.add(o2);
        }
        return false;
    }
    
    
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
