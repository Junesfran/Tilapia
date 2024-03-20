/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comandos;

import Comandos.Comando;
import Comandos.PalabrasComando;

/**
 *
 * @author Diurno
 */
public class Transformar {

    private String palabraComando;
    private PalabrasComando test2;

    public Transformar() {
        this.palabraComando = null;
    }

    public Comando getCommand(String comando) {
        //spliteo la entrada para el comando ir, que pueda comprobar hacia que direccion quiere ir en caso de necesitarlo
        String[] cortes = comando.split(" ");

        Comando c;

        if (cortes.length == 1) {
            c = new Comando(cortes[0], null);
        } else {
            c = new Comando(cortes[0], cortes[1]);
        }

        //Primero compruebo de que no me hallan pasado un null y luego que lo que me hallan pasado sea realmente un comando funcional
        //En ambos casos en los que ninguna es un buen valor indico un comando ayuda para que pase una lista con los comandos disponibles
        if (c.hayComando()) {
            System.out.println("Ha de indicar un comando de acción.");
            c = new Comando("ayuda", null);
        } else {
            if (!test2.esComando(c.getComando())) {
                System.out.println("El comando proporcionado no es un comando reconocible.");
                c = new Comando("ayuda", null);
            }
        }
        return c;
    }
}
