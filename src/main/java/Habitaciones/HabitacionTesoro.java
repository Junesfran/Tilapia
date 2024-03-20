/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Habitaciones;

import com.mycompany.practicalaberinto.Jugador;
import java.util.Random;

/**
 *
 * @author Néstor y asociados
 */
public class HabitacionTesoro extends Habitacion{
    private int monedas;
    //Estos atributos son staticos booleanos porque son los que me indican que esa recompensa ha o no salido.
    //Esto es así porque si no podría pasar que salieran solo monedas o solo llaves.
    private static boolean dinero1 = true, dinero2 = true, cuchillo = true, llave = true;
    //Este atributo de aqui me comprueba si el cofre particular ha sido o no abierto.
    private boolean abrir = true;
    
    public HabitacionTesoro(String descripcion) {
        super(descripcion);
    }
    
    //Hay cuatro cofres en total. Hay cuatro posibles recompensas pero estan repartidas de manera aleatoria.
    public void setTesoro(Jugador items){
        Random r = new Random();
        //El bucle infinito es porque la recompensa al ser aleatoria podría salir dos veces el mismo número y no hacer nada por tener 
        //su correspondiente booleano en false.
        //De esta manera solo tengo que romper el bucle con break una vez ha hecho lo que necesito.
        while(true){
            int gacha = r.nextInt(1,5);
            if (gacha == 1) {
                if(dinero1){
                    this.monedas = r.nextInt(1,50);
                    System.out.println("Has obtenido "+monedas+"$"); 
                    dinero1 = false;
                    items.setDinero(monedas);
                    break;
                }
            }else if(gacha == 2){
                if(dinero2){
                    this.monedas = r.nextInt(51,100);
                    System.out.println("Has obtenido "+monedas+"$");
                    dinero2 = false;
                    items.setDinero(monedas);
                    break;
                }
            }else if(gacha == 3){
                if(cuchillo){
                    System.out.println("Has conseguido un cuchillo con el que defenderte.");
                    cuchillo = false;
                    items.setArma();
                    break;
                }
            }else if(gacha == 4){
                if(llave){
                    System.out.println("Has conseguido una llave algo viejo, pero no sabes que puede abrir.");
                    llave = false;
                    items.setLlave(abrir);
                    break;
                }
            }
        }
    }
    
    //Me comprueba el estado de abrir
    public boolean isAbrir() {
        return abrir;
    }

    //Lo utilizo para cerrar el cofre una vez que se ha abierto
    public void setAbrir() {
        this.abrir = false;
    }
         
    
    }
    


