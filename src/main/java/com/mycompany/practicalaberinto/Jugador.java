/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicalaberinto;

import java.util.Random;

/**
 *
 * @author Néstor y asociados
 */
public class Jugador {
    private int vida;
    private int dinero;
    private boolean llave;
    private boolean arma;

    public Jugador() {
        dinero = 0;
        vida = 100;
        llave = false;
        arma = false;
    }
    public int daño(){
        Random r = new Random();
        int daño = 0;
        //Al pegar a puños hacer menos daño que pegando con un arma
        //Pero el daño siempre bajo unos rangos es aleatorio.
        if (arma) {
           daño = r.nextInt(1,50); 
        }else{
           daño = r.nextInt(1,10);
        }
        return daño;
    }
    
    //Getters
    public int getVida() {
        return vida;
    }

    public int getDinero() {
        return dinero;
    }
    
    public boolean getLlave(){
        return this.llave;
    }

    //Para el combate
    public void sumarVida(int vida) {
        this.vida += vida;
    }
    
    public void restarVida(int vida) {
        this.vida -= vida;
    }

    //Setters
    public void setDinero(int dinero) {
        this.dinero += dinero;
    }
    
    public void setLlave(boolean abrir){
        this.llave = abrir;
    }
    //Empiezas no teniendolo y no puedes soltarlo por lo que entiendo que el setter solo puede ponertelo a true.
    public void setArma(){
        this.arma = true;
    }
}
