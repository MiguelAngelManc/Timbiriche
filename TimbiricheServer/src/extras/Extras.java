/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extras;

import blackboard.Blackboard;
import java.io.IOException;
import java.util.Collections;
import objetosNegocios.ConexionServer;
import objetosNegocios.Jugador;
import objetosNegocios.Partida;

/**
 *
 * @author labcisco
 */
public class Extras {
    
    public static void mandaPartida(ConexionServer con){
        Blackboard bb = Blackboard.getInstance();
        Partida partida = bb.getPartidas().get(con.getPartidaID());
        for(ConexionServer c : bb.getConexiones()){
                if(c.isActivo()){
                    if(c.getPartidaID() == con.getPartidaID()){
                        try{
                            c.getOut().writeUTF("DATOS DE PARTIDA");
                            // Limpia el cache
                            c.getOut().reset();
                            c.getOut().writeObject(partida);
                            c.getOut().flush();
                        }catch(IOException e){
                            System.out.println("Error al enviar la partida a los usuarios. Mensaje original: "+e.getMessage());
                        }
                    }
                }
            }
    }
    
    public static void empezarPartida(ConexionServer con){
        Blackboard bb = Blackboard.getInstance();
        Partida partida = bb.getPartidas().get(con.getPartidaID());
        // Establece que empezo la partida
            partida.setEmpezada(true);
            // Vacia la lista de listos
            partida.getListos().clear();
            
            // Revuelve el orden de turnos
            Collections.shuffle(partida.getJugadores());
            
            // Indica que empezo la partida a los involucrados
            for(ConexionServer c : bb.getConexiones()){
                if(c.isActivo()){
                    if(c.getPartidaID() == con.getPartidaID()){
                        try{
                            c.getOut().writeUTF("EMPEZO PARTIDA");
                            // Limpia el cache
                            c.getOut().reset();
                            c.getOut().writeObject(partida);
                            // Manda el ID de jugador
                            c.getOut().writeInt(partida.getJugadores().indexOf(bb.getJugadores().get(c.getJugadorID()))+1);
                            c.getOut().flush();
                        }catch(IOException e){
                            System.out.println("Error al empezar la partida a los usuarios. Mensaje original: "+e.getMessage());
                        }
                    }
                }
            }
    }
    
    public static void avanzaTurn(Partida partida){
        // Avanza turno
        partida.setTurno(partida.getTurno()+1);
        if(partida.getTurno()>partida.getJugadores().size()){
                partida.setTurno(1);
        }
            // Itera por la lista de vivos para ver quien sigue
            while(!partida.getJugadores().get(partida.getTurno()-1).isVivo()){
                // Avanza turno
                partida.setTurno(partida.getTurno()+1);
                if(partida.getTurno()>partida.getJugadores().size()){
                    partida.setTurno(1);
                }
            }
            
    }
    
    public static void acabarPartida(int partidaID, Partida partida){
        Blackboard bb = Blackboard.getInstance();
        
        // Determina el ganador
        int i = 0;
        int mayorPuntos = -1;
        int mayorPosicion = -1;
        for(Jugador jugador : partida.getJugadores()){
            // Solo considera a los vivos
            if(jugador.isVivo()){
                if(jugador.getPuntos()>mayorPuntos){
                    mayorPuntos = jugador.getPuntos();
                    mayorPosicion = i;
                }
            }
            i++;
        }
        
        // Manda el ganador a los involucrados de la partida
        for(ConexionServer c : bb.getConexiones()){
                if(c.isActivo()){
                    if(c.getPartidaID() == partidaID){
                        try{
                            c.getOut().writeUTF("ACABO PARTIDA");
                            c.getOut().writeInt(mayorPosicion);
                            c.getOut().flush();
                            // Remueve de la partida
                            c.setPartidaID(-1);
                        }catch(IOException e){
                            System.out.println("Error al acabar la partida a los usuarios. Mensaje original: "+e.getMessage());
                        }
                    }
                }
            }
        
        // Remueve la partida del blackboard
        bb.getPartidas().remove(partidaID);
    }
}
