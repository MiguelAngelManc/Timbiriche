/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackboard;

import fuentes.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Home
 */
public class ControlRecibe {
    // Fuentes de conocimiento
    private ArrayList<Fuente> fuentes;
    
    // Conexion al blackboard
    private Blackboard bb;
    
    public ControlRecibe() throws IOException{
        fuentes = new ArrayList<>();
        bb = Blackboard.getInstance(null);
        
        // Agregar fuentes
        fuentes.add(new FuenteListaPartidas());
        fuentes.add(new FuenteChatLocal());
        fuentes.add(new FuenteChatGlobal());
        fuentes.add(new FuenteObtenerPartida());
        fuentes.add(new FuenteError());
        fuentes.add(new FuenteEmpezoPartida());
        fuentes.add(new FuenteMovimiento());
        fuentes.add(new FuenteRendicion());
        fuentes.add(new FuenteAcabarPartida());
    }
    
    public void actualizar(String msg){
        // Itera por las fuentes para ver quien puede manejarlo
        try{
            for(Fuente f : fuentes){
                if(f.tratarDeManejar(msg)){
                    f.hacerAccion(bb.getConexion());
                    synchronized(bb.getHilo()){
                        bb.getHilo().notifyAll();
                    }
                    break;
                }
            }
        }catch(IOException e){
            synchronized(bb.getHilo()){
                bb.getHilo().setError(e.getMessage());
                bb.getHilo().notify();
            }
            System.out.println("Error al recibir mensajes del servidor. "+e.getMessage());
        }
    }
    
}
