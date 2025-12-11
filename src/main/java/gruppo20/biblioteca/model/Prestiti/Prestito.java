package gruppo20.biblioteca.model.Prestiti;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @brief Questo file contiene l'implementazione dell'oggetto Prestito.
 * @author Gruppo20
 */
public class Prestito {

    /**
     * @brief Data in cui è stato effettuato il prestito.
     */
    private final LocalDate dataPrestito;

    /**
     * @brief Data di prevista restituzione.
     */    
    private final LocalDate dataPrevistaRestituzione;

    /**
     * @brief Oggetto che rappresenta lo stato della restituzione del prestito.
     * Informazioni sulla data di restituzione, se è già avvenuta o meno.
     */
    private final EffettivaRestituzione effettivaRestituzione;

    /**
     * @brief Isbn del libro oggetto del prestito. Ogni prestito è associato ad
     * un unico libro.
     */
    private final String isbn;

    /**
     * @brief titolo del libro oggetto del prestito. Ogni prestito è associato
     * ad un unico libro.
     */
    private final String titoloLibro;

    /**
     * @brief Matricola utente che effettua il prestito. Ogni prestito è
     * associato ad un unico utente.
     */
    private final String matricola;

    public Prestito(LocalDate dataPrestito, LocalDate dataPrevistaRestituzione, String effettivaRestituzione, String titoloLibro, String isbn, String matricola) {
        this.effettivaRestituzione = new EffettivaRestituzione(effettivaRestituzione);
        this.dataPrestito = dataPrestito;
        this.dataPrevistaRestituzione = dataPrevistaRestituzione;
        this.titoloLibro = titoloLibro;
        this.isbn = isbn;
        this.matricola = matricola;
    }

    //set per inserire la data di effettiva EffettivaRestituzione
    public void setEffettivaRestituzione(LocalDate dataRestituzione) {
        effettivaRestituzione.setEffettivaRestituzione(dataRestituzione);
    }

    public LocalDate getDataPrevistaRestituzione() {
        return dataPrevistaRestituzione;
    }
    
    public LocalDate getDataPrestito() {
        return dataPrestito;
    }

    public String getEffettivaRestituzione() {
        return effettivaRestituzione.getEffettivaRestituzione();

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
     * @return true se è in ritardo. false altrimenti.
     */
    public boolean isRitardo() {
        return dataPrevistaRestituzione.isBefore(LocalDate.now());
    }

    /**
     * @brief Se è in ritardo restituisce quanti giorni il prestito è in
     * ritardo.
     *
     * @return numero di giorni di ritardo. 0 se non in ritardo.
     */
    public int calcRitardo() {
        if (!isRitardo()) {
            return 0;
        }
        if(effettivaRestituzione.isRestituito()) return (int) ChronoUnit.DAYS.between(dataPrevistaRestituzione, LocalDate.parse(effettivaRestituzione.getEffettivaRestituzione()));
        return (int) ChronoUnit.DAYS.between(dataPrevistaRestituzione, LocalDate.now());
    }

    /**
     * @brief Verifica l'uguaglianza tra due prestiti. Due libri sono
     * considerati uguali se hanno la stessa data del prestito, lo stesso libro
     * prestato e lo stesso utente.
     *
     * @param o Oggetto da confrontare.
     * @return true se i prestiti sono uguali, false in caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Prestito)) {
            return false;
        }
        Prestito p = (Prestito) o;
        return dataPrestito.equals(p.getDataPrestito()) && isbn.equals(p.getIsbn()) && matricola.equals(p.getMatricola());
    }

    /**
     * @brief implementazione del metodo hashCode
     */
    @Override
    public int hashCode() {
        int h = 17;
        h = h * 31 + dataPrestito.hashCode();
        h = h * 31 + isbn.hashCode();
        h = h * 31 + matricola.hashCode();
        return h;
    }

}
