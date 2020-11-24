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
 * @author Home
 */
public class FuenteDeslisto extends Fuente{

    public FuenteDeslisto() {
    }

    @Override
    public boolean tratarDeManejar(String msg) {
        return msg.equals("DESLISTO");
    }

    @Override
    public void hacerAccion(ConexionServer con) {
        // Obtiene partida del usuario
        Partida partida = bb.getPartidas().get(con.getPartidaID());
        // Obtiene jugador del usuario
        Jugador jugador = bb.getJugadores().get(con.getJugadorID());
        // Agrega al jugador a los listos
        int id = partida.getJugadores().indexOf(jugador);
        // Anade a la lista de listos
        partida.getListos().remove(Integer.valueOf(id));
        
        // Envia la partida a todos los involucrados
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
    
    
}
