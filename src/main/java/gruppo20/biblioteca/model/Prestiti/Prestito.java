
package gruppo20.biblioteca.model.Prestiti;
import gruppo20.biblioteca.model.FileFormat;
import gruppo20.biblioteca.model.Libri.Libro;
import gruppo20.biblioteca.model.Utenti.Utente;
import java.time.LocalDate;
import java.time.Month;
/**
 * @brief Questo file contiene l'implementazione dell'oggetto Libro.
 * @author Gruppo20
 */
public class Prestito implements Comparable<Prestito>,FileFormat<Prestito>{
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
    public Prestito(LocalDate dataPrestito,LocalDate dataEffettivaRestituzione, Libro libroPrestato, Utente utente){
        
        if (dataEffettivaRestituzione == null || dataEffettivaRestituzione.equals(LocalDate.of(0, 1, 1))) { 
            this.dataEffettivaRestituzione = LocalDate.of(0, 1, 1);
        } 
        else {
            this.dataEffettivaRestituzione = dataEffettivaRestituzione;
        }
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
    public LocalDate getDataEffettivaRestituzione() throws IllegalStateException{
        if(!dataEffettivaRestituzione.equals(LocalDate.of(0000,01,01))) return dataEffettivaRestituzione;
        else throw new IllegalStateException("Errore, libro non ancora restituito");
    }
    
    public Libro getLibroPrestato() {
        return libroPrestato;
    }

    public Utente getUtente() {
        return utente;
    }
    
    @Override
    public String fileFormat(){
        StringBuilder builder = new StringBuilder();
        builder.append(dataPrestito+"§§"+dataEffettivaRestituzione+"§§"+libroPrestato+"§§"+utente);
        return builder.toString();
    }
    
    @Override
    public Prestito deFileFormat(String record){
        Libro l = new Libro(null,null,null,0,null);
        Utente u = new Utente(null,null,null,null);
        String[] parts = record.split("§§");
        return new Prestito(LocalDate.parse(parts[0]),LocalDate.parse(parts[1]),l.deFileFormat(parts[2]),u.deFileFormat(parts[3]));
    
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
