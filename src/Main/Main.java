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
public static Usuario[] listaUsuarios = new Usuario [13];
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MainFrame fr = new MainFrame();//Crear el FrameRegistro XD
        fr.setVisible(true);
    }
    
}
