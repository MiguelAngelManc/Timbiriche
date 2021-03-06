/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import blackboard.Blackboard;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import objetosNegocios.Jugador;

/**
 *
 * @author Leobardo Vizcarra
 */
public class SeleccionModo extends javax.swing.JFrame {
    Jugador jugador;
    Blackboard com;
    /**
     * Creates new form SeleccionModo
     */
    public SeleccionModo() {
        initComponents();
        Font font = new Font("Monospaced", Font.PLAIN, 12);
        historialChat.setFont(font);
        
        // Agrega metodo para seleccionar partida desde la tabla
        tablaPartidas.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if(tablaPartidas.getSelectedRow()>=0)
                    campoTextoCodigo.setText(tablaPartidas.getValueAt(tablaPartidas.getSelectedRow(), 0).toString());
            }
        });
        
        // Agrega metodo para cerrar el programa cuando se cierra el lobby
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        
        try{
        com = Blackboard.getInstance(null);
        com.ventanaLobby(tablaPartidas, historialChat);
        com.getEnvia().enviarTexto("LISTA DE USUARIOS");
        }catch(IOException e){
            JOptionPane.showMessageDialog(this, "Error al conectar al servidor. "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        obtenerPartidas();
    }
    
    public void obtenerPartidas(){
        try{
            com.getEnvia().enviarTexto("OBTENER PARTIDAS");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error al obtener partidas. "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelFondo = new javax.swing.JPanel();
        botonUnirse = new javax.swing.JButton();
        botonCrear = new javax.swing.JButton();
        labelInstruccionUnirse = new javax.swing.JLabel();
        labelInstruccionCrear = new javax.swing.JLabel();
        campoTextoCodigo = new javax.swing.JTextField();
        scrollLobby = new javax.swing.JScrollPane();
        tablaPartidas = new javax.swing.JTable();
        botonRefrescar = new javax.swing.JButton();
        pnlChat = new javax.swing.JPanel();
        campoTextoChat = new javax.swing.JTextField();
        scrollChat = new javax.swing.JScrollPane();
        historialChat = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modo");
        setResizable(false);

        jPanelFondo.setPreferredSize(new java.awt.Dimension(740, 469));

        botonUnirse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Unir.png"))); // NOI18N
        botonUnirse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonUnirseActionPerformed(evt);
            }
        });

        botonCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Crear.png"))); // NOI18N
        botonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearActionPerformed(evt);
            }
        });

        labelInstruccionUnirse.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelInstruccionUnirse.setText("Unirse a un juego existente");

        labelInstruccionCrear.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelInstruccionCrear.setText("Crear juego nuevo");

        campoTextoCodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoTextoCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoCodigoActionPerformed(evt);
            }
        });

        tablaPartidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "J. Act", "J. Max", "J1", "", "J2", "", "J3", "", "J4", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaPartidas.getTableHeader().setReorderingAllowed(false);
        scrollLobby.setViewportView(tablaPartidas);
        if (tablaPartidas.getColumnModel().getColumnCount() > 0) {
            tablaPartidas.getColumnModel().getColumn(0).setResizable(false);
            tablaPartidas.getColumnModel().getColumn(1).setResizable(false);
            tablaPartidas.getColumnModel().getColumn(2).setResizable(false);
            tablaPartidas.getColumnModel().getColumn(4).setResizable(false);
            tablaPartidas.getColumnModel().getColumn(6).setResizable(false);
            tablaPartidas.getColumnModel().getColumn(8).setResizable(false);
        }

        botonRefrescar.setText("Refrescar");
        botonRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRefrescarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFondoLayout = new javax.swing.GroupLayout(jPanelFondo);
        jPanelFondo.setLayout(jPanelFondoLayout);
        jPanelFondoLayout.setHorizontalGroup(
            jPanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFondoLayout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelInstruccionUnirse)
                        .addGroup(jPanelFondoLayout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(botonCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelFondoLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(jPanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(campoTextoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(botonUnirse, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFondoLayout.createSequentialGroup()
                        .addComponent(labelInstruccionCrear)
                        .addGap(26, 26, 26)))
                .addGap(18, 18, 18)
                .addGroup(jPanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonRefrescar)
                    .addComponent(scrollLobby, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanelFondoLayout.setVerticalGroup(
            jPanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFondoLayout.createSequentialGroup()
                .addComponent(botonRefrescar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollLobby, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelFondoLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(labelInstruccionCrear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(labelInstruccionUnirse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonUnirse, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(campoTextoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pnlChat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 4));

        campoTextoChat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoChatActionPerformed(evt);
            }
        });

        historialChat.setColumns(20);
        historialChat.setLineWrap(true);
        historialChat.setRows(5);
        historialChat.setText("[SERVER]: Bienvenido al servidor de Timbiriche!\n[SERVER]: Puedes escribir \"/lista\" en cualquier\n          momento para ver una lista de todos \n          los usuarios conectados.\n");
        scrollChat.setViewportView(historialChat);

        javax.swing.GroupLayout pnlChatLayout = new javax.swing.GroupLayout(pnlChat);
        pnlChat.setLayout(pnlChatLayout);
        pnlChatLayout.setHorizontalGroup(
            pnlChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(campoTextoChat)
            .addGroup(pnlChatLayout.createSequentialGroup()
                .addComponent(scrollChat, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlChatLayout.setVerticalGroup(
            pnlChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChatLayout.createSequentialGroup()
                .addComponent(scrollChat, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoTextoChat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(pnlChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearActionPerformed
        Juego partida = new Juego(true, this);
        this.setVisible(false);
        partida.setVisible(true);
    }//GEN-LAST:event_botonCrearActionPerformed

    private void botonUnirseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonUnirseActionPerformed
        int id = -1;
        try{
            if(campoTextoCodigo.getText().trim().isEmpty())
                throw new NumberFormatException("ID vacio");
            id = Integer.parseInt(campoTextoCodigo.getText());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Error con el ID ingresado. Mensaje original: "+e.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            Juego partida = new Juego(false, this);
            com.getEnvia().unirPartida(id);
            this.setVisible(false);
            partida.setVisible(true);
        }catch(IOException e){
            JOptionPane.showMessageDialog(this,"Error al unirse a la partida. Mensaje original: "+e.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        
    }//GEN-LAST:event_botonUnirseActionPerformed

    private void campoTextoCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoCodigoActionPerformed
        botonUnirseActionPerformed(evt);
    }//GEN-LAST:event_campoTextoCodigoActionPerformed

    private void campoTextoChatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoChatActionPerformed
       if(campoTextoChat.getText().equals("/lista")){
           try{
               com.getEnvia().enviarTexto("LISTA DE USUARIOS");
           }catch(IOException e){
               JOptionPane.showMessageDialog(this, "Error al enviar mensaje."+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
           }
       }else{
            try{
               if(!campoTextoChat.getText().equals("")){
                   com.getEnvia().enviarChat(campoTextoChat.getText(),true);
               }
           }catch(IOException e){
               JOptionPane.showMessageDialog(this, "Error al enviar mensaje."+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
           }
       }
       campoTextoChat.setText("");
    }//GEN-LAST:event_campoTextoChatActionPerformed

    private void botonRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRefrescarActionPerformed
        obtenerPartidas();
    }//GEN-LAST:event_botonRefrescarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCrear;
    private javax.swing.JButton botonRefrescar;
    private javax.swing.JButton botonUnirse;
    private javax.swing.JTextField campoTextoChat;
    private javax.swing.JTextField campoTextoCodigo;
    private javax.swing.JTextArea historialChat;
    private javax.swing.JPanel jPanelFondo;
    private javax.swing.JLabel labelInstruccionCrear;
    private javax.swing.JLabel labelInstruccionUnirse;
    private javax.swing.JPanel pnlChat;
    private javax.swing.JScrollPane scrollChat;
    private javax.swing.JScrollPane scrollLobby;
    private javax.swing.JTable tablaPartidas;
    // End of variables declaration//GEN-END:variables
}
