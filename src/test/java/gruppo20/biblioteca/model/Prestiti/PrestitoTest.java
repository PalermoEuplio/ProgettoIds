/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package gruppo20.biblioteca.model.Prestiti;

import java.time.LocalDate;
import java.util.stream.Stream;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;


/**
 *
 * @author Gruppo20
 */
public class PrestitoTest {
    
    public PrestitoTest() {
        System.out.println("inizio prestito test");
        Prestito notRest = new Prestito(LocalDate.MIN, LocalDate.MIN, "false", "Analisi1", "0000000000000","0612700000");
    }   
    
    
    /*
    Test Parametrizzato per il metodo SetEffettivaRestituzione con date valide da stato false
    */
    @ParameterizedTest
    @MethodSource("dateGen")
    public void testSetEffettivaRestituzione() {
        LocalDate input = LocalDate.of(2025,12,12);
        String ExpRitorno = input.toString();
        System.out.println("setEffettivaRestituzione");        
                
        Prestito instance = new Prestito(LocalDate.MIN, LocalDate.MIN, "false", "Analisi1", "0000000000000","0612700000");       
        instance.setEffettivaRestituzione(input);
        assertEquals(ExpRitorno, instance.getEffettivaRestituzione().getEffettivaRestituzione());
        
    }
    
    /*
    Test Parametrizzato per il metodo SetEffettivaRestituzione da data valida
    */
    @ParameterizedTest
    @MethodSource("dateGen")
    public void testSetEffettivaRestituzione2(LocalDate input){
        
        LocalDate dataRestituzione = input.plusDays(30);
        String ritorno = dataRestituzione.toString();   
        String dataAdd = input.plusDays(15).toString();
        System.out.println("setEffettivaRestituzione2");
        Prestito instance = new Prestito(input, input, ritorno, "Analisi_1", "0000000000000","0612700000");       

        
       
        instance.setEffettivaRestituzione(input.plusDays(15));
        assertEquals(dataAdd, instance.getEffettivaRestituzione().getEffettivaRestituzione());
    }
    

    
    @ParameterizedTest
    @MethodSource("dateGen")    
    public void testGetDataPrevistaRestituzione(LocalDate in) {
        System.out.println("getDataPrevistaRestituzione test");
        Prestito instance = new Prestito(in, in.plusDays(30), "false", "Analisi1", "0000000000000","0612700000");
        LocalDate expResult = in.plusDays(30);
        LocalDate result = instance.getDataPrevistaRestituzione();
        assertEquals(expResult, result);
        
    }

    @ParameterizedTest
    @MethodSource("dateGen")
    public void testGetDataPrestito(LocalDate in) {
        System.out.println("getDataPrestito");
        Prestito instance = new Prestito(in, in.plusDays(30), "false", "Analisi1", "0000000000000","0612700000");

        LocalDate expResult = in;
        LocalDate result = instance.getDataPrestito();
        assertEquals(expResult, result);
        
    }

    @Test
    public void testGetEffettivaRestituzione() {
        LocalDate in = LocalDate.now();
        System.out.println("getEffettivaRestituzione");
        Prestito instance = new Prestito(LocalDate.now(), in.plusDays(30), "false", "Analisi1", "0000000000000","0612700000");

        String expResult = "false";
        String result = instance.getEffettivaRestituzione().getEffettivaRestituzione();
        assertEquals(expResult, result);
        
    }
    
    @ParameterizedTest
    @MethodSource("dateGen")
    public void testGetEffettivaRestituzione2(LocalDate in) {
        
        System.out.println("getEffettivaRestituzione");
        Prestito instance = new Prestito(LocalDate.now(), in.plusDays(30), in.toString(), "Analisi1", "0000000000000","0612700000");

        String expResult = in.toString();
        String result = instance.getEffettivaRestituzione().getEffettivaRestituzione();
        assertEquals(expResult, result);
        
    }

    /**
     * test getIsbn
     * @param in 
     */
    @ParameterizedTest
    @MethodSource("isbnGen")
    public void testGetIsbn(String in) {
        System.out.println("getIsbn");
        Prestito instance = new Prestito(LocalDate.now(), LocalDate.now(), "false", "Analisi1", in,"0612700000");

        String expResult = in;
        String result = instance.getIsbn();
        assertEquals(expResult, result);
        
    }

    
    /**
     * Test per GetTitoloLibro
     * parametri CSV
     * @param in 
     */
    @ParameterizedTest
    @CsvSource({
        "Analisi 1,Analisi 1",
        "Fisica 2,Fisica 2",
        "Analisi 2,Analisi 2"
    })
    public void testGetTitoloLibro(String in) {
        System.out.println("getTitoloLibro");
        //String in = "Analisi 1";
        Prestito instance = new Prestito(LocalDate.now(), LocalDate.now(), "false", in, "0000000000000","0612700000");
        
        String expResult = in;
        String result = instance.getTitoloLibro();
        assertEquals(expResult, result);
        
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
        int result = instance.getRitardo();
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
    
    /**
     * generatore di date
     * 50
     * @brief
     * Metodi di supporto per i test
    
    */
    static Stream<LocalDate> dateGen() {
        LocalDate oggi = LocalDate.now();        
        return Stream.iterate(oggi, data -> data.plusDays(1)).limit(50);
                     
    }
    /**
     * generatore di isbn 
     * 50
     * @return 
     */
    static Stream<String> isbnGen() {
        Random random = new Random();   
        
        return Stream.generate(() -> {
            StringBuilder sb = new StringBuilder("978");            
            for (int i = 0; i < 10; i++) {
                sb.append(random.nextInt(10));
            }
            return sb.toString();
        }).limit(50);
    }
    
}
