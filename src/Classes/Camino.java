/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Sebas
 */
public class Camino {
    public Camino antA,sigA;//Siguiente arco
    public Ciudad destino;//Como es un grafo multilista, para hacer referencia al destino
    public int peso;//Peso del arco
    public Camino(int p){
        peso = p;
    }
}
