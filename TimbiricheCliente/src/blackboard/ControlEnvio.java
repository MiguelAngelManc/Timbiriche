/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackboard;

import java.io.IOException;

/**
 *
 * @author Home
 */
public class ControlEnvio {
    
    // Conexion al blackboard
    private Blackboard bb;
    
    public ControlEnvio() throws IOException{
        bb = Blackboard.getInstance(null);
    }
    
    // Metodos de comunicacion bb.getConexion() el server
    
    public void enviarTexto(String msg) throws IOException{
        bb.getConexion().getOut().writeUTF(msg);
        bb.getConexion().getOut().flush();
    }
    
    public void enviarUsuario() throws IOException{
        bb.getConexion().getOut().writeUTF("NUEVO USUARIO");
        bb.getConexion().getOut().writeObject(bb.getJugador());
        bb.getConexion().getOut().flush();
    }
    
    public void enviarChat(String msg,boolean global) throws IOException{
        bb.getConexion().getOut().writeUTF("MENSAJE DE CHAT");
        bb.getConexion().getOut().writeUTF(bb.getJugador().getNombre());
        bb.getConexion().getOut().writeBoolean(global);
        bb.getConexion().getOut().writeUTF(msg);
        bb.getConexion().getOut().flush();
        esperarError();
    }
    
    public void crearPartida(int jugadores) throws IOException{
        bb.getConexion().getOut().writeUTF("NUEVA PARTIDA");
        bb.getConexion().getOut().writeInt(jugadores);
        bb.getConexion().getOut().flush();
        esperarError();
    }
    
     public void unirPartida(int partidaID) throws IOException{
        bb.getConexion().getOut().writeUTF("UNIR A PARTIDA");
        bb.getConexion().getOut().writeInt(partidaID);
        bb.getConexion().getOut().flush();
        esperarError();
    }
     
    public void enviarMovimiento(int resultado) throws IOException{
        bb.getConexion().getOut().writeUTF("MOVIMIENTO");
        // Limpia el cache
        bb.getConexion().getOut().reset();
        bb.getConexion().getOut().writeObject(bb.getControlPartida().getPartida());
        bb.getConexion().getOut().writeInt(resultado);
        bb.getConexion().getOut().flush();
        esperarError();
    }
    
    public void enviarRendicion() throws IOException{
        bb.getConexion().getOut().writeUTF("RENDICION");
        // Limpia el cache
        bb.getConexion().getOut().reset();
        bb.getConexion().getOut().writeObject(bb.getControlPartida().getPartida());
        bb.getConexion().getOut().writeInt(bb.getJugadorInt());
        bb.getConexion().getOut().flush();
    }
    
    public void enviarFin() throws IOException{
        bb.getConexion().getOut().writeUTF("ACABO PARTIDA");
        // Limpia el cache
        bb.getConexion().getOut().reset();
        bb.getConexion().getOut().writeObject(bb.getControlPartida().getPartida());
        bb.getConexion().getOut().flush();
    }
    
    private void esperarError() throws IOException{
        synchronized(bb.getHilo()){
            try{
                bb.getHilo().wait();
                if(!bb.getHilo().getError().isEmpty()){
                    String error = bb.getHilo().getError();
                    bb.getHilo().setError("");
                    throw new IOException(error);
                }
            }catch(InterruptedException e){
                System.out.println("error interrumptido");
            }
        }
    }
    
}
