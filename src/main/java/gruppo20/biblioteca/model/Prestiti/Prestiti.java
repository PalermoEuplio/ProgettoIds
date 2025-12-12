package gruppo20.biblioteca.model.Prestiti;

import gruppo20.biblioteca.model.Libri.Libreria;
import java.sql.Connection;
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
public class Prestiti extends GestioneDB<Prestito> {

    /**
     * @brief Insieme dei prestiti attivi. Si utilizza un ObservableSet per
     * garantire l'unicità dei prestiti e l'osservabilità.
     */
    private final ObservableSet<Prestito> setPrestiti;

    /**
     * @brief Gestore degli utenti utilizzato per modificare il numero di
     * prestiti attivi per utente.
     */
    private final Utenti gestUtenti;

    /**
     * @brief Gestore della libreria utilizzato per modificare il numero di
     * copie per libro.
     */
    private final Libreria gestLibreria;

    /**
     * @brief Connessione per la gestione del database locale.
     */
    private final Connection conn;

    public Prestiti(Connection conn, Utenti gestUtenti, Libreria gestLibreria) throws SQLException {
        this.setPrestiti = FXCollections.observableSet(new HashSet<>());
        this.gestUtenti = gestUtenti;
        this.gestLibreria = gestLibreria;

        this.conn = conn;
        if (!super.tableExists(conn, "prestiti")) {
            String sqlPrestiti = """
            CREATE TABLE IF NOT EXISTS prestiti (
                dataPrestito TEXT,
                dataPrevistaRestituzione TEXT,
                effettivaRestituzione TEXT,
                titoloLibro TEXT,
                isbn TEXT,
                matricola TEXT

            );
        """;
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sqlPrestiti);
            }
        } else {
            carica();
        }

    }

    /**
     * @return restituisce l'ObservableSet relativo alla classe prestiti.
     */
    public ObservableSet<Prestito> getSetPrestiti() {
        return setPrestiti;
    }

    /**
     * @brief Registra la restituzione di un prestito. Il metodo cerca il
     * prestito, se lo trova imposta la data di restituzione a quella inserita.
     * Il numero di prestiti relativo all'utente della restituzione viene
     * decrementato di uno. Il numero i copie relative al libro viene aumentato
     * di 1. Se non viene trovato il prestito ritorna false.
     *
     * @param p Prestito da restituire.
     * @param dataRestituzione Data di restituzione.
     * @return true se la restituzione è avvenuta, false in caso contrario.
     */
    public boolean restituisci(Prestito p, LocalDate dataRestituzione) {
        if (setPrestiti.contains(p)) {
            Iterator<Prestito> it = setPrestiti.iterator();
            while (it.hasNext()) {
                Prestito pAp = it.next(); //variabile di appoggio
                if (p.equals(pAp)) {
                    p.setEffettivaRestituzione(dataRestituzione);
                    gestLibreria.addRestituzione(p.getIsbn());
                    gestUtenti.addRestituzione(p.getMatricola());
                    try {
                        return modifica(pAp, p);
                    } catch (SQLException e) {
                        return false;
                    }
                }
            }

        }
        return false;
    }

    /**
     * @brief Aggiunge un prestito. Il metodo non controlla che l'utente non
     * abbia più prestiti di quelli consentiti.
     *
     * Parametro in ingresso:
     * @param p prestito da dover aggiungere.
     *
     * @return restituisce true se il prestito è stato inserito. false se invece
     * non è stato inserito, è già presente o l'utente ha troppi prestiti.
     */
    @Override
    public boolean aggiungi(Prestito p) {
        String sql = "INSERT INTO prestiti (dataPrestito, dataPrevistaRestituzione, effettivaRestituzione, titoloLibro, isbn, matricola) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getDataPrestito().toString());
            ps.setString(2, p.getDataPrevistaRestituzione().toString());
            ps.setString(3, p.getEffettivaRestituzione().getEffettivaRestituzione());
            ps.setString(4, p.getTitoloLibro());
            ps.setString(5, p.getIsbn());
            ps.setString(6, p.getMatricola());
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return setPrestiti.add(p) && gestLibreria.addPrestito(p.getIsbn())&& gestUtenti.addPrestito(p.getMatricola());

    }

    /**
     * @brief elimina un prestito. Se il prestito esiste, ne effettua
     * l'eliminazione.
     *
     * Parametro in ingresso:
     * @param p prestito da dover eliminare.
     *
     * @return restituisce true se il prestito è stato eliminato. false se
     * fallisce.
     */
    @Override
    public boolean elimina(Prestito p) {
        String sql = "DELETE FROM prestiti WHERE dataPrestito = ? AND isbn = ? AND matricola = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getDataPrestito().toString());
            ps.setString(2, p.getIsbn());
            ps.setString(3, p.getMatricola());
            ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return setPrestiti.remove(p) && gestLibreria.addRestituzione(p.getIsbn()) && gestUtenti.addRestituzione(p.getMatricola());
    }

    /**
     * @brief Modifica del prestito. Se il prestito è presente effettua la
     * modifica di uno o più suoi dati. Se viene modificato l'isbn o la
     * matricola relativa al prestito, il prestito viene migrato da libro/utente
     * di p1 a libro/utente di p2 sfruttando l'isbn/la matricola.
     *
     * Parametro in ingresso:
     * @param p1 Prestito da modificare.
     * @param p2 Prestito modificato.
     *
     * @return restituisce true se la modifica del prestito è avvenuta
     * correttamente. false se il prestito non è presente o se fallisce.
     */
    @Override
    public boolean modifica(Prestito p1, Prestito p2) throws SQLException {
        conn.setAutoCommit(false); // inizio transazione
        boolean status;
        try {
            status = elimina(p1) && aggiungi(p2);
            conn.commit(); // conferma tutte le modifiche
        } catch (SQLException e) {
            conn.rollback(); // annulla tutto se c’è un errore
            return false;
        }
        conn.setAutoCommit(true);
        if (!p1.getIsbn().equals(p2.getIsbn())) {
            status = gestLibreria.addRestituzione(p1.getIsbn()) && gestLibreria.addPrestito(p2.getIsbn());
        }
        if (!p1.getMatricola().equals(p2.getMatricola())) {
            status = gestUtenti.addRestituzione(p1.getMatricola()) && gestUtenti.addPrestito(p2.getMatricola());
        }
        return status;
    }

    /**
     * @throws java.sql.SQLException
     * @brief Implementazione del metodo carica di GestioneDB.
     */
    @Override
    public final void carica() throws SQLException {
        String sql = "SELECT dataPrestito, dataPrevistaRestituzione, effettivaRestituzione, titoloLibro, isbn, matricola FROM prestiti";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                setPrestiti.add(new Prestito(LocalDate.parse(rs.getString("dataPrestito")),LocalDate.parse(rs.getString("dataPrevistaRestituzione")), rs.getString("effettivaRestituzione"), rs.getString("titoloLibro"), rs.getString("isbn"), rs.getString("matricola")));
            }
        }
    }

}
