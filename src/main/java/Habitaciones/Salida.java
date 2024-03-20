/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Habitaciones;

import Habitaciones.Habitacion;

/**
 *
 * @author Néstor y asociados
 */
public class Salida extends Habitacion{
    
    //Esta subclase de Habitacion es para la Habitacion j, porque como tiene ua puerta cerrada al distinguirla por ser instancia de 
    //Salida puedo hacer que solo se pueda pasar con la llave.
    public Salida(String descripcion) {
        super(descripcion);
    }
    
}
