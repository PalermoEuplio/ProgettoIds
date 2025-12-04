/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gruppo20.biblioteca;

import java.util.*;

/**
 *
 * @author giuli
 */
public class Libreria implements Comparable<Libro>,Gestione<Libro> {
    
    
    private TreeSet<Libro> libri;
    
    public Libreria(){
        libri = new TreeSet<>();
    }
    
    @Override
    public boolean aggiungi(Libro l){
        
    }
    
    @Override
    public boolean elimina(Libro l){
        
    }
    
    @Override
    public boolean modifica(Libro l){
        
    }   
    
    public Libro ricerca(String titolo){
        
    }
    
    public Libro ricerca(String[] autori){
        
    }
    
    public Libro ricerca(int isbn){
        
    }
    
    @Override
    public int compareTo(Libro l){
        
    }
    
    @Override
    public String toString(){
        
    }
    
    
    
}
