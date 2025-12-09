package gruppo20.biblioteca.model.Prestiti;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
/**
 * @brief Questo file contiene l'implementazione dell'oggetto Prestito.
 * @author Gruppo20
 */
public class Prestito{
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
    private int periodoPrestito;
    /**
     * @brief Oggetto che rappresenta lo stato della restituzione del prestito.
     * Informazioni sulla data di restituzione, se è già avvenuta o meno.
     */
    private Restituzione restituzione;
    /**
     * @brief Isbn e titolo del libro oggetto del prestito.
     * Ogni prestito è associato ad un unico libro.
     * Il libro non può essere modificato dopo la creazione dell'oggetto Prestito.
     */
    private final String isbn;
    private String titoloLibro;
    /**
     * @brief Matricola utente che effettua il prestito.
     * Ogni prestito è associato ad un unico utente.
     * la matricola non può essere modificata dopo la creazione dell'oggetto Prestito.
     */
    private final String matricola;
    
    public Prestito(LocalDate dataPrestito,String restituzione,String titoloLibro, String isbn, String matricola, int periodoPrestito){
        this.restituzione = new Restituzione(restituzione);
        this.dataPrestito = dataPrestito;
        this.titoloLibro = titoloLibro;
        this.isbn = isbn;
        this.matricola = matricola;
        this.periodoPrestito = periodoPrestito;
    }
    //set per inserire la data di restituzione effettiva
    public void setRestituzione(LocalDate dataRestituzione) {
        restituzione.setRestituzione(dataRestituzione);
    }
    
    public LocalDate getDataPrestito() {
        return dataPrestito;
    }

    public int getPeriodoPrestito() {
        return periodoPrestito;
    }
    
    

    public String getRestituzione(){
        return restituzione.getRestituzione();

    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitoloLibro() {
        return titoloLibro;
    }

    public String getMatricola() {
        return matricola;
    }
    

    //restituisce se è in ritardo
    public boolean isRitardo(){
        return dataPrestito.plusDays(periodoPrestito).isBefore(LocalDate.now());
    }
    //restituisce di quanto è in ritardo, da richiamare solo dopo aver certificato il ritardo con isRitardo
    public int calcRitardo(){
        return (int) ChronoUnit.DAYS.between(dataPrestito.plusDays(periodoPrestito),LocalDate.now());
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
        return dataPrestito.equals(p.getDataPrestito()) && isbn.equals(p.getIsbn()) && matricola.equals(p.getMatricola());
    }
    
    @Override
    public int hashCode(){
        int h = 17;
        h = h * 31 + dataPrestito.hashCode();
        h = h * 31 + isbn.hashCode();
        h = h * 31 + matricola.hashCode();
        return h;
    }
    

}
