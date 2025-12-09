
package gruppo20.biblioteca.model.Libri;

import gruppo20.biblioteca.model.Utility.GestioneDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import org.w3c.dom.ls.LSInput;
/**
 * @brief Questo file contiene l'implementazione della classe Libreria.
 * @author Gruppo20
 */
public class Libreria extends GestioneDB<Libro> {
    /**
     * @brief Insieme dei libri presenti in libreria.
     * Si utilizza un HashSet per garantire l'unicità dei libri.
     */
    private ObservableSet<Libro> setLibreria; 
    /**
     * @brief Connessione per la gestione del database locale.
     */
    private Connection conn;
    
    public Libreria(String DBPath) throws SQLException{
        setLibreria = FXCollections.observableSet(new HashSet<>());
        this.conn=DriverManager.getConnection("jdbc:sqlite:"+DBPath);
        if(!super.tableExists(conn, "libri")){
            String sqlLibri = """
            CREATE TABLE IF NOT EXISTS libri (
                titolo TEXT NOT NULL,
                autori TEXT,
                anno TEXT,
                copie INTEGER,
                isbn TEXT
            );
        """;
            try (Statement stmt = conn.createStatement()) {
            stmt.execute(sqlLibri);
            }
        }
        else{
            carica();
        }
       
    }

    public ObservableSet<Libro> getSetLibreria() {
        return setLibreria;
    }
    
    
    
   /**
    *@brief Aggiunge un libro alla libreria.
    *Se il libro non è presente nella libreria, lo aggiunge.
    * Nel caso in cui il libro è già presente, si andrà a modificare il numero di copie.
    * 
    * Parametro in ingresso:
    *   @param l2 libro da aggiungere alla setLibreria.
    * 
    *   @return restituisce true se il libro è stato inserito. 
    *           false se invece non è stato inserito. 
    */  
    @Override
    public boolean aggiungi(Libro l2){
        if(setLibreria.contains(l2)){
            Iterator<Libro> it = setLibreria.iterator();
            while(it.hasNext()){
                Libro l1 = it.next();
                if(l2.equals(l1)){
                    l2.setNCopie(l2.getNCopie()+l1.getNCopie());
                    try{
                        return modifica(l1, l2);
                    }
                    catch(SQLException e){return false;}
                }
            }
 
        }
        
        String sql = "INSERT INTO libri (titolo, autori, anno, copie, isbn) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, l2.getTitolo());
            ps.setString(2, l2.getAutori());
            ps.setString(3, l2.getAnno().toString());
            ps.setInt(4, l2.getNCopie());
            ps.setString(5, l2.getIsbn());
            ps.executeUpdate();
        }
        catch(SQLException e){return false;}
        setLibreria.add(l2);
        return true;
        
    }
    
    /**
     * @brief Elimina Libro.
     * Se il libro è presente, effettua la sua eliminazione.
     * 
     * Parametro in ingresso:
     *  @param l libro da eliminare.
     * 
     *  @return restituisce true se l'eliminazione è avvenuta correttamente.
     *          false se il libro non è presente.
     */
    @Override
    public boolean elimina(Libro l){
        String sql = "DELETE FROM libri WHERE isbn = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, l.getIsbn());
            ps.executeUpdate();
            
        }
        catch(SQLException e){return false;}
        setLibreria.remove(l);
        return true;
    }
    
       /**
     * @brief Modifica Libro.
     * Se il libro è presente effettua la modifica.
     * 
     * Parametro in ingresso:
     *  @param l1 libro originale da modificare.
     *  @param l2 libro aggiornato da inserire.
     * 
     *  @return restituisce true se la modifica del libro è avvenuta correttamente.
     *          false se il libro non è presente.
     */
    
    @Override
    public boolean modifica(Libro l1, Libro l2) throws SQLException{
        conn.setAutoCommit(false); // inizio transazione

        try {
                elimina(l1);
                aggiungi(l2);
                conn.commit(); // conferma tutte le modifiche
        } 
        catch (SQLException e) {
                conn.rollback(); // annulla tutto se c’è un errore
                return false;
        }
        conn.setAutoCommit(true);
        return true;
    }
    
    @Override
    public void carica() throws SQLException{
        String sql = "SELECT isbn, titolo, autori, copie FROM libri";
        try (PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            setLibreria.add(new Libro(rs.getString("titolo"),rs.getString("autori"),
                    LocalDate.parse(rs.getString("anno")),rs.getInt("copie"),rs.getString("isbn")));
        }
}
    }
    
    /**
     * @brief Restituisce una rappresentazione testuale della libreria.
     * Il metodo crea una stringa contenente la lista di tutti i libri della libreria.
     * Ogni elemento su una nuova linea.
     * Per ogni elemento viene utilizzato il metodo toString() della classe Libro.
     * 
     * @return Stringa che contiene tutti i libri della setLibreria.
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        
        for(Libro l : setLibreria){
            builder.append(l+"\n");
        }
        return builder.toString();
    }
}
