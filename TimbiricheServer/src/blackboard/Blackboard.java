/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackboard;

import hilos.HiloServer;
import java.util.ArrayList;
import objetosNegocios.ConexionServer;
import objetosNegocios.Jugador;
import objetosNegocios.Partida;

/**
 *
 * @author labcisco
 */
public class Blackboard {

    // Singleton
    private static Blackboard instance;

    // BLACKBOARD
    // Coleccion de personas conectadas al servidor
    private ArrayList<ConexionServer> conexiones;
    // Coleccion de objetos jugadores de cada usuario
    private ArrayList<Jugador> jugadores;
    // Coleccion de todas las partidas en el servidor
    private ArrayList<Partida> partidas;

    // Conexion con el control
    Control control;
    // Hilo de servidor
    HiloServer hilo;

    private Blackboard() {
        conexiones = new ArrayList<>();
        jugadores = new ArrayList<>();
        partidas = new ArrayList<>();
    }
    
    public static Blackboard getInstance(){
        if(instance == null)
            instance = new Blackboard();
        return instance;
    }

    public void empezar() {
        control = new Control();
        hilo = new HiloServer(conexiones, control);
        hilo.start();
    }

    public ArrayList<ConexionServer> getConexiones() {
        return conexiones;
    }

    public void setConexiones(ArrayList<ConexionServer> conexiones) {
        this.conexiones = conexiones;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public ArrayList<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(ArrayList<Partida> partidas) {
        this.partidas = partidas;
    }

}
