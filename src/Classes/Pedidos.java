/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author pache
 */
public class Pedidos {
    int id;
    int peso;
    String destino;
    String origen;
    Pedidos sig;

    public Pedidos(int id, int peso, String destino, String origen) {
        this.id = id;
        this.peso = peso;
        this.destino = destino;
        this.origen = origen;
    }

    
}
