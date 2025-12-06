package gruppo20.biblioteca.model.Libri;
import gruppo20.biblioteca.model.FileFormat;
import java.time.*;
import java.util.*;

/**
 * @brief Questo file contiene l'implementazione dell'oggetto Libro.
 * @author Gruppo20
 */
public class Libro implements Comparable<Libro>,FileFormat<Libro>{
    
    
    private String titolo;
    private ArrayList<Autore> autori;
    private LocalDate annoPublicazione;
    private int nCopie;
    private String isbn;
    
    /**
     * Costruttore Libro
     * Parametri in ingresso 
     *  @param titolo il titolo del libro.
     *  @param autori gli autori del libro. Il cognome e il nome.
     *  @param annoPublicazione la data di pubblicazione del libro.
     *  @param nCopie il numero delle copie presenti in biblioteca.
     *  @param isbn il codice identificativo ISBN del libro.
    */

    public Libro(String titolo, String autori, LocalDate annoPublicazione, int nCopie, String isbn) {
        this.autori = new ArrayList<>();
        this.setAutori(autori);        
        this.titolo = titolo;
        this.annoPublicazione = annoPublicazione;
        this.nCopie = nCopie;
        this.isbn = isbn;    
    }
    
    
    
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

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
    
    
    public String fileFormat(){
        StringBuilder builder = new StringBuilder();
        builder.append(titolo + "§");
        for(int i=0;i<autori.size();i++){
                builder.append(autori.get(i)+"§");
            }
        builder.append(annoPublicazione + "§" + nCopie + "§" + isbn);
        
        return builder.toString();        
    }
    
    @Override
    public Libro deFileFormat(String record){
        String[] parts = record.split("§");
        return new Libro(parts[0],parts[1],LocalDate.parse(parts[2]),Integer.parseInt(parts[3]),parts[4]);
    
    }
    
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Libro) || o==null)
            return false;
        
        Libro l = (Libro) o;
        
        return this.titolo.equals(l.getTitolo()) && this.annoPublicazione.equals(l.getAnno()) && this.isbn.equals(l.getIsbn()) && this.autori.equals(l.getAutori());
    }
    
    @Override
    public int compareTo(Libro l){
        return this.getTitolo().compareTo(l.getTitolo());
    }
    
    @Override
    public int hashCode(){
        return Integer.parseInt(isbn)*31;
    }
    
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
