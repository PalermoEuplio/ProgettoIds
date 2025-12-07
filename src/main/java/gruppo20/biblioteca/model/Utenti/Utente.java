/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gruppo20.biblioteca.model.Utenti;

import gruppo20.biblioteca.model.FileFormat;

/**
 * @brief Questo file contiene l'implementazione dell'oggetto Utente.
 * Identifica l'entità utente e i suoi attributi
 * @author Gruppo20
 */
public class Utente implements Comparable<Utente>,FileFormat<Utente>{
    
    private String nome;
    private String cognome;
    private String matricola;
    private String email;
    private int nPrestiti;

    /**
     * Costruttore Utente
     * Necessita come parametri in ingresso 
     *   @param nome il nome utente
     *   @param cognome il cognome utente
     *   @param matricola la matricola utente
     *   @param mail
    */
    public Utente(String nome, String cognome, String matricola, String mail) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.email=mail;
        this.nPrestiti = 0;
    }

    public void setnPrestiti(int nPrestiti) {
        this.nPrestiti = nPrestiti;
    }

    public int getnPrestiti() {
        return nPrestiti;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getMatricola() {
        return matricola;
    }

    public String getEmail() {
        return email;
    }
    @Override
    public String fileFormat(){
        StringBuilder builder = new StringBuilder();
        builder.append(nome+"§"+cognome+"§"+matricola+"§"+email);
        return builder.toString();        
    }
    
    @Override
    public Utente deFileFormat(String record){
        String[] parts = record.split("§");
        return new Utente(parts[0],parts[1],parts[2],parts[3]);
    
    }
    
    @Override
    public String toString() {
        return "Nome=" + nome + ", cognome=" + cognome + ", matricola=" + matricola + ", mail=" + email + '}';
    }

    @Override
    public int compareTo(Utente o) {
        return this.getNome().compareTo(o.getNome());
    }
    
}
