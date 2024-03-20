/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Habitaciones;

import com.mycompany.practicalaberinto.*;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Néstor y asociados
 */
//Clase exclusiva para el combate contra el mimico. En futuras Actualizaciones se añadiran combates.
public class HabitacionCombate extends Habitacion{
    private int vida;
    private int recompensa;

    public Random r = new Random();
    public HabitacionCombate(String descripcion) {
        super(descripcion);
        this.vida = 100;
        //Recompensa aleatoria
        this.recompensa = r.nextInt(1,50);
    }
    
    public void combate(Jugador item){
        Scanner sc = new Scanner(System.in);
        System.out.print("Un peligro te corta el paso.");
        String comando = "";
        boolean logrado = true;
        do{
            //Tu turno
            System.out.println("Comandos disponibles: ATACAR - ESQUIVAR");
            System.out.println("¿Que quieres hacer? ");
            comando = sc.nextLine();
            
            if (comando.equalsIgnoreCase("atacar")) {
                int ataque = item.daño();
                this.vida -= ataque;
                System.out.println("Le has quitado "+ataque+" puntos de vida al ser. Le quedan "+this.vida);
            }else if(comando.equalsIgnoreCase("esquivar")){
                int esquiva = r.nextInt(1,10);
                if (esquiva > 5) {
                    item.sumarVida(esquiva);
                    System.out.println("Esquivaste el golpe. Tienes un respiro en su golpe y recuperas "+esquiva+" puntos de vida");
                    logrado = false;
                }else{
                    System.out.println("No conseguirte esquivar el golpe.");
                }
            }else{
                System.out.println("No logras decidirte sobre que accion realizar");
            }
            
            //Turno del bicho
            //Las acciones del bicho son completamente aleatorias
            int accion = r.nextInt(1,3);
            if (accion == 1) {
                //Ataque
                if (logrado) {
                    int daño = r.nextInt(1,20);
                    item.restarVida(daño);
                    System.out.println("El ser te ha asestado un golpe y te quita "+daño+" puntos de vida. Te quedan "+item.getVida());
                }
                
            }else if(accion == 2){
             //respito
             int respiro = r.nextInt(1,25);
                if (respiro > 5) {
                    sumarVida(respiro);
                    System.out.println("El ser restrocede unos pasos para recuperar algo de aliento, recupera "+respiro+" puntos de vida");
                }else{
                    System.out.println("Intenta recuperar algo de aliento pero no se lo permites y el combate sigue.");
                }
            }
             //por si mueres durante la pelea
            if (item.getVida()< 0 ) {
                break;
            }
        //El bucle se mantiene con la vida del bicho
        }while(this.vida > 0);
        
        if (this.vida < 0) {
            int ganancias = r.nextInt(50,100);
            item.setDinero(ganancias);
            System.out.println("Lo lograste, saliste victorioso que ganaste "+ganancias+" deathcoins que tiene el cadaver del ser.");
        }
        
    }
    
    public void sumarVida(int vida){
        this.vida += vida;
    }
}
