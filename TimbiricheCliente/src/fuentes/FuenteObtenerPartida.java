/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes;

import java.io.IOException;
import javax.swing.JPanel;
import objetosNegocios.Conexion;
import objetosNegocios.Jugador;
import objetosNegocios.Partida;

/**
 *
 * @author labcisco
 */
public class FuenteObtenerPartida extends Fuente{

    public FuenteObtenerPartida() throws IOException {
        super();
    }

    @Override
    public boolean tratarDeManejar(String msg) {
        return msg.equals("DATOS DE PARTIDA");
    }

    @Override
    public void hacerAccion(Conexion con) throws IOException {
        // Recibe los datos del servidor
        Partida partida;
        try{
            partida = (Partida) con.getIn().readObject();
        }catch(ClassNotFoundException e){
                throw new IOException(e);
        }
        
        
        // Actualiza la partida que tiene el cliente
        bb.getControlPartida().crearPartida(partida);
        
        
        // Actualiza la interfaz de la partida
        
        // Oculta todos los paneles
        for(JPanel panel: bb.getPanelesJugadores()){
            panel.setVisible(false);
        }
        // Itera por los jugadores de la partida
        int i=0;
        for(Jugador jugador : partida.getJugadores()){
            bb.getPanelesJugadores()[i].setVisible(true);
            bb.getImagenes()[i].setIcon(jugador.getImagen());
            bb.getColores()[i].setBackground(jugador.getColor());
            bb.getPuntos()[i].setText(""+jugador.getPuntos());
            bb.getPuntos()[i].setForeground(jugador.getColor());
            bb.getOtros()[i].setForeground(jugador.getColor());
            

            // Estado de listo
            if(partida.getListos().contains(i)){
                bb.getOtros()[i].setText("LISTO");
            }else
            {
                bb.getOtros()[i].setText("");
            }
            // Estado de turno
            
            i++;
        }
    }
    
    
    
}
