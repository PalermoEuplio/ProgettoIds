package gruppo20.biblioteca.model.Utenti;

import gruppo20.biblioteca.model.Utility.GestioneDB;
import java.sql.Connection;
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
public class Utenti extends GestioneDB<Utente> {

    /**
     * @brief Insieme degli utenti presenti nel sistema. Si utilizza un
     * ObservableSet per garantire l'unicità degli utenti e l'osservabilità dei
     * cambiamenti.
     */
    private final ObservableSet<Utente> setUtenti;

    /**
     * @brief Connessione per la gestione del database locale.
     */
    private final Connection conn;

    public Utenti(Connection conn) throws SQLException {
        this.setUtenti = FXCollections.observableSet(new HashSet<>());
        this.conn = conn;
        if (!super.tableExists(conn, "utenti")) {
            String sqlUtenti = "CREATE TABLE IF NOT EXISTS utenti (nome TEXT,cognome TEXT,matricola TEXT NOT NULL,email TEXT,     prestiti INTEGER);";
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sqlUtenti);
            }
        } else {
            carica();
        }
    }

    /**
     * @return Restituisce l'ObservableSet relativo alla classe utenti.
     */
    public ObservableSet<Utente> getSetUtenti() {
        return setUtenti;
    }

    /**
     * Aggiunge un utente all'anagrafica.
     *
     * @param u utente da aggiungere al setUtenti.
     *
     * @return restituisce true se l'utente è stato inserito. false se invece
     * non è stato inserito o è già presente in setUtenti.
     */
    @Override
    public boolean aggiungi(Utente u) {
        String sql = "INSERT INTO utenti (nome, cognome, matricola, email, prestiti) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getCognome());
            ps.setString(3, u.getMatricola());
            ps.setString(4, u.getMail());
            ps.setInt(5, u.getNPrestiti());
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return setUtenti.add(u);
    }

    /**
     * @brief Elimina l'utente. Se l'utente è presente effettua l'eliminazione.
     *
     * @param u utente da eliminare
     *
     * @return restituisce true se elimina l'utente. false ses l'operazione
     * fallisce.
     */
    @Override
    public boolean elimina(Utente u) {
        String sql = "DELETE FROM utenti WHERE matricola = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getMatricola());
            ps.executeUpdate();

        } catch (SQLException e) {
            return false;
        }
        return setUtenti.remove(u);
    }

    /**
     * @throws java.sql.SQLException
     * @brief Modifica l'utente. Se l'utente è presente effettua la modifica di
     * uno o più suoi dati.
     *
     * @param u1 utente da modificare
     * @param u2 utente con modifiche
     *
     * @return restituisce true se la modifica dell'utente è avvenuta
     * correttamente. false se fallisce.
     */
    @Override
    public boolean modifica(Utente u1, Utente u2) throws SQLException {
        conn.setAutoCommit(false); // inizio transazione
        boolean status;
        try {
             status = elimina(u1) && aggiungi(u2);
            conn.commit(); // conferma tutte le modifiche
        } catch (SQLException e) {
            conn.rollback(); // annulla tutto se c’è un errore
            return false;
        }
        conn.setAutoCommit(true);
        return status;
    }

    /**
     * @throws java.sql.SQLException
     * @brief Implementazione del metodo carica di GestioneDB.
     */
    @Override
    public final void carica() throws SQLException {
        String sql = "SELECT nome, cognome, matricola, email, prestiti FROM utenti";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                setUtenti.add(new Utente(rs.getString("nome"), rs.getString("cognome"), rs.getString("matricola"), rs.getString("email"), rs.getInt("prestiti")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * @brief Decrementa il numero di prestiti attivi dell' utente collegato
     * alla matricola. Non c'è controllo del numero di prestiti attivi prima
     * della modifica.
     *
     * @param matricola matricola relativa all'utente oggetto della
     * restituzione.
     *
     * @return true se ha successo, false se fallisce.
     */
    public boolean addRestituzione(String matricola) {
        Iterator<Utente> it = setUtenti.iterator();
        Utente u1;
        while (it.hasNext()) {
            u1 = it.next();
            if (matricola.equals(u1.getMatricola())) {

                try {
                    return modifica(u1, new Utente(u1.getNome(), u1.getCognome(), matricola, u1.getMail(), u1.getNPrestiti() - 1));
                } catch (SQLException e) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @brief Aumenta il numero di prestiti attivi dell' utente collegato alla
     * matricola. Non c'è controllo del numero di prestiti attivi prima della
     * modifica.
     *
     * @param matricola matricola relativa all'utente oggetto del prestito.
     *
     * @return true se ha successo, false se fallisce.
     */
    public boolean addPrestito(String matricola) {
        Iterator<Utente> it = setUtenti.iterator();
        Utente u1;
        while (it.hasNext()) {
            u1 = it.next();
            if (matricola.equals(u1.getMatricola())) {

                try {
                    return modifica(u1, new Utente(u1.getNome(), u1.getCognome(), matricola, u1.getMail(), u1.getNPrestiti() + 1));
                } catch (SQLException e) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @brief Restituisce una rappresentazione testuale dell'insieme degli
     * Utenti. Il metodo crea una stringa contenente la lista di tutti gli
     * utenti presenti nell'anagrafica. Ogni elemento su una nuova linea. Per
     * ogni elemento viene utilizzato il metodo toString() della classe Utente.
     *
     * @return Stringa che contiene tutti gli utenti della struttura dati.
     */
    @Override
    public String toString() {
        //corretto
        StringBuilder s = new StringBuilder();
        for (Utente u : setUtenti) {
            s.append(u.toString()).append("\n");
        }
        return s.toString();
    }

}
