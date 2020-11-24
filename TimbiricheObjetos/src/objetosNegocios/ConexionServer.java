/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosNegocios;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Home
 */
public class ConexionServer extends Conexion{

    private int jugadorID;
    private int partidaID;
    
    public ConexionServer(Socket socket, int i) throws IOException {
        super(socket);
        jugadorID = i;
        partidaID = -1;
    }

    public int getJugadorID() {
        return jugadorID;
    }

    public void setJugadorID(int jugadorID) {
        this.jugadorID = jugadorID;
    }

    public int getPartidaID() {
        return partidaID;
    }

    public void setPartidaID(int partidaID) {
        this.partidaID = partidaID;
    }
    
}
