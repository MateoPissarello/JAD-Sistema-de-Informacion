/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.dao.DaoCuenta;
import modelos.dao.DaoPedido;
import modelos.dto.Detalle;
import modelos.dto.Pedido;
import vistas.UIAdminPedidos;

/**
 *
 * @author User
 */
public class ControladorAdminPedido implements ActionListener{
    private UIAdminPedidos miView;
    private DaoPedido modeloPedido;
    private DaoCuenta modeloCliente;
    private DefaultTableModel modeloTabla;
    
    
    public ControladorAdminPedido(UIAdminPedidos miView){
        this.miView = miView;
        this.modeloPedido = new DaoPedido();
        this.modeloCliente = new DaoCuenta();
        this.modeloTabla = (DefaultTableModel) this.miView.tablaPedidos.getModel();
        
        
        this.miView.consultarPedidosBtn.addActionListener(this);
        this.miView.entregasBtn.addActionListener(this);
        this.miView.setVisible(true);
        

       
    }
    public ArrayList<Pedido> obtenerListaPedidosPorId(int id){
        ArrayList<Pedido> miLista = modeloPedido.consultarPedidos(id);
        if (miLista == null){
            JOptionPane.showMessageDialog(null, "No hay pedidos para esa cuenta");
            return null;
        }
        return miLista;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == miView.consultarPedidosBtn){
            int filas = modeloTabla.getRowCount();
            ArrayList<Pedido> miLista = obtenerListaPedidosPorId(Integer.parseInt(miView.idTextField.getText()));
            for (int i = 0; filas> i; i++){
                modeloTabla.removeRow(0);
           }
            for (Pedido pedido: miLista){
                boolean entregado = !(pedido.getEstado().equals("No entregado"));
                ArrayList<String> listaDetalle = new ArrayList();
                for (Detalle miDetalle: pedido.getListaDetalle()){
                    String detalle = "Producto: " + miDetalle.getProducto().getDescripcion() + "Cantidad: " +  miDetalle.getCantidad();
                    listaDetalle.add(detalle);
                    
                }
                if(entregado == false){
                Object fila[] = {pedido.getPedidoId(),listaDetalle,pedido.getFecha_entrega(), pedido.valorAPagar(), entregado};
                modeloTabla.addRow(fila);
                }
            }
            
            
            
        }
        if(e.getSource() == miView.entregasBtn){
            int filas = modeloTabla.getRowCount();
            for (int i = 0; filas > i; i++){
                if ((Boolean)modeloTabla.getValueAt(i,4 )){
                    modeloPedido.modificarEntregaPedido((int)(modeloTabla.getValueAt(i, 0)));
                }
            }
            modeloPedido.guardar();
        }
    }
    
}
