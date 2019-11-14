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
import Methods.Metodos;

/**
 *
 * @author Sebas
 */
public class Main {
    
    public Usuario[] listaUsuarios = new Usuario [13];
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
        Metodos met = Metodos.getInstance();
        //arbol quemado
        Arbol arbol = new Arbol();
        
        arbol.insertar(50,arbol.raiz,300,"fdso","fsdf");
        arbol.insertar(2,arbol.raiz,86,"fdso","fsdf");
        arbol.insertar(100,arbol.raiz,300,"fdso","fsdf");
        
        //grafo quemado
        met.insertarVertices("Santa Clara");
        met.insertarVertices("Ciudad Quesada");
        met.insertarVertices("Aguas Zarcas");
        met.insertarVertices("Pital");
        
        met.insertarArco(met.buscar("Pital"), met.buscar("Aguas Zarcas"), 0, true, 0, 0);
        //abrir el frame
        MainFrame fr = new MainFrame();//Crear el FrameRegistro XD
        fr.setVisible(true);
    }
    
}
