package gruppo20.biblioteca.model.Utenti;

/**
 * @brief Questo file contiene l'implementazione dell'oggetto Utente.
 * Identifica l'entità utente e i suoi attributi
 * @author Gruppo20
 */
public class Utente{
    
    private String nome; ///< Nome dell'utente.
    private String cognome; ///< Cognome dell'utente.
    private String matricola; ///< Matricola dell'utente.
    private String mail; ///< E-mail istituzionale dell'utente.
    private int nPrestiti; ///< Numero dei prestiti attivi dell'utente.

    public Utente(String nome, String cognome, String matricola, String mail, int nPrestiti) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.mail = mail;
        this.nPrestiti = nPrestiti;
    }

    public void setNPrestiti(int nPrestiti) {
        this.nPrestiti = nPrestiti;
    }

    public int getNPrestiti() {
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

    public void seteMail(String mail) {
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
    public boolean equals(Object o){
        if(!(o instanceof Utente) || o==null)
            return false;
        
        Utente u = (Utente) o;
        
        return this.nome.equals(u.getNome()) && this.cognome.equals(u.getCognome()) && this.matricola.equals(u.getMatricola());
    }
    
    @Override
    public int hashCode(){
        int h = 17;
        h = h * 31 + matricola.hashCode();
        h = h * 31 + nome.hashCode();
        h = h * 31 + cognome.hashCode();
        return h;
    }
    
}
