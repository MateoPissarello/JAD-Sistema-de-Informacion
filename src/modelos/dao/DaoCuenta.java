package modelos.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelos.dto.Cuenta;

public class DaoCuenta implements Serializable {
    private List<Cuenta> listaCuenta;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private  String filePath = "cuenta.dat";
	/**
	 * 
	 */
	public DaoCuenta() {
		this.listaCuenta = new ArrayList();
		File file = new File(filePath);
		if (file.isFile()) {
			try {
				this.entrada = new ObjectInputStream(new FileInputStream("cuenta.dat"));
				this.listaCuenta = (List<Cuenta>) entrada.readObject();
				this.entrada.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				guardar();
			}
		}
	}
    /**
     * 
     * @param cliente
     */
	public ArrayList<Cuenta> obtenerLista(){
            return (ArrayList<Cuenta>) this.listaCuenta;
        }
	public void registro(Cuenta cuenta) {
                int id = cuenta.getId_cuenta(); //Obtiene el id de la cuenta pasada por parametro
                for (Cuenta miCuenta: listaCuenta){
                    if (miCuenta.getId_cuenta() == id){ //recorre la lista de datos de cuenta y revisa si ya existe una cuenta con ese id, en ese caso no se registra
                        JOptionPane.showMessageDialog(null, "Error, ya existe una cuenta con ese id");
                        return;
                    }
                    
                }
		listaCuenta.add(cuenta);
		JOptionPane.showMessageDialog(null, "Un nuevo cliente fue registrado");
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Cuenta consultar(int id) {
		for (Cuenta cuenta : listaCuenta) {
			if(id == cuenta.getId_cuenta())
				return cuenta;
			
		}
                JOptionPane.showMessageDialog(null, "Error: Cuenta no existe");
		return null;
	}
	/**
	 * Guarda los datos en la capa de persistencia
	 */
	public void guardar() {
		try {
			this.salida = new ObjectOutputStream(new FileOutputStream("cuenta.dat"));
			this.salida.writeObject(listaCuenta);
			this.salida.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        }
}