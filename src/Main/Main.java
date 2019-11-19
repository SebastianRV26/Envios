/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

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
        Metodos met = Metodos.getInstance();
        Hashtable hash = Hashtable.getInstance();
        //arbol quemado
        Arbol arbol = Arbol.getInstance();
        
        Usuario usuario = new Usuario(4984, "Jairo", "B4");
        hash.insertarUsuario(usuario);
        
        
        arbol.insertar(50,arbol.raiz,300,"fdso","fsdf","jairo");
        arbol.insertar(2,arbol.raiz,86,"fdso","fsdf","jairo");
        arbol.insertar(100,arbol.raiz,300,"fdso","fsdf","jairo");
        
        //grafo quemado
        //ciudades 
        met.insertarVertices("Santa Clara");
        met.insertarVertices("Ciudad Quesada");
        met.insertarVertices("Aguas Zarcas");
        met.insertarVertices("San Ramón");
        met.insertarVertices("Cuatro Esquinas");
        met.insertarVertices("Pital");
        
        //caminos
        met.insertarArco(met.buscar("Cuatro Esquinas"), met.buscar("Pital"), 3, true, 80, 0);
        met.insertarArco(met.buscar("Pital"), met.buscar("Aguas Zarcas"), 10, true, 100, 0);
        met.insertarArco(met.buscar("Aguas Zarcas"), met.buscar("Ciudad Quesada"), 15, true, 90, 0);
        met.insertarArco(met.buscar("Ciudad Quesada"), met.buscar("Santa Clara"), 5, true, 80, 0);
        met.insertarArco(met.buscar("Aguas Zarcas"), met.buscar("Santa Clara"), 10, true, 70, 0);
        met.insertarArco(met.buscar("Ciudad Quesada"), met.buscar("San Ramón"), 50, true, 120, 0);
        //met.insertarArco(met.buscar(""), met.buscar(""), 0, true, 0, 0);
        //met.insertarArco(met.buscar(""), met.buscar(""), 0, true, 0, 0);
        //abrir el frame
        MainFrame fr = new MainFrame();//Crear el FrameRegistro XD
        fr.setVisible(true);
    }
    
}
