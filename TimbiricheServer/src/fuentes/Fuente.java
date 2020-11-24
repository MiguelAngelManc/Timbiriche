/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes;

import blackboard.Blackboard;
import objetosNegocios.ConexionServer;

/**
 * Clase padre de las fuentes de conocimientos para poder agruparlas.
 *
 * @author labcisco
 */
public abstract class Fuente {

    protected Blackboard bb;

    public Fuente() {
        bb = Blackboard.getInstance();
    }
    
    public abstract boolean tratarDeManejar(String msg);

    public abstract void hacerAccion(ConexionServer con);
    
}
