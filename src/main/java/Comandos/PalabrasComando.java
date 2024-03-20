/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comandos;

import Comandos.ComandoPalabras;

/**
 *
 * @author Diurno
 */
public class PalabrasComando {

    //Los comando son buscados si existen en el enum por lo que para que compruebe y para mostrarlo en el toString solo tengo que añadirlo a enum
    public static boolean esComando(String orden) {
        //Esto comprueba que el si la palabra existe entre el enum pero no entre sus valores
        for (ComandoPalabras value : ComandoPalabras.values()) {
            if (value.toString().equalsIgnoreCase(orden)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        String aux = "";
        for (var object : ComandoPalabras.values()) {
            aux += object+" - ";
        }
        return "Comandos disponibles: "+ aux;
    }

}
