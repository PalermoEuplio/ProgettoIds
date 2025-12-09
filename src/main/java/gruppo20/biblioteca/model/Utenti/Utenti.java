package gruppo20.biblioteca.model.Utenti;

import gruppo20.biblioteca.model.Utility.GestioneDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

/**
 * @brief Questo file contiene l'implementazione della classe Utenza.
 * @author Gruppo20
 */

public class Utenti extends GestioneDB<Utente>{
    /**
     * @brief Insieme degli utenti presenti nel sistema.
     * Si utilizza un HashSet per garantire l'unicità degli utenti.
     */
    private ObservableSet<Utente> setUtenti;
    
    /**
     * @brief Connessione per la gestione del database locale.
     */
    private Connection conn;
    
    public Utenti(String DBPath) throws SQLException{
        this.setUtenti = FXCollections.observableSet(new HashSet<>());
        this.conn=DriverManager.getConnection("jdbc:sqlite:"+DBPath);
        if(!super.tableExists(conn, "utenti")){
            String sqlUtenti = """
            CREATE TABLE IF NOT EXISTS utenti (
                nome TEXT,
                cognome TEXT,
                matricola TEXT NOT NULL,
                email TEXT,     
                prestiti INTEGER

            );
        """;
            try (Statement stmt = conn.createStatement()) {
            stmt.execute(sqlUtenti);
            }
        }
        else{
            carica();
        }
    }

    public ObservableSet<Utente> getSetUtenti() {
        return setUtenti;
    }

    
    
    /**
    *Aggiunge un utente all'anagrafica.
    * 
    * Parametro in ingresso:
    *   @param u utente da aggiungere all'setUtenti.
    * 
    *   @return restituisce true se l'utente è stato inserito. 
           false se invece non è stato inserito o è già presente in setUtenti. 
    */  
    public boolean aggiungi(Utente u){
        String sql = "INSERT INTO utenti (nome, cognome, matricola, emial, prestiti) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getCognome());
            ps.setString(3, u.getMatricola());
            ps.setString(4, u.geteMail());
            ps.setInt(5, u.getnPrestiti());
            ps.executeUpdate();
        }
        catch(SQLException e){return false;}
        setUtenti.add(u);
        return true;
    }
    
    /**
     * @brief Elimina l'utente.
     * Se l'utente è presente effettua l'eliminazione.
     * 
     * Parametro in ingresso:
     *  @param u utente da eliminare
     * 
     *  @return restituisce true se elimina l'utente. false se non è già presente.
     */
    
    public boolean elimina(Utente u){
    String sql = "DELETE FROM utenti WHERE matricola = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getMatricola());
            ps.executeUpdate();
            
        }
        catch(SQLException e){return false;}
        setUtenti.remove(u);
        return true;
    }
    
     /**
     * @brief Modifica l'utente.
     * Se l'utente è presente effettua la modifica di uno o più suoi dati.
     * 
     * Parametro in ingresso:
     *  @param u1 utente da modificare
     *  @param u2 utente con modifiche
     * 
     *  @return restituisce true se la modifica dell'utente è avvenuta correttamente.
     *          false se l'utente non è presente.
     */
    public boolean modifica(Utente u1,Utente u2) throws SQLException{
        conn.setAutoCommit(false); // inizio transazione
        boolean status;
        try {
                status = elimina(u1) && aggiungi(u2);
                conn.commit(); // conferma tutte le modifiche
        } 
        catch (SQLException e) {
                conn.rollback(); // annulla tutto se c’è un errore
                return false;
        }
        conn.setAutoCommit(true);
        return status;
    }
    
    @Override
    public void carica() throws SQLException{
        String sql = "SELECT nome, cognome, matricola, email, prestiti FROM utenti";
        try (PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                setUtenti.add(new Utente(rs.getString("nome"),rs.getString("cognome"),rs.getString("matricola"),rs.getString("email"),rs.getInt("prestiti")));
            }
        }
    }
    
    public boolean setRestituzione(String matricola){
    Iterator<Utente> it = setUtenti.iterator();
    Utente u1;
        while(it.hasNext()){
            u1 = it.next();
            if(matricola.equals(u1.getMatricola())){

                try {return modifica(u1, new Utente(u1.getNome(),u1.getCognome(),matricola,u1.geteMail(),u1.getnPrestiti()-1));}
                catch(SQLException e){return false;}
            }
        }
    return true;
    }
    
    public boolean addPrestito(String matricola){
    Iterator<Utente> it = setUtenti.iterator();
    Utente u1;
        while(it.hasNext()){
            u1 = it.next();
            if(matricola.equals(u1.getMatricola())){

                try {return modifica(u1, new Utente(u1.getNome(),u1.getCognome(),matricola,u1.geteMail(),u1.getnPrestiti()+1));}
                catch(SQLException e){return false;}
            }
        }
    return true;
    }

     /**
     * @brief Restituisce una rappresentazione testuale dell'insieme degli Utenti.
     * Il metodo crea una stringa contenente la lista di tutti gli utenti presenti nell'anagrafica.
     * Ogni elemento su una nuova linea.
     * Per ogni elemento viene utilizzato il metodo toString() della classe Utente.
     * 
     * @return Stringa che contiene tutti gli utenti della struttura dati.
     */
    @Override
    public String toString() {
        //corretto
        StringBuilder s = new StringBuilder();
        for(Utente u : setUtenti){            
            s.append(u.toString()).append("\n");
        }
        return s.toString();
    }
    
    
    
}
