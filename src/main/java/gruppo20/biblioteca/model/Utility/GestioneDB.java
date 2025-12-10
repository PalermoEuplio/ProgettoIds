
package gruppo20.biblioteca.model.Utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @brief Questo file contiene l'implementazione della superclasse GestioneSet.
 * @author Gruppo20
 */
public abstract class GestioneDB<T> {
    
    /**
     * @brief Aggiungi oggetto.
     * Se l'oggetto è presente non effettua modifiche.
     * Se non è presente lo aggiuge.
     * 
     * Parametro in ingresso:
     *  @param file controller del file in cui aggiungere l'oggetto.
     *  @param set HashSet in cui aggiungere l'oggetto.
     *  @param o oggetto da aggiungere.
     * 
     *  @return restituisce true se l'aggiunta è avvenuta correttamente.
     *          false se l'oggetto è già contenuto.
     */
    public abstract boolean aggiungi(T o);
    
    /**
     * @brief Elimina oggetto.
     * Se l'oggetto è presente lo elimina.
     * 
     * Parametro in ingresso:
     *  @param file controller del file in cui eliminare l'oggetto.
     *  @param set HashSet in cui eliminare l'oggetto.
     *  @param o oggetto da eliminare.
     * 
     *  @return restituisce true se l'eliminazione è avvenuta correttamente.
     *          false se l'oggetto non è già contenuto.
     */
    public abstract boolean elimina(T o);
    
    /**
     * @brief Modifica oggetto.
     * Se l'oggetto è presente lo elimina.
     * 
     * Parametro in ingresso:
     *  @param file controller del file in cui modificare l'oggetto.
     *  @param set HashSet in cui modificare l'oggetto.
     *  @param o1 oggetto pre-modifica.
     *  @param o2 oggetto post-modifica.
     * 
     *  @return restituisce true se la modifica è avvenuta correttamente.
     *          false se l'oggetto non è già contenuto.
     */
    public abstract boolean modifica(T o1,T o2) throws SQLException;
    
    public abstract void carica() throws SQLException;
    
    
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
