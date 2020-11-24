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
public class FuenteError extends Fuente {

    public FuenteError() throws IOException {
        super();
    }

    @Override
    public boolean tratarDeManejar(String msg) {
        return msg.equals("ERROR");
    }

    @Override
    public void hacerAccion(Conexion con) throws IOException {
        String msg = con.getIn().readUTF();
        throw new IOException(msg);
    }
    
    
}
