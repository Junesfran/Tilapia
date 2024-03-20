/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicalaberinto;


import Habitaciones.Salida;
import Comandos.*;
import Habitaciones.*;
import java.util.Scanner;


/**
 *
 * @author Néstor y asociados
 */
public class Juego {

    private Transformar transformar;
    private Habitacion habitacionActual;
    //Necesario unicamente para el comando volver.
    private Habitacion habitacionAnterior;
    private static Jugador dani;
    
    //Para el final 
    private Habitacion entrada;
    private Habitacion salida;

    
    public Juego() {
        crearHabitaciones();
        transformar = new Transformar();
        dani = new Jugador();
    }
    
    public void crearHabitaciones() {
        //Creación del mapa.
        Habitacion a = new Habitacion("el VESTÍBULO de lo que parece una mansión, el suelo luce baldosas blancas y negras.\n Delante de ti se alzan dos escaleras curbas que llevan a lo que parece la parte de arriba del vestíbulo");
        Habitacion b = new HabitacionTesoro("lo que parece un DESPACHO de un ejecutivo muy desordenado. A espaldas de la mesa de ejecutivo parece haber una gran biblioteca\n pero no encuentras nada que despierte tu interes, ");
        Habitacion c = new HabitacionTesoro("una amplia COCINA con muchos utensileos dispuestos para cocinar. Hay muchas verduras a medio cortar,\n ollas con agua preparadas para ponerlas a hervir, pero nadie qe esté cocinando. Toda comida sin escepción está podrída, ");
        Habitacion d = new Habitacion("en un PASILLO muy raro. La iluminación es mala y esta lleno de retratos que da igual donde te pongas\n parecen que te siguen con la mirada,");
        Habitacion e = new HabitacionTesoro("un pequeño JARDÍN botanico muy distinto al resto de habitaciones. El techo es una cristalera y hay una amplia exposición de plantas exoticas.\n Al fondo de la habitación hay un banco pequeño bando de madera, ");
        Habitacion g = new HabitacionTesoro("una especie de sala de CINE bastante amplia, en algunas puestos sin un orden aparente hay maniquies que\n atienden a lo que parece una proyección de momentos de tu vida desde tu perspectiva,");
        Habitacion h = new HabitacionCombate("una  MAZMORRA algo bajo tierra. Parece que se ha usado recientemente para la tortura por las manchas de\n sangre en el suelo alrededor de una silla pero nadie que la ejecute. El olor a humedad es nauseabundo,");
        Habitacion i = new Habitacion("lo que parece un DORMITORIO bastante lujoso, con una cama de matrimonio en el medio que tiene un dosel de\n terciopelo, el suelo está adornado con una moqueta a juego con la cama y una pequeña mesa de noche de madera a la derecha de la cama, ");
        Habitacion j = new Salida("lo que parece una SALIDA. La sala está completamente a oscuras y se divisa al este de la habitacion lo que parece una luz en forma de puesta doble,");
        Habitacion f = new HabitacionFinal("?????¿¿¿¿");
        
        //A
        a.setSalida("este", c);
        a.setSalida("oeste", b);
        a.setSalida("norte", d);
        //B
        //solo se puede volver
        //C
        //solo se puede volver
        //D
        d.setSalida("oeste", e);
        d.setSalida("norte", h);
        //E
        e.setSalida("norte", g);
        //G
        //solo se puede volver
        //H
        h.setSalida("este", i);
        h.setSalida("oeste", g);
        //I
        i.setSalida("sur", j);
        //J Salida
        //Final temporal
        j.setSalida("este", f);

        habitacionActual = a;
        entrada = a;
        salida = f;
    }

    public final Scanner sc = new Scanner(System.in);
    //La excepción son por las pausas de ejecucion de la habitación final
    public void juego() throws InterruptedException {

        Comando c;
        bienvenido();
        System.out.println(habitacionActual.getDescipcionLarga());
        //Bucle que lleva a cabo de juego entero. Se rompe solo solucitando el comando fin
        do{ 
            System.out.println("Introduzca el tu siguiente acción: ");
            //guardo en c el comando solicitado tras comprobar que es un valor válido
            c = transformar.getCommand(sc.nextLine());
            
            if (c.getComando().equalsIgnoreCase("ir")) {
                
                irA(c);
                System.out.println(habitacionActual.getDescipcionLarga());
            //Esta condicional llama al final del juego en caso de intentar salir.
                if (habitacionActual == salida) {
                    ((HabitacionFinal) habitacionActual).finales(dani);
                    //El siguiente condicional es para la respuesta del final, si quiere seguir explorando que le lleve a la 
                    //Habitación a y si no que llame al comando fin y que rompa el bucle infinito
                    if (((HabitacionFinal) habitacionActual).isRespuesta() == true) {
                        fin();
                        break;
                    }else if(((HabitacionFinal) habitacionActual).isRespuesta() == false){
                        habitacionActual = entrada;
                        System.out.println(habitacionActual.getDescipcionLarga());
                    }
                }
            }else if(c.getComando().equalsIgnoreCase("ayuda")){
                //Lista los comandos válidos
                System.out.println(ayuda());
            }else if(c.getComando().equalsIgnoreCase("inspeccionar")){
                //Comando más complejo de hacer. Busca cofres en la sala
                investigar();
            }else if(c.getComando().equalsIgnoreCase("monedero")){
                //Consulta la cantidad de monedas que tienes
                System.out.println("Tienes "+monedero()+" deathcoins en tu monedero");
            }else if (c.getComando().equalsIgnoreCase("fin")) {
                //Deja un mensaje y se pone fin al bucle.
                fin();
            }else if(c.getComando().equalsIgnoreCase("volver")){
                //Vuelve a la habitación anterior.
                volver();
                System.out.println(habitacionActual.getDescipcionLarga());
            }
            
            //Por si llegaras a morir
            if (dani.getVida() < 0) {
                System.out.println("\nTus puntos de vida llegan a cero y la oscuridad se avalanza sobre ti.");
                fin();
                c = transformar.getCommand("fin");
            }
            
        }while(!c.getComando().equalsIgnoreCase("fin"));
    }

    public void bienvenido() {
        //Te lista los comandos disponibles y te da un contexto con el que empezar.
        System.out.println(ayuda());
        System.out.println("Te despiertas después de lo que parece un largo sueño algo confundido y manerado sin saber exactamete donde estas o como llegaste allí");
    }

    //Comandos
    public String ayuda() {
        String aux = "Aquí tienes una lista con los comandos:\n";
        aux += new PalabrasComando().toString() + "\n";
        aux += "(Por favor si quiere ir a alguna habitación separe el comando del lugar con un espacio).\n";
        return aux;
    }

    public void irA(Comando c) {
        //Comprobar esto de segunda palabra
        c.comprobar();
        String direct = c.getSegundaPalabra();
        
        //Primero me comprueba que a la dirección que quiere ir se puede efectivamente ir
        if (this.habitacionActual.getSalida(direct) != null) {
           
            //Este condicional es en caso de que quiera entrar a la Habitación j, ya que para entrar en esta hace falt una llave.
            if (this.habitacionActual.getSalida(direct) instanceof Salida && this.dani.getLlave() == true) {
                this.habitacionAnterior = this.habitacionActual; 
                this.habitacionActual = this.habitacionActual.getSalida(direct);  
            }else if(this.habitacionActual.getSalida(direct) instanceof Salida && this.dani.getLlave() == false){
                System.out.println("Necesitas una llave para poder acceder a esta puesta.");
            }else{
                //Para todo el resto de salas que NO están cerradas
               this.habitacionAnterior = this.habitacionActual; // Estó mantiene funcional al comando volver.
               this.habitacionActual = this.habitacionActual.getSalida(direct);
            }
        
        }
    }

    public void investigar(){
        String inves;
        //Comprueba primero si hay cofre. Si no te dice que no hay nada di interes
        if (this.habitacionActual instanceof HabitacionTesoro) {
            
            System.out.println("Encuentras un cofre en la extraña habitacion.¿Deseas abrirlo?[SI/NO] "); 
            inves = sc.nextLine();
            if (inves.equalsIgnoreCase("si")) {
                //Si el cofre ya ha sido abierto te dice si lo has o no abierto antes. ¿Porque no antes? Me parecio gracioso.
                if(((HabitacionTesoro)this.habitacionActual).isAbrir()){
                    
                    ((HabitacionTesoro)this.habitacionActual).setTesoro(dani);
                    ((HabitacionTesoro)this.habitacionActual).setAbrir();
                
                }else{
                    
                    System.out.println("Este cofre ya ha sido abierto");
                }
            }else if (inves.equalsIgnoreCase("no")) {
                System.out.println("Decides ser prudente ante las posibles trampas y continuar tu camino.");
            }else{
                //por si el la respuesta es otra que si o no.
                System.out.println("Quieres hacer una accion pero tu falta de decision te impide dar respuestas con sentido. Avanzas al rendirte de abrir el cofre.");
            }
            
            //Replica el condicional de abrir porque el Mimico en un principio te tiene que engañar haciendote pensar que es un cofre.
        }else if(this.habitacionActual instanceof HabitacionCombate){
            //Para el Mimico que lleva el sistema de combate.
            System.out.println("Encuentras un cofre en la extraña habitacion.¿Deseas abrirlo?[SI/NO] "); 
            inves = sc.nextLine();
            if (inves.equalsIgnoreCase("si")) {
                
                System.out.println("OH NO. El cofre resulto ser un mimico que se avalanza sobre ti.");
                ((HabitacionCombate) this.habitacionActual).combate(dani);
            }else if (inves.equalsIgnoreCase("no")) {
                
                System.out.println("Decides ser prudente ante las posibles trampas y continuar tu camino.");
            }else{
                
                System.out.println("Quieres hacer una accion pero tu falta de decision te impide dar respuestas con sentido. Avanzas al rendirte de abrir el cofre.");
            }
            
        }else{
            System.out.println("NO hay nada que te llame la atención");
        }
    }
    
    
    public int monedero(){
        return dani.getDinero();
    }
    
    public void volver(){
        if (this.habitacionAnterior != null) {
            this.habitacionActual = this.habitacionAnterior; 
            
        }
    }
    
    public void fin() {
        System.out.println("Gracias por jugar ha: La Mansion del Conde Post-Morten");
    }
}
