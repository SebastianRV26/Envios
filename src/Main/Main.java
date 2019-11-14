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

/**
 *
 * @author Sebas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Usuario[] listaUsuarios = new Usuario [13];
        Arbol arbol = new Arbol();
        
        arbol.insertar(50,arbol.raiz,300,"fdso","fsdf");
        arbol.insertar(2,arbol.raiz,86,"fdso","fsdf");
        arbol.insertar(100,arbol.raiz,300,"fdso","fsdf");
        
        arbol.imprimir(arbol.raiz);
        arbol.Eliminar(arbol.raiz, 2);
        System.out.println("----------");
        arbol.imprimir(arbol.raiz);
        MainFrame fr = new MainFrame();//Crear el FrameRegistro XD
        fr.setVisible(true);
    }
    
}
