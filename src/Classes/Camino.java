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
    public Camino antA,sigA; //Siguiente arco
    public Ciudad destino;   //Como es un grafo multilista, para hacer referencia al destino
    public int distancia;         //Peso del arco
    public boolean pasoVehiculosPesados; //true permitido, false no permitido
    public int peso;
    public int velMax; //velocidad máxima

    public Camino(Ciudad destino, int distancia, boolean pasoVehiculosPesados, int peso, int velMax) {
        this.destino = destino;
        this.distancia = distancia;
        this.pasoVehiculosPesados = pasoVehiculosPesados;
        this.peso = peso;
        this.velMax = velMax;
    }
}
