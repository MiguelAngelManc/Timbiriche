/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import objetosNegocios.Linea;
import objetosNegocios.Nodo;
import objetosNegocios.Partida;
import objetosNegocios.Punto;
import objetosNegocios.RectanguloJuego;

/**
 *
 * @author Home
 */
public class ControlPartida {
    private Partida partida;

    // Variable para determinar el tamanio de las figuras en el tablero
    int tamanio = 8;
    
    // Variable para saber si y donde se hizo un click
    Nodo inicioLinea = null;
    // Variable para saber la distancia entre cada nodo
    double distancia = 0;
    
    // Control de numero de jugadores y de turno
    int turno = 1;
    int jugadores = 0;
    
    // Crea la partida de un tamanio determinado y una lista de jugadores
    public void crearPartida(Partida partida){
        this.partida = partida;
        jugadores = partida.getJugadores().size();
    }
    
    
    // Crea las lineas iniciales del tablero segun el tamanio de este
    public void empezarPartida(int lado){
        // Crea tablero inicial segun el numero de jugadores
        // Crea una variable para saber la distancia entre los puntos a dibujar
        // En un tablero de 10x10, es el 92% dividido entre 9
        // En un tablero de 20x20, es el 92% dividido entre 19
        // En un tablero de 40x40, es el 92% dividido entre 39
        switch(jugadores){
            case 2:
                distancia = (lado*.92)/9;
                tamanio = 8;
                break;
            case 3:
                distancia = (lado*.92)/19;
                tamanio = 6;
                break;
            case 4:
                distancia = (lado*.92)/39;
                tamanio = 4;
                break;
        }
        // Crea los puntos del tablero y los guarda en el arrayList
        List<Nodo> lista = new ArrayList<>();
        // Empieza 3% del tamano total a la derecha y abajo, en la esquina superior izquierda y termina dejando un margen de 3%
        // Doble for, primero para vertical y luego para horizontal
        for(double i = (lado*.03);i<=(lado*.97);i+=distancia){
            for(double j = (lado*.03);j<=(lado*.97);j+=distancia){
                // Crea y guarda el punto
                lista.add(new Nodo(new Rectangle2D.Double(j, i, tamanio, tamanio)));
            }
        }
        // Manda la lista al tablero
        partida.getTablero().setNodos(lista);
        
    }
    
    public void cambiarColor(Color color, int jugadorID){
        partida.getJugadores().get(jugadorID).setColor(color);
    }
    
    /**
     * Realiza un movimiento en el juego, y llama a otros metodos dependiendo de los resultados
     * @param lugar Posicion en el tablero donde se hizo click
     * @param jugadorID El jugador que realizo el movimiento
     * @return Un codigo representando el resultado de la accion. 
     * <br> 0 = Linea repetida, nodo repetido, linea invalida, nodo invalido, etc. 
     * <br> 1 = Seleccionado un nodo.
     * <br> 2 = Movimiento realizado e hizo un punto, repite el turno.
     * <br> 3 = Partida terminada.
     * <br> 4 = Linea dibujada.
     */
    public int realizarMovimiento(Point lugar, int jugadorID){
        // Crea una variable para tener una linea y compararla con algunos requierimientos
        Rectangle2D linea;
        
        
        
        // No permite jugar a alguien que no es su turno
        if(partida.getTurno()!=jugadorID)
            return 0;
        
        // Revisa si no es el primer click
        if(inicioLinea!=null){
            // Itera sobre la lista de nodos
            for (Nodo nodo : partida.getTablero().getNodos()){
                // No permite que se repita el nodo dos veces
                if(nodo.getRectangulo().contains(lugar)){
                    // Si selecciona dos nodos distintos, trata de dibujar una linea
                    if(nodo!=inicioLinea){
                        //Crea la linea entre los dos nodos
                        linea = inicioLinea.getRectangulo().createUnion(nodo.getRectangulo());
                        // No permite lineas en diagonal
                        // Esto lo podemos checar si tanto su ancho como su alto son mayores al tamanio de los puntos y lineas
                        if(linea.getWidth()>tamanio&&linea.getHeight()>tamanio)
                            return 0;
                        // No permite lineas que midan mas de un nodo
                        if(linea.getWidth()==tamanio && linea.getHeight()>(distancia*1.8))
                            return 0;
                        if(linea.getWidth()>(distancia*1.8) && linea.getHeight()==tamanio)
                            return 0;
                        
                        // Itera sobre la lista de lineas para ver si esta repetida
                        for(Linea line : partida.getTablero().getLineas()){
                            if(line.getRectangulo().contains(linea))
                                return 0;
                        }
                        
                        // Dibuja la linea
                        return dibujarLinea(linea, jugadorID);
                    }else{
                        inicioLinea = null;
                        nodo.setJugadorID(0);
                        return 1;
                    }
                }
            }
        }else{
            // Itera sobre la lista de nodos
            for (Nodo nodo : partida.getTablero().getNodos()){
                if(nodo.getRectangulo().contains(lugar)){
                    inicioLinea = nodo;
                    // Cambia el color del punto que se dio click
                    nodo.setJugadorID(jugadorID);
                    return 1;
                }
            }
        }
        return 0;
    }

    // Trata de dibujar una linea, se tiene que dar click sobre dos nodos diferentes
    private int dibujarLinea(Rectangle2D linea, int jugadorID){
        // Boleano que indica si hizo punto o nada mas una linea
        boolean hizoPunto = false;
        
        // Obtiene la lista de lineas en el tablero
        List<Linea> lineas = partida.getTablero().getLineas();
        // Guarda en la lista la linea creada
        lineas.add(new Linea(linea, jugadorID));
        // Restablece el color del punto que se dio click
        inicioLinea.setJugadorID(0);
        
        // Vacia el primer click
        inicioLinea = null;
        
        // Crea un punto que se encuentra en el medio de la linea creada, para comprobar si se completa el cuadro
        Point centro = new Point((int)linea.getCenterX(), (int)linea.getCenterY());
        // Crea marcadores para saber si estan completos ambos lados, ya que una linea puede completar dos cuadros al mismo tiempo
        int lado1 = 0;
        int lado2 = 0;
        // Itera sobre las lineas
        for(Linea line : partida.getTablero().getLineas()){
            // Si la linea es horizontal
            if(linea.getWidth()>tamanio){
                // Crea un punto que se mueve para checar los lados
                // Directamente arriba
                Point nuevo = new Point(centro.x,(int)(centro.y-distancia));
                if(line.getRectangulo().contains(nuevo))
                    lado1++;
                // arriba y a izquierda
                nuevo = new Point((int)(centro.x-(distancia/2)),(int)(centro.y-(distancia/2)));
                if(line.getRectangulo().contains(nuevo))
                    lado1++;
                // arriba y a derecha
                nuevo = new Point((int)(centro.x+(distancia/2)),(int)(centro.y-(distancia/2)));
                if(line.getRectangulo().contains(nuevo))
                    lado1++;
                
                // Directamente abajo
                nuevo = new Point(centro.x,(int)(centro.y+distancia));
                if(line.getRectangulo().contains(nuevo))
                    lado2++;
                // abajo y a izquierda
                nuevo = new Point((int)(centro.x-(distancia/2)),(int)(centro.y+(distancia/2)));
                if(line.getRectangulo().contains(nuevo))
                    lado2++;
                // abajo y a derecha
                nuevo = new Point((int)(centro.x+(distancia/2)),(int)(centro.y+(distancia/2)));
                if(line.getRectangulo().contains(nuevo))
                    lado2++;
            }else{
                // Crea un punto que se mueve para checar los lados
                // Directamente izquierda
                Point nuevo = new Point((int)(centro.x-distancia),centro.y);
                if(line.getRectangulo().contains(nuevo))
                    lado1++;
                // izquierda y a arriba
                nuevo = new Point((int)(centro.x-(distancia/2)),(int)(centro.y-(distancia/2)));
                if(line.getRectangulo().contains(nuevo))
                    lado1++;
                // izquierda y a abajo
                nuevo = new Point((int)(centro.x-(distancia/2)),(int)(centro.y+(distancia/2)));
                if(line.getRectangulo().contains(nuevo))
                    lado1++;
                
                // Directamente derecha
                nuevo = new Point((int)(centro.x+distancia),centro.y);
                if(line.getRectangulo().contains(nuevo))
                    lado2++;
                // derecha y a arriba
                nuevo = new Point((int)(centro.x+(distancia/2)),(int)(centro.y-(distancia/2)));
                if(line.getRectangulo().contains(nuevo))
                    lado2++;
                // derecha y a abajo
                nuevo = new Point((int)(centro.x+(distancia/2)),(int)(centro.y+(distancia/2)));
                if(line.getRectangulo().contains(nuevo))
                    lado2++;
            }
        }
        
        // Variable para determinar si el juego acabo o no
        boolean acabo = false;
        
        if(lado1 == 3){
            acabo = hacerPunto(linea, jugadorID, true);
            hizoPunto = true;
        }
        if(lado2 == 3){
            acabo = hacerPunto(linea, jugadorID, false);
            hizoPunto = true;
        }
        
        if(acabo)
            return 3;
        if(hizoPunto)
            return 2;
        
        return 4;
    }
    
    private boolean hacerPunto(Rectangle2D linea, int jugadorID, boolean arrIzq){
        // Obtiene la lista de puntos
        List<Punto> puntos = partida.getTablero().getPuntos();
        // Crea un punto
        Punto punto = new Punto(null);
        punto.setJugadorID(jugadorID);
        Rectangle2D figura = null;
        // Si la linea es horizontal
        if(linea.getWidth()>tamanio){
            // Dibuja el punto para arriba
            if(arrIzq){
                figura = new Rectangle2D.Double(linea.getMinX(), linea.getCenterY()-distancia, distancia, distancia);
            }else{
                figura = new Rectangle2D.Double(linea.getMinX(), linea.getCenterY(), distancia, distancia);
            }
        }else{
            // Dibuja el punto para izquierda
            if(arrIzq){
                figura = new Rectangle2D.Double(linea.getCenterX()-distancia, linea.getMinY(), distancia, distancia);
            }else{
                figura = new Rectangle2D.Double(linea.getCenterX(), linea.getMinY(), distancia, distancia);
            }
        }
        
        punto.setRectangulo(figura);
        puntos.add(punto);
        // Le da un punto al jugador
        partida.getJugadores().get(jugadorID-1).darPunto();
        
        // Revisa si acabo el juego
        switch(jugadores){
            case 2: if(puntos.size()==81)
                        return true;break;
            case 3: if(puntos.size()==361)
                        return true;break;
            case 4: if(puntos.size()==1521)
                        return true;break;
        }
        return false;
    }
    
    
    public void abandonarPartida(int jugadorID){
        // Remueve de la lista de jugadores 
        partida.getJugadores().get(jugadorID-1).setVivo(false);
        // Crea listas temporales para recoger las lineas y puntos
        List<Punto> puntos = new ArrayList();
        List<Linea> lineas = new ArrayList();
        // Itera la lista
        for(RectanguloJuego punto : partida.getTablero().getTodos()){
            if(punto.getJugadorID()==(jugadorID)){
                if(punto.getClass()==Nodo.class){
                    punto.setJugadorID(0);
                    inicioLinea = null;
                }else if(punto.getClass()==Punto.class)
                    puntos.add((Punto)punto);
                else
                    lineas.add((Linea)punto);
            }
        }
        // Remueve de las listas
        partida.getTablero().getPuntos().removeAll(puntos);
        partida.getTablero().getLineas().removeAll(lineas);
    }
    
    public void pintarTablero(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        // Obtiene la lista de colores y nombres
        List<Color> colores = partida.getColores();
        List<String> nombres = partida.getNombres();
        
        // Dibuja todas las figuras
        for(RectanguloJuego figura: partida.getTablero().getTodos()){
            g2.setColor(colores.get(figura.getJugadorID()));
            g2.draw(figura.getRectangulo());
            g2.fill(figura.getRectangulo());
            if(figura.getClass() == Punto.class){
                g2.setColor(Color.white);
                g2.drawString(nombres.get(figura.getJugadorID()), (float)figura.getRectangulo().getCenterX(), (float)figura.getRectangulo().getCenterY());
            }
        }
    }
    
    public Partida getPartida(){
        return partida;
    }
    
    public void setPartida(Partida partida) {
        this.partida = partida;
    }
}
