/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import objetosNegocios.ConexionServer;
import objetosNegocios.Partida;

/**
 *
 * @author Home
 */
public class FuenteNuevaPartida extends Fuente{

    public FuenteNuevaPartida() {
        super();
    }

    @Override
    public boolean tratarDeManejar(String msg) {
        return msg.equals("NUEVA PARTIDA");
    }

    @Override
    public void hacerAccion(ConexionServer con) {
        // Obtiene la lista de partidas
        ArrayList<Partida> partidas = bb.getPartidas();
        // Obtiene el numero de jugadores
        int jugadores;
        try{
            jugadores = con.getIn().readInt();
        }catch(IOException e){
            System.out.println("Error al crear partida nueva. Mensaje original"+e.getMessage());
            return;
        }
        // Obtiene el ID de la partida
        int id = partidas.size();
        
        // Crea una nueva partida
        Partida nueva = new Partida(id,jugadores);
        
        // Agrega al jugador que la crea segun su ID
        nueva.agregarJugador(bb.getJugadores().get(con.getJugadorID()));
        // Asigna color default al jugador
        nueva.getJugadores().get(0).setColor(Color.red);
        
        // Asigna el ID de partida a la conexion
        con.setPartidaID(id);
        
        // Agrega la partida al blackboard
        partidas.add(nueva);
        
        // Regresa la partida creada al usuario
        try{
            con.getOut().writeUTF("DATOS DE PARTIDA");
            con.getOut().writeObject(nueva);
            con.getOut().flush();
        }catch(IOException e){
            System.out.println("Error al terminar de crear partida nueva. Mensaje original:"+e.getMessage());
        }
    }
    
    
}
