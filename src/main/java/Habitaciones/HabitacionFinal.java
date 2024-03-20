/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Habitaciones;

import com.mycompany.practicalaberinto.Jugador;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Néstor y asociados
 */
public class HabitacionFinal extends Habitacion{

    private boolean respuesta;
    
    public HabitacionFinal(String descripcion) {
        super(descripcion);
    }
    //Está subclase solo existe para llevar a cabo el final del juego.
    public void finales(Jugador items) throws InterruptedException{
        Scanner sc = new Scanner(System.in);
        String fin= "";
        System.out.println("Una luz intensa te rodea. Tu intensidad te deslumbra y no te deja ver. Escuhcas una voz que viene de todas partes.");
        //Las paradas le dan dramatismo y favorecea su lectura.
        TimeUnit.SECONDS.sleep(5);
        System.out.print("-Has llegado lejos. Ha estas alturas has de saber ya lo que ocurre.");
        TimeUnit.SECONDS.sleep(2);
        System.out.println(" Has muerto.-");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("-Por la mansion he dejado trozos de tus memorias en forma de deathcoins para que al recolectarlos pudieras reflexionar sobre tu vida-");
        TimeUnit.SECONDS.sleep(2);
        //Solo se puede completar el juego bajo tener cierta cantidad de monedas.
        if (items.getDinero() > 50) {
            System.out.println("Veo que tienes suficientes deathcoins.");
            TimeUnit.SECONDS.sleep(2);
            do{
                //La posibilidad de decision es por si el jugador quiere poder revisar las salas antes de irse.
                System.out.println("-Dime, ¿Estas listo para partir?[SI/NO]- ");
                fin = sc.nextLine();

                if (fin.equalsIgnoreCase("si")) {
                    System.out.println("Felicidades, sigueme y marchemos hasta el infinito y más alla.");
                    respuesta = true;
                    break;
                }else if (fin.equalsIgnoreCase("no")) {
                    System.out.println("Vuelve cuando estes listo.");
                    respuesta = false;
                    break;
                }else{
                    System.out.println("No te entiendo.");
                }
            }while(true);
        }else if(items.getDinero() < 50){
            System.out.println("No tienes suficientes deathcoins");
            System.out.println("Vuelve cuando tengas más.");
            respuesta = false;
        }
    }


    public boolean isRespuesta() {
        return respuesta;
    }
    

}
