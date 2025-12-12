/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package gruppo20.biblioteca.model.Prestiti;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;


/**
 *
 * @author Gruppo20
 */
public class PrestitoTest {
    
    public PrestitoTest() {
        System.out.println("inizio prestito test");
        Prestito notRest = new Prestito(LocalDate.MIN, LocalDate.MIN, "false", "Analisi1", "0000000000000","0612700000");
    }
    
    
    
    @Test
    public void testSetEffettivaRestituzione() {
        LocalDate input = LocalDate.of(2025,12,12);
        String ritorno = input.toString();
        System.out.println("setEffettivaRestituzione");
        
        LocalDate dataRestituzione = LocalDate.now();
        Prestito instance = new Prestito(LocalDate.MIN, LocalDate.MIN, "false", "Analisi1", "0000000000000","0612700000");       
        instance.setEffettivaRestituzione(input);
        assertEquals(ritorno, instance.getEffettivaRestituzione());
        //fail("The test case is a prototype.");
    }
    @Test
    public void testSetEffettivaRestituzione2(){
        LocalDate input = LocalDate.of(2025,12,12);
        String ritorno = input.toString();
        System.out.println("setEffettivaRestituzione2");
        
        LocalDate dataRestituzione = LocalDate.now();
        Prestito instance = new Prestito(LocalDate.MIN, LocalDate.MIN, ritorno, "Analisi1", "0000000000000","0612700000");       
        instance.setEffettivaRestituzione(input);
        assertEquals(ritorno, instance.getEffettivaRestituzione());
    }
    

    @Test
    public void testGetDataPrevistaRestituzione() {
        System.out.println("getDataPrevistaRestituzione");
        Prestito instance = null;
        LocalDate expResult = null;
        LocalDate result = instance.getDataPrevistaRestituzione();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetDataPrestito() {
        System.out.println("getDataPrestito");
        Prestito instance = null;
        LocalDate expResult = null;
        LocalDate result = instance.getDataPrestito();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetEffettivaRestituzione() {
        System.out.println("getEffettivaRestituzione");
        Prestito instance = null;
        String expResult = "";
        String result = instance.getEffettivaRestituzione();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetIsbn() {
        System.out.println("getIsbn");
        Prestito instance = null;
        String expResult = "";
        String result = instance.getIsbn();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetTitoloLibro() {
        System.out.println("getTitoloLibro");
        Prestito instance = null;
        String expResult = "";
        String result = instance.getTitoloLibro();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetMatricola() {
        System.out.println("getMatricola");
        Prestito instance = null;
        String expResult = "";
        String result = instance.getMatricola();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testIsRitardo() {
        System.out.println("isRitardo");
        Prestito instance = null;
        boolean expResult = false;
        boolean result = instance.isRitardo();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCalcRitardo() {
        System.out.println("calcRitardo");
        Prestito instance = null;
        int expResult = 0;
        int result = instance.calcRitardo();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = null;
        Prestito instance = null;
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Prestito instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
