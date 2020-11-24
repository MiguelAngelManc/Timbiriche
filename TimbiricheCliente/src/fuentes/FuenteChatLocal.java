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
 * @author labcisco
 */
public class FuenteChatLocal extends Fuente{

    public FuenteChatLocal() throws IOException {
        super();
    }

    @Override
    public boolean tratarDeManejar(String msg) {
        return msg.equals("MENSAJE CHAT LOCAL");
    }

    @Override
    public void hacerAccion(Conexion con) throws IOException {
        // Lee el mensaje
        String chat = con.getIn().readUTF();
        bb.getChatLocal().setText(bb.getChatLocal().getText()+chat+"\n");
        // Actualiza la pestana si no esta seleccionada
        if(bb.getPanelChat().getSelectedIndex() != 0){
            bb.getPanelChat().setTitleAt(0, "Local (!)");
        }
    }
    
    
}
