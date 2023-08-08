/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.dao.DaoPedido;
import modelos.dto.Detalle;
import modelos.dto.Pedido;
import vistas.UIInformePedidos;

/**
 *
 * @author User
 */
public class ControladorInformePedido implements ActionListener{
    private UIInformePedidos vista;
    private DaoPedido modelo;
    private DefaultTableModel modeloTabla;
    
    public ControladorInformePedido(UIInformePedidos vista){
        this.vista = vista;
        this.modelo = new DaoPedido();
        this.modeloTabla = (DefaultTableModel) this.vista.tablaPedidos.getModel();
        this.vista.datosBtn.addActionListener(this);
        this.vista.informeBtn.addActionListener(this);
        
        this.vista.setVisible(true);
        
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.datosBtn){
            int filas = modeloTabla.getRowCount(); //Obtiene la cantidad de filas en la tabla que se encuentra en la vista
            ArrayList<Pedido> misPedidos = modelo.obtenerLista(); //Obtiene la lista de pedidos que se encuentra en el Dao,
            for (int i = 0; filas > i; i++){//Recorre la tabla y la vacia
                modeloTabla.removeRow(0);
            }
            for(Pedido miPedido: misPedidos){
                String detalle = "";
                for (Detalle miDetalle: miPedido.getListaDetalle()){
                    int cantidad = miDetalle.getCantidad();
                    String tipo = miDetalle.getProducto().getDescripcion();
                    detalle += "[Tipo: " + tipo + "|" + "Cantidad: " + cantidad + "] \n" ;
                    
                }
                Object fila[] = {miPedido.getFecha_entrega(),detalle,miPedido.getCuenta().getId_cuenta(),miPedido.getEstado()};
                modeloTabla.addRow(fila);
            }
        }
        if (e.getSource() == vista.informeBtn){
            int cantidadTotalImpresiones = 0;
            int cantidadTotalDisenios = 0;
            ArrayList<Pedido> misPedidos = modelo.obtenerLista();
            for(Pedido miPedido: misPedidos){
                for (Detalle detalle : miPedido.getListaDetalle()){
                    if (detalle.getProducto().getCodigo() == 0){
                        cantidadTotalImpresiones += detalle.getCantidad();
                        
                    }
                    else{
                        cantidadTotalDisenios += detalle.getCantidad();
                    }
                }
            }
            if (cantidadTotalDisenios > cantidadTotalImpresiones){
                JOptionPane.showMessageDialog(null,"El tipo de producto mas vendido son los Diseños con una cantidad de " + cantidadTotalDisenios + " unidades vendidas.");
            }
            else{
                JOptionPane.showMessageDialog(null,"El tipo de producto mas vendido son las Impresiones con una cantidad de " + cantidadTotalImpresiones + " unidades vendidas.");
            }
            int ingresosTotalesDisenios = cantidadTotalDisenios * 10000;
            int ingresosTotalesImpresiones = cantidadTotalImpresiones * 5000;
            JOptionPane.showMessageDialog(null,"Se ha obtenido un total de " + "$"+ingresosTotalesDisenios +" mediante diseños");
            JOptionPane.showMessageDialog(null,"Se ha obtenido un total de " + "$" + ingresosTotalesImpresiones + " mediante impresiones");
            JOptionPane.showMessageDialog(null,"Total de ingresos: " + "$" + (ingresosTotalesDisenios + ingresosTotalesImpresiones));
            
        }
    }
    
}
