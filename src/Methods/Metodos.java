/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Methods;

import Classes.Camino;
import Classes.Ciudad;
import javax.swing.DefaultListModel;

/**
 *
 * @author Sebas
 */
public class Metodos {
    
    public static Metodos instance = null;
    public static Metodos getInstance(){
        if(instance == null){
            instance = new Metodos();
        }
        return instance;
    }
    
    DefaultListModel<String> listModel = new DefaultListModel<>();
    
    Ciudad grafo;
    
    public String insertarVertices(String nombre) {
        Ciudad nuevo = new Ciudad(nombre, false);
        if (grafo == null) {
            grafo = nuevo;
            return "Insertado";
        }
        nuevo.sigV = grafo;
        grafo = nuevo;
        return "";
    }

    public Ciudad buscar(String nombre) {
        Ciudad aux = grafo;
        while (aux != null) {
            if (aux.nombre.equals(nombre)) {
                return aux;
            }
            aux = aux.sigV;
        }
        return null;
    }

    public String insertarArco(Ciudad origen, Ciudad destino, int distancia, boolean pasoVehiculosPesados, int velMax, int peso) {
        if (buscar(origen, destino) == null) {
            Camino nuevo = new Camino(destino, distancia, pasoVehiculosPesados, velMax, peso);
            nuevo.destino = destino;
            if (origen.sigA == null) {
                origen.sigA = nuevo;
            } else {
                nuevo.sigA = origen.sigA;
                origen.sigA.antA = nuevo;
                origen.sigA = nuevo;
            }
            return "Insertado";
        }
        return "No se pueden repetir arcos";
    }

    public Camino buscar(Ciudad origen, Ciudad destino) {
        if (origen.sigA != null) {
            Camino aux = origen.sigA;
            while (aux != null) {
                if (aux.destino == destino) {
                    return aux;
                }
                aux = aux.sigA;
            }
        }
        return null;
    }
    
    public boolean eliminaArco (Ciudad origen, Ciudad destino) {
        //origen destino y elimina el arco si existe (un arco directo)
        //en la interfaz primero buscar vertice 
        
        Camino aux = buscar (origen, destino);
        if (aux != null){
            if (origen.sigA == aux){ //es el primero
                origen.sigA = aux.sigA;
                if (origen.sigA!=null){ //si hay otro nodo
                    aux.sigA.antA = null; 
                }
                return true;
            }//si no es el primero
            aux.antA.sigA = aux.sigA;
            if (aux.sigA!=null){
                aux.sigA.antA = aux.antA;
            }
            return true;
        }
        return false;
    }
    public boolean eliminarVertice (Ciudad verticePorEliminar){
    //antes de eiliminar un vertice tiene que eliminar los arcos que apuntan a este   
    Ciudad aux = grafo;
    Ciudad ant = grafo;
    while (aux!=null){ //se eliminan los arcos que apunten al vertice por eliminar
        eliminaArco(aux, verticePorEliminar);
        
        //para el anterior
        if (aux.sigV.equals(verticePorEliminar))
            ant = aux;
        aux = aux.sigV;
    }//eliminar el vertice
    if (verticePorEliminar.equals(grafo)){ //si es el primero
        grafo = verticePorEliminar.sigV;
    }
    ant.sigV = verticePorEliminar.sigV;
    return true;
    }
    
    public void quitarMarca(){
        Ciudad aux = grafo;
        while (aux!=null){
            aux.marca=false;
            aux = aux.sigV;
        }
    }
}
