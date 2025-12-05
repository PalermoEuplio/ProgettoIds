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
    
    public void aggiungi(T t) throws IOException{
        checkChiuso();
        file.seek(file.length());
        file.writeUTF(t.fileFormat());
    }
    // elimina il record t dal file, non controlla se è effettivamente presente, controllo da fare a monte, prerequisito
    public void elimina(T t) throws IOException{
        checkChiuso(); //controllo che il file non sia già chiuso
        file.seek(0);
        long target;
        StringBuilder builder = new StringBuilder();
        String[] buffer;
        //cerco il record nel file e salvo l'offset
        while(file.getFilePointer()<file.length()){
            target = file.getFilePointer();
            if(file.readUTF().equals(t.fileFormat())) break;
        }
        //mi sposto all'inizio del record successivo
        file.seek(target+t.fileFormat().length()+2);
        //salvo tutti i record successivi 
        while(file.getFilePointer()<file.length()){
            builder.append(file.readUTF()+"\n");
        }
        
        file.setLength(target);//così elimino tutto ciò che è successivo al punto in cui iniziava il record da eliminare
        file.seek(file.length());
        buffer = builder.toString().split("\n"); //splitto i record salvati altrimenti non posso reinserirli correttamente
        
        //reinserisco tutti i record
        for(String line : buffer){
            file.writeUTF(line);
        }
    }
    
    public void modifica(T t1, T t2) throws IOException{
        checkChiuso(); //controllo che il file non sia già chiuso
        file.seek(0);
        
        long target;
        StringBuilder builder = new StringBuilder();
        String[] buffer;
        
        //cerco il record nel file e salvo l'offset
        while(file.getFilePointer()<file.length()){
            target = file.getFilePointer();
            if(file.readUTF().equals(t1.fileFormat())) break;
        }
        
        if(t1.fileFormat().compareTo(t2.fileFormat())==0){
            file.seek(target);
            file.writeUTF(t2.fileFormat());
        }
        else{
            //mi sposto all'inizio del record successivo
            file.seek(target+t1.fileFormat().length()+2);
            //salvo tutti i record successivi 
            while(file.getFilePointer()<file.length()){
                builder.append(file.readUTF()+"\n");
            }

            file.setLength(target);//così elimino tutto ciò che è successivo al punto in cui iniziava il record da modificare
            file.seek(file.length());
            buffer = builder.toString().split("\n"); //splitto i record salvati altrimenti non posso reinserirli correttamente

            //reinserisco il record corretto e quelli successivi
            file.writeUTF(t2.fileFormat());
            for(String line : buffer){
                file.writeUTF(line);
            }        
        }
    }
    
    //carica il file nella struttura interna del programma, in questo caso HashSet
    public String[] carica() throws IOException{
        checkChiuso();
        StringBuilder builder = new StringBuilder();
        file.seek(0);
        while(file.getFilePointer()<file.length()){
            builder.append(file.readUTF()+"\n");
        }
        return builder.toString().split("\n");
    }
    //controlla se il file è chiuso
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
