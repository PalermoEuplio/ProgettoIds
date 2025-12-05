
package gruppo20.biblioteca.model;

/**
 * @brief Questo file contiene l'implementazione dell'interfaccia Gestione.
 * @author Gruppo20
 */
public interface Gestione<T> {
    boolean aggiungi(T o);
    boolean elimina(T o); 
}
