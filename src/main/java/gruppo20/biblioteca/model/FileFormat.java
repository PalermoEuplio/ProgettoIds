package gruppo20.biblioteca.model;

/**
 *
 * @author Osv
 */
public interface FileFormat<T> {
    //formatta per scrivere i record su file, useremo § come carattere di separazione
    //
    String fileFormat(); 
    public T deFileFormat(String s); //deformatta, operazione inversa di fileFormat
}
