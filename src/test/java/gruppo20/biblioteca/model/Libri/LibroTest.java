
package gruppo20.biblioteca.model.Libri;

import java.time.LocalDate;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @brief Questo file contiene l'implementazione della classe LibroTest, per il test della classe Libro.
 * @author Gruppo20
 */
public class LibroTest {
    /**
     * Test del metodo setAutori della classe Libro.
     * Controllo che una volta modificata la stringa degli autori,
     * il valore venga realmente modificato.
     */
    @Test
    public void testSetAutori() {
        Libro l = new Libro("Good Omens","Agnes Nutter",LocalDate.of(1990,5,10),2,"9780060853969");
        l.setAutori("Neil Gaiman,Terry Pratchett");
        assertEquals("Neil Gaiman,Terry Pratchett", l.getAutori());
    }
    
    /**
     * Test del metodo setAutori della classe Libro.
     * Controlla che cambuando il campo autori in null,
     * la sezione autori rimanga invariata.
     */
    @Test
    public void testSetAutoriNull(){
        Libro l = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        l.setAutori(null);
        assertEquals("Neil Gaiman,Terry Pratchett", l.getAutori());
    }

    /**
     * Test del metodo setNCopie della classe Libro.
     * Controllo che una volta modificato il numero copie, il valore venga realmente modificato.
     */
    @Test
    public void testSetNCopie() {
        Libro l = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        l.setNCopie(5);
        assertEquals(5, l.getNCopie());
    }

    /**
     * Test del metodo getTitolo della classe Libro.
     */
    @Test
    public void testGetTitolo() {
        Libro l = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        assertEquals("Good Omens", l.getTitolo());
    }

    /**
     * Test del metodo getAutori della classe Libro.
     */
    @Test
    public void testGetAutori() {
        Libro l = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        assertEquals("Neil Gaiman,Terry Pratchett", l.getAutori());
    }

    /**
     * Test del metodo getAnno della classe Libro.
     */
    @Test
    public void testGetAnno() {
        Libro l = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        assertEquals(LocalDate.of(1990,5,10), l.getAnno());
    }

    /**
     * Test del metodo getNCopie della classe Libro.
     */
    @Test
    public void testGetNCopie() {
        Libro l = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        assertEquals(2, l.getNCopie());
    }

    /**
     * Test del metodo getIsbn della classe Libro.
     */
    @Test
    public void testGetIsbn() {
        Libro l = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        assertEquals("9780060853969", l.getIsbn());
    }

    /**
     * Test del metodo equals della classe Libro.
     * Nel caso in cui l'ISBN dei libri è uguale.
     */
    @Test
    public void testEqualsIsbnUguale() {
        Libro l1 = new Libro("Buona Apocalisse","Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        Libro l2 = new Libro("Good Omens","Neil Gaiman",LocalDate.of(1990,5,10),12,"9780060853969");
        assertTrue(l1.equals(l2));
    }
    
    /**
     * Test del metodo equals della classe Libro.
     * Nel caso in cui l'ISBN dei libri NON è uguale.
     */
    @Test
    public void testEqualsIsbnDiverso() {
        Libro l1 = new Libro("Buona Apocalisse","Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        Libro l2 = new Libro("Good Omens","Neil Gaiman",LocalDate.of(1990,5,10),12,"9781160853969");
        assertFalse(l1.equals(l2));
    }

    /**
     * Test del metodo equals della classe Libro.
     * Nel caso in cui l'oggetto passato è NULL.
     */
    @Test
    public void testEqualsObjectNull() {
        Libro l1 = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        assertFalse(l1.equals(null));
    }
    
    /**
     * Test del metodo hashCode della classe Libro.
     */
    @Test
    public void testHashCode() {
        Libro l1 = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        Libro l2 = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        assertEquals(l1.hashCode(), l2.hashCode());
    }
    
}
