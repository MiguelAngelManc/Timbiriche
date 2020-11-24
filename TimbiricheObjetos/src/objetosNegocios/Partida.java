/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosNegocios;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lv1822
 */
public class Partida implements Serializable {
    private int id;
    private int jugadoresMax;
    private boolean empezada;
    private List<Jugador> jugadores;
    private Tablero tablero;
    private List<Integer> listos;
    private int turno;
    
    public Partida(int i, int jugadoresMax){
        jugadores = new ArrayList();
        tablero = new Tablero();
        id = i;
        empezada = false;
        listos = new ArrayList();
        this.jugadoresMax = jugadoresMax;
        turno = 1;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    
    // Regresa una lista con los colores de los jugadores, para pintar el tablero
    public List<Color> getColores(){
        List<Color> lista = new ArrayList<>();
        // El primer color, 0, es negro
        lista.add(Color.black);
        // Los demas son los colores de los jugadores
        for(Jugador jugador: jugadores){
            lista.add(jugador.getColor());
        }
        return lista;
    }
    
    // Regresa una lista con todas las iniciales de los jugadores
    public List<String> getNombres(){
        List<String> lista = new ArrayList<>();
        lista.add("0");
        for(Jugador jugador : jugadores){
            lista.add(jugador.getNombre().substring(0, 1));
        }
        return lista;
    }
    
    public int[] getPuntaciones(){
        int[] lista = new int[jugadores.size()];
        for(int i = 0;i<jugadores.size();i++){
            lista[i] = jugadores.get(i).getPuntos();
        }
        return lista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getJugadoresMax() {
        return jugadoresMax;
    }

    public void setJugadoresMax(int jugadoresMax) {
        this.jugadoresMax = jugadoresMax;
    }

    public boolean isEmpezada() {
        return empezada;
    }

    public void setEmpezada(boolean empezada) {
        this.empezada = empezada;
    }
    
    public void agregarJugador(Jugador jugador){
        jugadores.add(jugador);
    }

    public List<Integer> getListos() {
        return listos;
    }

    public void setListos(List<Integer> listos) {
        this.listos = listos;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
    
}
