/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Methods;
import Main.Main;
import Classes.Usuario;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pache
 */
public class Hashtable {
    private int hash(int number){
        return (number & 0xfffffff)% 13;
    }
    
    /**
     * Insert user in table
     * @param user user to insert
     * @return  TRUE if inserted or False if not inserted.
     */
    public boolean insertarUsuario(Usuario user){
        int key = this.hash(user.ID);
        
        Usuario aux = Main.listaUsuarios[key];
        
        if(aux == null){
            Main.listaUsuarios[key] = user;
        }else{
            for (;aux != null; aux = aux.sig){
                if(aux.ID == user.ID){
                    return false;
                }
                if(aux.sig == null){
                    aux.sig = user;
                    break;
                }
            }
        }
        return true;
    } 
    
    /**
     * Search user by Id
     * @param number id to search in list.
     * @return Object User if is found or NULL if not found.
     */
    public Usuario BuscarUsuario(int number){
        int key = this.hash(number);
        
        if(Main.listaUsuarios[key] == null){
            return null;
        }
        
        Usuario aux = Main.listaUsuarios[key];
        
        for (;aux != null; aux = aux.sig){
            if(aux.ID == number)
                break;
        }
        
        return aux;
       
    }
    /**
     * Print HashTable in console
     */
    public void print(){
        for (int i = 0; i < Main.listaUsuarios.length; i++) {
            System.out.print("Indice: "+i+"[");
            for (Usuario aux = Main.listaUsuarios[i]; aux != null; aux = aux.sig) {
                System.out.print(aux.ID+",");
            }
            System.out.print("]\n");
        }
         
    }
    
    /**
     * Create a new Table model with all users.
     * @param model table model where the data going inserted
     * @return DefaulsTableModel with users to use in table component.
     */
    public void getModel(DefaultTableModel model){
        for (int i = 0; i < Main.listaUsuarios.length; i++) {
            for (Usuario aux = Main.listaUsuarios[i]; aux != null; aux = aux.sig) {
                Object[] row =new Object[3];
                row[0] = aux.ID;
                row[1] = aux.nombre;
                row[2] = aux.tipoLicencia;
                model.addRow(row);
            } 
        }
    }
}
