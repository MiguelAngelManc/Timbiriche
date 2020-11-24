/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes;

import java.io.IOException;
import java.util.ArrayList;
import objetosNegocios.ConexionServer;

/**
 *
 * @author labcisco
 */
public class FuenteChat extends Fuente {

    public FuenteChat(){
        super();
    }
    
    @Override
    public boolean tratarDeManejar(String msg) {
        // Solo la maneja si el mensaje empieza con CHAT
        return msg.startsWith("MENSAJE DE CHAT");
    }

    @Override
    public void hacerAccion(ConexionServer con) {
        // Identifica de quien y de que sala es, para estructurar el mensaje
        try{
            String quien = con.getIn().readUTF();
            int donde = con.getPartidaID();
            boolean global = con.getIn().readBoolean();
            String msg = con.getIn().readUTF();
            
            
            String chat;
            // Caso donde el mensaje es global (lobby)
            if(donde == -1)
                chat = "[LOBBY] "+quien+": "+msg;
            else
                chat = "[SALA "+donde+"] "+quien+": "+msg;
            
            // Obitene todo los usuarios conectados actualmente
            ArrayList<ConexionServer> conexiones = bb.getConexiones();
            // Envia mensaje a los que corresponde
            for(ConexionServer c : conexiones){
                // Solo a los activos
                if(c.isActivo()){
                    // Mensaje global
                    if(global){
                        c.getOut().writeUTF("MENSAJE CHAT GLOBAL");
                        c.getOut().writeUTF(chat);
                        c.getOut().flush();
                    }
                    // Mensaje local de partida
                    else if(c.getPartidaID() == donde){
                        c.getOut().writeUTF("MENSAJE CHAT LOCAL");
                        c.getOut().writeUTF(chat);
                        c.getOut().flush();
                    }
                }
            }
            
        }catch(IOException e){
            System.out.println("Error al momento del chat de la conexion "+con.getJugadorID()+". Mensaje original:"+e.getMessage());
        }
    }
    
}
