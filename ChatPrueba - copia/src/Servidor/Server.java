/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * Handler se encarga de procesar y enviar todos los datos de tipo mensajes, usa
 * por dentro hilos, sirve para facilitar //su funcion los socket son los que se
 * encargan de lo que son estas conexiones que hacen los chats, principlamente
 * hacen todo el manejo de flujo de datos
 *
 * @author HPENVY
 */
public class Server {
    
    //Un vector es similar a un array, la diferencia estriba en que un vector crece automáticamente cuando alcanza la dimensión inicial máxima. 
    //Además, proporciona métodos adicionales para añadir, eliminar elementos, e insertar elementos entre otros dos existentes.

    static Vector<manejaCliente> manejaClie = new Vector<>();

    // contador para el numero de usuarios/clientes que entran al chat 
    static int jugador = 1; //jugador es para ir nombrando a cada jugador ej. jugador 1, 2, etc. 
    

    public static void main(String[] args) throws IOException {
        // Aqui se muestra que el Socket del servidor se conecta a lo que es el puerto designado 
        ServerSocket ss = new ServerSocket(1234);

        Socket s;

        // hace un ciclo infinito para buscar lo que son las nuevas peticiones de  
        // los nuevos jugadores 
        while (true) {
            // Acepta a los nuevos jugadores a entrar al chat 
            s = ss.accept();

            System.out.println("Peticion del nuevo jugador... : " + s);

            // Obtiene los flujos de entrada y de salida 
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            System.out.println("Creando un nuevo apartado para el nuevo jugador...");

            // Crea un nuevo handler para este nuevo jugador. 
            manejaCliente mtch = new manejaCliente(s, "client " + jugador, dis, dos);

            // Crea un nuevo hilo para este jugador
            Thread hilo = new Thread((Runnable) mtch);

            System.out.println("Añadiendo nuevo usuario a la lista...");

            // lo agrega a la lista e participantes activos  
            manejaClie.add(mtch);

            // inicia el hilo. 
            hilo.start();

            // va incrementando el ciclo por numero de jugadroees
            jugador++;

        }
    }

}
