package gruppo20.biblioteca.model.Libri;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * @brief Questo file contiene l'implementazione della classe AutoreTest, per il test della classe Autore.
 * @author Gruppo20
 */
public class AutoreTest {

    /**
     * Test del metodo getNome della classe Autore.
     */
    @Test
    void testGetNome() {
        Autore a = new Autore("Ernest", "Hemingway");
        assertEquals("Ernest", a.getNome());
    }

    /**
     * Test del metodo getCognome della classe Autore.
     */
    @Test
    public void testGetCognome() {
        Autore a = new Autore("Ernest", "Hemingway");
        assertEquals("Hemingway", a.getCognome());
    }

    /**
     * Test del metodo convert della classe Autore.
     */
    @Test
    public void testConvert() {
        Autore a = Autore.convert("Ernest Hemingway");
        assertEquals("Ernest", a.getNome());
        assertEquals("Hemingway", a.getCognome());
    }

    /**
     * Test del metodo hashCode della classe Autore.
     */
    @Test
    public void testHashCode() {
        Autore a1 = new Autore("Ernest", "Hemingway");
        Autore a2 = new Autore("Ernest", "Hemingway");
        assertEquals(a1.hashCode(), a2.hashCode());
    }

    /**
     * Test del metodo equals della classe Autore.
     */
    @Test
    public void testEquals() {
        Autore a1 = new Autore("Ernest", "Hemingway");
        Autore a2 = new Autore("Ernest", "Hemingway");
        assertTrue(a1.equals(a2));
    }

    /**
     * Test del metodo toString della classe Autore.
     */
    @Test
    public void testToString() {
        Autore a = new Autore("Ernest", "Hemingway");
        assertEquals("Ernest Hemingway", a.toString());
    }
    
}
