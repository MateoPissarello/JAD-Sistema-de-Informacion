/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelos.dao.DaoPedido;
import vistas.UIPrincipal;
import controladores.ControladorCuenta;
import controladores.ControladorPedido;
import vistas.UIAdminPedidos;
import vistas.UICuenta;
import vistas.UIInformeCuentas;
import vistas.UIInformePedidos;
import vistas.UIPedido;

/**
 *
 * @author User
 */
public class ControladorPrincipal implements ActionListener{
    private UIPrincipal vista;
    
    public ControladorPrincipal(UIPrincipal vista){
        this.vista = vista;
        this.vista.cuentas.addActionListener(this);
        this.vista.agregarPedidos.addActionListener(this);
        this.vista.adminPedidos.addActionListener(this);
        this.vista.informeCuentas.addActionListener(this);
        this.vista.informePedidos.addActionListener(this);
        this.vista.salir.addActionListener(this);
        
        this.vista.setVisible(true);
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.cuentas){
            ControladorCuenta miControlador = new ControladorCuenta(new UICuenta());
            
        }
        if (e.getSource() == vista.agregarPedidos){
            ControladorPedido miControlador = new ControladorPedido(new UIPedido());
            
        }
        if (e.getSource() == vista.adminPedidos){
            ControladorAdminPedido miControlador = new ControladorAdminPedido(new UIAdminPedidos());
            
        }
        if (e.getSource() == vista.informeCuentas){
           ControladorInformeCuentas miControlador = new ControladorInformeCuentas(new UIInformeCuentas());
        }
        if (e.getSource() == vista.informePedidos){
            ControladorInformePedido miControlador = new ControladorInformePedido(new UIInformePedidos());
        }
        if (e.getSource() == vista.salir){
            System.exit(0);
            
        }
    }
    
}
