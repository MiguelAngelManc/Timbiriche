/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes;

import java.util.ArrayList;
import objetosNegocios.ConexionServer;
import objetosNegocios.Partida;

/**
 *
 * @author labcisco
 */
public class FuenteObtenerPartidas extends Fuente {

    public FuenteObtenerPartidas(){
        super();
    }
    
    @Override
    public void hacerAccion(ConexionServer con) {
        ArrayList<Partida> partidas = bb.getPartidas();
        // Regresa la lista de partidas en el servidor
        try {
            con.getOut().writeUTF("LISTA DE PARTIDAS");
            con.getOut().writeInt(partidas.size());
            // Limpia el cache de objetos
            con.getOut().reset();
            for (Partida p : partidas) {
                con.getOut().writeObject(p);
            }
            con.getOut().flush();
        } catch (Exception e) {
            System.out.println("Error al enviar partidas al usuario nuevo. Mensaje original: " + e.getMessage());
        }
    }

    @Override
    public boolean tratarDeManejar(String msg) {
        return msg.equals("OBTENER PARTIDAS");
    }

    
    
}
