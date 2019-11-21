/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Methods;

import Classes.Camino;
import Classes.Ciudad;
import Classes.Pedidos;
import javax.swing.DefaultListModel;

/**
 *
 * @author Sebas
 */
public class Metodos {

    public DefaultListModel<String> LISTMODEL = new DefaultListModel<>();
    
    public static Metodos instance = null;

    public static Metodos getInstance() {
        if (instance == null) {
            instance = new Metodos();
        }
        return instance;
    }

    public DefaultListModel<String> listModel = new DefaultListModel<>();

    public Ciudad grafo;

    public boolean insertarVertices(String nombre) {
        //insertar al inicio de una lista simple
        if (buscar(nombre) == null) {
            Ciudad nuevo = new Ciudad(nombre, false);
            if (grafo == null) {
                grafo = nuevo;
                return true;
            }
            nuevo.sigV = grafo;
            grafo = nuevo;
            return true;
        }
        return false;
    }

    public Ciudad buscar(String nombre) {
        //recorre la lista simple de vertices y retorna el vertice o null
        Ciudad aux = grafo;
        while (aux != null) {
            if (aux.nombre.equals(nombre)) {
                return aux;
            }
            aux = aux.sigV;
        }
        return null;
    }

    public boolean insertarArco2(Ciudad origen, Ciudad destino, int distancia, boolean pasoVehiculosPesados, int velMax) {
        //metodo que llama al insertar arco para crear un grafo con doble direccion
        return insertarArco(origen, destino, distancia, pasoVehiculosPesados, velMax);
    }

    public boolean insertarArco(Ciudad origen, Ciudad destino, int distancia, boolean pasoVehiculosPesados, int velMax) {
        //metodo que inserta un camino
        if (buscar(origen, destino) == null) {
            Camino nuevo = new Camino(destino, distancia, pasoVehiculosPesados, velMax);
            nuevo.destino = destino;
            if (origen.sigA == null) {
                origen.sigA = nuevo;
            } else {
                nuevo.sigA = origen.sigA;
                origen.sigA.antA = nuevo;
                origen.sigA = nuevo;
            }
            insertarArco2(destino, origen, distancia, pasoVehiculosPesados, velMax);
            return true;
        }
        return false;
    }

    public Camino buscar(Ciudad origen, Ciudad destino) {
        //metodo que recorre la sublista del nodo origen y retorna el camino o null
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

    public boolean eliminaArco(Ciudad origen, Ciudad destino) {
        //origen destino y elimina el arco si existe (un arco directo)
        //en la interfaz primero buscar vertice 

        Camino aux = buscar(origen, destino);
        if (aux != null) {
            if (origen.sigA == aux) { //es el primero
                origen.sigA = aux.sigA;
                if (origen.sigA != null) { //si hay otro nodo
                    aux.sigA.antA = null;
                }
                return true;
            }//si no es el primero
            aux.antA.sigA = aux.sigA;
            if (aux.sigA != null) {
                aux.sigA.antA = aux.antA;
            }
            return true;
        }
        return false;
    }

    public boolean eliminarVertice(Ciudad verticePorEliminar) {
        //antes de eiliminar un vertice tiene que eliminar los arcos que apuntan a este   
        Ciudad aux = grafo;
        Ciudad ant = grafo;
        while (aux != null) { //se eliminan los arcos que apunten al vertice por eliminar
            eliminaArco(aux, verticePorEliminar);

            //para el anterior
            if (aux.sigV != null && aux.sigV.equals(verticePorEliminar)) {
                ant = aux;
            }
            aux = aux.sigV;
        }//eliminar el vertice
        if (verticePorEliminar.equals(grafo)) { //si es el primero
            grafo = verticePorEliminar.sigV;
        }
        ant.sigV = verticePorEliminar.sigV;
        return true;
    }

    public void profundidad(Ciudad aux) {
        //metodo recursivo que imprime los caminos por donde pasan por esta ciudad
        if (aux == null || aux.marca) {
            return;
        }
        aux.marca = true;
        Camino auxA = aux.sigA;
        while (auxA != null) {
            LISTMODEL.addElement("Origen: "+ aux.nombre);
            LISTMODEL.addElement("Destino: " + auxA.destino.nombre);
            if (auxA.pasoVehiculosPesados){
                LISTMODEL.addElement("El camino es pesado");
            }else{
                LISTMODEL.addElement("El camino es liviano");
            }
            LISTMODEL.addElement("================================");
            profundidad(auxA.destino);
            auxA = auxA.sigA;

        }
    }

    public void amplitud(Ciudad grafo)// metodo para imprimir el inicio en amplitud
            //imprime todas las ciudades con sus respectivos caminos
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

    public String rc = "";
    public int minRC = 0;
    public double velocidad=0;
    public boolean existe = false;
    
    public void rutaCortaDistancia(Ciudad origen, Ciudad destino, String ruta, int dist, int vel, Pedidos pedido){
        //metodo recursivo que utiliza muchos parámetros para obtener atributos de los caminos y despues
        //guarda el valor total en una variable global
        if ((origen == null) || (origen.marca == true)){
            return;
        } 
        if (origen == destino){  
            if((rc.equals("")) || (minRC > dist) ) {
                rc = ruta + " / "+ destino.nombre;
                minRC = dist;
                velocidad = vel;
            }  
            existe=true;
            return;
        }
        origen.marca = true;
        Camino a = origen.sigA;
        while (a != null) {
            if ((pedido.peso>5000) && (a.pasoVehiculosPesados==false)){
                //no haga nada 
            }else{
                rutaCortaDistancia(a.destino, destino, ruta +" / "+ origen.nombre, dist + a.distancia,vel+a.velMax, pedido);
            }     
            a = a.sigA;
        }
        origen.marca = false;
    }
    public float mintime=0F;
    public void rutaCortaTiempo(Ciudad origen, Ciudad destino, String ruta, int dist, int vel, Pedidos pedido) {
        //metodo recursivo que utiliza muchos parámetros para obtener atributos de los caminos y despues
        //guarda el valor total en una variable global
        if ((origen == null) || (origen.marca == true)){
            return;
        } 
        if (origen == destino){  
            if((rc.equals("")) || (mintime > dist/vel) ) {
                rc = ruta + " / "+ destino.nombre;
                minRC = dist;
                velocidad = vel;
                mintime=((float)dist/vel)*60;
            }  
            existe=true;
            return;
        }
        origen.marca = true;
        Camino a = origen.sigA;
        while (a != null) {
            if ((pedido.peso>5000) && (a.pasoVehiculosPesados==false)){
                //no haga nada 
            }else{
                rutaCortaTiempo(a.destino, destino, ruta +" / "+ origen.nombre, dist + a.distancia,vel+a.velMax, pedido);
            }     
            a = a.sigA;
        }
        origen.marca = false;
    }

    public void quitarMarca() {
        //recorre la lista simple enlazada de los caminos y cambia la marca por false, utilizada para volver a 
        //llamar a profundidad o amplitud
        Ciudad aux = grafo;
        while (aux != null) {
            aux.marca = false;
            aux = aux.sigV;
        }
    }

    public boolean modificarVertice(Ciudad city, String nombre) {
        //metodo que modifica el nombre de la ciudad dada
        if (buscar(nombre) == null) {
            city.nombre = nombre;
            return true;
        }
        return false;
    }
}
