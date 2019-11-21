/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author pache
 * Esta es la clase pedido con su constructor
 */
public class Pedidos {
    public int id;
    public int peso;
    public String destino;
    public String origen;
    public String conductor;
    public Pedidos der;
    public Pedidos izq;

    public Pedidos(int id, int peso, String destino, String origen,String conductor) {
        this.id = id;
        this.peso = peso;
        this.destino = destino;
        this.origen = origen;
        this.conductor = conductor;
    }

    
}
