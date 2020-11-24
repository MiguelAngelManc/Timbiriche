/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes;

import objetosNegocios.ConexionServer;
import objetosNegocios.Partida;

/**
 *
 * @author Home
 */
public class FuenteAcaboPartida extends Fuente{

    public FuenteAcaboPartida() {
        super();
    }

    @Override
    public boolean tratarDeManejar(String msg) {
        return msg.equals("ACABO PARTIDA");
    }

    @Override
    public void hacerAccion(ConexionServer con) {
        // Recupera informacion necesario
        int id = con.getPartidaID();
        Partida partidaFinal;
        try{
            partidaFinal = (Partida)con.getIn().readObject();
        }catch(Exception e){
            System.out.println("Error al recibir final del jugador. Mensaje original: "+e.getMessage());
            return;
        }
        
        extras.Extras.acabarPartida(id, partidaFinal);
    }
    
    
}
