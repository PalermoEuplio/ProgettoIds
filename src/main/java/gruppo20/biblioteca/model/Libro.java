package gruppo20.biblioteca.model;

import java.time.*;
import java.util.*;

/**
 * @brief Questo file contiene l'implementazione dell'oggetto Libro.
 * @author Gruppo20
 */
public class Libro implements Comparable<Libro> {
    
    
    private String titolo;
    private String autori;
    private LocalDate annoPublicazione;
    private int nCopie;
    private int isbn;
    
    /**
     * Costruttore Libro
     * Parametri in ingresso 
     *  @param titolo il titolo del libro.
     *  @param autori gli autori del libro. Il cognome e il nome.
     *  @param annoPublicazione la data di pubblicazione del libro.
     *  @param nCopie il numero delle copie presenti in biblioteca.
     *  @param isbn il codice identificativo ISBN del libro.
    */

    public Libro(String titolo, String autori, LocalDate annoPublicazione, int nCopie, int isbn) {
        this.titolo = titolo;
        this.autori=autori;
        this.annoPublicazione = annoPublicazione;
        this.nCopie = nCopie;
        this.isbn = isbn;
    }
    
    
    
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setAutori(String autori) {
        this.autori = autori;
    }

    public void setAnno(LocalDate anno) {
        this.annoPublicazione = anno;
    }

    public void setNCopie(int nCopie) {
        this.nCopie = nCopie;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }
    
    

    public String getTitolo() {
        return titolo;
    }

    public String getAutori() {
        return autori;
    }

    public LocalDate getAnno() {
        return annoPublicazione;
    }

    public int getNCopie() {
        return nCopie;
    }

    public int getIsbn() {
        return isbn;
    }
    
    @Override
    public boolean equals(Object o){
        Libro l = (Libro) o;
        
        if(!(o instanceof Libro) || o==null)
            return false;
 
        if(this.titolo.compareTo(l.getTitolo())==1 && this.annoPublicazione.compareTo(l.getAnno())==1 && this.isbn==l.getIsbn() 
                && this.nCopie==l.getNCopie() && this.autori.compareTo(l.getAutori())==1)
            return true;
        
        return false;
    }
    
    @Override
    public int compareTo(Libro l){
        return this.getTitolo().compareTo(l.getTitolo());
    }
    
    
    @Override
    public String toString() {
        return "Titolo=" + titolo + ", autori=" + autori + ", data publiczione=" + annoPublicazione + ", nCopie=" + nCopie + ", isbn=" + isbn;
    } 
    
}
