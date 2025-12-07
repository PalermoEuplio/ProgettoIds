/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gruppo20.biblioteca.model.PrestitiERestituzioni;

import java.time.LocalDate;

/**
 * @brief Questo file contiene l'implementazione della classe Restituzione.
 * @author Gruppo20
 */
public class Restituzione {
    /**
     * @brief Indica se il libro è stato restituito.
     * Valore booleano che assume true se la restituzione del libro è avvenuta, 
     * falso in caso contrario.
     */
    private boolean restituito = false;
    /**
     * @brief Data effettiva di restituzione.
     * Viene impostata quando il libro è stato restituito.
     * Se ancora non avviene la restituzione il valore del campo è null.
     */
    private LocalDate dataRestituzione;
    
    public Restituzione(){
        dataRestituzione = null;
    }

    public boolean isRestituito() {
        return restituito;
    }

    public LocalDate getRestituzione() {
        if(restituito)
        return dataRestituzione;
        else throw new IllegalStateException("Libro non ancora restituito");
    }

    public void setRestituzione(LocalDate dataRestituzione) {
        restituito = true;
        this.dataRestituzione = dataRestituzione;
    }
    
}
