/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gruppo20.biblioteca.model.Utenti;

/**
 * @brief Questo file contiene l'implementazione dell'oggetto Utente.
 * Identifica l'entità utente e i suoi attributi
 * @author Gruppo20
 */
public class Utente implements Comparable<Utente>{
    
    private String nome;
    private String cognome;
    private String matricola;
    private String mail;

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
        this.mail=mail;
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
    
    @Override
    public String toString() {
        return "Nome=" + nome + ", cognome=" + cognome + ", matricola=" + matricola + ", mail=" + mail + '}';
    }

    @Override
    public int compareTo(Utente o) {
        return this.getNome().compareTo(o.getNome());
    }
    
}
