/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes;

import java.awt.Color;
import java.io.IOException;
import objetosNegocios.Conexion;
import objetosNegocios.Jugador;
import objetosNegocios.Partida;

/**
 *
 * @author labcisco
 */
public class FuenteEmpezoPartida extends Fuente{

    public FuenteEmpezoPartida() throws IOException {
        super();
    }

    @Override
    public boolean tratarDeManejar(String msg) {
        return msg.equals("EMPEZO PARTIDA");
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
        // Empieza la partida
        bb.getControlPartida().empezarPartida(bb.getLadoTablero());
        
        // Asigna el turno al jugador
        bb.setJugadorInt(con.getIn().readInt());
        
        // Actualiza la interfaz de la partida
        
        // Itera por los jugadores de la partida
        int i=0;
        for(Jugador jugador : partida.getJugadores()){
            bb.getPanelesJugadores()[i].setVisible(true);
            bb.getImagenes()[i].setIcon(jugador.getImagen());
            bb.getColores()[i].setBackground(jugador.getColor());
            bb.getPuntos()[i].setText(""+jugador.getPuntos());
            bb.getPuntos()[i].setForeground(jugador.getColor());
            bb.getOtros()[i].setForeground(jugador.getColor());
            if(i==0){
                bb.getOtros()[i].setText("TURNO");
            }else{
                bb.getOtros()[i].setText("");
            }
            // Identificador propio
            if(i+1==bb.getJugadorInt()){
                bb.getColores()[i].setForeground(Color.white);
                bb.getColores()[i].setText("Tu!");
            }
            i++;
        }
        
        bb.getJuego().empezarPartida();
        bb.setPnlJuego(bb.getJuego().getPnlJuego());
    }
    
    
}
