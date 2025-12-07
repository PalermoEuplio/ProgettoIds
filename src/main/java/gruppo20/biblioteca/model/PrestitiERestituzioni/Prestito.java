
package gruppo20.biblioteca.model.PrestitiERestituzioni;
import gruppo20.biblioteca.model.FileFormat;
import gruppo20.biblioteca.model.Libri.Libro;
import gruppo20.biblioteca.model.Utenti.Utente;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
/**
 * @brief Questo file contiene l'implementazione dell'oggetto Libro.
 * @author Gruppo20
 */
public class Prestito implements Comparable<Prestito>,FileFormat<Prestito>{
    private final LocalDate dataPrestito;
    private final static int periodoPrestito = 1; //quanto tempo deve durare il prestito
    private Restituzione restituzione;
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
        this.restituzione = new Restituzione();
        this.dataPrestito = dataPrestito;
        this.libroPrestato = libroPrestato;
        this.utente = utente;
    }
    //set per inserire la data di restituzione effettiva
    public void setRestituzione(LocalDate dataRestituzione) {
        restituzione.setRestituzione(dataRestituzione);
    }
    
    public LocalDate getDataPrestito() {
        return dataPrestito;
    }

    public LocalDate getRestituzione(){
        return restituzione.getRestituzione();

    }
    
    public Libro getLibroPrestato() {
        return libroPrestato;
    }

    public Utente getUtente() {
        return utente;
    }
    //restituisce se è in ritardo
    public boolean isRitardo(){
        return dataPrestito.plusMonths(periodoPrestito).isBefore(LocalDate.now());
    }
    //restituisce di quanto è in ritardo, da richiamare solo dopo aver certificato il ritardo con isRitardo
    public int calcRitardo(){
        return (int) ChronoUnit.DAYS.between(dataPrestito.plusMonths(periodoPrestito),LocalDate.now());
    }
    
    @Override
    public String fileFormat(){
        StringBuilder builder = new StringBuilder();
        builder.append(dataPrestito+"§§"); 
        if(restituzione.isRestituito()) builder.append(restituzione.getRestituzione()+"§§"+libroPrestato+"§§"+utente);
        else builder.append("false"+"§§"+libroPrestato+"§§"+utente);
        return builder.toString();
    }
    
    @Override
    public Prestito deFileFormat(String record){
        Libro l = new Libro(null,null,null,0,null);
        Utente u = new Utente(null,null,null,null);
        
        String[] parts = record.split("§§");
        
        Prestito p = new Prestito(LocalDate.parse(parts[0]),l.deFileFormat(parts[2]),u.deFileFormat(parts[3]));
        if("false".equalsIgnoreCase(parts[1])) return p;
        p.setRestituzione(LocalDate.parse(parts[1]));
        return p;

    
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
    
    @Override
    public int hashCode(){
        int h = 17;
        h = h * 31 + dataPrestito.hashCode();
        h = h * 31 + libroPrestato.hashCode();
        h = h * 31 + utente.hashCode();
        return h;
    }
    

}
