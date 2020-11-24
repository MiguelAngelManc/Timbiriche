/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosNegocios;

import java.awt.Color;
import java.io.Serializable;
import javax.swing.Icon;

/**
 *
 * @author lv1822
 */
public class Jugador implements Serializable{
    private String nombre;
    private Color color;
    private int puntos;
    private Icon imagen;
    private boolean vivo;
    
    public Jugador(String nombre, Color color){
        this.nombre = nombre;
        this.color = color;
        puntos = 0;
        vivo = true;
    }
    
    public Jugador(String nombre, Color color, Icon imagen){
        this.nombre = nombre;
        this.color = color;
        puntos = 0;
        this.imagen = imagen;
        vivo = true;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Icon getImagen() {
        return imagen;
    }

    public void setImagen(Icon imagen) {
        this.imagen = imagen;
    }
    
    public void darPunto(){
        puntos++;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
    
    
}
