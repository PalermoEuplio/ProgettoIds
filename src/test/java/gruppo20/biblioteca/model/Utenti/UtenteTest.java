package gruppo20.biblioteca.model.Utenti;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @brief Questo file contiene l'implementazione della classe UtenteTest, per il test della classe Utente.
 * @author Gruppo20
 */
public class UtenteTest {
    /**
     * Test del metodo getNPrestiti della classe Utente.
     */
    @Test
    public void testGetNPrestiti() {
        Utente u1 = new Utente("Andrea", "Esposito", "123456", "andrea.esposito@unisa.it", 0);
        assertEquals(0, u1.getNPrestiti());
    }

    /**
     * Test del metodo getNome della classe Utente.
     */
    @Test
    public void testGetNome() {
        Utente u1 = new Utente("Andrea", "Esposito", "123456", "andrea.esposito@unisa.it", 0);
        assertEquals("Andrea", u1.getNome());
    }

    /**
     * Test del metodo getCognome della classe Utente.
     */
    @Test
    public void testGetCognome() {
        Utente u1 = new Utente("Andrea", "Esposito", "123456", "andrea.esposito@unisa.it", 0);
        assertEquals("Esposito", u1.getCognome());
    }

    /**
     * Test del metodo getMatricola della classe Utente.
     */
    @Test
    public void testGetMatricola() {
        Utente u1 = new Utente("Andrea", "Esposito", "123456", "andrea.esposito@unisa.it", 0);
        assertEquals("123456", u1.getMatricola());
    }

    /**
     * Test del metodo getMail della classe Utente.
     */
    @Test
    public void testGetMail() {
        Utente u1 = new Utente("Andrea", "Esposito", "123456", "andrea.esposito@unisa.it", 0);
        assertEquals("andrea.esposito@unisa.it", u1.getMail());
    }

    /**
     * Test del metodo equals della classe Utente.
     * Per il caso in cui l'oggetto è NULL.
     */
    @Test
    public void testEqualsNull() {
        Utente u1 = new Utente("Andrea", "Esposito", "123456", "andrea.esposito@unisa.it", 0);
        assertFalse(u1.equals(null));
    }

    /**
     * Test del metodo equals della classe Utente.
     * Nel caso in cui i dati degli utenti sono uguali.
     */
    @Test
    public void testEqualsUguali() {
        Utente u1 = new Utente("Andrea", "Esposito", "123456", "andrea.esposito@unisa.it", 0);
        Utente u2 = new Utente("Andrea", "Esposito", "123456", "andrea.esp@unina.it", 3);
        assertTrue(u1.equals(u2));
    }
    
    /**
     * Test del metodo equals della classe Utente.
     * Nel caso in cui i dati degli utenti NON sono uguali.
     */
    @Test
    public void testEqualsDiversi() {
        Utente u1 = new Utente("Andrea", "Esposito", "123456", "andrea.esposito@unisa.it", 0);
        Utente u2 = new Utente("Anna", "Esposito", "198765", "andrea.esposito@unisa.it", 0);
        assertFalse(u1.equals(u2));
    }
    /**
     * Test del metodo hashCode della classe Utente.
     */
    @Test
    public void testHashCode() {
        Utente u1 = new Utente("Andrea", "Esposito", "123456", "andrea.esposito@unisa.it", 0);
        Utente u2 = new Utente("Andrea", "Esposito", "123456", "andrea.esp@unina.it", 3);
        assertEquals(u1.hashCode(), u2.hashCode());
    }
    
}
