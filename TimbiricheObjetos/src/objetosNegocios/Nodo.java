/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetosNegocios;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Home
 */
public class Nodo extends RectanguloJuego{

    public Nodo(Rectangle2D rectangulo) {
        super(rectangulo);
    }

    public Nodo(Rectangle2D rectangulo, int jugadorID) {
        super(rectangulo, jugadorID);
    }

    
    
}
