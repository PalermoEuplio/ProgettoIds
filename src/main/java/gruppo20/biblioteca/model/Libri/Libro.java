package gruppo20.biblioteca.model.Libri;
import gruppo20.biblioteca.model.FileFormat;
import java.time.*;
import java.util.*;

/**
 * @brief Questo file contiene l'implementazione dell'oggetto Libro.
 * @author Gruppo20
 */
public class Libro implements Comparable<Libro>,FileFormat<Libro>{
    
    
    private String titolo; ///< Titolo del libro.
    private ArrayList<Autore> autori; ///< Autrori del libro.
    private LocalDate annoPublicazione; ///< Anno di publicazione del libro.
    private int nCopie; ///< Numero copie del libro disponibili in biblioteca.
    private String isbn; ///< Codice identificativo ISBN del libro.

    public Libro(String titolo, String autori, LocalDate annoPublicazione, int nCopie, String isbn) {
        this.autori = new ArrayList<>();
        this.setAutori(autori);        
        this.titolo = titolo;
        this.annoPublicazione = annoPublicazione;
        this.nCopie = nCopie;
        this.isbn = isbn;    
    }
    
    //ma i metodi setter vengono effettivamente usati?, ho usato solo setAutori per comodità
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * @brief Imposta la lista degli autori.
     * Ogni elemento della stringa degli autori è convertito in un oggetto Autore.
     * 
     * @param autori Stringa contenente gli autori, separati da virgola.
     */
    public void setAutori(String autori) {
        this.autori.clear();

        String[] buffer = autori.split(",");

        for (String s : buffer) {
            this.autori.add(Autore.convert(s));
        }

    }

    public void setAnno(LocalDate anno) {
        this.annoPublicazione = anno;
    }

    public void setNCopie(int nCopie) {
        this.nCopie = nCopie;
    }

    public void setIsbn(String isbn) {
        this.isbn=isbn;
    }
    
    

    public String getTitolo() {
        return titolo;
    }

    public ArrayList<Autore> getAutori() {
        return autori;
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
     * @brief Converte il libro in una stringa formattata per la memorizzazione sul file.
     * La stringa utilizza '§' come carattere di separazione.
     * 
     * @return Stringa contenente la rappresentazione serializzata del libro.
     */
    public String fileFormat(){
        StringBuilder builder = new StringBuilder();
        builder.append(titolo + "§");
        for(int i=0;i<autori.size();i++){
                builder.append(autori.get(i)+"§");
            }
        builder.append(annoPublicazione + "§" + nCopie + "§" + isbn);
        
        return builder.toString();        
    }
    
    /**
     * @brief Deformatta, ricostruisce un oggetto Libro a partire da una stringa formattata.
     * La stringa deve avere formato coerente con quello profotto da fileFormat.
     * 
     * @param record Stringa contenente i campi del libro serializzati.
     * @return Oggetto Libro ottenuto dai dati contenuti in record.
     */
    @Override
    public Libro deFileFormat(String record){
        String[] parts = record.split("§");
        return new Libro(parts[0],parts[1],LocalDate.parse(parts[2]),Integer.parseInt(parts[3]),parts[4]);
    
    }
    
     /**
     * @brief Verifica l'uguaglianza tra due libri.
     * Due libri sono considerati uguali se hanno lo stesso titolo, 
     * lo stesso anno di pubblicazione,
     * lo stesso ISBN e la stessa lista di autori.
     * 
     * @param o Oggetto da confrontare.
     * @return true se i libri sono uguali, false in caso contrario.
     */
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Libro) || o==null)
            return false;
        
        Libro l = (Libro) o;
        
        return this.titolo.equals(l.getTitolo()) && this.annoPublicazione.equals(l.getAnno()) && this.isbn.equals(l.getIsbn()) && this.autori.equals(l.getAutori());
    }
    
    /**
     * @brief Confronta due libri utilizzando il titolo.
     * 
     * @param l Libro da confrontare.
     * @return 
     */
    @Override
    public int compareTo(Libro l){
        return this.getTitolo().compareTo(l.getTitolo());
    }
    
    @Override
    public int hashCode(){
        int h = 17;
        h = h * 31 + titolo.hashCode();
        h = h * 31 + annoPublicazione.hashCode();
        h = h * 31 + isbn.hashCode();
        h = h * 31 + autori.hashCode();
        return h;
    }
    
    /**
     * @brief Restituisce una rappresentazione testuale del libro.
     * Il metodo crea una stringa contenente 
     * Titolo
     * Autori (separati da virgola se più di uno)
     * Data di pubblicazione
     * Numero di copie disponibili
     * ISBN
     * 
     * @return Stringa che contiene tutti i dati del libro.
     */
    @Override
    public String toString() {
        
        StringBuilder builder = new StringBuilder();
        builder.append("Titolo: " + titolo + "; ");
        
        if(autori.size()>1){
            builder.append("Autori: ");
            int i;
            for(i=0;i<autori.size()-1;i++){
                builder.append(autori.get(i)+", ");
            }
            builder.append(autori.get(++i));
            
        }   else builder.append("Autori: " + autori);
        builder.append("; Data publiczione: " + annoPublicazione + "; NumeroCopie: " + nCopie + "; Isbn: " + isbn);
        
        return builder.toString();
    } 
    
}
