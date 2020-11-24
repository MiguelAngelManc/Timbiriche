/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import blackboard.Blackboard;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import objetosNegocios.Jugador;

/**
 *
 * @author labcisco
 */
public class Juego extends javax.swing.JFrame {
    
    // Blackboard
    Blackboard com;

    // Agrupa los puntajes para actualizarlos con un ciclo
    JLabel[] puntos;
    // Agrupa los paneles de los jugadores
    JPanel[] panelesJugadores;
    // Agrupa las imagenes de los jugadores
    JLabel[] imagenes;
    // Agrupa los colores de los jugadores
    JLabel[] colores;
    // Agrupa los cuadros adicionales de jugadores (listo, turno, rendido)
    JLabel[] otros;
    
    // Lista para saber quienes de los jugadores estan listos
    List<Integer> listos = new ArrayList<>();

    // Variable del tablero del juego
    JPanel pnlJuego;
    // Variable para saber si el jugador es el creador de la partida
    boolean hosteando;
    // Variable para saber si el juego empezo o no
    boolean empezada = false;
    // Variable para saber si la partida esta creada o no
    boolean creada = false;
    
    // Variable con el numero de jugadores para mejor control
    int jugadores = 0;
    
    /**
     * Creates new form Juego
     */
    public Juego(boolean hosteando, SeleccionModo padre) {
        initComponents();
        this.hosteando = hosteando;
        
        Font font = new Font("Monospaced", Font.PLAIN, 11);
        txtHistorialChatGlobal.setFont(font);
        txtHistorialChatLocal.setFont(font);
        
        // Agrupa los paneles de jugadores 
        panelesJugadores = new JPanel[]{pnlPlayer1, pnlPlayer2, pnlPlayer3, pnlPlayer4};
        // Agrupa los puntajes
        puntos = new JLabel[]{ptsPlayer1, ptsPlayer2, ptsPlayer3, ptsPlayer4};
        // Agrupa las imagenes de los jugadores
        imagenes = new JLabel[]{imgPlayer1, imgPlayer2, imgPlayer3, imgPlayer4};
        // Agrupa los colores de los jugadores
        colores = new JLabel[]{clrPlayer1, clrPlayer2, clrPlayer3, clrPlayer4};
        // Agrupa los cuadros adicionales de jugadores (listo, turno, rendido)
        otros = new JLabel[]{otroPlayer1,otroPlayer2,otroPlayer3,otroPlayer4};

        // Oculta todos los paneles 
        for (int i = 0; i < 4; i++) {
            panelesJugadores[i].setVisible(false);
        }
        
        // Manda los elementos de interfaz
        try{
            com = Blackboard.getInstance(null);
            com.ventanaJuego(this);
        }catch(IOException e){
            JOptionPane.showMessageDialog(this, "Error al conectar al servidor. "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        
        final SeleccionModo padreF = padre;

        // Diferencias entre host y cliente
        if(!hosteando){
            crearPartida();
        }
        
        
        // Agrega un listener para cerrar conexiones cuando se cierra la ventana
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if(empezada){
                    int resultado = JOptionPane.showConfirmDialog(rootPane, "Seguro que quieres abandonar la partida? Perderas todos tus puntos y lineas.", "Abandonar partida", JOptionPane.WARNING_MESSAGE);
                    if (resultado == JOptionPane.YES_OPTION) {
                        // Rendirse
                        try{
                            com.getControlPartida().abandonarPartida(com.getJugadorInt());
                            com.getEnvia().enviarRendicion();
                        }catch(IOException e){
                            JOptionPane.showMessageDialog(padreF, "Error al rendirese de la partida. Mensaje original: "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    
                }
                // Desconecta al jugador de la partida.
                if(creada){
                    try{
                        com.getEnvia().enviarTexto("DESLISTO");
                        com.getEnvia().enviarTexto("ABANDONAR PARTIDA");
                    }catch(IOException e){
                        JOptionPane.showMessageDialog(padreF, "Error al salir de partida. Mensaje original: "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
                // Cierra la ventana y abre el lobby
                            dispose();
                            padreF.setVisible(true);
                            padreF.obtenerPartidas();
            }
        });
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPlayer1 = new javax.swing.JPanel();
        imgPlayer1 = new javax.swing.JLabel();
        clrPlayer1 = new javax.swing.JLabel();
        ptsPlayer1 = new javax.swing.JLabel();
        otroPlayer1 = new javax.swing.JLabel();
        pnlPlayer2 = new javax.swing.JPanel();
        clrPlayer2 = new javax.swing.JLabel();
        imgPlayer2 = new javax.swing.JLabel();
        ptsPlayer2 = new javax.swing.JLabel();
        otroPlayer2 = new javax.swing.JLabel();
        pnlPlayer4 = new javax.swing.JPanel();
        clrPlayer4 = new javax.swing.JLabel();
        imgPlayer4 = new javax.swing.JLabel();
        ptsPlayer4 = new javax.swing.JLabel();
        otroPlayer4 = new javax.swing.JLabel();
        pnlFondo = new javax.swing.JPanel();
        pnlCuantos = new javax.swing.JPanel();
        lblCuantos = new javax.swing.JLabel();
        btnCuantos2 = new javax.swing.JButton();
        btnCuantos3 = new javax.swing.JButton();
        btnCuantos4 = new javax.swing.JButton();
        btnListo = new javax.swing.JToggleButton();
        pnlPlayer3 = new javax.swing.JPanel();
        imgPlayer3 = new javax.swing.JLabel();
        ptsPlayer3 = new javax.swing.JLabel();
        clrPlayer3 = new javax.swing.JLabel();
        otroPlayer3 = new javax.swing.JLabel();
        panelChat = new javax.swing.JTabbedPane();
        pnlChatLocal = new javax.swing.JPanel();
        txtChatLocal = new javax.swing.JTextField();
        scrollChatLocal = new javax.swing.JScrollPane();
        txtHistorialChatLocal = new javax.swing.JTextArea();
        pnlChatGlobal = new javax.swing.JPanel();
        txtChatGlobal = new javax.swing.JTextField();
        scrollChatGlobal = new javax.swing.JScrollPane();
        txtHistorialChatGlobal = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Timbiriche");
        setResizable(false);

        pnlPlayer1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlPlayer1.setPreferredSize(new java.awt.Dimension(250, 110));

        imgPlayer1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/1.png"))); // NOI18N
        imgPlayer1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        imgPlayer1.setPreferredSize(new java.awt.Dimension(50, 50));

        clrPlayer1.setBackground(new java.awt.Color(255, 51, 51));
        clrPlayer1.setToolTipText("");
        clrPlayer1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        clrPlayer1.setOpaque(true);
        clrPlayer1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clrPlayer1MouseClicked(evt);
            }
        });

        ptsPlayer1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        ptsPlayer1.setForeground(new java.awt.Color(255, 51, 51));
        ptsPlayer1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ptsPlayer1.setText("0");
        ptsPlayer1.setToolTipText("");
        ptsPlayer1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        otroPlayer1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        otroPlayer1.setForeground(new java.awt.Color(255, 51, 51));
        otroPlayer1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        otroPlayer1.setToolTipText("");
        otroPlayer1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout pnlPlayer1Layout = new javax.swing.GroupLayout(pnlPlayer1);
        pnlPlayer1.setLayout(pnlPlayer1Layout);
        pnlPlayer1Layout.setHorizontalGroup(
            pnlPlayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPlayer1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(clrPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ptsPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(otroPlayer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlPlayer1Layout.setVerticalGroup(
            pnlPlayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPlayer1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPlayer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(imgPlayer1, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(clrPlayer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ptsPlayer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(otroPlayer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlPlayer2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlPlayer2.setPreferredSize(new java.awt.Dimension(250, 110));

        clrPlayer2.setBackground(java.awt.Color.blue);
        clrPlayer2.setToolTipText("");
        clrPlayer2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        clrPlayer2.setOpaque(true);
        clrPlayer2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clrPlayer2MouseClicked(evt);
            }
        });

        imgPlayer2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/2.png"))); // NOI18N
        imgPlayer2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        imgPlayer2.setPreferredSize(new java.awt.Dimension(50, 50));

        ptsPlayer2.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        ptsPlayer2.setForeground(java.awt.Color.blue);
        ptsPlayer2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ptsPlayer2.setText("0");
        ptsPlayer2.setToolTipText("");
        ptsPlayer2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        otroPlayer2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        otroPlayer2.setForeground(new java.awt.Color(255, 51, 51));
        otroPlayer2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        otroPlayer2.setToolTipText("");
        otroPlayer2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout pnlPlayer2Layout = new javax.swing.GroupLayout(pnlPlayer2);
        pnlPlayer2.setLayout(pnlPlayer2Layout);
        pnlPlayer2Layout.setHorizontalGroup(
            pnlPlayer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPlayer2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(clrPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ptsPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(otroPlayer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlPlayer2Layout.setVerticalGroup(
            pnlPlayer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPlayer2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPlayer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(otroPlayer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlPlayer2Layout.createSequentialGroup()
                        .addGroup(pnlPlayer2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(imgPlayer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clrPlayer2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ptsPlayer2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnlPlayer4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlPlayer4.setPreferredSize(new java.awt.Dimension(250, 110));

        clrPlayer4.setBackground(java.awt.Color.green);
        clrPlayer4.setToolTipText("");
        clrPlayer4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        clrPlayer4.setOpaque(true);
        clrPlayer4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clrPlayer4MouseClicked(evt);
            }
        });

        imgPlayer4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/4.png"))); // NOI18N
        imgPlayer4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        imgPlayer4.setPreferredSize(new java.awt.Dimension(50, 50));

        ptsPlayer4.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        ptsPlayer4.setForeground(java.awt.Color.green);
        ptsPlayer4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ptsPlayer4.setText("0");
        ptsPlayer4.setToolTipText("");
        ptsPlayer4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        otroPlayer4.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        otroPlayer4.setForeground(new java.awt.Color(255, 51, 51));
        otroPlayer4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        otroPlayer4.setToolTipText("");
        otroPlayer4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout pnlPlayer4Layout = new javax.swing.GroupLayout(pnlPlayer4);
        pnlPlayer4.setLayout(pnlPlayer4Layout);
        pnlPlayer4Layout.setHorizontalGroup(
            pnlPlayer4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPlayer4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgPlayer4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(clrPlayer4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ptsPlayer4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(otroPlayer4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlPlayer4Layout.setVerticalGroup(
            pnlPlayer4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPlayer4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlPlayer4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(imgPlayer4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clrPlayer4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ptsPlayer4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(otroPlayer4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlFondo.setBackground(new java.awt.Color(255, 255, 255));
        pnlFondo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        pnlFondo.setPreferredSize(new java.awt.Dimension(539, 539));

        pnlCuantos.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblCuantos.setText("Número de jugadores:");

        btnCuantos2.setText("2");
        btnCuantos2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuantos2ActionPerformed(evt);
            }
        });

        btnCuantos3.setText("3");
        btnCuantos3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuantos3ActionPerformed(evt);
            }
        });

        btnCuantos4.setText("4");
        btnCuantos4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuantos4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCuantosLayout = new javax.swing.GroupLayout(pnlCuantos);
        pnlCuantos.setLayout(pnlCuantosLayout);
        pnlCuantosLayout.setHorizontalGroup(
            pnlCuantosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCuantosLayout.createSequentialGroup()
                .addGroup(pnlCuantosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCuantosLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnCuantos2)
                        .addGap(18, 18, 18)
                        .addComponent(btnCuantos3)
                        .addGap(18, 18, 18)
                        .addComponent(btnCuantos4))
                    .addGroup(pnlCuantosLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(lblCuantos)))
                .addGap(29, 29, 29))
        );
        pnlCuantosLayout.setVerticalGroup(
            pnlCuantosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCuantosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCuantos)
                .addGap(18, 18, 18)
                .addGroup(pnlCuantosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCuantos2)
                    .addComponent(btnCuantos3)
                    .addComponent(btnCuantos4))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        btnListo.setText("Estoy listo!");
        btnListo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFondoLayout = new javax.swing.GroupLayout(pnlFondo);
        pnlFondo.setLayout(pnlFondoLayout);
        pnlFondoLayout.setHorizontalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFondoLayout.createSequentialGroup()
                        .addGap(214, 214, 214)
                        .addComponent(pnlCuantos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFondoLayout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(btnListo)))
                .addContainerGap(230, Short.MAX_VALUE))
        );
        pnlFondoLayout.setVerticalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addGap(267, 267, 267)
                .addComponent(pnlCuantos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(btnListo)
                .addContainerGap(195, Short.MAX_VALUE))
        );

        pnlPlayer3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlPlayer3.setPreferredSize(new java.awt.Dimension(250, 110));

        imgPlayer3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/3.png"))); // NOI18N
        imgPlayer3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        imgPlayer3.setPreferredSize(new java.awt.Dimension(50, 50));

        ptsPlayer3.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        ptsPlayer3.setForeground(java.awt.Color.yellow);
        ptsPlayer3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ptsPlayer3.setText("0");
        ptsPlayer3.setToolTipText("");
        ptsPlayer3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        clrPlayer3.setBackground(java.awt.Color.yellow);
        clrPlayer3.setToolTipText("");
        clrPlayer3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        clrPlayer3.setOpaque(true);
        clrPlayer3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clrPlayer3MouseClicked(evt);
            }
        });

        otroPlayer3.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        otroPlayer3.setForeground(new java.awt.Color(255, 51, 51));
        otroPlayer3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        otroPlayer3.setToolTipText("");
        otroPlayer3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout pnlPlayer3Layout = new javax.swing.GroupLayout(pnlPlayer3);
        pnlPlayer3.setLayout(pnlPlayer3Layout);
        pnlPlayer3Layout.setHorizontalGroup(
            pnlPlayer3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPlayer3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgPlayer3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(clrPlayer3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ptsPlayer3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(otroPlayer3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlPlayer3Layout.setVerticalGroup(
            pnlPlayer3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPlayer3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPlayer3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(otroPlayer3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPlayer3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(imgPlayer3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clrPlayer3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ptsPlayer3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelChat.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                panelChatStateChanged(evt);
            }
        });

        pnlChatLocal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtChatLocal.setToolTipText("Ingresa un mensaje para mandar a los jugadores del mismo cuarto.");
        txtChatLocal.setEnabled(false);
        txtChatLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChatLocalActionPerformed(evt);
            }
        });

        txtHistorialChatLocal.setEditable(false);
        txtHistorialChatLocal.setColumns(20);
        txtHistorialChatLocal.setRows(5);
        scrollChatLocal.setViewportView(txtHistorialChatLocal);

        javax.swing.GroupLayout pnlChatLocalLayout = new javax.swing.GroupLayout(pnlChatLocal);
        pnlChatLocal.setLayout(pnlChatLocalLayout);
        pnlChatLocalLayout.setHorizontalGroup(
            pnlChatLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtChatLocal, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
            .addComponent(scrollChatLocal)
        );
        pnlChatLocalLayout.setVerticalGroup(
            pnlChatLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChatLocalLayout.createSequentialGroup()
                .addComponent(scrollChatLocal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtChatLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelChat.addTab("Local", pnlChatLocal);

        pnlChatGlobal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtChatGlobal.setToolTipText("Ingresa un mensaje para enviar a todos los usuarios conectados.");
        txtChatGlobal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChatGlobalActionPerformed(evt);
            }
        });

        txtHistorialChatGlobal.setEditable(false);
        txtHistorialChatGlobal.setColumns(20);
        txtHistorialChatGlobal.setRows(5);
        scrollChatGlobal.setViewportView(txtHistorialChatGlobal);

        javax.swing.GroupLayout pnlChatGlobalLayout = new javax.swing.GroupLayout(pnlChatGlobal);
        pnlChatGlobal.setLayout(pnlChatGlobalLayout);
        pnlChatGlobalLayout.setHorizontalGroup(
            pnlChatGlobalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtChatGlobal)
            .addGroup(pnlChatGlobalLayout.createSequentialGroup()
                .addComponent(scrollChatGlobal, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlChatGlobalLayout.setVerticalGroup(
            pnlChatGlobalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChatGlobalLayout.createSequentialGroup()
                .addComponent(scrollChatGlobal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtChatGlobal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelChat.addTab("Global", pnlChatGlobal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlPlayer1, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                    .addComponent(panelChat)
                    .addComponent(pnlPlayer3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                    .addComponent(pnlPlayer4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                    .addComponent(pnlPlayer2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlPlayer3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlPlayer4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelChat))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Inicio de metodos para cambiar color
    private void clrPlayer1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clrPlayer1MouseClicked
        if(empezada){
            Color nuevo = JColorChooser.showDialog(this, "Seleccionar color", Color.BLACK);
            if (nuevo != null) {
                if (empezada) {
                    com.getControlPartida().cambiarColor(nuevo, 0);
                }
                actualizaColor(nuevo, 0);
            }
        }
    }//GEN-LAST:event_clrPlayer1MouseClicked

    private void clrPlayer2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clrPlayer2MouseClicked
        if(empezada){
            Color nuevo = JColorChooser.showDialog(this, "Seleccionar color", Color.BLACK);
            if (nuevo != null) {
                if (empezada) {
                    com.getControlPartida().cambiarColor(nuevo, 1);
                }
                actualizaColor(nuevo, 1);
            }
        }
    }//GEN-LAST:event_clrPlayer2MouseClicked

    private void clrPlayer3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clrPlayer3MouseClicked
        if(empezada){
            Color nuevo = JColorChooser.showDialog(this, "Seleccionar color", Color.BLACK);
            if (nuevo != null) {
                if (empezada) {
                    com.getControlPartida().cambiarColor(nuevo, 2);
                }
                actualizaColor(nuevo, 2);
            }
        }
    }//GEN-LAST:event_clrPlayer3MouseClicked

    private void clrPlayer4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clrPlayer4MouseClicked
        if(empezada){
            Color nuevo = JColorChooser.showDialog(this, "Seleccionar color", Color.BLACK);
            if (nuevo != null) {
                if (empezada) {
                    com.getControlPartida().cambiarColor(nuevo, 3);
                }
                actualizaColor(nuevo, 3);
            }
        }
    }//GEN-LAST:event_clrPlayer4MouseClicked

    // Inicio de metodos para seleccionar cantidad de jugadores
    private void btnCuantos2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuantos2ActionPerformed
        jugadores = 2;
        crearPartida();
    }//GEN-LAST:event_btnCuantos2ActionPerformed

    private void btnCuantos3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuantos3ActionPerformed
        jugadores = 3;
        crearPartida();
    }//GEN-LAST:event_btnCuantos3ActionPerformed

    private void btnCuantos4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuantos4ActionPerformed
        jugadores = 4;
        crearPartida();
    }//GEN-LAST:event_btnCuantos4ActionPerformed

    private void txtChatLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChatLocalActionPerformed
       try{
           if(!txtChatLocal.getText().equals("")){
               com.getEnvia().enviarChat(txtChatLocal.getText(),false);
           }
       }catch(IOException e){
           JOptionPane.showMessageDialog(this, "Error al enviar mensaje."+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
       }
       txtChatLocal.setText("");
    }//GEN-LAST:event_txtChatLocalActionPerformed

    private void btnListoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListoActionPerformed
        try{
        if (btnListo.isSelected()) {
            com.getEnvia().enviarTexto("LISTO");
        } else {
            com.getEnvia().enviarTexto("DESLISTO");
        }
        }catch(IOException e){
            JOptionPane.showMessageDialog(this, "Error al cambiar estado de listo. Mensaje original: "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnListoActionPerformed

    private void txtChatGlobalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChatGlobalActionPerformed
       if(txtChatGlobal.getText().equals("/lista")){
           try{
               com.getEnvia().enviarTexto("LISTA DE USUARIOS");
           }catch(IOException e){
               JOptionPane.showMessageDialog(this, "Error al enviar mensaje."+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
           }
       }else{
        try{
           if(!txtChatGlobal.getText().equals("")){
               com.getEnvia().enviarChat(txtChatGlobal.getText(),true);
           }
       }catch(IOException e){
           JOptionPane.showMessageDialog(this, "Error al enviar mensaje."+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
       }
       }
       txtChatGlobal.setText("");
    }//GEN-LAST:event_txtChatGlobalActionPerformed

    // Restablece los valores originales de las pestanas cuando se les da click.
    private void panelChatStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_panelChatStateChanged
        int pestana = panelChat.getSelectedIndex();
        switch(pestana){
            case 0: panelChat.setTitleAt(pestana, "Local"); 
                    txtChatLocal.requestFocus();
                    break;
            case 1: panelChat.setTitleAt(pestana, "Global");
                    txtChatGlobal.requestFocus();
                    break;
        }
    }//GEN-LAST:event_panelChatStateChanged

    // Crear partida, se llama cuando se elije el numero de jugadores
    private void crearPartida() {
        pnlCuantos.setVisible(false);
        btnListo.setVisible(true);
        txtChatLocal.setEnabled(true);
        creada = true;
        
        if(hosteando){
            try{
                com.getEnvia().crearPartida(jugadores);
            }catch(IOException e){
                JOptionPane.showMessageDialog(this, "Error al crear partida. Mensaje original:"+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Empezar partida, se llama cuando todos estan listos
    public void empezarPartida() {
        // Oculta el boton de listos
        btnListo.setVisible(false);
        // Crea y empieza la partida
        creada = false;
        empezada = true;

        // Crea un panel del mismo tamanio del fondo para jugar en el
        // Sobreescribe el metodo de dibujar para que sea el que esta en la capa de negocios
        pnlJuego = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponents(g);
                com.getControlPartida().pintarTablero(g);
            }
        };
        pnlJuego.setOpaque(false);
        pnlJuego.setSize(pnlFondo.getSize());
        pnlJuego.setBorder(pnlFondo.getBorder());
        pnlFondo.add(pnlJuego);
        pnlFondo.repaint();

        // Agrega un listener de mouse al panel, para que haga los metodos de negocio cuando de click en un lugar
        pnlJuego.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if(empezada){
                    int resultado = com.getControlPartida().realizarMovimiento(me.getPoint(), com.getJugadorInt());
                    if (resultado > 1) {
                        // Comunica el movimiento al resto de los jugadores
                        try{
                            com.getEnvia().enviarMovimiento(resultado);
                        }catch(IOException e){
                            JOptionPane.showMessageDialog(null, "Error al conectar al servidor. "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    // Finaliza el juego si acabo
                    if (resultado == 3) {
                        // Comunica el movimiento al resto de los jugadores
                        try{
                            com.getEnvia().enviarFin();
                        }catch(IOException e){
                            JOptionPane.showMessageDialog(null, "Error al conectar al servidor. "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    // Repinta el panel
                    repaint();
                }
            }
            
            @Override
            public void mousePressed(MouseEvent me) {
                // Nada
            }
            
            @Override
            public void mouseReleased(MouseEvent me) {
                // Nada
            }
            
            @Override
            public void mouseEntered(MouseEvent me) {
                // Nada
            }
            
            @Override
            public void mouseExited(MouseEvent me) {
                // Nada
            }
        });
    }

    // Termina la partida e indica al ganador
    public void terminarPartida(Jugador ganador) {
        JOptionPane.showMessageDialog(this, "EL JUGADOR " + ganador.getNombre() + " ES EL GANADOR.", "VICTORY ROYALE", JOptionPane.INFORMATION_MESSAGE);
        empezada = false;
        txtChatLocal.setEnabled(false);
    }

    // Metodo para cambiar de color a los jugadores, cuando se da click etxtHistorialChatn su caja de color respectiva
    private void actualizaColor(Color color, int jugadorID) {
        //Solo cambia los datos del jugador relevante
        colores[jugadorID].setBackground(color);
        puntos[jugadorID].setForeground(color);
        otros[jugadorID].setForeground(color);
        // Repinta el panel
        pnlJuego.repaint();
    }

    public JLabel[] getPuntos() {
        return puntos;
    }

    public JPanel[] getPanelesJugadores() {
        return panelesJugadores;
    }

    public JLabel[] getImagenes() {
        return imagenes;
    }

    public JLabel[] getColores() {
        return colores;
    }

    public JLabel[] getOtros() {
        return otros;
    }

    public JPanel getPnlFondo() {
        return pnlFondo;
    }

    public JTextField getTxtChatGlobal() {
        return txtChatGlobal;
    }

    public JTextField getTxtChatLocal() {
        return txtChatLocal;
    }

    public JTextArea getTxtHistorialChatGlobal() {
        return txtHistorialChatGlobal;
    }

    public JTextArea getTxtHistorialChatLocal() {
        return txtHistorialChatLocal;
    }

    public JTabbedPane getPanelChat() {
        return panelChat;
    }

    public JPanel getPnlJuego() {
        return pnlJuego;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCuantos2;
    private javax.swing.JButton btnCuantos3;
    private javax.swing.JButton btnCuantos4;
    private javax.swing.JToggleButton btnListo;
    private javax.swing.JLabel clrPlayer1;
    private javax.swing.JLabel clrPlayer2;
    private javax.swing.JLabel clrPlayer3;
    private javax.swing.JLabel clrPlayer4;
    private javax.swing.JLabel imgPlayer1;
    private javax.swing.JLabel imgPlayer2;
    private javax.swing.JLabel imgPlayer3;
    private javax.swing.JLabel imgPlayer4;
    private javax.swing.JLabel lblCuantos;
    private javax.swing.JLabel otroPlayer1;
    private javax.swing.JLabel otroPlayer2;
    private javax.swing.JLabel otroPlayer3;
    private javax.swing.JLabel otroPlayer4;
    private javax.swing.JTabbedPane panelChat;
    private javax.swing.JPanel pnlChatGlobal;
    private javax.swing.JPanel pnlChatLocal;
    private javax.swing.JPanel pnlCuantos;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlPlayer1;
    private javax.swing.JPanel pnlPlayer2;
    private javax.swing.JPanel pnlPlayer3;
    private javax.swing.JPanel pnlPlayer4;
    private javax.swing.JLabel ptsPlayer1;
    private javax.swing.JLabel ptsPlayer2;
    private javax.swing.JLabel ptsPlayer3;
    private javax.swing.JLabel ptsPlayer4;
    private javax.swing.JScrollPane scrollChatGlobal;
    private javax.swing.JScrollPane scrollChatLocal;
    private javax.swing.JTextField txtChatGlobal;
    private javax.swing.JTextField txtChatLocal;
    private javax.swing.JTextArea txtHistorialChatGlobal;
    private javax.swing.JTextArea txtHistorialChatLocal;
    // End of variables declaration//GEN-END:variables
}
