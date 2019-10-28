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
public class Ciudad {
    public String nombre;	//Nombre del vertice
    public Ciudad sigV;//Puntero para referenciar a los siguientes vertices
    public Camino sigA;//Puntero para hacer referencia a los arcos
    public boolean marca;//Marca, utilizado para los diferentes imprimir y recorridos recursivos
    public Ciudad(String n, boolean m)
    {
        nombre = n;
        marca = m;
    }

}
