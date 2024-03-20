/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Habitaciones;

/**
 *
 * @author Diurno
 */
public class Habitacion {
    private String descripcion;
    private Habitacion sNorte, sSur, sEste, sOeste;
    
    public Habitacion(String descripcion){
        this.descripcion = descripcion;
    }
    
    //Para crear el mapa
    public void setSalida(String direct, Habitacion sala){
        if (direct.equalsIgnoreCase("norte") ) {
            sNorte = sala;
        }else if(direct.equalsIgnoreCase("sur")){
            sSur = sala;
        }else if(direct.equalsIgnoreCase("este")){
            sEste = sala;
        }else if(direct.equalsIgnoreCase("oeste")){
            sOeste = sala;
        }    
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public String getDescipcionLarga(){
        String aux = "\nEstas en " + this.descripcion + " y las salidas posibles son ";
        aux += getStringDeSalidas();

        //Supongo que habra un final sin salida. Esta linea es para eso o por si casca y no hay salidas.
        if (sNorte == null && sSur == null && sEste == null && sOeste == null) {
            aux = "Estas en " + this.descripcion + " y no hay salidas posibles";
        }
        return aux+"\n";
    }
// ⡠⠤⢄⠀⠀⠀⠀
//⠰⣉⡱⠀⡏⢹⠀⠀⠀
// ⡇⣀⠀⣇⡇⠀⠀
// ⠈⠊⠑⠋⠀⠀

    public String getStringDeSalidas(){
        String aux = "";
        //Al separarlos en distintos if puede haber varias salidas
        if (sNorte != null){
          aux +=  "norte";
          //estos condicionales son solo para que se vea bien. Que ponga , si hay mas y punto si es el último
            if (sSur == null && sEste == null && sOeste == null) {
                aux += ".";
            }else{
                aux += ", ";
            }
        }
        if(sSur != null){
            aux +=  "sur"; 
            if (sEste == null && sOeste == null) {
                aux += ".";
            }else{
                aux += ", ";
            }
        }
        if (sEste != null){
            aux +=  "este"; 
            if (sOeste == null) {
                aux += ".";
            }else{
                aux += ", ";
            }
        }
        if (sOeste != null)
            aux +=  "oeste."; 

        return aux;
    }
    
    //Para luego poder moverse entre las salas del mapa
    public Habitacion getSalida(String direct){
        Habitacion salida = null;
        
        if (direct.equalsIgnoreCase("norte") ) {
            salida = sNorte;
            
        }else if(direct.equalsIgnoreCase("sur")){
            salida = sSur;
            
        }else if(direct.equalsIgnoreCase("este")){
            salida = sEste;
            
        }else if(direct.equalsIgnoreCase("oeste")){
            salida = sOeste;
            
        } 
        
        return salida;
    }
}
