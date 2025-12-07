/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gruppo20.biblioteca.model.PrestitiERestituzioni;

import java.time.LocalDate;

/**
 *
 * @author Osv
 */
public class Restituzione {
    private boolean restituito = false;
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
