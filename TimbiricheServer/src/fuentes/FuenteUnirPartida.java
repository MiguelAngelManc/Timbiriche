/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes;

import java.awt.Color;
import java.io.IOException;
import objetosNegocios.ConexionServer;
import objetosNegocios.Jugador;
import objetosNegocios.Partida;

/**
 *
 * @author labcisco
 */
public class FuenteUnirPartida extends Fuente{

    public FuenteUnirPartida() {
        super();
    }

    @Override
    public boolean tratarDeManejar(String msg) {
        return msg.equals("UNIR A PARTIDA");
    }

    @Override
    public void hacerAccion(ConexionServer con) {
        int id = -1;
        // Lee a cual partida se quiere unir
        try{
            id = con.getIn().readInt();
        }catch(IOException e){
            System.out.println("Error al leer la partida que se quiere unir el usuario. Mensaje original:"+e.getMessage());
        }
        
        // Variable de error
        boolean error = false;
        
        // Obtiene la partida
        Partida partida = null;
        try{
            partida = bb.getPartidas().get(id);
        }catch(IndexOutOfBoundsException e){
            error = true;
        }
        
        if(partida == null){
            error = true;
        }
        
        if(error){
            try{
                con.getOut().writeUTF("ERROR");
                con.getOut().writeUTF("La partida con ID "+id+" no existe.");
                con.getOut().flush();
                return;
            }catch(IOException e){
                System.out.println("Error al mandar mensaje de error al usuario.");
            }
        }
        
        
        // Trata de unir
        if(!partida.isEmpezada()){
            // Agrega el jugador nuevo
            partida.agregarJugador(bb.getJugadores().get(con.getJugadorID()));
            // Asigna el ID
            con.setPartidaID(id);
            
            // Actualiza colores por default
            int i = 0;
            for(Jugador jugador : partida.getJugadores()){
                switch(i){
                    case 0: jugador.setColor(Color.red);break;
                    case 1: jugador.setColor(Color.blue);break;
                    case 2: jugador.setColor(Color.green);break;
                    case 3: jugador.setColor(Color.yellow);break;
                }
                i++;
            }
            
            // Revisa si debe cerrar la partida
            if(partida.getJugadores().size() == partida.getJugadoresMax()){
                extras.Extras.empezarPartida(con);
                return;
            }
            
            // Envia la partida a todos los involucrados
            extras.Extras.mandaPartida(con);
            
        }else{
            try{
                con.getOut().writeUTF("ERROR");
                con.getOut().writeUTF("La partida con ID "+id+" se encuentra llena o ya empezo.");
                con.getOut().flush();
            }catch(IOException e){
                System.out.println("Error al mandar mensaje de error al usuario.");
            }
        }
    }
    
    
}
