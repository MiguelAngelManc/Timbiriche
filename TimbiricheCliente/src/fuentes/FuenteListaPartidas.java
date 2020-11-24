/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuentes;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import objetosNegocios.Conexion;
import objetosNegocios.Jugador;
import objetosNegocios.Partida;

/**
 *
 * @author labcisco
 */
public class FuenteListaPartidas extends Fuente{

    public FuenteListaPartidas() throws IOException {
        super();
    }
    
    @Override
    public boolean tratarDeManejar(String msg) {
        return msg.equals("LISTA DE PARTIDAS");
    }

    @Override
    public void hacerAccion(Conexion con) throws IOException {
        // Obtiene lista de partidas
        ArrayList<Partida> partidas = new ArrayList();
        partidas.clear();
        int max = con.getIn().readInt();
        for(int i=0;i<max;i++){
            Partida nueva = null;
            try{
                nueva = (Partida)con.getIn().readObject();
            }catch(ClassNotFoundException e){
                throw new IOException(e);
            }
            if(nueva != null && !nueva.isEmpezada()){
                partidas.add(nueva);
            }
        }
        
        // Actualiza tabla de partidas
        DefaultTableModel model = (DefaultTableModel)bb.getTablaPartidas().getModel();
        model.setRowCount(0);
        for(Partida p : partidas){
            Object[] datos = {""+p.getId(),""+p.getJugadores().size(),""+p.getJugadoresMax(),"","","","","","","",""};
            // Itera sobre los jugadores
            int i = 0;
            for(Jugador j : p.getJugadores()){
                datos[3+(2*i)] = j.getNombre();
                datos[4+(2*i)] = j.getImagen();
                i++;
            }
            model.addRow(datos);
            bb.getTablaPartidas().setModel(model);
            bb.getTablaPartidas().repaint();
        }
    }
    
}
