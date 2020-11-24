/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes;

import java.io.IOException;
import objetosNegocios.ConexionServer;

/**
 *
 * @author labcisco
 */
public class FuenteListaDeUsuarios extends Fuente {
    
    public FuenteListaDeUsuarios(){
        super();
    }

    @Override
    public boolean tratarDeManejar(String msg) {
        return msg.equals("LISTA DE USUARIOS");
    }

    @Override
    public void hacerAccion(ConexionServer con) {
        String chat = "[SERVER]: Lista de usuarios actualmente conectados:";
        
        try{
            // Manda alerta para que empieze a escuchar el usuario
            con.getOut().writeUTF("MENSAJE CHAT GLOBAL");
            con.getOut().writeUTF(chat);
            // Itera sobre las conexiones
            for(ConexionServer c : bb.getConexiones()){
                // Solo envia los activos
                if(c.isActivo()){
                    // Construye el mensaje
                    String lugar = "";
                    String quien = "";
                    String tu = "";
                    
                    if(c.getPartidaID() == -1){
                        lugar = "[LOBBY] ";
                    }else{
                        lugar = "[SALA "+c.getPartidaID()+"] ";
                    }
                    
                    quien = bb.getJugadores().get(c.getJugadorID()).getNombre();
                    
                    if(c.equals(con)){
                        tu = " (Tu!)";
                    }
                    
                    // Junta todo y lo manda
                    String msgFinal = " - "+lugar+quien+tu;
                    con.getOut().writeUTF("MENSAJE CHAT GLOBAL");
                    con.getOut().writeUTF(msgFinal);
                }
            }
            
            con.getOut().flush();
        }catch(IOException e){
            System.out.println("Error al enviar lista de usuarios al usuario. Mensaje original:"+e.getMessage());
        }
    }
    
    
}
