/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gruppo20.biblioteca;
import java.time.LocalDate;
/**
 *
 * @author Osv
 */
public class Prestito {
    private final LocalDate dataPrestito;
    private final LocalDate dataRestituzione;
    private LocalDate dataEffettivaRestituzione;
    //inizializza un prestito, possibiità di sceglierne la durata
    //WARNING: LocalDate arrotonda, se si arriva al 31 febbraio potrebbe ad esempio arrotondare per difetto al 28
    //scegliere le opzioni da presentare sull interfaccia
    public Prestito(LocalDate dataPrestito, int durata){
        this.dataEffettivaRestituzione = null;
        this.dataPrestito = dataPrestito;
        this.dataRestituzione = dataPrestito.plusDays(durata);
    }
    //set per inserire la data di restituzione effettiva
    public void setDataEffettivaRestituzione(LocalDate dataEffettivaRestituzione) {
        this.dataEffettivaRestituzione = dataEffettivaRestituzione;
    }
    
    public LocalDate getDataPrestito() {
        return dataPrestito;
    }

    public LocalDate getDataRestituzione() {
        return dataRestituzione;
    }
    //controlla se il libro è stato già consegnato, in caso lo sia restituisce il quando
    public String getDataEffettivaRestituzione() {
        if(dataEffettivaRestituzione!=null) return dataEffettivaRestituzione.toString();
        else return "Non ancora restituito";
    }
    

}
