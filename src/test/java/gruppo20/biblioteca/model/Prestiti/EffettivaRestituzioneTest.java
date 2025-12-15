package gruppo20.biblioteca.model.Prestiti;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

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
     * Imposta la data di restituzione e verifica se è restituito
     * @param in data di restituzione
     */
    @ParameterizedTest
    @MethodSource("dateGen")
    public void testIsRestituito(LocalDate in) {
        System.out.println("isRestituito");
        EffettivaRestituzione instance = new EffettivaRestituzione(in.toString());
        boolean expResult = true;
        boolean result = instance.isRestituito();
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of isRestituito method, of class EffettivaRestituzione.
     * Non imposta la data e verifica che non è restituito
     * @param in data di restituzione
     */
    @ParameterizedTest
    @MethodSource("dateGen")
    public void testIsRestituito2(LocalDate in) {
        System.out.println("isRestituito");
        EffettivaRestituzione instance = new EffettivaRestituzione("false");
        boolean expResult = false;
        boolean result = instance.isRestituito();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getEffettivaRestituzione method, of class EffettivaRestituzione.
     * Caso in cui viene inserita la restituzione, deve resitutire la data di restituzione
     * @param in
     */
    @ParameterizedTest
    @MethodSource("dateGen")
    public void testGetEffettivaRestituzione(LocalDate in) {
        System.out.println("getEffettivaRestituzione");
        EffettivaRestituzione instance = new EffettivaRestituzione(in.toString());
        String expResult = in.toString();
        String result = instance.getEffettivaRestituzione();
        assertEquals(expResult, result);
        
    }
    /**
     * Test of getEffettivaRestituzione method, of class EffettivaRestituzione.
     * Caso in cui la restituzione non è avvenuta, deve resitutire false
     * @param in
     */
    @ParameterizedTest
    @MethodSource("dateGen")
    public void testGetEffettivaRestituzione2(LocalDate in) {
        System.out.println("getEffettivaRestituzione");
        EffettivaRestituzione instance = new EffettivaRestituzione("false");
        String expResult = "false";
        String result = instance.getEffettivaRestituzione();
        assertEquals(expResult, result);
        
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
