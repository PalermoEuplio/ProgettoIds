package gruppo20.biblioteca.model.Prestiti;

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
    
    public Restituzione(String s){
        if(s.equals("false")) dataRestituzione = null;
        else {
            restituito = true;
            dataRestituzione = LocalDate.parse(s);
        }
    }
    
    public boolean isRestituito() {
        return restituito;
    }

    public String getRestituzione() throws IllegalStateException{
        if(restituito)
        return dataRestituzione.toString();
        else return "false";
    }

    public void setRestituzione(LocalDate dataRestituzione) {
        restituito = true;
        this.dataRestituzione = dataRestituzione;
    }
    
}
