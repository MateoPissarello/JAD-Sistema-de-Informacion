    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;
import modelos.dao.DaoPedido;
import modelos.dto.Cuenta;
import modelos.dto.Detalle;
import modelos.dto.Pedido;
import modelos.dto.Producto;
import modelos.dao.DaoCuenta;
import vistas.UIPedido;

/**
 *
 * @author User
 */
public class ControladorPedido implements ActionListener{
    private UIPedido uiPedido;
    private DaoPedido modeloPedido;
    private DaoCuenta modeloCuenta;
    private Pedido miPedido = new Pedido(); //Crea un objeto Pedido

    public ControladorPedido(UIPedido uiPedido){
        this.uiPedido = uiPedido;
        this.modeloPedido = new DaoPedido(); //Composicion
        this.modeloCuenta = new DaoCuenta();//Composicion
        
        
        
        this.uiPedido.agregarPedidoBtn.addActionListener(this);
        this.uiPedido.valorAPagarBtn.addActionListener(this);
        this.uiPedido.confirmarPedidoBtn.addActionListener(this);
        this.uiPedido.setVisible(true);
        
    }
    private void limpiarView(){ //Funcion para limpiar la vista
        uiPedido.cantidadProducto.setValue(1);
        uiPedido.comentariosPedido.setText("");
        uiPedido.detallePedidoTextArea.setText("");
        uiPedido.valorAPagarLabel.setText("");
        
    }
    private LocalDateTime convertDate(Date dateToConvert){ //Convierte la fecha del spinner de la vista a un LocalDateTime
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == uiPedido.agregarPedidoBtn){
            Detalle detalle = new Detalle(); // Se crea un objeto detalle
            detalle.setCantidad(uiPedido.cantidadProducto.getValue()); //Se le asigna la cantidad segun el JSlider de la vista
            Producto miProducto = new Producto(); //Creo un objeto de producto
            if (uiPedido.selectorDetalle.getSelectedIndex() == 0){ //Si esta en 0 es impresion
                miProducto.setCodigo(uiPedido.selectorDetalle.getSelectedIndex());
                miProducto.setDescripcion("Impresion");
                miProducto.setPrecio(5000);
            }else{
                miProducto.setCodigo(uiPedido.selectorDetalle.getSelectedIndex());
                miProducto.setDescripcion("Diseño");
                miProducto.setPrecio(10000);
            }
            detalle.setProducto(miProducto);
            detalle.setComentarios(uiPedido.comentariosPedido.getText());
            miPedido.agregarDetalle(detalle);
            uiPedido.cantidadProducto.setValue(1);
            uiPedido.comentariosPedido.setText("");
            uiPedido.detallePedidoTextArea.append("Producto: "+ detalle.getProducto().getDescripcion() + " Cantidad: " + detalle.getCantidad() + "\n");
            uiPedido.valorAPagarLabel.setText("");
            
       
        }
        if (e.getSource() == uiPedido.valorAPagarBtn){
            int precio = miPedido.valorAPagar();
            uiPedido.valorAPagarLabel.setText(String.valueOf(precio));
        }
        if (e.getSource() == uiPedido.confirmarPedidoBtn){
           Date fecha = (Date) uiPedido.fechaSpinner.getValue();
           miPedido.setFecha_entrega(convertDate(fecha));
           int id = Integer.parseInt(uiPedido.idCuentaTextField.getText());
           miPedido.setPedidoId((int) (id + Math.floor(Math.random() * 600 + 1)));
           Cuenta cuenta = modeloCuenta.consultar(id);
           modeloPedido.agregarPedido(miPedido, cuenta);
           modeloCuenta.guardar();
           modeloPedido.guardar();
           limpiarView();
           
           
        }
    }
    
    
    
}
