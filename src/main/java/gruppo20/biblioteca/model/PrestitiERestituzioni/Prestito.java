
package gruppo20.biblioteca.model.PrestitiERestituzioni;
import gruppo20.biblioteca.model.Utility.FileFormat;
import gruppo20.biblioteca.model.Libri.Libro;
import gruppo20.biblioteca.model.Utenti.Utente;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
/**
 * @brief Questo file contiene l'implementazione dell'oggetto Prestito.
 * @author Gruppo20
 */
public class Prestito implements FileFormat<Prestito>{
    /**
     * @brief Data in cui è stato effettuato il prestito.
     * Il valore è immutabile e viene impostato con la data al momento della creazione del prestito.
     */
    private final LocalDate dataPrestito; 
    /**
     * @brief Durata del prestito.
     * Indica per quanto tempo il prestito rimane attivo.
     * Il valore di default è di 1 mese.
     */
    private final static int periodoPrestito = 1;
    /**
     * @brief Oggetto che rappresenta lo stato della restituzione del prestito.
     * Informazioni sulla data di restituzione, se è già avvenuta o meno.
     */
    private Restituzione restituzione;
    /**
     * @brief Libro oggetto del prestito.
     * Ogni prestito è associato ad un unico libro.
     * Il libro non può essere modificato dopo la creazione dell'oggetto Prestito.
     */
    private final Libro libroPrestato; 
    /**
     * @brief Utente che effettua il prestito.
     * Ogni prestito è associato ad un unico utente.
     * L'utente non può essere modificato dopo la creazione dell'oggetto Prestito.
     */
    private final Utente utente;
    
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
    
     /**
     * @brief Converte il prestito in una stringa formattata per la memorizzazione sul file.
     * La stringa utilizza '§' come carattere di separazione.
     * 
     * @return Stringa contenente la rappresentazione serializzata del prestito.
     */
    @Override
    public String fileFormat(){
        StringBuilder builder = new StringBuilder();
        builder.append(dataPrestito+"§§"); 
        if(restituzione.isRestituito()) builder.append(restituzione.getRestituzione()+"§§"+libroPrestato.fileFormat()+"§§"+utente.fileFormat());
        else builder.append("false"+"§§"+libroPrestato.fileFormat()+"§§"+utente.fileFormat());
        return builder.toString();
    }
    /**
     * @brief Deformatta, ricostruisce un oggetto Prestito a partire da una stringa formattata.
     * La stringa deve avere formato coerente con quello profotto da fileFormat.
     * 
     * @param record Stringa contenente i campi del prestito serializzati.
     * @return Oggetto Prestito ottenuto dai dati contenuti in record.
     */
    @Override
    public Prestito deFileFormat(String record){
        Libro l = new Libro(null,null,null,0,null);
        Utente u = new Utente(null,null,null,null,0);
        
        String[] parts = record.split("§§");
        
        Prestito p = new Prestito(LocalDate.parse(parts[0]),l.deFileFormat(parts[2]),u.deFileFormat(parts[3]));
        if("false".equalsIgnoreCase(parts[1])) return p;
        p.setRestituzione(LocalDate.parse(parts[1]));
        return p;

    
    }
    
     /**
     * @brief Verifica l'uguaglianza tra due prestiti.
     * Due libri sono considerati uguali se hanno la stessa data del prestito, 
     * lo stesso libro prestato e lo stesso utente.
     * 
     * @param o Oggetto da confrontare.
     * @return true se i prestiti sono uguali, false in caso contrario.
     */
    @Override
    public boolean equals(Object o){
        if(o==null || !(o instanceof Prestito))return false;
        Prestito p = (Prestito) o;
        return dataPrestito.equals(p.getDataPrestito()) && libroPrestato.equals(p.getLibroPrestato()) && utente.equals(p.getUtente());
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
