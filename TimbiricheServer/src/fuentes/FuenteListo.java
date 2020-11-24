/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes;

import java.io.IOException;
import java.util.Collections;
import objetosNegocios.ConexionServer;
import objetosNegocios.Jugador;
import objetosNegocios.Partida;

/**
 *
 * @author Home
 */
public class FuenteListo extends Fuente {

    public FuenteListo() {
        super();
    }

    @Override
    public boolean tratarDeManejar(String msg) {
        return msg.equals("LISTO");
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
        partida.getListos().add(id);
        
        // Revisa si deberia empezar
        if(partida.getJugadores().size()==partida.getListos().size() && partida.getListos().size()!=1){
            extras.Extras.empezarPartida(con);
            return;
        }
        
        // Envia la partida a todos los involucrados
        extras.Extras.mandaPartida(con);
    }
    
    
}
