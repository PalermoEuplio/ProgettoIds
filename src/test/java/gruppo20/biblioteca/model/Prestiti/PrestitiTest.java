/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package gruppo20.biblioteca.model.Prestiti;

import gruppo20.biblioteca.controller.Contesto;
import java.time.LocalDate;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Riccardo
 */
public class PrestitiTest {
    
    private Contesto co = new Contesto();
    private Prestiti prestiti = co.getGestPrestiti();
    Prestito p1 = new Prestito(LocalDate.now(), LocalDate.now(), "false", "Analisi1", "0000000000000","0612700000");       

     public PrestitiTest() {
    }
    
    
    /**
     * Reset prestiti prima di ogni test.
     * Genera un prestito base
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
     */
    @Test
    public void testRestituisci() {
        System.out.println("restituisci");
        Prestito p = null;
        LocalDate dataRestituzione = null;
        Prestiti instance = null;
        boolean expResult = false;
        boolean result = instance.restituisci(p, dataRestituzione);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of aggiungi method, of class Prestiti.
     */
    @Test
    public void testAggiungi() {
        System.out.println("aggiungi");
        Prestito p = null;
        Prestiti instance = null;
        boolean expResult = false;
        boolean result = instance.aggiungi(p);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of elimina method, of class Prestiti.
     */
    @Test
    public void testElimina() {
        System.out.println("elimina");
        Prestito p = null;
        Prestiti instance = null;
        boolean expResult = false;
        boolean result = instance.elimina(p);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifica method, of class Prestiti.
     */
    @Test
    public void testModifica() throws Exception {
        System.out.println("modifica");
        Prestito p1 = null;
        Prestito p2 = null;
        Prestiti instance = null;
        boolean expResult = false;
        boolean result = instance.modifica(p1, p2);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of carica method, of class Prestiti.
     */
    @Test
    public void testCarica() throws Exception {
        System.out.println("carica");
        Prestiti instance = null;
        instance.carica();
        fail("The test case is a prototype.");
    }
    
}
