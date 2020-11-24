package interfaces;

import blackboard.Blackboard;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import objetosNegocios.Jugador;

public class Menu extends javax.swing.JFrame {

    // Variable para la direccion IP del servidor
    String server = "localhost";
    // Variable para el icono del jugador
    Image imagenFinal;
    
    public Menu() {
        initComponents();
        // Pone la imagen de default
        try{
            File file = new File("src/imagenes/Perfil.png");
            imagenFinal = ImageIO.read(file).getScaledInstance(72, 72, Image.SCALE_DEFAULT);
        }catch(Exception e){
            imagenFinal = null;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelFondo = new javax.swing.JPanel();
        botonAceptar = new javax.swing.JButton();
        botonOpciones = new javax.swing.JButton();
        jPanelBordeColor = new javax.swing.JPanel();
        jPanelFotoPerfil = new javax.swing.JPanel();
        labelFotoPerfil = new javax.swing.JLabel();
        areaTextoNombreUsuario = new javax.swing.JTextField();
        labelInstruccionFotoPerfil = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menú principal");
        setMinimumSize(new java.awt.Dimension(327, 297));
        setResizable(false);

        jPanelFondo.setMaximumSize(new java.awt.Dimension(327, 297));
        jPanelFondo.setMinimumSize(new java.awt.Dimension(327, 297));

        botonAceptar.setText("Conectar");
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        botonOpciones.setText("Opciones");
        botonOpciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOpcionesActionPerformed(evt);
            }
        });

        jPanelBordeColor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 10, true));
        jPanelBordeColor.setForeground(new java.awt.Color(255, 51, 51));
        jPanelBordeColor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelBordeColorMouseClicked(evt);
            }
        });

        jPanelFotoPerfil.setPreferredSize(new java.awt.Dimension(150, 140));

        javax.swing.GroupLayout jPanelFotoPerfilLayout = new javax.swing.GroupLayout(jPanelFotoPerfil);
        jPanelFotoPerfil.setLayout(jPanelFotoPerfilLayout);
        jPanelFotoPerfilLayout.setHorizontalGroup(
            jPanelFotoPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelFotoPerfilLayout.setVerticalGroup(
            jPanelFotoPerfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        labelFotoPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Perfil.png"))); // NOI18N
        labelFotoPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelFotoPerfilMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanelBordeColorLayout = new javax.swing.GroupLayout(jPanelBordeColor);
        jPanelBordeColor.setLayout(jPanelBordeColorLayout);
        jPanelBordeColorLayout.setHorizontalGroup(
            jPanelBordeColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBordeColorLayout.createSequentialGroup()
                .addComponent(labelFotoPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelFotoPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
        );
        jPanelBordeColorLayout.setVerticalGroup(
            jPanelBordeColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFotoPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
            .addComponent(labelFotoPerfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        areaTextoNombreUsuario.setText("Ingrese nombre de usuario");
        areaTextoNombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaTextoNombreUsuarioActionPerformed(evt);
            }
        });

        labelInstruccionFotoPerfil.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        labelInstruccionFotoPerfil.setText("Seleccione el fondo para escoger una imagen de perfil");

        javax.swing.GroupLayout jPanelFondoLayout = new javax.swing.GroupLayout(jPanelFondo);
        jPanelFondo.setLayout(jPanelFondoLayout);
        jPanelFondoLayout.setHorizontalGroup(
            jPanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFondoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInstruccionFotoPerfil)
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFondoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelFondoLayout.createSequentialGroup()
                        .addGroup(jPanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(areaTextoNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelBordeColor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(88, 88, 88))
                    .addGroup(jPanelFondoLayout.createSequentialGroup()
                        .addComponent(botonAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonOpciones)
                        .addGap(84, 84, 84))))
        );
        jPanelFondoLayout.setVerticalGroup(
            jPanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFondoLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(labelInstruccionFotoPerfil)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelBordeColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(areaTextoNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAceptar)
                    .addComponent(botonOpciones))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        // Crea un usuario con los datos del menu y los guarda en alguna parte
        Jugador jugador;
        if(imagenFinal != null)
            jugador = new Jugador(areaTextoNombreUsuario.getText(), jPanelBordeColor.getForeground(),new ImageIcon(imagenFinal));
        else
            jugador = new Jugador(areaTextoNombreUsuario.getText(), jPanelBordeColor.getForeground(), labelFotoPerfil.getIcon());
        // Realiza conexion con el servidor
        try{
            Blackboard com = Blackboard.getInstance(server);
            com.empezar();
            // Envia datos de jugador
            com.setJugador(jugador);
            com.getEnvia().enviarUsuario();
            SeleccionModo ventana = new SeleccionModo();
            this.setVisible(false);
            ventana.setVisible(true);
        }catch(IOException e){
            JOptionPane.showMessageDialog(this, "Error al conectar al servidor. "+e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_botonAceptarActionPerformed

    private void botonOpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOpcionesActionPerformed
        server = JOptionPane.showInputDialog(this, "Direccion IP del servidor (Dejar blanco para usar el default.):");
        if(server == null || server.equals(""))
            server = "localhost";
    }//GEN-LAST:event_botonOpcionesActionPerformed

    private void areaTextoNombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_areaTextoNombreUsuarioActionPerformed
        botonAceptarActionPerformed(evt);
    }//GEN-LAST:event_areaTextoNombreUsuarioActionPerformed

    private void jPanelBordeColorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelBordeColorMouseClicked
//        Color nuevo = JColorChooser.showDialog(this, "Seleccionar color", Color.BLACK);
//        if (nuevo != null) {
//            jPanelBordeColor.setForeground(nuevo);
//            Border nuevin = new LineBorder(nuevo, 10);
//            jPanelBordeColor.setBorder(nuevin);
//        }
    }//GEN-LAST:event_jPanelBordeColorMouseClicked

    private void labelFotoPerfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelFotoPerfilMouseClicked
        //Selecciona un archivo que abrir
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes","png","jpg");
        chooser.setFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        int valido = chooser.showOpenDialog(this);
        if(valido==JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();

            try{
                // Consigue la imagen
                BufferedImage imagen = ImageIO.read(file);
                // Cambia de tamanio a la imagen
                Image imagenEscalada = imagen.getScaledInstance(130, 130, Image.SCALE_DEFAULT);
                // Guarda una copia escalada a 72x72, para el juego
                imagenFinal = imagen.getScaledInstance(72, 72, Image.SCALE_DEFAULT);
                // Convierte la foto a icono
                ImageIcon icono = new ImageIcon(imagenEscalada);

                // Cambia la foto
                labelFotoPerfil.setIcon(icono);
            }catch(Exception e){
                System.out.println("error");
            }
        }
    }//GEN-LAST:event_labelFotoPerfilMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField areaTextoNombreUsuario;
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonOpciones;
    private javax.swing.JPanel jPanelBordeColor;
    private javax.swing.JPanel jPanelFondo;
    private javax.swing.JPanel jPanelFotoPerfil;
    private javax.swing.JLabel labelFotoPerfil;
    private javax.swing.JLabel labelInstruccionFotoPerfil;
    // End of variables declaration//GEN-END:variables
}
