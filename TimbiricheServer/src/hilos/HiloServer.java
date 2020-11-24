/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import blackboard.Control;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import objetosNegocios.ConexionServer;

/**
 *
 * @author labcisco
 */
public class HiloServer extends Thread{
    // Constante de puerto de conexion
    final int PUERTO = 7522;
    // Socket que funciona como punto de acceso al servidor
    ServerSocket server;
    
    // Cosas que ocupa del blackboard
    private ArrayList<ConexionServer> conexiones;
    private Control control;

    public HiloServer(ArrayList<ConexionServer> conexiones, Control control) {
        this.conexiones = conexiones;
        this.control = control;
    }
    
        @Override
        public void run() {
            // Crea el punto de acceso al servidor
            try {
                server = new ServerSocket(PUERTO);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                interrupt();
            }
            // Establece un contador para el numero de conexiones
            int i = 0;
            // Intenta establecer conexion con jugador nuevo
            while (true) {
                try {
                    // Establece conexion con el nuevo jugador
                    Socket nuevo = server.accept();
                    ConexionServer conexion = new ConexionServer(nuevo, i);
                    
                    conexiones.add(conexion);
                    // Crea un hilo de comunicacion
                    Thread hiloComunicacion = new HiloComunicacion(conexion, control);
                    hiloComunicacion.start();
                } catch (IOException e) {
                    System.out.println("Error añadiendo jugador. Mensaje original: " + e.getMessage());
                }
                // Incrementa el contador de conexiones
                i++;
            }
        }
    
}
