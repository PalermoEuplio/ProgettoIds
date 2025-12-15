package gruppo20.biblioteca.model.Prestiti;

import java.time.LocalDate;
import java.util.stream.Stream;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;


/**
 * /**
 * @brief Test per la classe Prestito
 *
 * @author Gruppo20
 */
public class PrestitoTest {
    private final int n = 50;
    
    public PrestitoTest() {
        
        Prestito p1 = new Prestito(LocalDate.now(), LocalDate.now(), "false", "Analisi1", "0000000000000","0612700000");
       
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
     * test parametrizzato con funzine isbnGen
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

    /**
     * test per getMatricola
     * @param in 
     */
    @ParameterizedTest
    @CsvSource({
        "0612700000,0612700000",
        "0612700010,0612700010",
        "0612700009,0612700009",
            
    })
    public void testGetMatricola(String in) {
        System.out.println("getMatricola");
        Prestito instance = new Prestito(LocalDate.now(), LocalDate.now(), "false", "Analisi1", "0000000000000",in);
        String expResult = in;
        String result = instance.getMatricola();
        assertEquals(expResult, result);
        
    }

    @ParameterizedTest
    @MethodSource("dateGen")
    public void testIsRitardo(LocalDate in) {        
        System.out.println("isRitardo");
        LocalDate datRest = in.minusDays(n);
        Prestito instance = new Prestito(in, datRest, "false", "Analisi1", "0000000000000","0612700000");
       
        boolean expResult = true;
        boolean result = instance.isRitardo();
        assertEquals(expResult, result);        
        
        
    }

    /**
     * Funzione test per il calcolo del ritardo
     * ritardo max 50 days
     * 
     */
    @RepeatedTest(50)
    public void testgetRitardo() {
        LocalDate in = LocalDate.now();        
        Random rand = new Random();
        int ritardo= rand.nextInt(50);
        //System.out.println("calcRitardo");
        
        LocalDate datRest = in.minusDays(ritardo);
        System.out.println("data ins: "+ in +"\n data rest :"+datRest);
        
        Prestito instance = new Prestito(in, datRest, "false", "Analisi1", "0000000000000","0612700000");
       
        int expResult = ritardo;
        int result = instance.getRitardo();
        
        //System.out.println("data ins: "+ in +"\n data rest :"+datRest+"\n confronto"+ritardo+" "+result);
        
        assertEquals(expResult, result);
        
    }

    /**
     * test equals con prestito uguale
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Prestito instance1 = new Prestito(LocalDate.now(), LocalDate.now().plusDays(10), "false", "Analisi1", "0000000000000","0612700000");
        Prestito instance2 = new Prestito(LocalDate.now(), LocalDate.now().plusDays(10), "false", "Analisi1", "0000000000000","0612700000");

        boolean expResult = true;
        boolean result = instance1.equals(instance2);
        assertEquals(expResult, result);
      
    }
    
    /**
     * test equals con prestito differente
     */
    @Test
    public void testEquals2() {
        System.out.println("equals");
        Prestito instance1 = new Prestito(LocalDate.now(), LocalDate.now().plusDays(10), "false", "Analisi1", "0000000000000","0612700000");
        Prestito instance2 = new Prestito(LocalDate.now(), LocalDate.now().plusDays(10), "false", "Fisica 2", "0000000000001","0612700000");

        boolean expResult = false;
        boolean result = instance1.equals(instance2);
        assertEquals(expResult, result);
      
    }

    @Test
    public void testHashCode() {
        Prestito instance1 = new Prestito(LocalDate.now(), LocalDate.now().plusDays(10), "false", "Analisi1", "0000000000000","0612700000");
        Prestito instance2 = new Prestito(LocalDate.now(), LocalDate.now().plusDays(10), "false", "Analisi1", "0000000000000","0612700000");

        int expResult = instance1.hashCode();
        int result = instance2.hashCode();
        assertEquals(expResult, result);
        
    }
    
    /**
     * @brief generatore di date
     * 50
     * Metodo di supporto per i test    
    */
    static Stream<LocalDate> dateGen() {
        LocalDate oggi = LocalDate.now();        
        return Stream.iterate(oggi, data -> data.plusDays(1)).limit(50);
                     
    }
    /**
     * @brief generatore di isbn random 
     * 50
     * Metodo di supporto per i test
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
