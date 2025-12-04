/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gruppo20.biblioteca;

/**
 *
 * @author Osv
 */
public interface Gestione<T> {
    boolean aggiungi(T o);
    boolean elimina(T o);
    boolean modifica(T o);
    
}
