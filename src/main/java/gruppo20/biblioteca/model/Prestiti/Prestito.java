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
     */
    private final LocalDate dataPrestito; 
    
    /**
     * @brief Durata del prestito.
     * Indica per quanto tempo il prestito non è in ritardo.
     */
    private final int periodoPrestito;
    
    /**
     * @brief Oggetto che rappresenta lo stato della restituzione del prestito.
     * Informazioni sulla data di restituzione, se è già avvenuta o meno.
     */
    private final Restituzione restituzione;
    
    /**
     * @brief Isbn del libro oggetto del prestito.
     * Ogni prestito è associato ad un unico libro.
     */
    private final String isbn;
    
    /**
     * @brief titolo del libro oggetto del prestito.
     * Ogni prestito è associato ad un unico libro.
     */
    private final String titoloLibro;
    
    /**
     * @brief Matricola utente che effettua il prestito.
     * Ogni prestito è associato ad un unico utente.
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
    

    /**
     * @brief Restituisce se il prestito è in ritardo.
     * 
     * @return true se è in ritardo.
     * false altrimenti.
     */
    public boolean isRitardo(){
        return dataPrestito.plusDays(periodoPrestito).isBefore(LocalDate.now());
    }
    
    /**
     * @brief Se è in ritardo restituisce quanti giorni il prestito è in ritardo.
     * 
     * @return numero di giorni di ritardo.
     * 0 se non in ritardo.
     */
    public int calcRitardo(){
        if(!isRitardo()) return 0;
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
    
    /**
     * @brief implementazione del metodo hashCode
     */
    @Override
    public int hashCode(){
        int h = 17;
        h = h * 31 + dataPrestito.hashCode();
        h = h * 31 + isbn.hashCode();
        h = h * 31 + matricola.hashCode();
        return h;
    }
    

}
