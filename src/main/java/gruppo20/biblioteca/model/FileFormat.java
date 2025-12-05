package gruppo20.biblioteca.model;

/**
 *
 * @author Osv
 */
public interface FileFormat<T> {
    String fileFormat(); //formatta per scrivere i record su file
    public T deFileFormat(String s); //deformatta, operazione inversa di fileFormat
}
