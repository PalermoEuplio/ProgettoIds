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
     * Per il caso in cui l'oggetto è NULL.
     * Testaimo questa condizione per verificare che il metodo non fallisca.
     * Ma termini con un return false.
     */
    @Test
    public void testEqualsNull() {
        Autore a1 = new Autore("Ernest", "Hemingway");
        assertFalse(a1.equals(null));
    }
    
    /**
     * Test del metodo equals della classe Autore.
     * Per il caso in cui l'oggetto è diverso da Autore.
     * Testaimo questa condizione per verificare che il metodo non fallisca.
     * Ma termini con un return false.
     */
    
    @Test
    void testEqualsNonAutore(){
        String s = "123456";
        Autore a1 = new Autore("Ernest", "Hemingway");
        assertFalse(a1.equals(s));
    }
    
    /**
     * Test del metodo equals della classe Autore.
     * Nel caso in cui sono uguali.
     */
    @Test
    public void testEqualsUguali() {
        Autore a1 = new Autore("Ernest", "Hemingway");
        Autore a2 = new Autore("Ernest", "Hemingway");
        assertTrue(a1.equals(a2));
    }
    
     /**
     * Test del metodo equals della classe Autore.
     * Nel caso in cui NON sono uguali.
     */
    @Test
    public void testEqualsDiverso() {
        Autore a1 = new Autore("Ernest", "Hemingway");
        Autore a2 = new Autore("Ernest", "Renan");
        assertFalse(a1.equals(a2));
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
