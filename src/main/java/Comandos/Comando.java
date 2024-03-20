/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comandos;

/**
 *
 * @author Diurno
 */
public class Comando {

    private String comando;
    private String segundaPalabra;

    public Comando(String comando, String segundaPalabra) {
        this.comando = comando;
        this.segundaPalabra = segundaPalabra;
    }

    //gets
    public String getComando() {
        return this.comando;
    }

    public String getSegundaPalabra() {
        return this.segundaPalabra;
    }

    //Para que el método irA sea más cómodo
   public boolean comprobar(){
       if (hayComando() && haySegundaPalabra()) {
           return true;
       }
       return false;
   }
   
    public boolean hayComando() {
        if (comando == null) {
            return true;
        }
        return false;
    }

    public boolean haySegundaPalabra() {
        if (this.segundaPalabra == null) {
            return true;
        }
        return false;
    }
}
