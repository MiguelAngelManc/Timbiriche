/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosNegocios;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 *
 * @author labcisco
 */
public class RectanguloJuego implements Serializable{
    private Rectangle2D rectangulo;
    private int jugadorID;
    
    // Crea un cuadrado negro, para los puntos del tablero
    public RectanguloJuego(Rectangle2D rectangulo){
        this.rectangulo = rectangulo;
        jugadorID = 0;
    }
    
    // Crea un rectangulo con color, para las lineas del tablero
    public RectanguloJuego(Rectangle2D rectangulo, int jugadorID){
        this.rectangulo = rectangulo;
        this.jugadorID = jugadorID;
    }

    public Rectangle2D getRectangulo() {
        return rectangulo;
    }

    public void setRectangulo(Rectangle2D rectangulo) {
        this.rectangulo = rectangulo;
    }

    public int getJugadorID() {
        return jugadorID;
    }

    public void setJugadorID(int jugadorID) {
        this.jugadorID = jugadorID;
    }
    
}
