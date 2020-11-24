/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes;

import java.io.IOException;
import objetosNegocios.ConexionServer;
import objetosNegocios.Jugador;
import objetosNegocios.Partida;

/**
 *
 * @author labcisco
 */
public class FuenteAbandonarPartida extends Fuente{

    public FuenteAbandonarPartida() {
        super();
    }

    @Override
    public boolean tratarDeManejar(String msg) {
        return msg.equals("ABANDONAR PARTIDA");
    }

    @Override
    public void hacerAccion(ConexionServer con) {
        // Obtiene datos relevantes del usuario (su jugador y partida)
        Jugador jugador = bb.getJugadores().get(con.getJugadorID());
        Partida partida = bb.getPartidas().get(con.getPartidaID());
        
        // Hace cosas diferentes si la partida esta empezada o solo en fase de preparacion
        if(!partida.isEmpezada()){
            // Remueve al jugador
            partida.getJugadores().remove(jugador);
            // Si ya no hay nadie, elimina la partida del blackboard
            if(partida.getJugadores().isEmpty()){
                bb.getPartidas().remove(partida);
            }else{
                // Manda el estado de la partida al resto de los jugadores
                // Envia la partida a todos los involucrados
                for(ConexionServer c : bb.getConexiones()){
                    if(c.isActivo()){
                        if(c.getPartidaID() == con.getPartidaID()){
                            try{
                                if(!c.equals(con)){
                                    c.getOut().writeUTF("DATOS DE PARTIDA");
                                    // Limpia el cache
                                    c.getOut().reset();
                                    c.getOut().writeObject(partida);
                                    c.getOut().flush();
                                }
                            }catch(IOException e){
                                System.out.println("Error al enviar la partida a los usuarios. Mensaje original: "+e.getMessage());
                            }
                        }
                    }
                }
            }
        }
        
        // Establece el id de partida del usuario a lobby
        con.setPartidaID(-1);
    }
    
    
}
