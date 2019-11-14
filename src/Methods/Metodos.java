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
    
    public DefaultListModel<String> LISTMODEL = new DefaultListModel<>();
    
    public static Metodos instance = null;
    public static Metodos getInstance(){
        if(instance == null){
            instance = new Metodos();
        }
        return instance;
    }
    
    public DefaultListModel<String> listModel = new DefaultListModel<>();
    
    public Ciudad grafo;
    
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

    public String insertarArco2(Ciudad origen, Ciudad destino, int distancia, boolean pasoVehiculosPesados, int velMax, int peso) {
        return insertarArco(origen, destino, distancia, pasoVehiculosPesados, velMax, peso);
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
            insertarArco2(destino, origen, distancia, pasoVehiculosPesados, velMax, peso);
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
    
    public void profundidad(Ciudad aux){ 
        if(aux != null && !aux.marca){
            aux.marca = true;
            Camino auxA = aux.sigA;
            while(auxA != null){
                LISTMODEL.addElement("Destino: " + auxA.destino.nombre);
                LISTMODEL.addElement("Peso: " + auxA.peso);
                LISTMODEL.addElement("================================");
                profundidad(auxA.destino);
                auxA = auxA.sigA;
            }
        }
    }
    
    public void amplitud(Ciudad grafo)// metodo para imprimir el inicio en amplitud
    {
        if (grafo == null) {
            System.out.println("No hay grafo");
        } else {
            Ciudad temp = grafo;
            while (temp != null) {
                listModel.addElement("Vertice: " + temp.nombre);
                Camino aux = temp.sigA;
                while (aux != null) {
                    //listModel.addElement("Peso: " + aux.peso);
                    listModel.addElement("Destino: " + aux.destino.nombre);
                    aux = aux.sigA;
                }
                listModel.addElement("-----------");
                temp = temp.sigV;
            }
        }
    }
    
    String rc="";
    int minRC=0;
    boolean existe=false;
    void rutaCorta(Ciudad origen, Ciudad destino, String ruta, int dist) {
        if ((origen == null) || (origen.marca == true)) {
            return;
        }
        if (origen == destino) {
            System.out.println("Ruta: " + ruta + destino.nombre);
            System.out.println("Con una distancia de: " + dist);

            if ((rc.equals("")) || (minRC > dist)) {
                rc = ruta + destino.nombre;
                minRC = dist;
            }
            existe = true;
            return;
        }
        origen.marca = true;
        Camino a = origen.sigA;
        while (a != null) {
            rutaCorta(a.destino, destino, ruta + origen.nombre, dist + a.distancia);
            a = a.sigA;
        }
        origen.marca = false;
    }
    public void quitarMarca(){
        Ciudad aux = grafo;
        while (aux!=null){
            aux.marca=false;
            aux = aux.sigV;
        }
    }
}
