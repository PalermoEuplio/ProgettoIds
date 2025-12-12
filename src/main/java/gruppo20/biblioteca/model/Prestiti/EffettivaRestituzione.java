package gruppo20.biblioteca.model.Prestiti;

import java.time.LocalDate;

/**
 * @brief Questo file contiene l'implementazione della classe Restituzione.
 * @author Gruppo20
 */
public class EffettivaRestituzione {

    /**
     * @brief Indica se il libro è stato restituito. Valore booleano che assume
     * true se la restituzione del libro è avvenuta, falso in caso contrario.
     */
    private boolean restituito = false;
    /**
     * @brief Data effettiva di restituzione. 
     * Viene impostata quando il libro è stato restituito.
     * Se ancora non avviene la restituzione il valore del campo è null.
     */
    private LocalDate dataEffettivaRestituzione;

    /**
     * @brief Costruttore dell'oggetto restituzione.
     *
     * @param s stringa che indica la data effettiva di restituzione. 
     * Se s è false il libro non è stato restituito.
     */
    public EffettivaRestituzione(String s) {
        if (s.equals("false")) {
            dataEffettivaRestituzione = null;
        } 
        else {
            restituito = true;
            dataEffettivaRestituzione = LocalDate.parse(s);
        }
    }

    /**
     * @brief Setter della data di restituzione. Aggiorna anche lo stato
     * restituito in true.
     *
     * @param dataRestituzione data effettiva della restituzione.
     */
    public void setEffettivaRestituzione(LocalDate dataRestituzione) {
        restituito = true;
        this.dataEffettivaRestituzione = dataRestituzione;
    }

    /**
     * @brief Indica se il libro è stato restituito
     *
     * @return true se il libro è stato restituito. false altrimenti.
     */
    public boolean isRestituito() {
        return restituito;
    }

    /**
     * @return Resrtituisce la data di restituzione. La stringa false se non è
     * stato restituito
     */
    public String getEffettivaRestituzione() {
        if (restituito) {
            return dataEffettivaRestituzione.toString();
        } 
        else {
            return "false";
        }
    }

}
