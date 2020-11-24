/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes;

import java.io.IOException;
import objetosNegocios.Conexion;

/**
 *
 * @author Home
 */
public class FuenteChatGlobal extends Fuente{

    public FuenteChatGlobal() throws IOException {
        super();
    }
    
    @Override
    public boolean tratarDeManejar(String msg) {
        return msg.equals("MENSAJE CHAT GLOBAL");
    }

    @Override
    public void hacerAccion(Conexion con) throws IOException {
        // Lee el mensaje
        String chat = con.getIn().readUTF();
        
        // Actualiza el chat del lobby
        bb.getChatGlobalLobby().setText(bb.getChatGlobalLobby().getText()+chat+"\n");
        // Actualiza el chat de la partida si existe
        if(bb.getChatGlobalJuego() != null){
            bb.getChatGlobalJuego().setText(bb.getChatGlobalJuego().getText()+chat+"\n");
            // Actualiza la pestana si no esta seleccionada
            if(bb.getPanelChat().getSelectedIndex() != 1){
                bb.getPanelChat().setTitleAt(1, "Global (!)");
        }
        }
    }
}
