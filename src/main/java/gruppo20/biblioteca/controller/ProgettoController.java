/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gruppo20.biblioteca.controller;
import gruppo20.biblioteca.model.*;


/**
 *
 * @author euppa
 */
public class ProgettoController {
    
    private String nome;
    
    //Costruttore con chiamate alle funzioni di gestione dell'interfaccia
    public ProgettoController(){
    }
    
    
    
    /**
    *@brief Si occupa della gestione dell'input nel file.
    * In caso d'errore gestisce l'eccezione.
    * 
    * Parametro in ingresso:
    *   @param nomeFile Stringa contenente il nome del file da aprire, compreso d'estenzione.
    * 
    *   @return restituisce void. 
    */  
    public void scriviFile(String nomeFile){ 
        /*
    //apro il file in rw read/write , creo anche una cartella data per lo storing dei data
    try(RandomAccessFile file = new RandomAccessFile("data/test.txt","rw")){
        //scrivo le 3 stringhe iniziali di prova
        file.writeUTF("linea 1\n");
        file.writeUTF("linea 2\n");
        file.writeUTF("linea 3\n");
        //seek sposta il file pointer di n byte dall'inizio del file
        file.seek(0);
        //creo e inizializzo delle variabili di appoggio, non basatevi sul nome le userò a caso
        long  start = 0;
        String find = "";
        StringBuilder buffer = new StringBuilder();
        //mi fermo quando il file pointer raggiunge la fine del file
        while (file.getFilePointer()<file.length())
        {
            try {
                //mi segno il file pointer prima di leggere la linea
                start = file.getFilePointer();
                //leggo la linea, le op di lettura e scrittura spostano il file pointer della lunghezza in byte della linea
                // in caso di scrittura con writeUTF bisogna considerare che alla stringa che si manda in output vengono
                // aggiunti 2 byte usati da UTF per i metadata, il lettore testuale vede quei 2 byte come valori strani ASCII prima della stringa inserita
                find = file.readUTF();
                System.out.println(find);
                //cerco la stringa che contiene 2
                if (find.contains("2")){
                    System.out.println("\nOffset: "+start+" Length: "+(find.length()+2));
                    break;
                }
            }
            //readUTF può lanciare EOFException se arriva alla fine del file e prova a leggere, ridondante rispetto al controllo del while, ma lo aggiungo per chiarezza
            catch (EOFException e){
                System.out.println("Fine file raggiunto");
                break;
            }
        }
        //mi salvo la stringa/stringhe successiva alla stringa che voglio sovrascrivere, parto da start e mi muovo di length della stringa + 2 byte di meta
        file.seek((start+(find.length()+2)));
        buffer.append(file.readUTF());
        //mi risposto a start
        file.seek(start);
        //sovrascrivo
        file.writeUTF("Riscrivo a lunghezza variabile maggiore\n");
        //riscrivo tutto ciò che c'era dopo la riga sovrascritta
        file.writeUTF(buffer.toString());
        
        Da tenere a mente che, se sostituiamo una stringa con un'altra stringa di lunghezza uguale o maggiore il metodo
        precedente funziona senza problemi, ma se la sostituiamo con una stringa più corta allora rimarranno dei caratteri sul file che verranno
        interpretati come facenti parti del file, perchè se diminuiamo la lunghezza di una stringa dobbiamo diminuire la grandezza di tutto il file o si richiama
        i dati delle vecchie righe che non sono state sovrascritte dal write
         
        //mi sposto a start
        file.seek(start);
        //sostituisco la riga più lunga con quella più corta
        String corta = "SostCorta\n";
        file.writeUTF(corta);
        //modifico la lunghezza del file per cancellare tutto ciò che c'è dopo la stringa corta
        file.setLength(start+corta.length()+2);
        //reinserisco tutto ciò che c'era dopo, già avevo salvato tutto in buffer
        file.writeUTF(buffer.toString());

        /*piccolo controllo, a volte anche se nel file di testo viene
            salvato un pò a chazzo, la formattazione UTF regge ancora
            e readUTF e writeUTF funzionano lo stesso, vedi il file di testo generato
            , anche se non sono uno sotto l'altro come dovrebbero essere readUTF li legge
            comunque come linee diverse. Cambiando la stringa da SostCorta in qualcosa come Cort
            il file di testo viene mostrato bene, quindi si sminchia per i byte meta che vengono
            interpretati male dal reader testuale.
        * 
        file.seek(start);
        System.out.println(file.readUTF());
        System.out.println(file.readUTF());


    } catch (FileNotFoundException e) {
        System.out.println("File non trovato");
    } catch (IOException e) {
        System.out.println("Eccezione IO");
    }
*/
    }
    
    
    
    /**
    *@brief Si occupa della gestione della lettura del file.
    * In caso d'errore gestisce l'eccezione.
    * 
    * Parametro in ingresso:
    *   @param nomeFile Stringa contenente il nome del file da aprire, compreso d'estenzione.
    * 
    * 
    */    
    
}