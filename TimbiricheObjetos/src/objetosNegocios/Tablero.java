/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosNegocios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lv1822
 */
public class Tablero implements Serializable {
    // Coleccion de figuras
    List<Nodo> nodos;
    List<Linea> lineas;
    List<Punto> puntos;
    
    public Tablero(){
        nodos = new ArrayList<>();
        lineas = new ArrayList<>();
        puntos = new ArrayList<>();
    }
    
    // Regresa una lista con todas las listas combinadas
    public List<RectanguloJuego> getTodos(){
        List<RectanguloJuego> lista = new ArrayList<>();
        lista.addAll(puntos);
        lista.addAll(lineas);
        lista.addAll(nodos);
        
        return lista;
    }

    public List<Nodo> getNodos() {
        return nodos;
    }

    public void setNodos(List<Nodo> nodos) {
        this.nodos = nodos;
    }

    public List<Linea> getLineas() {
        return lineas;
    }

    public void setLineas(List<Linea> lineas) {
        this.lineas = lineas;
    }

    public List<Punto> getPuntos() {
        return puntos;
    }

    public void setPuntos(List<Punto> puntos) {
        this.puntos = puntos;
    }
    
    
}
