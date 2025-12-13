package gruppo20.biblioteca.model.Libri;

import gruppo20.biblioteca.controller.Contesto;
import java.time.LocalDate;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @brief Questo file contiene l'implementazione della classe LibreriaTest, per il test della classe Libreria.
 * @author Gruppo20
 */
public class LibreriaTest {
    /**
     * Creazione del Contesto dell'applicazione, dove avverrà l'esecuzione del test.
     */
    private final Contesto co = new Contesto();
    /**
     * Dal contesto ottengo il gestore Libreria.
     * Libreria gestisce una collezione di Libro, con un ObservableSet.
     */
    private final Libreria libreria = co.getGestLibreria();
    
    /**
     * Fase di setup.
     * Restituisce la collazione che contiene tutti i libri.
     * La svuota prima di ogni test.
     */
    @BeforeEach
    void resetLibreria(){
        libreria.getSetLibreria().clear();
    }
    
    /**
     * Test del metodo getSetLibreria della classe Libreria.
     */
    @Test
    void testGetSetLibreria(){
        assertNotNull(libreria.getSetLibreria()); //controllo che il set non sia null.
        assertEquals(0,libreria.getSetLibreria().size()); //controllo se inizialmente la libreria risulta vuota.
        
        //aggiungo un libro.
        Libro l = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        libreria.aggiungi(l);
        
        //verifico che il set contenga il libro.
        assertTrue(libreria.getSetLibreria().contains(l));
        assertEquals(1, libreria.getSetLibreria().size());
    }
    
    /**
     * Test del metodo aggiungi della classe Libreria.
     * Il caso in cui il libro non è presente e deve essere aggiunto
     */
    @Test
    void testAggiungiNuovoLibro() {
        Libro l = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        assertTrue(libreria.aggiungi(l)); //controllo che libro sia stato aggiunto.
        assertEquals(1, libreria.getSetLibreria().size()); //controllo che dimensione libreria sia 1 dopol'aggiunta.
        assertTrue(libreria.getSetLibreria().contains(l)); //controllo che ci sia il libro in libreria.
    }
    
    /**
     * Test del metodo aggiungi della classe Libreria.
     * Il caso in cui il libro è presente e deve essere incrementato il numero di copie.
     */
    @Test
    public void testAggiungiIncrementoCopie() {
        Libro l1 = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        libreria.aggiungi(l1);
        Libro l2 = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),4,"9780060853969");
        libreria.aggiungi(l2);
        
        Libro l = libreria.getSetLibreria().iterator().next(); //recupera il libro dalla libreria.
        assertEquals(6, l.getNCopie());
    }

    /**
     * Test del metodo elimina della classe Libreria.
     */
    @Test
    public void testElimina() {
        Libro l = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        libreria.aggiungi(l);
        
        assertTrue(libreria.elimina(l)); //controllo che l'operazione sia avvenuta.
        assertTrue(libreria.getSetLibreria().isEmpty()); //controllo che l'elemento sia eliminato.
    }

    /**
     * Test del metodo modifica della classe Libreria.
     */
    @Test
    public void testModifica() throws Exception {
        Libro l1 = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        libreria.aggiungi(l1);
        
        Libro l2 = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),4,"9780060853969");
        assertTrue(libreria.modifica(l1, l2)); //verifica che la modifica sia stata apportata.
        
        Libro l = libreria.getSetLibreria().iterator().next(); //recupera il libro dalla libreria.
        assertEquals(4, l.getNCopie()); //controlla che il numero di copie sia stato aggiornato.
    }

    /**
     * Test del metodo addRestituzione della classe Libreria.
     */
    @Test
    public void testAddRestituzione() {
        Libro l = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        libreria.aggiungi(l);
        
        libreria.addRestituzione("9780060853969");
        
        Libro l1 = libreria.getSetLibreria().iterator().next(); //recupera il libro dalla libreria e lo associa a l1 = libro aggiornato.
        assertEquals(3, l1.getNCopie());
    }

    /**
     * Test del metodo addRestituzione della classe Libreria.
     * Caso in cui si cerca di restituire un libro non presente in biblioteca.
     * Il codice non lancia eccezioni e non apporta modifica ad alcun libro presente.
     */
    @Test
    public void testAddRestituzioneLibroNonPresente(){
        Libro l = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        libreria.aggiungi(l);
        
        assertTrue(libreria.addRestituzione("9781160853969")); //se è true l'operazione non è fallita.
        
        assertEquals(1, libreria.getSetLibreria().size());
        assertTrue(libreria.getSetLibreria().contains(l));
        
        Libro l1 = libreria.getSetLibreria().iterator().next(); //recupera il libro dalla libreria.
        assertEquals(2, l1.getNCopie()); //controllo che il libro presente nella libreria non sia stato modificato.
        assertEquals("Good Omens", l1.getTitolo());
        assertEquals("Neil Gaiman,Terry Pratchett", l1.getAutori());
    }
    
    /**
     * Test del metodo addPrestito della classe Libreria.
     * Controlla che il numero di copie venga decrementato all'aggiunta di un prestito.
     */
    @Test
    public void testAddPrestito() {
        Libro l = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        libreria.aggiungi(l);
        
        libreria.addPrestito("9780060853969");
        
        Libro l1 = libreria.getSetLibreria().iterator().next(); //recupera il libro dalla libreria e lo associa a l1 = libro aggiornato.
        assertEquals(1, l1.getNCopie());
    }
    
    /**
     * Test del metodo addPrestito della classe Libreria.
     * Controlla l'aggiunta di un prestitoper un libro non presente in biblioteca.
     * Il codice non lancia eccezioni e non apporta modifica ad alcun libro.
     */
    @Test
    public void testAddPrestitoLibroNonPresente(){
        Libro l = new Libro("Good Omens","Neil Gaiman,Terry Pratchett",LocalDate.of(1990,5,10),2,"9780060853969");
        libreria.aggiungi(l);
        
        assertTrue(libreria.addPrestito("9781160853969")); //se è true l'operazione non è fallita.
        
        assertEquals(1, libreria.getSetLibreria().size());
        assertTrue(libreria.getSetLibreria().contains(l));
        
        Libro l1 = libreria.getSetLibreria().iterator().next(); //recupera il libro dalla libreria.
        assertEquals(2, l1.getNCopie()); //controllo che il libro presente nella libreria non sia stato modificato.
        assertEquals("Good Omens", l1.getTitolo());
        assertEquals("Neil Gaiman,Terry Pratchett", l1.getAutori());
    }
    
}
