/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Classes.Camino;
import Classes.Pedidos;
import Classes.Usuario;
import Methods.Arbol;
import Frames.MainFrame;
import Methods.Hashtable;
import Methods.Metodos;

/**
 *
 * @author Sebas
 */
public class Main {
public static Usuario[] listaUsuarios = new Usuario [13];

    public static void main(String[] args) {  
        /*
        Fecha de inicio: 28/10/2019
        Fecha última modificación: 20/11/2019
        */
        Metodos met = Metodos.getInstance();
        Hashtable hash = Hashtable.getInstance();
        //arbol quemado
        Arbol arbol = Arbol.getInstance();
        
        Usuario usuario = new Usuario(4984, "Jairo", "B4");
        hash.insertarUsuario(usuario);
        hash.insertarUsuario(new Usuario(2820, "Sebas", "B4"));
        
        arbol.insertar(50,arbol.raiz,300,"Cuatro Esquinas","San Ramón","jairo");
        arbol.insertar(2,arbol.raiz,86,"Aguas Zarcas","Cuatro Esquinas","jairo");
        arbol.insertar(100,arbol.raiz,300,"San Ramón","Pital","jairo");
        
        arbol.insertar(111,arbol.raiz,3000,"Cuatro Esquinas","Pital","Sebas");
        arbol.insertar(222,arbol.raiz,7000,"Cuatro Esquinas","Pital","Sebas");
        arbol.insertar(333,arbol.raiz,3000,"Pital","Santa Clara","Sebas");
        arbol.insertar(444,arbol.raiz,7000,"Pital","Santa Clara","Sebas");
        
        arbol.buscar(100, arbol.raiz);
        Pedidos ver = arbol.variable;
        //System.out.println(ver.peso);
        
        //grafo quemado
        //ciudades 
        met.insertarVertices("Santa Clara");
        met.insertarVertices("Ciudad Quesada");
        met.insertarVertices("Aguas Zarcas");
        met.insertarVertices("San Ramón");
        met.insertarVertices("Cuatro Esquinas");
        met.insertarVertices("Pital");
        
        //caminos
        met.insertarArco(met.buscar("Cuatro Esquinas"), met.buscar("Pital"), 3, false, 80);
        met.buscar(met.buscar("Cuatro Esquinas"), met.buscar("Pital"));
        met.insertarArco(met.buscar("Pital"), met.buscar("Aguas Zarcas"), 10, true, 100);
        met.insertarArco(met.buscar("Aguas Zarcas"), met.buscar("Ciudad Quesada"), 15, true, 90);
        met.insertarArco(met.buscar("Ciudad Quesada"), met.buscar("Santa Clara"), 5, true, 80);
        met.insertarArco(met.buscar("Aguas Zarcas"), met.buscar("Santa Clara"), 10, true, 70);
        met.insertarArco(met.buscar("Ciudad Quesada"), met.buscar("San Ramón"), 50, true, 120);
        //abrir el frame
        MainFrame fr = new MainFrame();//Crear el FrameRegistro XD
        fr.setVisible(true);
    }
    
}
