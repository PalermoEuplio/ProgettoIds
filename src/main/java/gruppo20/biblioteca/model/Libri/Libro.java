package gruppo20.biblioteca.model.Libri;

import java.time.*;
import java.util.*;

/**
 * @brief Questo file contiene l'implementazione dell'oggetto Libro.
 * @author Gruppo20
 */
public class Libro implements Comparable<Libro> {
    
    
    private String titolo;
    private ArrayList<Autore> autori;
    private LocalDate annoPublicazione;
    private int nCopie;
    private Isbn isbn;
    
    /**
     * Costruttore Libro
     * Parametri in ingresso 
     *  @param titolo il titolo del libro.
     *  @param autori gli autori del libro. Il cognome e il nome.
     *  @param annoPublicazione la data di pubblicazione del libro.
     *  @param nCopie il numero delle copie presenti in biblioteca.
     *  @param isbn il codice identificativo ISBN del libro.
    */

    public Libro(String titolo, Autore[] autori, LocalDate annoPublicazione, int nCopie, Isbn isbn) {
        this.autori = new ArrayList<>();
        this.autori.addAll(Arrays.asList(autori));        
        this.titolo = titolo;
        this.annoPublicazione = annoPublicazione;
        this.nCopie = nCopie;
        this.isbn = isbn;    
    }
    
    
    
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setAutori(Autore[] autori) {
        this.autori.clear();
        this.autori.addAll(Arrays.asList(autori));  
    }

    public void setAnno(LocalDate anno) {
        this.annoPublicazione = anno;
    }

    public void setNCopie(int nCopie) {
        this.nCopie = nCopie;
    }

    public void setIsbn(Isbn isbn) {
        this.isbn.setIsbn(isbn.toString());
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

    public Isbn getIsbn() {
        return isbn;
    }
    
    @Override
    public boolean equals(Object o){
        Libro l = (Libro) o;
        
        if(!(o instanceof Libro) || o==null)
            return false;
 
        return this.titolo.equals(l.getTitolo()) && this.annoPublicazione.equals(l.getAnno()) && this.isbn==l.getIsbn() 
                && this.autori.equals(l.getAutori());
    }
    
    @Override
    public int compareTo(Libro l){
        return this.getTitolo().compareTo(l.getTitolo());
    }
    
    
    @Override
    public String toString() {
        
        StringBuilder buffer = new StringBuilder();
        buffer.append("Titolo: " + titolo + "; ");
        
        if(autori.size()>1){
            buffer.append("Autori: ");
            int i;
            for(i=0;i<autori.size()-1;i++){
                buffer.append(autori.get(i)+", ");
            }
            buffer.append(autori.get(++i));
            
        }   else buffer.append("Autori: " + autori);
        buffer.append("; Data publiczione: " + annoPublicazione + "; NumeroCopie: " + nCopie + "; Isbn: " + isbn);
        
        return buffer.toString();
    } 
    
}
