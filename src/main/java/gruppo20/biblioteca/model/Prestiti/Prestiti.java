
package gruppo20.biblioteca.model.Prestiti;

import gruppo20.biblioteca.model.Libri.Libreria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import gruppo20.biblioteca.model.Utenti.Utenti;
import gruppo20.biblioteca.model.Utility.GestioneDB;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
/**
 * @brief Questo file contiene l'implementazione della classe Prestiti.
 * @author Gruppo20
 */
public class Prestiti extends GestioneDB<Prestito>{
    /**
     * @brief Numero massimo di prestiti attivi che un utente può avere contemporaneamente.
     */
    private static final int maxPrestiti = 3; 
    
    /**
     * @brief Insieme dei prestiti attivi.
     * Si utilizza un HashSet per garantire l'unicità dei prestiti.
     */
    private ObservableSet<Prestito> setPrestiti;

    private Utenti gestUtenti;
    private Libreria gestLibreria;

   private Connection conn;
    
    public Prestiti(String DBPath,Utenti gestUtenti,Libreria gestLibreria) throws SQLException{
        this.setPrestiti = FXCollections.observableSet(new HashSet<>());
        this.gestUtenti= gestUtenti;
        this.gestLibreria = gestLibreria;
        
        this.conn=DriverManager.getConnection("jdbc:sqlite:"+DBPath);
        if(!super.tableExists(conn, "prestiti")){
            String sqlPrestiti = """
            CREATE TABLE IF NOT EXISTS prestiti (
                dataPrestito TEXT,
                restituzione TEXT,
                titoloLibro TEXT,
                isbn TEXT,
                matricola TEXT,     
                periodoPrestito INTEGER

            );
        """;
            try (Statement stmt = conn.createStatement()) {
            stmt.execute(sqlPrestiti);
            }
        }
        else{
            carica();
        }
        

    }

    public ObservableSet<Prestito> getSetPrestiti() {
        return setPrestiti;
    }
    
    
    
    /**
     * @brief Registra la restituzione di un prestito.
     * Il metodo cerca il prestito, se lo trova imposta la data di restituzione a quella inserita.
     * Il numero di prestiti relativo all'utente della restituzione viene decrementato di uno.
     * Se non viene trovato il prestito ritorna false.
     *
     * @param p Prestito da restituire.
     * @param dataRestituzione Data di restituzione.
     * @return true se la restituzione è avvenuta, false in caso contrario.
     */
    public boolean restituisci(Prestito p,LocalDate dataRestituzione){
        if(setPrestiti.contains(p)){
            Iterator<Prestito> it = setPrestiti.iterator();
            while(it.hasNext()){
                Prestito pAp = it.next(); //variabile di appoggio
                if(p.equals(pAp)){
                    p.setRestituzione(dataRestituzione);
                    gestLibreria.setRestituzione(p.getIsbn());
                    gestUtenti.setRestituzione(p.getMatricola());
                    try{return modifica(pAp, p);}
                    catch(SQLException e) {return false;}
                }
            }
            
        }
        return false;
    }
    
    /**
    *@brief Aggiunge un prestito.
    * Il metodo controlla che l'utente non abbia più prestiti di quelli consentiti.
    * 
    * Parametro in ingresso:
    *   @param p prestito da dover aggiungere.
    * 
    *   @return restituisce true se il prestito è stato inserito. 
    *           false se invece non è stato inserito, è già presente o l'utente ha troppi prestiti. 
    */ 

    public boolean aggiungi (Prestito p){
        String sql = "INSERT INTO prestiti (dataPrestito, restituzione, titoloLibro, isbn, matricola, periodoPrestito) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getDataPrestito().toString());
            ps.setString(2, p.getRestituzione());
            ps.setString(3, p.getTitoloLibro());
            ps.setString(4, p.getIsbn());
            ps.setString(5, p.getMatricola());
            ps.setInt(6, p.getPeriodoPrestito());
            ps.executeUpdate();
        }
        catch(SQLException e){return false;}
        setPrestiti.add(p);
        gestLibreria.addPrestito(p.getIsbn());
        gestUtenti.addPrestito(p.getMatricola());
        return true;
        
    }
        
    
    /**
    *@brief elimina un prestito.
    *Se il prestito esiste, ne effettua l'eliminazione.
    * 
    * Parametro in ingresso:
    *   @param p prestito da dover eliminare.
    * 
    *   @return restituisce true se il prestito è stato eliminato. 
    *           false se invece non è presente.
    */ 

    public boolean elimina (Prestito p){
        String sql = "DELETE FROM prestiti WHERE dataPrestito = ? AND isbn = ? AND matricola = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getDataPrestito().toString());
            ps.setString(2, p.getIsbn());
            ps.setString(3, p.getMatricola());
            ps.executeUpdate();
            
        }
        catch(SQLException e){return false;}
        setPrestiti.remove(p);
        gestLibreria.setRestituzione(p.getIsbn());
        gestUtenti.setRestituzione(p.getMatricola());
        return true;
        
    }
    
    /**
     * @brief Modifica del prestito.
     * Se il prestito è presente effettua la modifica di uno o più suoi dati.
     * 
     * Parametro in ingresso:
     *  @param p1 Prestito da modificare.
     *  @param p2 Prestito modificato.
     * 
     *  @return restituisce true se la modifica del prestito è avvenuta correttamente.
     *          false se il prestito non è presente.
     */
    public boolean modifica(Prestito p1, Prestito p2) throws SQLException{
        conn.setAutoCommit(false); // inizio transazione
        boolean status;
        try {
                status = elimina(p1) && aggiungi(p2);
                conn.commit(); // conferma tutte le modifiche
        } 
        catch (SQLException e) {
                conn.rollback(); // annulla tutto se c’è un errore
                return false;
        }
        conn.setAutoCommit(true);
        if(!p1.getIsbn().equals(p2.getIsbn())){
            gestLibreria.setRestituzione(p1.getIsbn());
            gestLibreria.addPrestito(p2.getIsbn());
        }
        if(!p1.getMatricola().equals(p2.getMatricola())){
            gestUtenti.setRestituzione(p1.getMatricola());
            gestUtenti.addPrestito(p2.getMatricola());
        }
        return status;
    }
    
    @Override
    public void carica() throws SQLException{
        String sql = "SELECT dataPrestito, restituzione, titoloLibro, isbn, matricola, periodoPrestito FROM prestiti";
        try (PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                setPrestiti.add(new Prestito(LocalDate.parse(rs.getString("dataPrestito")),rs.getString("restituzione"),rs.getString("titoloLibro"),rs.getString("isbn"),rs.getString("matricola"),rs.getInt("periodoPrestito")));
            }
        }
    }
   /**
    *@brief Controlla se un prestio è già presente.
    *
    *   @param p prestito da dover controllare.
    * 
    *   @return restituisce true se il prestito è già presente. 
    *           false se invece non è presente.
    */
    public boolean hasPrestito (Prestito p){
        return setPrestiti.contains(p);
    }
      
}
