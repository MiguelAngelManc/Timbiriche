/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import objetosNegocios.ConexionServer;
import objetosNegocios.Jugador;
import objetosNegocios.Partida;

/**
 *
 * @author Home
 */
public class FuenteRendicion extends Fuente{

    public FuenteRendicion() {
        super();
    }

    @Override
    public boolean tratarDeManejar(String msg) {
        return msg.equals("RENDICION");
    }

    @Override
    public void hacerAccion(ConexionServer con) {
        // Recupera datos de cliente
        Partida partida;
        int cobarde;
        try{
            partida = (Partida)con.getIn().readObject();
            cobarde = con.getIn().readInt()-1;
        }catch(Exception e){
            System.out.println("Error al recibir movimiento del jugador. Mensaje original: "+e.getMessage());
            return;
        }
        
        try{
            // Consigue la imagen de gallina
            BufferedImage imagen = ImageIO.read(new File("src/extras/Gallina.jpg"));
            // Guarda una copia escalada a 72x72, para el juego
            Image imagenFinal = imagen.getScaledInstance(72, 72, Image.SCALE_DEFAULT);
            // Convierte la foto a icono
            ImageIcon icono = new ImageIcon(imagenFinal);
            partida.getJugadores().get(cobarde).setImagen(icono);
            System.out.println("Cambiado imagen");
        }catch(IOException e){
            System.out.println("Error al actualizar jugador. Mensaje original: "+e.getMessage());
        }
        
        // Avanza el turno si el jugador actual era el que se fue
        if(partida.getTurno() == cobarde+1){
            extras.Extras.avanzaTurn(partida);
        }
        
        // Guarda el ID de partida
        int id = con.getPartidaID();
        // Remuve al jugador de la partida
        con.setPartidaID(-1);
                
        // Manda datos a los relacionados
        for(ConexionServer c : bb.getConexiones()){
                if(c.isActivo()){
                    if(c.getPartidaID() == id){
                        try{
                            c.getOut().writeUTF("RENDICION");
                            // Limpia cache
                            c.getOut().reset();
                            c.getOut().writeObject(partida);
                            c.getOut().writeInt(partida.getTurno()-1);
                            c.getOut().writeInt(cobarde);
                            c.getOut().flush();
                        }catch(IOException e){
                            System.out.println("Error al enviar la partida a los usuarios. Mensaje original: "+e.getMessage());
                        }
                    }
                }
            }
        
        // Revisa si deberia acabar la partida (solo un jugador restante)
        int vivos = 0;
        for(Jugador jugador : partida.getJugadores()){
            if(jugador.isVivo()){
                vivos++;
            }
            if(vivos>1){
                return;
            }
        }
        extras.Extras.acabarPartida(id, partida);
    }
    
    
}
