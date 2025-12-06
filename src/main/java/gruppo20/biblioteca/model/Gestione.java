
package gruppo20.biblioteca.model;

import java.io.IOException;

/**
 * @brief Questo file contiene l'implementazione dell'interfaccia Gestione.
 * @author Gruppo20
 */
public interface Gestione<T> {
    boolean aggiungi(T o) throws IOException;
    boolean elimina(T o) throws IOException; 
    boolean modifica(T o1, T o2) throws IOException;
}
