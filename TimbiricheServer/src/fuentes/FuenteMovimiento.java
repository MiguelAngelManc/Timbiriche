/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes;

import java.io.IOException;
import objetosNegocios.ConexionServer;
import objetosNegocios.Partida;

/**
 *
 * @author labcisco
 */
public class FuenteMovimiento extends Fuente{

    public FuenteMovimiento() {
        super();
    }

    @Override
    public boolean tratarDeManejar(String msg) {
        return msg.equals("MOVIMIENTO");
    }

    @Override
    public void hacerAccion(ConexionServer con) {
        // Recupera datos de cliente
        Partida partida;
        int resultado;
        try{
            partida = (Partida)con.getIn().readObject();
            resultado = con.getIn().readInt();
        }catch(Exception e){
            System.out.println("Error al recibir movimiento del jugador. Mensaje original: "+e.getMessage());
            return;
        }
        
        // Avanza el turno si dibujo linea
        if(resultado == 4){
            extras.Extras.avanzaTurn(partida);
        }
        
        // Actualiza la partida que tiene el servidor
        bb.getPartidas().set(con.getPartidaID(), partida);
        
        // Recomunica el movimiento a todos los usuarios de la misma partida
        for(ConexionServer c : bb.getConexiones()){
                if(c.isActivo()){
                    if(c.getPartidaID() == con.getPartidaID()){
                        try{
                            c.getOut().writeUTF("MOVIMIENTO");
                            // Limpia cache
                            c.getOut().reset();
                            c.getOut().writeObject(partida);
                            c.getOut().writeInt(partida.getTurno()-1);
                            c.getOut().flush();
                        }catch(IOException e){
                            System.out.println("Error al enviar la partida a los usuarios. Mensaje original: "+e.getMessage());
                        }
                    }
                }
            }
    }
    
    
}
