/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import blackboard.Control;
import java.io.IOException;
import objetosNegocios.ConexionServer;

/**
 *
 * @author labcisco
 */
public class HiloComunicacion extends Thread{
    private ConexionServer con;
    private Control control;
    
    public HiloComunicacion(ConexionServer con, Control control){
        this.con = con;
        this.control = control;
    }
    
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                // Obtiene la informacion que mando el cliente
                                String msg = con.getIn().readUTF();
                                System.out.println("Conexion "+con.getJugadorID()+":"+msg);
                                // Notifica a la clase de control
                                control.actualizar(con, msg);

                            } catch (IOException e) {
                                System.out.println("Error en la comunicacion con usuario " + con.getJugadorID() + ". Mensaje original: " + e.getMessage());
                                // Marca la conexion como inactiva
                                con.setActivo(false);
                                // Mata la conexion.
                                interrupt();
                                break;
                            }
                        }
                    }
}
