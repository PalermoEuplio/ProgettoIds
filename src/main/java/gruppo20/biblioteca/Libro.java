package gruppo20.biblioteca;

import java.time.*;
import java.util.*;

/**
 * @brief Questo file contiene l'implementazione dell'oggetto Libro.
 * @author Gruppo20
 */
public class Libro {
    
    
    private String titolo;
    private String[] autori;
    private LocalDate date;
    private int nCopie;
    private int isbn;
    
    /**
     * Costruttore Libro
     * Parametri in ingresso 
     *  @param titolo il titolo del libro.
     *  @param autori gli autori del libro. Il cognome e il nome.
     *  @param date la data di pubblicazione del libro.
     *  @param nCopie il numero delle copie presenti in biblioteca.
     *  @param isbn il codice identificativo ISBN del libro.
    */

    public Libro(String titolo, String[] autori, LocalDate date, int nCopie, int isbn) {
        this.titolo = titolo;
        this.autori=autori;
        this.date = date;
        this.nCopie = nCopie;
        this.isbn = isbn;
    }
    
    
    
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setAutori(String[] autori) {
        this.autori = autori;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setnCopie(int nCopie) {
        this.nCopie = nCopie;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }
    
    

    public String getTitolo() {
        return titolo;
    }

    public String[] getAutori() {
        return autori;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getnCopie() {
        return nCopie;
    }

    public int getIsbn() {
        return isbn;
    }
    
    
    @Override
    public String toString() {
        return "Titolo=" + titolo + ", autori=" + autori + ", date=" + date + ", nCopie=" + nCopie + ", isbn=" + isbn;
    } 
    
}
