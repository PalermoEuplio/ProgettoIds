package gruppo20.biblioteca.controller;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Osv
 */

interface FileFormat{
    String fileFormat();
}

public class ControllerFile<T extends FileFormat>{
    private final String filePath;
    private final RandomAccessFile file;
    private boolean chiuso = false;
    
    
    public ControllerFile(String filePath) throws IOException{
        this.filePath=filePath;
        this.file = new RandomAccessFile(filePath, "rw");
    }
    
    public boolean aggiungi(T t) throws IOException{
        checkChiuso();
        file.seek(file.length());
        file.writeUTF(t.fileFormat());
    }
    
    public void chiudi() throws IOException {
        if (!chiuso) {
            file.close();
            chiuso = true;
        }
    }
    
    private void checkChiuso() {
        if (chiuso) throw new IllegalStateException("File già chiuso");
    }

}
