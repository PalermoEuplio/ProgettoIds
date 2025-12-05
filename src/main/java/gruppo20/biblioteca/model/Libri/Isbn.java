/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gruppo20.biblioteca.model.Libri;

/**
 *
 * @author Osv
 */
public class Isbn {
    private String isbn;
    
    public Isbn(String isbn){
        this.isbn=isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }  
        
    @Override
    public boolean equals(Object o){
        if(o==null || !(o instanceof Isbn))return false;
        Isbn isbn = (Isbn) o;        
        if(this.isbn.equals(isbn.toString())) return true;
        else return false;
    }
    @Override
    public String toString(){
        return isbn;
    }
    
}
