package gruppo20.biblioteca.model.Utenti;

import gruppo20.biblioteca.controller.Contesto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author giuli
 */
public class UtentiTest {
     private Contesto co = new Contesto();
    private Utenti utenti = co.getGestUtenti();
    

    /**
     * Pulisce l'anagrafica prima di ogni test.
     */
    @BeforeEach
    void resetUtenti(){
        utenti.getSetUtenti().clear();
    }
    /**
     * Test del metodo getSetUtenti della classe Utenti.
     */
    @Test
    public void testGetSetUtenti() {
        assertNotNull(utenti.getSetUtenti()); //controllo che il set non sia null.
        assertEquals(0,utenti.getSetUtenti().size()); //controllo se inizialmente risulta vuoto.
        
        //aggiunfgo un utente.
        Utente u = new Utente("Andrea", "Esposito", "123456", "andrea.esposito@unisa.it", 0);
        utenti.aggiungi(u);
        
        //verifico che il set contenga l'utente.
        assertTrue(utenti.getSetUtenti().contains(u));
        assertEquals(1, utenti.getSetUtenti().size());
    }

    /**
     * Test del metodo aggiungi della classe Utenti.
     * Caso in cui l'utente non è già presente e deve essere aggiunto.
     */
    @Test
    public void testAggiungiNuovoUtente() {
        Utente u = new Utente("Andrea", "Esposito", "123456", "andrea.esposito@unisa.it", 0);
        assertTrue(utenti.aggiungi(u)); //controllo che l'aggiunta sia avvenuta.
        
        //controllo che ci sia l'utente in utenti.
        assertEquals(1, utenti.getSetUtenti().size());
        assertTrue(utenti.getSetUtenti().contains(u));
    }

    /**
     * Test del metodo elimina della classe Utenti.
     */
    @Test
    public void testElimina() {
        Utente u = new Utente("Andrea", "Esposito", "123456", "andrea.esposito@unisa.it", 0);
        utenti.aggiungi(u); 
        
        //controllo che l'eliminazione sia avvenuta.
        assertTrue(utenti.elimina(u)); 
        //controllo che l'utente sia stato eliminato.
        assertTrue(utenti.getSetUtenti().isEmpty());
    }

    /**
     * Test del metodo modifica della classe Utenti.
     */
    @Test
    public void testModifica() throws Exception {
        Utente u1 = new Utente("Giulia", "Esposito", "123456", "giulia.esposito@unisa.it", 0);
        utenti.aggiungi(u1);
        
        Utente u2 = new Utente("Giulio", "Espositi", "123457", "giulio.espositi@unisa.it", 3);
        assertTrue(utenti.modifica(u1, u2));
        
        Utente u = utenti.getSetUtenti().iterator().next();
        
        assertEquals("Giulio", u.getNome());
        assertEquals("Espositi", u.getCognome());
        assertEquals("123457", u.getMatricola());
        assertEquals("giulio.espositi@unisa.it", u.getMail());
        assertEquals(3, u.getNPrestiti());
    }

    /**
     * Test del metodo addRestituzione della classe Utenti.
     */
    @Test
    public void testAddRestituzione() {
        Utente u = new Utente("Andrea", "Esposito", "123456", "andrea.esposito@unisa.it", 2);
        utenti.aggiungi(u); 
        
        assertTrue(utenti.addRestituzione("123456"));
        
        Utente u1 = utenti.getSetUtenti().iterator().next();
        assertEquals(1, u1.getNPrestiti());
    }
    
    /**
     * Test del metodo ddPrestito della classe Utenti.
     * Nel caso in cui l'utente selezionato non esista.
     * Il codice non lancia eccezioni e non apporta modifica ad alcun dato.
     */
    @Test 
    void testAddRestituzioneUtenteNonPresente(){
        Utente u = new Utente("Andrea", "Esposito", "123456", "andrea.esposito@unisa.it", 2);
        utenti.aggiungi(u); 
        
        assertTrue(utenti.addRestituzione("123654"));
        
        assertEquals(1, utenti.getSetUtenti().size());
        assertTrue(utenti.getSetUtenti().contains(u));
        
        Utente u1 = utenti.getSetUtenti().iterator().next();
        assertEquals(2, u1.getNPrestiti());
    }

    /**
     * Test del metodo ddPrestito della classe Utenti.
     */
    @Test
    public void testAddPrestito() {
        Utente u = new Utente("Andrea", "Esposito", "123456", "andrea.esposito@unisa.it", 2);
        utenti.aggiungi(u); 
        
        assertTrue(utenti.addPrestito("123456"));
        
        Utente u1 = utenti.getSetUtenti().iterator().next();
        assertEquals(3, u1.getNPrestiti());
    }
    
    /**
     * Test del metodo ddPrestito della classe Utenti.
     * Nel caso in cui l'utente selezionato non esista.
     * Il codice non lancia eccezioni e non apporta modifica ad alcun dato.
     */
    @Test
    public void testAddPrestitoUtenteNonPresente() {
        Utente u = new Utente("Andrea", "Esposito", "123456", "andrea.esposito@unisa.it", 2);
        utenti.aggiungi(u); 
        
        assertTrue(utenti.addPrestito("123654"));
        
        assertEquals(1, utenti.getSetUtenti().size());
        assertTrue(utenti.getSetUtenti().contains(u));
        
        Utente u1 = utenti.getSetUtenti().iterator().next();
        assertEquals(2, u1.getNPrestiti());
    }
    

    /**
     * Test del metodo toString della classe Utenti.
     */
    @Test
    public void testToString() {
        Utente u1 = new Utente("Anna", "Esposito", "123456", "anna.esposito@unisa.it", 2);
        utenti.aggiungi(u1);
        Utente u2 = new Utente("Giulia", "Esposito", "123654", "giulia.esposito@unisa.it", 0);
        utenti.aggiungi(u2);
        
        assertEquals(2, utenti.toString().split("\n").length); //controlla che ci siano due righe, una per ogni utente.
    }
    
}
