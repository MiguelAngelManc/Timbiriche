/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes;

import java.io.IOException;
import java.util.ArrayList;
import objetosNegocios.ConexionServer;
import objetosNegocios.Jugador;

/**
 *
 * @author Home
 */
public class FuenteNuevoUsuario extends Fuente {

    public FuenteNuevoUsuario() {
        super();
    }

    @Override
    public void hacerAccion(ConexionServer con) {
        // Obtiene la lista de jugadores del blackboard
        ArrayList<Jugador> jugadores = bb.getJugadores();
        // Agrega el jugador nuevo que manda el cliente.
        try {
            Jugador nuevo = (Jugador) con.getIn().readObject();
            jugadores.add(nuevo);
        } catch (Exception e) {
            System.out.println("Error al obtener el jugador nuevo. Mensaje original: " + e.getMessage());
        }
        // Comunica a los demas
        for(ConexionServer c : bb.getConexiones()){
            if(c.isActivo()){
                if(c!=con){
                    try{
                        c.getOut().writeUTF("MENSAJE CHAT GLOBAL");
                        c.getOut().writeUTF("[SERVER]: "+bb.getJugadores().get(con.getJugadorID()).getNombre()+" se ha unido al server!");
                        c.getOut().flush();
                    }catch(IOException e){
                        System.out.println("Error al informar a los demas usuarios que se unio uno nuevo. Mensaje original:"+e.getMessage());
                    }
                }
            }
        }
    }

    @Override
    public boolean tratarDeManejar(String msg) {
        return msg.equals("NUEVO USUARIO");
    }

}
