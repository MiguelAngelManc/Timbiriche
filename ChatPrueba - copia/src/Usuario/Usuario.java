/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author HPENVY
 */
public class Usuario {

    final static int ServerPort = 1234;

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     *
     */
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);

        // getting localhost ip 
        InetAddress ip = InetAddress.getByName("localhost"); //se encuentra la direccion ip por el nombre que se tenga designada, 
        //si se desea por direccion se tendria que hacer un arreglo 

        // establece lo que es la conexion que esta fija e instanciada arriba (verificar que sea el mismo numero que el server), junto con la ip del chat 
        Socket s = new Socket(ip, ServerPort);

        // obtiene los flujos de entrada y salida por socket 
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        //Hilo para enviar a los mensajes
        Thread enviaMensaje = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    // lee el mensaje a enviar 
                    String mensajeEnvi = scn.nextLine();

                    try {
                        // Escribe el flujo de salida 
                        dos.writeUTF(mensajeEnvi);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //hilo para leer los mensajes 
        Thread leerMensaje = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        //Lee el mensaje 
                        String mensajeRecib = dis.readUTF();
                        System.out.println(mensajeRecib);
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            }
        });

        enviaMensaje.start();
        leerMensaje.start();

        // TODO code application logic here
    }

}
