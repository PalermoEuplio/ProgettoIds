/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gruppo20.biblioteca;
import java.util.*;
/**
 *
 * @author Osv
 */
public class GestionePrestiti {
    private TreeSet<Prestito> Prestiti;
    private final String nome;
   
    public GestionePrestiti(String nome){
        this.nome = nome;
        this.Prestiti = new TreeSet<>();
    }
    //restituisce direttamente una stringa con la libreria
    public String getPrestiti() {
        return Prestiti.toString();//fare override toString
    }

    public String getNome() {
        return nome;
    }
    
    public void addPrestito (Prestito p){
        Prestiti.add(p);
    }
    
    public void delPrestito (Prestito p){
        Prestiti.remove(p);
    }

    public boolean hasPrestito (Prestito p){
        return Prestiti.contains(p);
    }
    
}
