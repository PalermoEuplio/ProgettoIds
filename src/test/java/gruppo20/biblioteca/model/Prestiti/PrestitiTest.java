/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package gruppo20.biblioteca.model.Prestiti;

import gruppo20.biblioteca.controller.Contesto;
import java.time.LocalDate;
//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @brief Test per la classe Prestiti
 * 
 * @author Gruppo20
 */
public class PrestitiTest {
    //inizializzazione appoggio
    private Contesto co = new Contesto(); 
    private Prestiti prestiti = co.getGestPrestiti();
    Prestito p1 = new Prestito(LocalDate.now(), LocalDate.now(), "false", "Analisi1", "0000000000000","0612700000");       

     public PrestitiTest() {
    }
    
    
    /**
     * Reset prestiti prima di ogni test.
     */
    @BeforeEach
    void resetPrestiti(){
        prestiti.getSetPrestiti().clear();
    }
    
   
    
    /**
     * Test of getSetPrestiti method, of class Prestiti.
     * verifica se il set Contiene il prestito inserito
     */
    @Test
    public void testGetSetPrestiti() {
        assertNotNull(prestiti.getSetPrestiti()); 
        assertEquals(0,prestiti.getSetPrestiti().size());       
        
        prestiti.aggiungi(p1);
        
        //set contiene prestito
        assertTrue(prestiti.getSetPrestiti().contains(p1));
        assertEquals(1, prestiti.getSetPrestiti().size());
    }

    /**
     * Test of restituisci method, of class Prestiti.
     * Viene aggiunto il prestito p1
     * Caso in cui il prestito viene restituito
     */
    @Test
    public void testRestituisci() {
        System.out.println("restituisci");
        prestiti.aggiungi(p1);
        LocalDate dataRestituzione = LocalDate.now();
        Prestiti instance = prestiti;
        boolean expResult = true;
        boolean result = instance.restituisci(p1, dataRestituzione);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of aggiungi method, of class Prestiti.
     * Caso in cui il prestito viene aggiunto
     */
    @Test
    public void testAggiungi() {
        System.out.println("aggiungi");
        Prestito p = p1;
        Prestiti instance = prestiti;        
        boolean expResult = true;
        boolean result = instance.aggiungi(p);
        assertEquals(expResult, result);
        
    }
    /**
     * Test of aggiungi method, of class Prestiti.
     * Caso in cui il prestito non viene aggiunto
     */
    @Test
    public void testAggiungi2() {
        System.out.println("aggiungi");
        Prestito p = p1;
        Prestiti instance = prestiti;        
        boolean expResult =false;
        instance.aggiungi(p);
        boolean result = instance.aggiungi(p);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of elimina method, of class Prestiti.
     * Caso di eliminazione di un prestito inserito
     */
    @Test
    public void testElimina() {
        System.out.println("elimina");
        Prestito p = p1;
        Prestiti instance = prestiti;
        assertTrue(instance.aggiungi(p1));
        
        boolean expResult = true;
        boolean result = instance.elimina(p1);
        assertEquals(expResult, result);        
        assertTrue(prestiti.getSetPrestiti().isEmpty());
        
    }
    /**
     * Test of elimina method, of class Prestiti.
     * Caso di eliminazione di un prestito non presente
     */
    @Test
    public void testElimina2() {
        System.out.println("elimina");
        Prestito p = p1;
        Prestiti instance = prestiti;        
        
        boolean expResult = false;
        boolean result = instance.elimina(p1);
        assertEquals(expResult, result);        
        assertTrue(prestiti.getSetPrestiti().isEmpty());
        
    }

    /**
     * Test of modifica method, of class Prestiti.
     * Caso di modifica prestito presente in db, modifica isbn
     * @throws java.lang.Exception
     */
    @Test
    public void testModifica() throws Exception {
        System.out.println("modifica");
        Prestito pi = new Prestito(LocalDate.now(), LocalDate.now(), "false", "Analisi1", "0000000000000","0612700000");       
        Prestito pf = new Prestito(LocalDate.now(), LocalDate.now(), "false", "Analisi1", "0000000000001","0612700000");       

                
        Prestiti instance = prestiti;
        assertTrue(prestiti.aggiungi(pi));//verifica che l'utente iniziale venga aggiunto
        assertTrue(prestiti.getSetPrestiti().contains(pi));//verifica che l'utente iniziale sia inserito
        

        
        boolean expResult = true;
        boolean result = instance.modifica(pi, pf);
        assertEquals(expResult, result);
        assertTrue(prestiti.getSetPrestiti().contains(pf)); //verifica che sia presente l'utente modificato
        
        
    }
    /**
     * Test of modifica method, of class Prestiti.
     * Caso di modifica prestito presente in db, modifica matricola
     * @throws java.lang.Exception
     */
    @Test
    public void testModifica2() throws Exception {
        System.out.println("modifica");
        Prestito pi = new Prestito(LocalDate.now(), LocalDate.now(), "false", "Analisi1", "0000000000000","0612700000");       
        Prestito pf = new Prestito(LocalDate.now(), LocalDate.now(), "false", "Analisi1", "0000000000000","0612700001");       

                
        Prestiti instance = prestiti;
        assertTrue(prestiti.aggiungi(pi));//verifica che l'utente iniziale venga aggiunto
        assertTrue(prestiti.getSetPrestiti().contains(pi));//verifica che l'utente iniziale sia inserito
        

        boolean expResult = true;
        boolean result = instance.modifica(pi, pf);
        assertEquals(expResult, result);
        assertTrue(prestiti.getSetPrestiti().contains(pf)); //verifica che sia presente l'utente modificato

        
    }
    

    
    
}
