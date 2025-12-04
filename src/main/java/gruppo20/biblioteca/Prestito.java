
package gruppo20.biblioteca;
import java.time.LocalDate;
/**
 * @brief Questo file contiene l'implementazione dell'oggetto Libro.
 * @author Gruppo20
 */
public class Prestito {
    private final LocalDate dataPrestito;
    private final LocalDate dataRestituzione;
    private LocalDate dataEffettivaRestituzione;
    private final Libro libroPrestato; 
    private final Utente utente;
    //inizializza un prestito, possibiità di sceglierne la durata
    //WARNING: LocalDate arrotonda, se si arriva al 31 febbraio potrebbe ad esempio arrotondare per difetto al 28
    //scegliere le opzioni da presentare sull interfaccia
    
    /**
     * Costruttore Prestito
     * Parametri in ingresso 
     *  @param dataPrestito la data in cui viene inserito il prestito.
     * @param libroPrestato le informazioni del libro preso in prestito.
     * @param utente le informazioni dell'utente che ha effettuato il prestito.
    */
    public Prestito(LocalDate dataPrestito, Libro libroPrestato, Utente utente){
        this.dataEffettivaRestituzione = null;
        this.dataPrestito = dataPrestito;
        this.dataRestituzione = dataPrestito.plusMonths(1);
        this.libroPrestato = libroPrestato;
        this.utente = utente;
    }
    //set per inserire la data di restituzione effettiva
    public void setDataEffettivaRestituzione(LocalDate dataEffettivaRestituzione) {
        this.dataEffettivaRestituzione = dataEffettivaRestituzione;
    }
    
    public LocalDate getDataPrestito() {
        return dataPrestito;
    }

    public LocalDate getDataRestituzione() {
        return dataRestituzione;
    }
    //controlla se il libro è stato già consegnato, in caso lo sia restituisce il quando
    public String getDataEffettivaRestituzione() {
        if(dataEffettivaRestituzione!=null) return dataEffettivaRestituzione.toString();
        else return "Non ancora restituito";
    }
    
    public Libro getLibroPrestato() {
        return libroPrestato;
    }

    public Utente getUtente() {
        return utente;
    }
    //TODO: implementare interfaccia comparable

}
