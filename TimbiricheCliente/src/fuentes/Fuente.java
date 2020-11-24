/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes;

import blackboard.Blackboard;
import java.io.IOException;
import objetosNegocios.Conexion;

/**
 * Clase padre de las fuentes de conocimientos para poder agruparlas.
 *
 * @author labcisco
 */
public abstract class Fuente {
    
    protected Blackboard bb;
    
    public Fuente() throws IOException{
        this.bb = Blackboard.getInstance(null);
    }
    
    public abstract boolean tratarDeManejar(String msg);

    public abstract void hacerAccion(Conexion con) throws IOException;
    
}
