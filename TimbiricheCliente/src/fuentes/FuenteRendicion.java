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
 * @author Home
 */
public class FuenteRendicion extends Fuente{

    public FuenteRendicion() throws IOException {
        super();
    }

    @Override
    public boolean tratarDeManejar(String msg) {
        return msg.equals("RENDICION");
    }

    @Override
    public void hacerAccion(Conexion con) throws IOException {
        // Recibe los datos del servidor
        Partida partida;
        int turno;
        int cobarde;
        try{
            partida = (Partida) con.getIn().readObject();
            turno = con.getIn().readInt();
            cobarde = con.getIn().readInt();
        }catch(ClassNotFoundException e){
                throw new IOException(e);
        }
        
        // Actualiza la partida que tiene el cliente
        bb.getControlPartida().setPartida(partida);
        
        // Actualiza la interfaz de la partida
        String nombre = bb.getControlPartida().getPartida().getJugadores().get(cobarde).getNombre();
        bb.getChatLocal().setText(bb.getChatLocal().getText()+"[SERVER]: "+nombre+" ha abandonado la partida.\n");
        
        // Itera por los jugadores de la partida
        int i=0;
        for(Jugador jugador : partida.getJugadores()){
            // Control de turno
            if(i==turno){
                bb.getOtros()[i].setText("TURNO");
            }else{
                bb.getOtros()[i].setText("");
            }
            
            // Actualiza gente que se fue
            if(!jugador.isVivo()){
                bb.getImagenes()[i].setIcon(jugador.getImagen());
                bb.getColores()[i].setBackground(jugador.getColor());
                bb.getPuntos()[i].setText(""+jugador.getPuntos());
                
                // Conserva el color de lado cliente
                Color local = bb.getColores()[i].getBackground();
                Color server = jugador.getColor();
                if(!local.equals(server)){
                    bb.getControlPartida().cambiarColor(local, i);
                }
                
                bb.getOtros()[i].setText("COBARDE");
            }
            
            i++;
        }
        
        // Actualiza el panel de juego
        bb.getPnlJuego().repaint();

    }
    
    
}
