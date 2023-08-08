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

import modelos.dto.Pedido;

public class DaoPedido implements Serializable {
    private List<Pedido> listaPedido;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    private  String filePath = "pedido.dat";
	/**
	 * 
	 */
	public DaoPedido() {
		this.listaPedido = new ArrayList();
		File file = new File(filePath);
		if (file.isFile()) {
			try {
				this.entrada = new ObjectInputStream(new FileInputStream("pedido.dat"));
				this.listaPedido = (List<Pedido>) entrada.readObject();
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
     * 
     */
        public void modificarEntregaPedido(int pedidoId){
            for (Pedido miPedido: listaPedido){
                if (miPedido.getPedidoId() == pedidoId){
                    miPedido.setEstado("Entregado");
                }
            }
            JOptionPane.showMessageDialog(null, "Se ha confirmado la entrega");
            
        }
	
	public void agregarPedido(Pedido pedido, Cuenta cuenta) {
		pedido.setCuenta(cuenta);
                cuenta.setPedidos_realizados(cuenta.getPedidos_realizados()+1);
                listaPedido.add(pedido);
            
		JOptionPane.showMessageDialog(null, "Se agregó exitosamente un nuevo pedido bajo el nombre de: " + cuenta.getRepresentante() + " y con id: " + cuenta.getId_cuenta());
	}
        
        public ArrayList<Pedido> consultarPedidos(int id){
            ArrayList<Pedido> pedidos = new ArrayList();
            for (Pedido pedido: listaPedido){
                if (pedido.getCuenta().getId_cuenta() == id){
                    pedidos.add(pedido);
                }
            }
            return pedidos;
            
        }
	/**
	 * 
	 * @param id
	 * @return
	 */
	public void eliminarPedido(int id) {
		for (Pedido pedido : listaPedido) {
			if(id == pedido.getCuenta().getId_cuenta())
                             listaPedido.remove(pedido);
			
		}
		
	}
        public ArrayList<Pedido> obtenerLista(){
            return (ArrayList<Pedido>) this.listaPedido;
        }
	/**
	 * Guarda los datos en la capa de persistencia
	 */
	public void guardar() {
		try {
			this.salida = new ObjectOutputStream(new FileOutputStream("pedido.dat"));
			this.salida.writeObject(listaPedido);
			this.salida.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        }
}