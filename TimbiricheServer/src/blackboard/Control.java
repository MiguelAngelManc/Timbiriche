/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackboard;

import fuentes.*;
import java.util.ArrayList;
import objetosNegocios.ConexionServer;

/**
 *
 * @author labcisco
 */
public class Control {

    // Coleccion de fuentes de conocimiento
    ArrayList<Fuente> fuentes;

    // Conexion al blackboard
    private Blackboard bb;

    // Constructor vacio que inicializa los valores
    public Control() {
        bb = Blackboard.getInstance();
        fuentes = new ArrayList<>();

        // Agregar las fuentes cuando las creamos
        fuentes.add(new FuenteNuevoUsuario());
        fuentes.add(new FuenteObtenerPartidas());
        fuentes.add(new FuenteChat());
        fuentes.add(new FuenteNuevaPartida());
        fuentes.add(new FuenteListaDeUsuarios());
        fuentes.add(new FuenteUnirPartida());
        fuentes.add(new FuenteAbandonarPartida());
        fuentes.add(new FuenteListo());
        fuentes.add(new FuenteDeslisto());
        fuentes.add(new FuenteMovimiento());
        fuentes.add(new FuenteRendicion());
        fuentes.add(new FuenteAcaboPartida());
    }

    // Metodo que llama el blackboard cuando llega nueva informacion
    public void actualizar(ConexionServer con, String msg) {
        for (Fuente f : fuentes) {
            if (f.tratarDeManejar(msg)) {
                f.hacerAccion(con);
                break;
            }
        }
    }

}
