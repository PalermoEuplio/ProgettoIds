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
    
    /**
     * @brief Costruttore dell'oggetto restituzione.
     * 
     * @param s stringa che indica la data effettiva di restituzione.
     * Se s è false il libro non è stato restituito.
     */
    public Restituzione(String s){
        if(s.equals("false")) dataRestituzione = null;
        else {
            restituito = true;
            dataRestituzione = LocalDate.parse(s);
        }
    }
    
    /**
     * @brief Indica se il libro è stato restituito
     * 
     * @return true se il libro 
     */
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
