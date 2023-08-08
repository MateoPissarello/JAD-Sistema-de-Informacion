/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelos.dao.DaoCuenta;
import modelos.dto.Cuenta;
import vistas.UICuenta;

/**
 *
 * @author User
 */
public class ControladorCuenta implements ActionListener {
    private UICuenta uiCuenta;
    private DaoCuenta modeloCuenta;
    
    
    public ControladorCuenta(UICuenta uiCliente){
        this.uiCuenta = uiCliente;
        this.modeloCuenta= new DaoCuenta(); //Composicion con DAOCuenta
       
       this.uiCuenta.consultarBtn.addActionListener(this);
       this.uiCuenta.registrarBtn.addActionListener(this);
       this.uiCuenta.salirBtn.addActionListener(this);
       this.uiCuenta.setVisible(true);
       
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == uiCuenta.registrarBtn){ //Crea una cuenta y la añada a la ListaCuenta de Daocuenta
           Cuenta cuenta = new Cuenta(); //Se crea un objeto de cuenta
           cuenta.setId_cuenta(Integer.parseInt(uiCuenta.idTextField.getText())); // se extraen los datos de la vista y se colocan en el objeto cuenta creado
           cuenta.setNicho_mercado(uiCuenta.nichoMercadoTextField.getText());
           cuenta.setNombre_empresa(uiCuenta.nomEmpresaTextField.getText());
           cuenta.setNumero_contacto(uiCuenta.numContactoTextField.getText());
           cuenta.setRepresentante(uiCuenta.nomRepresentanteTextField.getText());
           cuenta.setEmail(uiCuenta.emailTextField.getText());
           
           modeloCuenta.registro(cuenta); // se llama a la funcion registar del DaoCuenta
         
           uiCuenta.idTextField.setText("");
           uiCuenta.nichoMercadoTextField.setText("");
           uiCuenta.numContactoTextField.setText("");
           uiCuenta.nomEmpresaTextField.setText("");
           uiCuenta.nomRepresentanteTextField.setText("");
           uiCuenta.emailTextField.setText("");

       }
       if (e.getSource() == uiCuenta.consultarBtn){
           int id = Integer.parseInt(uiCuenta.idTextField.getText()); //obtiene el id de la casilla de id en la vista
           Cuenta cuenta = modeloCuenta.consultar(id); //Revisa si existe una cuenta con ese id
           
           if (cuenta == null){
              JOptionPane.showMessageDialog(null, "No existe");
              
           }else{
               //Se coloca en las casillas de texto los datos de la cuenta consultada
               uiCuenta.nomEmpresaTextField.setText(cuenta.getNombre_empresa());
               uiCuenta.nichoMercadoTextField.setText(cuenta.getNicho_mercado());
               uiCuenta.nomRepresentanteTextField.setText(cuenta.getRepresentante());
               uiCuenta.numContactoTextField.setText(cuenta.getNumero_contacto());
               uiCuenta.emailTextField.setText(cuenta.getEmail());
               uiCuenta.pedidosLabel.setText(String.valueOf(cuenta.getPedidos_realizados()));
           }
           
           
           
       }
       if (e.getSource() == uiCuenta.salirBtn){
           //Se guardan las nuevas cuentas
           modeloCuenta.guardar();
           JOptionPane.showMessageDialog(null, "Guardado");
           
       }
    }
    
    
}
