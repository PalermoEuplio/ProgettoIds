
package gruppo20.biblioteca.model;
import java.time.LocalDate;
/**
 * @brief Questo file contiene l'implementazione dell'oggetto Libro.
 * @author Gruppo20
 */
public class Prestito implements Comparable<Prestito>{
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private final LocalDate dataPrestito;
    private final static int tempoPrestito = 1; //quanto tempo deve durare il prestito
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
        return dataPrestito.plusMonths(tempoPrestito);
    }
    //controlla se il libro è stato già consegnato, in caso lo sia restituisce il quando
    public LocalDate getDataEffettivaRestituzione() throws NullPointerException{
        if(dataEffettivaRestituzione!=null) return dataEffettivaRestituzione;
        else throw new NullPointerException("Non ancora restituito");
    }
    
    public Libro getLibroPrestato() {
        return libroPrestato;
    }

    public Utente getUtente() {
        return utente;
    }
    
    @Override
    public boolean equals(Object o){
        if(o==null || !(o instanceof Prestito))return false;
        Prestito p = (Prestito) o;
        return dataPrestito.equals(p.getDataPrestito()) && libroPrestato.equals(p.getLibroPrestato()) && utente.equals(p.getUtente());
    }
    
    @Override
    public int compareTo(Prestito p){
        if(this.equals(p)) return 0;
        else if (this.dataPrestito.isBefore(p.getDataPrestito())) return -1;
        else return 1;
        
    }

}
