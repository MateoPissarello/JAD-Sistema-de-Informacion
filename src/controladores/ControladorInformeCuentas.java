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
import modelos.dao.DaoCuenta;
import modelos.dto.Cuenta;
import vistas.UIInformeCuentas;

/**
 *
 * @author User
 */
public class ControladorInformeCuentas implements ActionListener {
    private UIInformeCuentas vista;
    private DaoCuenta modelo;
    private DefaultTableModel modeloTabla;
    
    public ControladorInformeCuentas(UIInformeCuentas vista){
        this.vista = vista;
        this.modelo = new DaoCuenta();
        this.modeloTabla = (DefaultTableModel) this.vista.tablaCuentas.getModel();
        this.vista.informeBtn.addActionListener(this); 
        this.vista.datosBtn.addActionListener(this);
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.datosBtn){
            int filas = modeloTabla.getRowCount();
            ArrayList<Cuenta> miLista = modelo.obtenerLista();
            for (int i = 0; filas > i; i++){ //Borra los datos que hay para que no se concatenen
                modeloTabla.removeRow(0);
            }
            for (Cuenta miCuenta: miLista){
                Object fila[] = {miCuenta.getId_cuenta(),miCuenta.getNombre_empresa(),miCuenta.getRepresentante(),
                    miCuenta.getNumero_contacto(),miCuenta.getNicho_mercado(), miCuenta.getEmail(),
                    miCuenta.getPedidos_realizados() //Colocar la infomracion de cada cuenta en la tabla
                };
                modeloTabla.addRow(fila); //Agrega el objeto creado arriba a la tabla
                        }
            
        }
        if (e.getSource() == vista.informeBtn){
            int mayor = (int) modeloTabla.getValueAt(0, 6);
            int mayorId = (int) modeloTabla.getValueAt(0,0);
            int filas = modeloTabla.getRowCount();
            ArrayList<Cuenta> miLista = modelo.obtenerLista();
            
            
            for(int i = 0; filas> i; i++){
                if((int)modeloTabla.getValueAt(i, 6) > mayor){
                    mayorId = (int)modeloTabla.getValueAt(i, 0);
                    mayor = miLista.get(i).getPedidos_realizados();
                }
            }
            JOptionPane.showMessageDialog(null, "El id de la cuenta con mas pedidos realizados es: " + mayorId);
        }
    }
    
}
