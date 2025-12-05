/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gruppo20.biblioteca.model.Libri;

/**
 *
 * @author Osv
 */
public class Autore {
    private String nome;
    private String cognome;
    
    public Autore(String nome, String cognome){
        this.nome=nome;
        this.cognome=cognome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }    
    public String getNome(){
        return nome;
    }

    public String getCognome() {
        return cognome;
    }
    
    public static Autore convert(String s){
            s = s.trim();
            String[] parts = s.split(" ");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Formato autore non valido: " + s);
            }
            return new Autore(parts[0],parts[1]);
    }

    @Override
    public boolean equals(Object o) {
        if(o==null || !(o instanceof Autore))return false;
        Autore a = (Autore) o;
        return this.cognome.equals(a.getCognome()) && this.nome.equals(a.getNome());
    }
    
    @Override
    public String toString(){
        return nome+" "+cognome;
    }
    
    
}
