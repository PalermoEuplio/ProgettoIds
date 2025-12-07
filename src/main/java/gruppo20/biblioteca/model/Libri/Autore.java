
package gruppo20.biblioteca.model.Libri;

/**
 * @brief Questo file contiene l'implementazione della classe Autore.
 * @author Gruppo20
 */
public class Autore {
    private String nome; ///< Nome dell'autore.
    private String cognome; ///< Cognome dell'autore.
    
    /**
     * @brief Costruisce un nuovo oggetto Autore.
     * @param nome nome dell'autore.
     * @param cognome cognome dell'autore.
     */
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
    //converte una stringa "nome cognome" in un oggetto Autore
    /**
     * @brief Converte una stringa in un oggetto Autore.
     * La stringa deve essere nel formato "nome cognome".
     * Se il formato non è valido viene sollevata un'eccezione.
     * @param s Stringa da convertire.
     * @return Nuovo oggeto Autore.
     * @throws IllegalArgumentException Se il formato stringa non è valido. 
    */
    public static Autore convert(String s){
            s = s.trim();
            String[] parts = s.split(" ");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Formato autore non valido: " + s);
            }
            return new Autore(parts[0],parts[1]);
    }
    
    @Override
    public int hashCode(){
        int h = 17;
        h = h * 31 + nome.hashCode();
        h = h * 31 + cognome.hashCode();
        return h;
    }

    /**
     * @brief Verifica l'uguaglianza tra due autori.
     * Due autori sono considerati uguali se hanno lo stesso nome e lo stesso cognome.
     * 
     * @param o Oggetto da confrontare.
     * @return true se gli autori sono uguali, false in caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if(o==null || !(o instanceof Autore))return false;
        Autore a = (Autore) o;
        return this.nome.equals(a.getCognome()) && this.cognome.equals(a.getNome());
    }
    
    /**
     * @brief Restituisce una rappresentazione testuale dell'autore.
     * @return Stringa nel formato "nome cognome".
     */
    
    @Override
    public String toString(){
        return nome+" "+cognome;
    }
    
    
}
