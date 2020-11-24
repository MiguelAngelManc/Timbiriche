/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author HPENVY
 */
public class manejaCliente implements Runnable {

    Scanner scn = new Scanner(System.in);
    private String jugadorJuego;
    DataInputStream dis;
    DataOutputStream dos;
    Socket s;
    boolean estaEnSesion;

    // constructor 
    public manejaCliente(Socket s, String jugadorJuego,
            DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        this.jugadorJuego = jugadorJuego;
        this.s = s;
        this.estaEnSesion = true;
    }

    @Override
    public void run() {

        //AQUI INICIALIZA LO QUE ES EL MENSAJE QUE SE VA A RECIBIR
        String MenRecibido;
        while (true) {
            try {
                // Recibe el String y lo lee
                MenRecibido = dis.readUTF();
                //lee la linea ya descifrada, que va a ser el mensaje 

                System.out.println(MenRecibido);

                //Esto se va a encargar de sacar a un usuario de la sesion de chat por mensaje 
                if (MenRecibido.equals("Salir sesion")) {
                    this.estaEnSesion = false;
                    this.s.close();
                    break;
                }
                
                //La clase StringTokenizer nos ayuda a dividir un string en substrings o tokens,
                //en base a otro string (normalmente un carácter) separador entre ellos denominado delimitador.
                // Rompe el string en lo que es el mensaje y el receptor de este 
                StringTokenizer st = new StringTokenizer(MenRecibido, "#"); 
                String MensajeAEnviar = st.nextToken();
                String receptor = st.nextToken();

                //Busca a quien corresponde el mensaje de la lista de los jugadores 
                //El vector aqui se encarfa de guardar al cliente de usuarios activos 
                for (manejaCliente manejacli : Server.manejaClie) {
                    // si se encuentra el receptor se encarga de enviar el mensaje dado
                    if (manejacli.jugadorJuego.equals(receptor) && manejacli.estaEnSesion == true) {
                        manejacli.dos.writeUTF(this.jugadorJuego + " : " + MensajeAEnviar);
                        break;
                    }
                }
            } catch (IOException e) {
            }

        }
        try {
            // cierra todo 
            this.dis.close();
            this.dos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
