/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackboard;

import control.ControlPartida;
import hilos.HiloComunicacion;
import interfaces.Juego;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import objetosNegocios.Conexion;
import objetosNegocios.Jugador;

/**
 * Clase que maneja la comunicacion con el servidor de manera dinamica.
 * @author labcisco
 */
public class Blackboard {
    
    // Singleton
    private static Blackboard instance;
    
    // Elementos de comunicacion con otros elementos de blackboard
    private HiloComunicacion hilo;
    private ControlEnvio envia;
    private ControlRecibe recibe;
    
    // Datos
    private Conexion con;
    private Jugador jugador;
    private ControlPartida controlPartida;
    private int jugadorId;
    
    // Obtiene elementos de las interfaces para manejarlas desde aqui
    // Lobby
    private JTable tablaPartidas;
    private JTextArea chatGlobalLobby;
    // Juego
    private Juego juego;
    
    private JTextArea chatGlobalJuego;
    private JTextArea chatLocal;
    private JTabbedPane panelChat;
    private int ladoTablero;
    private JPanel pnlJuego;
   
    // Juego - datos de jugadores
    private JLabel[] puntos;
    private JPanel[] panelesJugadores;
    private JLabel[] imagenes;
    private JLabel[] colores;
    private JLabel[] otros;
    
    private Blackboard(String host) throws IOException{
        this.con = new Conexion(host);
        controlPartida = new ControlPartida();
        jugadorId = 0;
    }
    
    public static Blackboard getInstance(String host) throws IOException{
        if(instance == null)
            instance = new Blackboard(host);
        return instance;
    }
    
    public void empezar() throws IOException{
        recibe = new ControlRecibe();
        envia = new ControlEnvio();
        hilo = new HiloComunicacion(this);
        hilo.start();
    }
    
    public Conexion getConexion(){
        return con;
    }

    public HiloComunicacion getHilo() {
        return hilo;
    }

    public ControlEnvio getEnvia() {
        return envia;
    }

    public void setEnvia(ControlEnvio envia) {
        this.envia = envia;
    }

    public ControlRecibe getRecibe() {
        return recibe;
    }

    public void setRecibe(ControlRecibe recibe) {
        this.recibe = recibe;
    }
    
    public void setJugador(Jugador jugador){
        this.jugador = jugador;
    }

    public JTable getTablaPartidas() {
        return tablaPartidas;
    }

    public JTextArea getChatGlobalLobby() {
        return chatGlobalLobby;
    }

    public JTextArea getChatGlobalJuego() {
        return chatGlobalJuego;
    }

    public JTextArea getChatLocal() {
        return chatLocal;
    }

    public JTabbedPane getPanelChat() {
        return panelChat;
    }

    public ControlPartida getControlPartida() {
        return controlPartida;
    }

    public void setControlPartida(ControlPartida controlPartida) {
        this.controlPartida = controlPartida;
    }

    public JLabel[] getPuntos() {
        return puntos;
    }

    public void setPuntos(JLabel[] puntos) {
        this.puntos = puntos;
    }

    public JPanel[] getPanelesJugadores() {
        return panelesJugadores;
    }

    public void setPanelesJugadores(JPanel[] panelesJugadores) {
        this.panelesJugadores = panelesJugadores;
    }

    public JLabel[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(JLabel[] imagenes) {
        this.imagenes = imagenes;
    }

    public JLabel[] getColores() {
        return colores;
    }

    public void setColores(JLabel[] colores) {
        this.colores = colores;
    }

    public JLabel[] getOtros() {
        return otros;
    }

    public void setOtros(JLabel[] otros) {
        this.otros = otros;
    }
    
    public int getLadoTablero(){
        return ladoTablero;
    }

    public int getJugadorInt() {
        return jugadorId;
    }

    public void setJugadorInt(int jugadorInt) {
        this.jugadorId = jugadorInt;
    }

    public Juego getJuego() {
        return juego;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public JPanel getPnlJuego() {
        return pnlJuego;
    }

    public void setPnlJuego(JPanel pnlJuego) {
        this.pnlJuego = pnlJuego;
    }
    
    // Establece elementos de interfaz
    
    public void ventanaLobby(JTable tablaPartidas, JTextArea chatGlobalLobby){
        this.tablaPartidas = tablaPartidas;
        this.chatGlobalLobby = chatGlobalLobby;
    }
    
    public void ventanaJuego(Juego juego){
        this.juego = juego;
        this.chatGlobalJuego = juego.getTxtHistorialChatGlobal();
        this.chatLocal = juego.getTxtHistorialChatLocal();
        this.panelChat = juego.getPanelChat();
        this.ladoTablero = juego.getPnlFondo().getWidth();
        
        this.panelesJugadores = juego.getPanelesJugadores();
        this.puntos = juego.getPuntos();
        this.imagenes = juego.getImagenes();
        this.colores = juego.getColores();
        this.otros = juego.getOtros();
        
        // Retiene los datos del chat global
        chatGlobalJuego.setText(chatGlobalLobby.getText());
    }
    
    
    
    
    
}
