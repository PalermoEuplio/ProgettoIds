package gruppo20.biblioteca.model.Libri;

/**
 * @brief Questo file contiene l'implementazione della classe Autore.
 * @author Gruppo20
 */
public class Autore {

    private final String nome;///< Nome dell'autore.
    private final String cognome;///< Cognome dell'autore.
    
    public Autore(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    //converte una stringa "nome/nomi cognome" in un oggetto Autore
    /**
     * @brief Converte una stringa in un oggetto Autore. La stringa deve essere
     * nel formato "nome cognome".
     * 
     * @param s Stringa da convertire.
     * 
     * @return Nuovo oggetto Autore.
     */
    public static Autore convert(String s) {
        s = s.trim();
        int idx = s.lastIndexOf(" ");

        return new Autore(s.substring(0, idx).trim(), s.substring(idx + " ".length()).trim());

    }

    @Override
    public int hashCode() {
        int h = 17;
        h = h * 31 + nome.hashCode();
        h = h * 31 + cognome.hashCode();
        return h;
    }

    /**
     * @brief Verifica l'uguaglianza tra due autori. Due autori sono considerati
     * uguali se hanno lo stesso nome e lo stesso cognome.
     *
     * @param o Oggetto da confrontare.
     * @return true se gli autori sono uguali, false in caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Autore)) {
            return false;
        }
        Autore a = (Autore) o;
        return this.nome.equals(a.getNome()) && this.cognome.equals(a.getCognome());
    }
    
    /**
     * @brief Restituisce una rappresentazione testuale dell'autore.
     * @return Stringa nel formato "nome cognome".
     */
    @Override
    public String toString() {
        return nome + " " + cognome;
    }

}
