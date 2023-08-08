/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.dto;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Cuenta implements Serializable {
    private int id_cuenta;
    private String nombre_empresa;
    private String representante;
    private String numero_contacto;
    private int pedidos_realizados;
    private String nicho_mercado;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getNumero_contacto() {
        return numero_contacto;
    }

    public void setNumero_contacto(String numero_contacto) {
        this.numero_contacto = numero_contacto;
    }

    public int getPedidos_realizados() {
        return pedidos_realizados;
    }

    public void setPedidos_realizados(int pedidos_realizados) {
        this.pedidos_realizados = pedidos_realizados;
    }

    public String getNicho_mercado() {
        return nicho_mercado;
    }

    public void setNicho_mercado(String nicho_mercado) {
        this.nicho_mercado = nicho_mercado;
    }
    
    
    
    
}
