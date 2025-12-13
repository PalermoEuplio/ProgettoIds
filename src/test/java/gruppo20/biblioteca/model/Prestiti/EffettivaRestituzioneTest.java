/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package gruppo20.biblioteca.model.Prestiti;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runners.Parameterized;

/**
 *
 * @author Riccardo
 */
public class EffettivaRestituzioneTest {
    
    public EffettivaRestituzioneTest() {
    }

    /**
     * Test of setEffettivaRestituzione method, of class EffettivaRestituzione.
     * Imposta data generata dal metodo dateGen
     * @param in
     */
    @ParameterizedTest
    @MethodSource("dateGen")
    public void testSetEffettivaRestituzione(LocalDate in) {
        System.out.println("setEffettivaRestituzione");
        LocalDate dataRestituzione = in;
        EffettivaRestituzione instance = new EffettivaRestituzione("false");
        instance.setEffettivaRestituzione(dataRestituzione);
        assertEquals(in.toString(), instance.getEffettivaRestituzione());
    }

    /**
     * Test of isRestituito method, of class EffettivaRestituzione.
     */
    @Test
    public void testIsRestituito() {
        System.out.println("isRestituito");
        EffettivaRestituzione instance = null;
        boolean expResult = false;
        boolean result = instance.isRestituito();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEffettivaRestituzione method, of class EffettivaRestituzione.
     */
    @Test
    public void testGetEffettivaRestituzione() {
        System.out.println("getEffettivaRestituzione");
        EffettivaRestituzione instance = null;
        String expResult = "";
        String result = instance.getEffettivaRestituzione();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
    /**
     * generatore di date random
     * numero di date : 50
     * @brief
     * Metodi di supporto per i test
    
    */
    static Stream<LocalDate> dateGen() {
        Random rnd = new Random();
        LocalDate oggi = LocalDate.now();        
        return Stream.iterate(oggi, data -> {
            return data.plusDays(rnd.nextInt());
        }).limit(50);
                     
    }
    
}
