/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import blackboard.Blackboard;
import java.io.IOException;

/**
 *
 * @author labcisco
 */
public class HiloComunicacion extends Thread{
    
    private Blackboard com;
    private String error;

    public HiloComunicacion(Blackboard com) {
        this.com = com;
        error = "";
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    @Override
    public void run(){
        while (true) {
                            try {
                                // Obtiene la informacion que mando el servidor
                                String msg = com.getConexion().getIn().readUTF();
                                System.out.println("Server:"+msg);
                                // Notifica a la clase de control
                                com.getRecibe().actualizar(msg);

                            } catch (IOException e) {
                                System.out.println("Error en la comunicacion con el servidor. Mensaje original: " + e.getMessage());
                                // Mata la conexion y cierra el programa.
                                interrupt();
                                System.exit(1);
                                break;
                            }
                        }
    }
}
