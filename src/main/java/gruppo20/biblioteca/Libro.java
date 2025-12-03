/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gruppo20.biblioteca;

import java.time.*;
import java.util.*;

/**
 *
 * @author giuli
 */
public class Libro {
    
    
    private String titolo;
    private String[] autori;
    private LocalDate date;
    private int nCopie;
    private int isbn;

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
        return Titolo;
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
