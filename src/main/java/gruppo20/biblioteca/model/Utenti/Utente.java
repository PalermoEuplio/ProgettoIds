package gruppo20.biblioteca.model.Utenti;

/**
 * @brief Questo file contiene l'implementazione dell'oggetto Utente. Identifica
 * l'entità utente e i suoi attributi
 * @author Gruppo20
 */
public class Utente {

    private final String nome;///< Nome dell'utente.
    private final String cognome;///< Cognome dell'utente.
    private final String matricola;///< Matricola dell'utente.
    private final String mail;///< E-mail istituzionale dell'utente.
    private final int nPrestiti;///< Numero dei prestiti attivi dell'utente.

    public Utente(String nome, String cognome, String matricola, String mail, int nPrestiti) {
        this.nome = nome;
        this.cognome = cognome;
        this.matricola = matricola;
        this.mail = mail;
        this.nPrestiti = nPrestiti;
    }

    //Getter
    public int getNPrestiti() {
        return nPrestiti;
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

    /**
     * @brief Implementazione del metodo equals.
     *
     * @param o oggetto su cui controllare l'uguaglianza.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Utente) || o == null) {
            return false;
        }

        Utente u = (Utente) o;

        return this.matricola.equals(u.getMatricola());
    }

    /**
     * @brief Implementazione del metodo hashCode
     */
    @Override
    public int hashCode() {
        int h = 17;
        h = h * 31 + matricola.hashCode();
        return h;
    }
    
    @Override
    public String toString(){
        return nome+" "+cognome+"; "+matricola;
    }
}
