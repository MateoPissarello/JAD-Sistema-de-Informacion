/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Pedido implements Serializable {
    
    private LocalDateTime fecha_entrega;
    private Cuenta cuenta;
    private ArrayList<Detalle> listaDetalle= new ArrayList<>();
    private int pedidoId;
    private String estado = "No entregado";

    public Cuenta getCuenta() {
        return cuenta;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }
    
    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    
    
    public void agregarDetalle(Detalle detalle){
        listaDetalle.add(detalle);
    }

    public LocalDateTime getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(LocalDateTime fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public ArrayList<Detalle> getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(ArrayList<Detalle> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }
    
    public int valorAPagar(){
        int precio = 0;
        for (Detalle miDetalle : this.listaDetalle){
            precio += miDetalle.getCantidad() * miDetalle.getProducto().getPrecio();
            
        }
        return precio;   
    }
    
}
