package gruppo20.biblioteca.model.Libri;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @brief Questo file contiene l'implementazione della classe Libro.
 * @author Gruppo20
 */
public class Libro {

    private String titolo;///< Titolo del libro.
    private ArrayList<Autore> autori;///< Autrori del libro.
    private LocalDate annoPublicazione;///< Anno di publicazione del libro.
    private int nCopie;///< Numero copie del libro disponibili in biblioteca.
    private String isbn;///< Codice identificativo ISBN del libro.

    public Libro(String titolo, String autori, LocalDate annoPublicazione, int nCopie, String isbn) {
        this.autori = new ArrayList<>();
        this.setAutori(autori);
        this.titolo = titolo;
        this.annoPublicazione = annoPublicazione;
        this.nCopie = nCopie;
        this.isbn = isbn;
    }

    /**
     * @brief Imposta la lista degli autori. Ogni elemento della stringa degli
     * autori è convertito in un oggetto Autore e viene aggiungo all'ArrayList autori.
     *
     * @param autori Stringa contenente gli autori, separati da virgola.
     */
    public void setAutori(String autori) {
        if (autori == null) ; else {
            this.autori.clear();

            String[] buffer = autori.split(",");

            for (String s : buffer) {
                this.autori.add(Autore.convert(s));
            }
        }
    }

    public void setNCopie(int nCopie) {
        this.nCopie = nCopie;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutori() {
        return String.join(",", autori.stream().map(Autore::toString).toList());
    }

    public LocalDate getAnno() {
        return annoPublicazione;
    }

    public int getNCopie() {
        return nCopie;
    }

    public String getIsbn() {
        return isbn;
    }

    /**
     * @brief Verifica l'uguaglianza tra due libri. Due libri sono considerati
     * uguali se hanno ISBN.
     *
     * @param o Oggetto da confrontare.
     * @return true se i libri sono uguali, false in caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Libro) ) {
            return false;
        }

        Libro l = (Libro) o;

        return this.isbn.equals(l.getIsbn());
    }
    /**
     * @brief IMplementazione del metodo hashCode.
     */
    @Override
    public int hashCode() {
        int h = 17;
        h = h * 31 + titolo.hashCode();
        h = h * 31 + annoPublicazione.hashCode();
        h = h * 31 + isbn.hashCode();
        h = h * 31 + autori.hashCode();
        return h;
    }
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(titolo);
        for(Autore x : autori){
            builder.append("; "+x);
        }
        builder.append("; "+isbn);
        return builder.toString();
    }

}
