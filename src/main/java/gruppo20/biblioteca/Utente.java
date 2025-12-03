/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gruppo20.biblioteca;

/**
 *Classe Utente
 * Identifica l'entità utente e i suoi attributi
 * 
 * @author Riccardo
 */
public class Utente {
    
    private String nome;
    private String cognome;
    private String matricola;

    @Override
    public String toString() {
        return "Nome=" + nome + ", cognome=" + cognome + ", matricola=" + matricola + ", mail=" + mail + '}';
    }
    private String mail;

    /**
    *Costruttore Utente
    *Necessita come parametri
     * @param nome
     * @param cognome
     * @param matricola
    * 
    */
    public Utente(String nome, String cognome, String matricola) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
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

    public void setMail(String mail) {
        this.mail = mail;
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

    public String getMail() {
        return mail;
    }
    
    
    
}
