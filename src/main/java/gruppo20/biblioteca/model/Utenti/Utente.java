package gruppo20.biblioteca.model.Utenti;
import gruppo20.biblioteca.model.Utility.FileFormat;

/**
 * @brief Questo file contiene l'implementazione dell'oggetto Utente.
 * Identifica l'entità utente e i suoi attributi
 * @author Gruppo20
 */
public class Utente implements Comparable<Utente>,FileFormat<Utente>{
    
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

    public void setMail(String email) {
        this.mail = email;
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
     * @brief Converte l'utente in una stringa formattata per la memorizzazione sul file.
     * La stringa utilizza '§' come carattere di separazione.
     * 
     * @return Stringa contenente la rappresentazione serializzata dell'utente.
     */
    @Override
    public String fileFormat(){
        StringBuilder builder = new StringBuilder();
        builder.append(nome+"§"+cognome+"§"+matricola+"§"+mail+"§"+nPrestiti);
        return builder.toString();        
    }
    
    /**
     * @brief Deformatta, ricostruisce un oggetto Utente a partire da una stringa formattata.
     * La stringa deve avere formato coerente con quello profotto da fileFormat.
     * 
     * @param record Stringa contenente i campi dell'utente serializzati.
     * @return Oggetto Utente ottenuto dai dati contenuti in record.
     */
    @Override
    public Utente deFileFormat(String record){
        String[] parts = record.split("§");
        return new Utente(parts[0],parts[1],parts[2],parts[3],Integer.parseInt(parts[4]));
    
    }
    
    @Override
    public String toString() {
        return "Nome: " + nome + ", cognome: " + cognome + ", matricola: " + matricola + ", mail: " + mail+"\n";
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

    
    /*
    *Ordinamneto basato sull attributo nome
    */
    @Override
    public int compareTo(Utente o) {
        return this.getNome().compareTo(o.getNome());
    }
    
}
