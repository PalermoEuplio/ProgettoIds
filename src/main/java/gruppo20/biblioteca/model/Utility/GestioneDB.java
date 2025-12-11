package gruppo20.biblioteca.model.Utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @brief Questo file contiene l'implementazione della superclasse GestioneDB.
 * Tutte le classi che estendono questa garantiranno la presenza delle
 * operazioni per la gestione del DB.
 *
 * @param <T> tipo dell'oggetto gestito dal DB.
 *
 * @author Gruppo20
 */
public abstract class GestioneDB<T> {

    /**
     * @brief Aggiungi oggetto. Se l'oggetto è presente non effettua modifiche.
     * Se non è presente lo aggiuge.
     *
     * Parametro in ingresso:
     * @param o oggetto da aggiungere.
     *
     * @return restituisce true se l'aggiunta è avvenuta correttamente. false se
     * fallisce o l'oggetto è già presente.
     */
    public abstract boolean aggiungi(T o);

    /**
     * @brief Elimina oggetto. Se l'oggetto è presente lo elimina.
     *
     * Parametro in ingresso:
     * @param o oggetto da eliminare.
     *
     * @return restituisce true se l'eliminazione è avvenuta correttamente.
     * false se fallisce.
     */
    public abstract boolean elimina(T o);

    /**
     * @throws java.sql.SQLException
     * @brief Modifica oggetto.
     *
     * Parametro in ingresso:
     * @param o1 oggetto pre-modifica.
     * @param o2 oggetto post-modifica.
     *
     * @return restituisce true se la modifica è avvenuta correttamente. false
     * se l'oggetto non è già contenuto.
     */
    public abstract boolean modifica(T o1, T o2) throws SQLException;

    /**
     * @throws java.sql.SQLException
     * @brief Se il database è già presente in memoria, lo carica nelle
     * strutture interne del programma. Se fallisce il programma viene chiuso
     * forzatamente.
     */
    public abstract void carica() throws SQLException;

    /**
     * @throws java.sql.SQLException
     * @brief Ccontrolla se nel database esiste la relativa tabella.
     *
     * @param conn connessione al database.
     * @param tableName nome della tabella da controllare.
     *
     * @return true se esiste, false se non esiste.
     */
    public boolean tableExists(Connection conn, String tableName) throws SQLException {
        String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tableName);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // se rs.next() è true, la tabella esiste
            }
        }
    }

}
