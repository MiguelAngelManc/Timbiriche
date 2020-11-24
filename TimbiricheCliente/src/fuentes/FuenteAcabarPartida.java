/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes;

import java.io.IOException;
import objetosNegocios.Conexion;
import objetosNegocios.Jugador;

/**
 *
 * @author Home
 */
public class FuenteAcabarPartida extends Fuente{

    public FuenteAcabarPartida() throws IOException {
        super();
    }

    @Override
    public boolean tratarDeManejar(String msg) {
        return msg.equals("ACABO PARTIDA");
    }

    @Override
    public void hacerAccion(Conexion con) throws IOException {
        // Recibe datos del server
        int ganadorID;
        ganadorID = con.getIn().readInt();
        
        // Recibe el jugador que gano
        Jugador ganador = bb.getControlPartida().getPartida().getJugadores().get(ganadorID);
        bb.getOtros()[ganadorID].setText("GANO!");
        
        // Termina la partida localmente
        bb.getJuego().terminarPartida(ganador);
    }
    
    
}
